package controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import javax.jws.WebMethod;
import javax.swing.JOptionPane;

import database.DBManager;
import enums.Enums;
import enums.Enums.Auftragsstatus;

import util.Publisher;
import webservices.impl.BVWSImplService;
import webservices.impl.BVWebService;
import webservices.impl.BuchhaltungWS;
import webservices.impl.BuchhaltungWsImplService;
import webservices.impl.GmWS;
import webservices.impl.GmWSImplService;

import model.Auftrag;
import model.Auftraggeber;
import model.Dienstleistung;
import model.Mitarbeiter;
import model.Position;
import model.Rechnung;
import model.User;

public class Verwaltung {

	private static Verwaltung verwaltung;

	public List<User> userList;
	public List<Mitarbeiter> mitarbeiterList;
	public List<Auftrag> auftragList;
	public List<Dienstleistung> dienstleistungList;
	public List<Position> positionList;
	public List<Rechnung> rechnungList;
	public Queue<Position> positionQueue;
	public Auftraggeber auftraggeber;
	public DBManager conn;

	public Date tag;
	public Date zieltag;

	private Verwaltung() {
	}

	public boolean login(String user, String pw) {
		boolean login = false;
		for (int i = 0; i < userList.size(); i++) {
			User u = userList.get(i);
			if (u.name.matches(user) && u.password.matches(pw)) {
				login = true;
				break;
			}
		}
		return login;

	}

	public void tag() {
		Calendar c = Calendar.getInstance();
		c.setTime(tag);
		System.out.println(c.getTime() + " ist vergangen");
		c.add(Calendar.DATE, 1);
		tag = c.getTime();
		// TODO jeden tag vergeht zeit?
		// try {
		// //pausiert 1 sekunde
		// Thread.sleep(5*60);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		for (int i = 0; i < positionList.size(); i++) {
			if (positionList.get(i).positionAusfuehrungsdatum.before(tag)) {
				if (positionList.get(i).mitarbeiter != null && positionList.get(i).positionStatus == Enums.Auftragsstatus.INARBEIT) {
					positionList.get(i).mitarbeiter.positionAusfuehren(positionList.get(i));
				} else {
					c = Calendar.getInstance();
					c.setTime(positionList.get(i).positionAusfuehrungsdatum);
					c.add(Calendar.DATE, 1);
					positionList.get(i).positionAusfuehrungsdatum = c.getTime();
				}
			}
		}
		for (int i = 0; i < mitarbeiterList.size(); i++) {
			if (mitarbeiterList.get(i).mitarbeiterStatus == Enums.Mitarbeiterstatus.VERFUEGBAR) {
				positionVergeben(mitarbeiterList.get(i));
			}
		}


	}
	
	public void test()
	{
//		tag = new java.util.Date();
//		zieltag = new java.util.Date();
//		Calendar c = Calendar.getInstance();
//		c.setTime(zieltag);
//		c.add(Calendar.DATE, 5);
//		zieltag = c.getTime();
//		for(int i = 20; i < 45; i++)
//		  {
//			  System.out.println(Verwaltung.getInstance().addAuftrag("Gartenpflege", 4, i).getZieldatum());
//		  }
//		timestep();
	}

	//TODO updatemethoden für rechnung und ma

	// verarbeitungen nach jeden timestep
	@SuppressWarnings("deprecation")
	public void timestep() {
		// TODO verarbeitungen per step db aktualisieren
		while (tag.before(zieltag)) {
			int alt = tag.getMonth();
			tag();
			int neu = tag.getMonth();
			if (alt != neu) {
				//TODO rechnung nur am monatsende?
				// TODO rechnung auch an gm
				// TODO schnittstellen( ein und ausgehend) in try catch
			}
		}
		instructTimepush(1);
	}

	public String unbezahlteRechnungen() {
		String s = "";
		for (int i = 0; i < rechnungList.size(); i++) {
			if (rechnungList.get(i).auftrag.getAuftragstatus() != Enums.Auftragsstatus.BEZAHLT) {
				s += rechnungList.get(i).auftrag.auftragID + " ";
			}
		}
		return s;

	}

	public void positionVergeben(Mitarbeiter m) {
		if(positionQueue.size() != 0)
		{
			Position p = positionQueue.poll();
			p.mitarbeiter = m;
			p.positionStatus = Enums.Auftragsstatus.INARBEIT;
			p.auftrag.setAuftragstatus(Enums.Auftragsstatus.INARBEIT);
			m.mitarbeiterStatus = Enums.Mitarbeiterstatus.ARBEITET;
			m.aktuellePosition = p;
		}
	}

	public Dienstleistung getDienstleistung(int dienstleistungsID) {
		Dienstleistung erg = null;
		for (int i = 0; i < dienstleistungList.size(); i++) {
			if (dienstleistungList.get(i) != null) {
				//System.out.println(dienstleistungList.get(i).dienstleistungsName);
				if (dienstleistungList.get(i).getDienstleistungsID() == dienstleistungsID) {
					erg = dienstleistungList.get(i);
					break;
				}
			}
		}
		// System.out.println(erg);
		return erg;
	}

	public Auftrag addAuftrag(String name, int size, int orderID) {
		Auftrag a = new Auftrag(auftraggeber, orderID,
				Enums.Auftragsstatus.ANGEKOMMEN, new Date(),
				getDienstleistung(name), size);
		auftragList.add(a);
		a.positionErzeugen(getDienstleistung(name), size);
		positionList.add(a.positionen.get(0));
		positionQueue.add(a.positionen.get(0));
		MainWindowController.getInstance().addOrChangeAuftrag(a);
		return a;

	}

	public Auftrag getAuftrag(int auftragsID) {
		Auftrag erg = null;
		for (int i = 0; i < auftragList.size(); i++) {
			if (auftragList.get(i).auftragID == auftragsID) {
				erg = auftragList.get(i);
				break;
			}
		}
		return erg;
	}

	public Dienstleistung getDienstleistung(String name) {
		Dienstleistung erg = null;
		// System.out.println(name);
		for (int i = 0; i < dienstleistungList.size(); i++) {
			if (dienstleistungList.get(i).dienstleistungsName.equals(name)) {
				erg = dienstleistungList.get(i);
				break;
			}
		}
		return erg;
	}

	public Mitarbeiter getMitarbeiter(int mitarbeiterID) {
		Mitarbeiter erg = null;
		for (int i = 0; i < mitarbeiterList.size(); i++) {
			if (mitarbeiterList.get(i).mitarbeiterID == mitarbeiterID) {
				erg = mitarbeiterList.get(i);
				break;
			}
		}
		return erg;
	}

	int zaehl;

	public Date getZieldatum(Auftrag a) {
		zaehl++;
		Date d = null;
		Position p = null;
		for (int i = 0; i < positionList.size(); i++) {
			if (d == null
					|| (positionList.get(i).positionAusfuehrungsdatum != null && positionList
							.get(i).positionAusfuehrungsdatum.after(d))) {
				p = positionList.get(i);
				d = positionList.get(i).positionAusfuehrungsdatum;				
			}
		}
		if(tag != null && (d == null || d.before(tag)))
		{
			d = tag;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, -1*Math.round(p.dienstleistung.aufwandermitteln(p.positionMenge)));
		c.add(Calendar.DATE, Math.round(a.dienstleistung.aufwandermitteln(p.positionMenge)));
		if (mitarbeiterList.size() <= zaehl) {
			c.add(Calendar.DATE, Math.round(a.dienstleistung.aufwandermitteln(p.positionMenge)));
			zaehl = 0;
		}
		d = c.getTime();
		return d;
	}

	public void mahnen(String verwendungszweck) {
		instructDunnning(verwendungszweck);
	}

	// TODO an timepush anpassen da nicht in bank.jar drin
	// versendet einen timepush wenn stepverarbeitung abgeschlossen ist
	public void instructTimepush(int timestep) { //oder als date?
		BVWSImplService timeService = new BVWSImplService();
		BVWebService timeWS = timeService.getBVWSImplPort();
		try {
			// 0 ist rc für fehlerlos?
			// if(timeWS.pushTime(timestep) == 0)
			// {
			// System.err.println("Timepush erfolgreich");
			// }
			// else
			//
			// {
			// System.out.println("Timepush abgelehnt");
			// }
		} catch (Exception e) {
			System.err
					.println("Es ist ein Fehler beim Versenden der pushNachricht aufgetreten.");
		}
	}

	public void sendInvoice(String verwendungszweck, String sender,
			String rechnungsersteller, String rechnungsempfaenger,
			double betrag, String rechnungsdatum, String zahlungsdatum) {
		BuchhaltungWsImplService InvoiceService = new BuchhaltungWsImplService();
		BuchhaltungWS InvoiceWS = InvoiceService.getBuchhaltungWsImplPort();
		try {
			 if(InvoiceWS.erfasseRechnung(verwendungszweck, sender,
			 rechnungsersteller, rechnungsempfaenger, betrag
			 , rechnungsdatum, zahlungsdatum) == "Rechnung wurde bearbeitet")
			 {
			 System.err.println("Rechnungsversand erfolgreich");
			 }
			 else
			
			 {
			 System.out.println("Rechnung abgelehnt");
			 }
		} catch (Exception e) {
			System.err
					.println("Es ist ein Fehler beim Versenden der Rechnung aufgetreten.");
		}
	}
	
	public void sendInvoiceGM(String verwendungszweck, String sender,
			String rechnungsersteller, String rechnungsempfaenger,
			double betrag, String rechnungsdatum, String zahlungsdatum) {
		GmWSImplService InvoiceService = new GmWSImplService();
		GmWS InvoiceWS = InvoiceService.getGmWSImplPort();
		try {
			 if(InvoiceWS.erfasseRechnung(verwendungszweck, sender,
			 rechnungsersteller, rechnungsempfaenger, betrag
			 , rechnungsdatum, zahlungsdatum) == "Rechnung wurde bearbeitet")
			 {
			 System.err.println("Rechnungsversand erfolgreich");
			 }
			 else
			
			 {
			 System.out.println("Rechnung abgelehnt");
			 }
		} catch (Exception e) {
			System.err
					.println("Es ist ein Fehler beim Versenden der Rechnung aufgetreten.");
		}
	}

	public List<String> getOpenitems(String sender) {
		List<String> liste = null;
		BuchhaltungWsImplService itemService = new BuchhaltungWsImplService();
		BuchhaltungWS itemWS = itemService.getBuchhaltungWsImplPort();
		 try
		 {
		 //liste = itemWS.getOffenePosten(sender);
		 }
		 catch(Exception e)
		 {
		 System.err.println("Es ist ein Fehler beim anfragen der offenen Posten aufgetreten.");
		 }
		return liste;
	}

	public void instructDunnning(String verwendungszweck) {
		BuchhaltungWsImplService dunningService = new BuchhaltungWsImplService();
		BuchhaltungWS dunningWS = dunningService.getBuchhaltungWsImplPort();
		try {
			if (dunningWS.uebergabeMahnauftrag(verwendungszweck) == "Mahnauftrag erhalten") {
				System.err.println("Mahnauftrag erfolgreich");
			} else

			{
				System.out.println("Mahnauftrag abgelehnt");
			}
		} catch (Exception e) {
			System.err
					.println("Es ist ein Fehler beim Versenden des Mahnauftrages aufgetreten.");
		}
	}

	public synchronized static Verwaltung getInstance() {
		if (verwaltung == null) {
			verwaltung = new Verwaltung();
			System.out.println("Gebaeudeserviceanwendung wurde gestartet");
			verwaltung.conn = new DBManager();
			verwaltung.userList = verwaltung.conn.getAllUser();
			verwaltung.auftraggeber = verwaltung.conn.readAuftraggeber(1);
			verwaltung.mitarbeiterList = verwaltung.conn.getAllMitarbeiter();
			verwaltung.dienstleistungList = verwaltung.conn
					.getAllDienstleistung();
			verwaltung.auftragList = verwaltung.conn.getAllAuftrag();
			verwaltung.rechnungList = verwaltung.conn.getAllRechnung();
			verwaltung.positionList = verwaltung.conn.getAllPosition();
			verwaltung.positionQueue = new ArrayDeque<Position>();
			for (int i = 0; i < verwaltung.positionList.size(); i++) {

				verwaltung.positionList.get(i).auftrag.positionen
						.add(verwaltung.positionList.get(i));

				// System.out.println(""+verwaltung.positionList.get(i).auftrag.positionen.get(0).dienstleistung);
			}
			for (int i = 0; i < verwaltung.positionList.size(); i++) {
				if (verwaltung.positionList.get(i).mitarbeiter == null) {
					verwaltung.positionQueue
							.add(verwaltung.positionList.get(i));
				}
			}

			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					// TODO Daten zurück in die Datenbank schreiben
					verwaltung.conn.close();
				}
			});
		}
		return verwaltung;
	}

	// zum export der schnittstellenmethoden:
	// Starten der eigenen Anwendung als Java Application
	// Öffnen der Konsole um wsimport zu nutzen: 
	// wsimport -keep -clientjar Gebaeudeservice.jar http://10.10.10.25:1000/ws?wsdl

}
