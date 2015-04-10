package controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import database.DBManager;
import enums.Enums;
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
	public List<Rechnung> rechnungversendenList;
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
				System.out.println("Login für User "+user+" erfolgreich. Willkommen!");
				System.out.println("Anwendungsoberfläche wird geöffnet.");
				break;
			}
		}
		return login;

	}

	public void tag() {
		Calendar c = Calendar.getInstance();
		c.setTime(tag);
		System.out.println(c.getTime().toLocaleString().split(" ")[0] + " ("+c.getTime().toString()+") ist vergangen");
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
				if (positionList.get(i).mitarbeiter != null
						&& positionList.get(i).positionStatus == Enums.Auftragsstatus.INARBEIT) {
					positionList.get(i).mitarbeiter
							.positionAusfuehren(positionList.get(i));
					
				} else {
					c = Calendar.getInstance();
					c.setTime(positionList.get(i).positionAusfuehrungsdatum);
					c.add(Calendar.DATE, 1);
					positionList.get(i).positionAusfuehrungsdatum = c.getTime();
				}
			}
		}
		for (int i = 0; i < mitarbeiterList.size(); i++) {
			if (mitarbeiterList.get(i).getMitarbeiterStatus() == Enums.Mitarbeiterstatus.VERFUEGBAR) {
				positionVergeben(mitarbeiterList.get(i));
			}
		}
		// writedb();

	}

	public void test() {
		// tag = new java.util.Date();
		// zieltag = new java.util.Date();
		// Calendar c = Calendar.getInstance();
		// c.setTime(zieltag);
		// c.add(Calendar.DATE, 30);
		// zieltag = c.getTime();
		// for(int i = 20000000; i < 20000002; i++)
		// {
		// System.out.println(Verwaltung.getInstance().addAuftrag("Gartenpflege",
		// 4, +i).getZieldatum());
		// }
		// timestep();
	}

	// verarbeitungen nach jeden timestep
	@SuppressWarnings("deprecation")
	public void timestep() {
		zaehl = 0;
		zieltag.setHours(tag.getHours()-1);
		// TODO verarbeitungen per step db aktualisieren
		while (tag.before(zieltag)) {
			int alt = tag.getMonth();
			Calendar c = Calendar.getInstance();
			c.setTime(tag);
			c.add(Calendar.DATE, 1);
			int neu = c.getTime().getMonth();
			if (alt != neu) {
				System.out.println("Monatsende: Eingegangene Zahlungen werden geprüft");
				RechnungenZahlen();
				System.out.println("Monatsende: Rechnungen werden an GM und BH versendet");
				for (int i = 0; i < rechnungversendenList.size(); i++) {
					rechnungversendenList.get(i).versenden();
					//System.out.println("Die Rechnung "+rechnungversendenList.get(i).rechnungVerwendungszweck+ " wurde versendet.");
				}
				if(rechnungversendenList.size() == 0)
				{
					System.out.println("Es wurden keine zu versendenden Rechnungen gefunden.");
				}
				rechnungversendenList = new ArrayList<Rechnung>();
				
			}
			tag();
		}
		System.out.println("Zeitsprung abgeschlossen, neues Localdate: "+tag.toLocaleString());
		instructTimepush(1);
	}

	public String unbezahlteRechnungen() {
		String s = "";
		for (int i = 0; i < rechnungList.size(); i++) {
			if (rechnungList.get(i).auftrag.getAuftragstatus() != Enums.Auftragsstatus.BEZAHLT) {
				s += rechnungList.get(i).auftrag.auftragID + " ";
				System.out.println("Rechnung "+rechnungList.get(i).rechnungVerwendungszweck+" ist bezahlt worden.");
			}
		}
		return s;

	}

	public void positionVergeben(Mitarbeiter m) {
		if (positionQueue.size() != 0) {
			Position p = positionQueue.poll();
			p.mitarbeiter = m;
			p.positionStatus = Enums.Auftragsstatus.INARBEIT;
			p.auftrag.setAuftragstatus(Enums.Auftragsstatus.INARBEIT);
			m.setMitarbeiterStatus(Enums.Mitarbeiterstatus.ARBEITET);
			m.aktuellePosition = p;
			System.out.println("Auftrag " +p.auftrag.auftragID+ " an Mitarbeiter vergeben: "+m.mitarbeiterName);
		}
	}

	public Dienstleistung getDienstleistung(int dienstleistungsID) {
		Dienstleistung erg = null;
		for (int i = 0; i < dienstleistungList.size(); i++) {
			if (dienstleistungList.get(i) != null) {
				// System.out.println(dienstleistungList.get(i).dienstleistungsName);
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
				Enums.Auftragsstatus.ANGEKOMMEN, tag,
				getDienstleistung(name), size);
		auftragList.add(a);
		conn.writeAuftrag(a);
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
			if (positionQueue.contains(positionList.get(i)) && (d == null
					|| (positionList.get(i).positionAusfuehrungsdatum != null && positionList
							.get(i).positionAusfuehrungsdatum.after(d)))) {
				p = positionList.get(i);
				d = positionList.get(i).positionAusfuehrungsdatum;
			}
		}
		if (tag != null && (d == null || d.before(tag))) {
			d = tag;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		if(p != null)
		{
		c.add(Calendar.DATE,
				-1
						* Math.round(p.dienstleistung
								.aufwandermitteln(p.positionMenge)));
		try
		{
		c.add(Calendar.DATE,
				Math.round(a.dienstleistung.aufwandermitteln(a.menge)));
		}
		catch(Exception ex)
		{
			try
			{
			c.add(Calendar.DATE,
					1
							+ Math.round(p.dienstleistung
									.aufwandermitteln(p.positionMenge)));
			}
			catch(Exception ex2)
			{
				
			}
			System.err.println("Die Dienstleistung konnte nicht gefunden werden");
		}
		}
		try
		{
		if (mitarbeiterList.size() <= zaehl) {
			c.add(Calendar.DATE, Math.round(zaehl/mitarbeiterList.size()*p.auftrag.dienstleistung
					.aufwandermitteln(p.positionMenge)));
			
		}
		}
		catch (Exception ex)
		{
			
		}
		d = c.getTime();
		return d;
	}

	public void mahnen(String verwendungszweck) {
		instructDunnning(verwendungszweck);
	}

	// TODO an timepush anpassen da nicht in bank.jar drin
	// versendet einen timepush wenn stepverarbeitung abgeschlossen ist
	public void instructTimepush(int timestep) { // oder als date?
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
			Double betrag, String rechnungsdatum, String zahlungsdatum) {
		System.out.println("Versenden einer Rechnung an BH: "+verwendungszweck);
		BuchhaltungWsImplService InvoiceService = new BuchhaltungWsImplService();
		BuchhaltungWS InvoiceWS = InvoiceService.getBuchhaltungWsImplPort();
		try {
			if (InvoiceWS.erfasseRechnung(verwendungszweck, sender,
					rechnungsersteller, rechnungsempfaenger, betrag,
					rechnungsdatum, zahlungsdatum).equals("true")) {
				System.out.println("Rechnungsversand erfolgreich");
			} else

			{
				System.out.println("Rechnung abgelehnt");
			}
		} catch (Exception e) {
			System.err
					.println("Es ist ein Fehler beim Versenden der Rechnung an BH aufgetreten.");
		}
	}

	public void sendInvoiceGM(String verwendungszweck, String sender,
			String rechnungsersteller, String rechnungsempfaenger,
			Double betrag, String rechnungsdatum, String zahlungsdatum) {
		GmWSImplService InvoiceService = new GmWSImplService();
		GmWS InvoiceWS = InvoiceService.getGmWSImplPort();
		System.out
				.println("Versenden einer Rechnung mit folgenden Daten an GM:"+verwendungszweck +", "+ sender+", " + rechnungsersteller
						+", "+ rechnungsempfaenger+", " + betrag +", "+ rechnungsdatum
						+", "+ zahlungsdatum);
		try {
			if (InvoiceWS.erfasseRechnung(verwendungszweck, sender,
					rechnungsersteller, rechnungsempfaenger, betrag,
					rechnungsdatum, zahlungsdatum).equals("true")) {
				System.out.println("Rechnungsversand erfolgreich");
			} else

			{
				System.out.println("Rechnung abgelehnt");
			}
		} catch (Exception e) {
			System.err
					.println("Es ist ein Fehler beim Versenden der Rechnung an GM aufgetreten.");
		}
	}

	public void RechnungenZahlen() {
		String s;
		int zaehl = 0;
		List<Rechnung> r = new ArrayList<Rechnung>();
		BuchhaltungWsImplService itemService = new BuchhaltungWsImplService();
		BuchhaltungWS itemWS = itemService.getBuchhaltungWsImplPort();
		try {
			Map<String, String> m = baldoapp.ProjektXMLParser
					.XMLStringToMap(itemWS.gebeForderungsliste("GS"));
			for (int i = 0; i < m.size(); i++) {
				s = baldoapp.ProjektXMLParser.XMLStringToMap(m.get(i)).get(4);
				for (int j = 0; j < rechnungList.size(); j++) {
					if (rechnungList.get(j).rechnungVerwendungszweck.equals(s)) {
						r.add(rechnungList.get(j));
					}
				}
			}
			for (int i = 0; i < rechnungList.size(); i++) {
				if (!r.contains(rechnungList.get(i))
						&& !rechnungversendenList.contains(rechnungList.get(i))) {
					if (rechnungList.get(i).auftrag.getAuftragstatus() != Enums.Auftragsstatus.BEZAHLT) {
						rechnungList.get(i).auftrag
								.setAuftragstatus(Enums.Auftragsstatus.BEZAHLT);
						System.out.println("Rechnung "+rechnungList.get(i).rechnungVerwendungszweck+" wurde bezahlt;");
						zaehl++;
						MainWindowController.getInstance().addOrChangeRechnung(
								rechnungList.get(i));
					}
				}
			}
			System.out.println("Es wurden insgesamt "+zaehl+" Zahlungen empfangen");
		} catch (Exception e) {
			System.err
					.println("Es ist ein Fehler beim anfragen der offenen Posten aufgetreten.");
		}
	}

	public void instructDunnning(String verwendungszweck) {
		BuchhaltungWsImplService dunningService = new BuchhaltungWsImplService();
		BuchhaltungWS dunningWS = dunningService.getBuchhaltungWsImplPort();
		try {
			if (!dunningWS.uebergabeMahnauftrag(verwendungszweck).equals("Mahnauftrag fehlgeschlagen")) {
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
			verwaltung.rechnungversendenList = new ArrayList<Rechnung>();
			//TODO testdatum falls bank nicht verfügbar
			//verwaltung.tag = new Date();
			for (int i = 0; i < verwaltung.positionList.size(); i++) {

				verwaltung.positionList.get(i).auftrag.positionen
						.add(verwaltung.positionList.get(i));

				 //System.out.println(""+verwaltung.positionList.get(i).auftrag.positionen.get(0).dienstleistung.dienstleistungsName);
			}
			for (int i = 0; i < verwaltung.positionList.size(); i++) {
				if (verwaltung.positionList.get(i).mitarbeiter == null) {
					verwaltung.positionQueue
							.add(verwaltung.positionList.get(i));
				}
			}
			System.out.println("Verbindung zur DB hergestellt");
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					System.out.println("Anwendungsoberfläche wurde geschlossen.");
					//writedb();
					verwaltung.conn.close();
					System.out.println("Verbindung zur Datenbank wurde geschlossen!");
					System.out.println("Gebaeudeserviceanwendung wurde beendet!");
				}
			});
		}
		return verwaltung;
	}

	public static void writedb() {
		for (int i = 0; i < verwaltung.mitarbeiterList.size(); i++) {
			try {
				verwaltung.conn.writeMitarbeiter(verwaltung.mitarbeiterList
						.get(i));
			} catch (Exception ex) {
			}
		}
		for (int i = 0; i < verwaltung.auftragList.size(); i++) {
			try {
				verwaltung.conn.writeAuftrag(verwaltung.auftragList.get(i));
			} catch (Exception ex) {
			}
		}
		for (int i = 0; i < verwaltung.positionList.size(); i++) {
			try {
				verwaltung.conn.writePosition(verwaltung.positionList.get(i));
			} catch (Exception ex) {
			}
		}
		for (int i = 0; i < verwaltung.rechnungList.size(); i++) {
			try {
				verwaltung.conn.writeRechnung(verwaltung.rechnungList.get(i));
			} catch (Exception ex) {
			}
		}
	}

	// zum export der schnittstellenmethoden:
	// Starten der eigenen Anwendung als Java Application
	// Öffnen der Konsole um wsimport zu nutzen:
	// wsimport -keep -clientjar Gebaeudeservice.jar
	// http://10.10.10.25:1000/ws?wsdl
	// Dann neu verpacken damit die jar richtige pfade verknüpft
	
	/*
	 * SQL zum berreinigen DB:
DELETE FROM `position` WHERE 1;
Insert INTO `position` VALUES (1,1,50193,1,'2015-04-07','Bezahlt');
Insert INTO `position` VALUES(5,2,50190,2,'2015-04-07','Bezahlt');
Insert INTO `position` VALUES (11,3,50195,1,'2015-04-07','Bezahlt');
Insert INTO `position` VALUES (1,4,50191,1,'2015-04-07','Bezahlt');
Insert INTO `position` VALUES (7,5,50195,1,'2015-04-07','Bezahlt');
Insert INTO `position` VALUES (11,6,50205,1,'2015-04-07','Bezahlt');
Insert INTO `position` VALUES (6,7,50196,1,'2015-04-07','Bezahlt');
Insert INTO `position` VALUES (14,8,50206,1,'2015-04-07','Bezahlt');
Insert INTO `position` VALUES (3,9,50208,1,'2015-04-07','Bezahlt');
Insert INTO `position` VALUES (11,10,50203,1,'2015-04-07','Bezahlt');
Insert INTO `position` VALUES (6,11,50205,1,'2015-04-07','Bezahlt');
DELETE FROM `rechnung` WHERE `ID` > 11;
DELETE FROM `auftrag` WHERE `ID` > 11;
*/

}
