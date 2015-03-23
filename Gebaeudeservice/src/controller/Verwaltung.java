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
import webservices.impl.SchnittstelleBH;
import webservices.impl.SchnittstellenimplService;

import model.Auftrag;
import model.Auftraggeber;
import model.Dienstleistung;
import model.Mitarbeiter;
import model.Position;
import model.Rechnung;
import model.User;

public class Verwaltung {


	public static Verwaltung verwaltung;
	
	//startmethode um schnittstellen zu publishen und anwendung zu starten
	public static void main(String[] args) {
		Publisher.getInstance();
		

	}
	
	
	
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
	
	//TODO Startinitialisierung
	public Verwaltung()
	{
		System.out.println("Gebaeudeserviceanwendung wurde gestartet");
		conn = new DBManager();
		userList = conn.getAllUser();
		auftraggeber = conn.readAuftraggeber(1);
		mitarbeiterList = conn.getAllMitarbeiter();
		dienstleistungList = conn.getAllDienstleistung();
		auftragList = conn.getAllAuftrag();
		rechnungList = conn.getAllRechnung();
		positionList = conn.getAllPosition();
		positionQueue = new ArrayDeque<Position>();
		//TODO positionen im auftrag füllen
		for(int i = 0; i < positionList.size(); i ++)
		{
			//TODO vorher nach datum sortieren?
			if(positionList.get(i).mitarbeiter == null)
			{
				positionQueue.add(positionList.get(i));
			}
		}
		
		
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
		    @Override
		    public void run()
		    {
		        //TODO Daten zurück in die Datenbank schreiben
		    	conn.close();
		    }
		});
	}
	
	
	public boolean login(String user,String pw)
	{
		boolean login = false;
		for(int i = 0; i < userList.size(); i++)
		{
			User u = userList.get(i);
			if(u.name.matches(user) && u.password.matches(pw))
			{
				login = true;
				break;
			}
		}
		return login;

	}
	
	
	
	public void tag()
	{
		Calendar c = Calendar.getInstance(); 
		c.setTime(tag); 
		c.add(Calendar.DATE, 1);
		tag = c.getTime();
		//TODO jeden tag vergeht zeit?
//		try {
//			//pausiert 1 sekunde
//			Thread.sleep(5*60);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} 
		for(int i = 0; i < positionList.size();i++)
		{
			if(positionList.get(i).positionAusfuehrungsdatum == tag)
			{
				if(positionList.get(i).mitarbeiter != null)
				{
					positionList.get(i).mitarbeiter.positionAusfuehren(positionList.get(i));
				}
				else
				{
					c = Calendar.getInstance(); 
					c.setTime(positionList.get(i).positionAusfuehrungsdatum); 
					c.add(Calendar.DATE, 1);
					positionList.get(i).positionAusfuehrungsdatum = c.getTime();
				}
			}
		}
		for(int i = 0; i < mitarbeiterList.size();i++)
		{
			if(mitarbeiterList.get(i).mitarbeiterStatus == Enums.Mitarbeiterstatus.VERFUEGBAR)
			{
				positionVergeben(mitarbeiterList.get(i));
			}
		}

		updateView();
		
	}
	
	public void updateView()
	{
		//TODO view updatemethode aufrufen?
	}
	
	//verarbeitungen nach jeden timestep
	@SuppressWarnings("deprecation")
	public void timestep()
	{
		//TODO verarbeitungen per step
		while(tag.before(zieltag))
		{
			int alt = tag.getMonth();
			tag();
			int neu = tag.getMonth();
			if(alt != neu)
			{
				
			//TODO monatsverarbeitung--> rechnnungen verschicken statt von ma?
				//TODO rechnung auch an gm
				//TODO Rechnung erhalten
				//TODO schnittstellen( ein und ausgehend) in try catch
			}
		}
		instructTimepush(1);
	}
	

	
	
	public String unbezahlteRechnungen() {
		String s = "";
		for(int i = 0; i < rechnungList.size();i++)
		{
			if(rechnungList.get(i).auftrag.auftragstatus != Enums.Auftragsstatus.BEZAHLT)
			{
				s += rechnungList.get(i).auftrag.auftragID + " ";
			}
		}
		return s;
		
	}
	
	public void positionVergeben(Mitarbeiter m)
	{
		positionQueue.poll().mitarbeiter = m;
	}
	

	
	public Dienstleistung getDienstleistung(int dienstleistungsID)
	{
		Dienstleistung erg = null;
		for(int i = 0; i < dienstleistungList.size(); i++)
		{
			if( dienstleistungList.get(i).getDienstleistungsID() == dienstleistungsID)
			{
				erg = dienstleistungList.get(i);
				break;
			}
		}
		return erg;
	}
	
	public Auftrag addAuftrag(String name, int size, int orderID)
	{
		Auftrag a = new Auftrag(auftraggeber, orderID, Enums.Auftragsstatus.ANGEKOMMEN, new Date(), getDienstleistung(name), size);
		return a;
		
	}
	
	public Auftrag getAuftrag(int auftragsID)
	{
		Auftrag erg = null;
		for(int i = 0; i < auftragList.size(); i++)
		{
			if( auftragList.get(i).auftragID == auftragsID)
			{
				erg = auftragList.get(i);
				break;
			}
		}
		return erg;
	}
	
	public Dienstleistung getDienstleistung(String name)
	{
		Dienstleistung erg = null;
		for(int i = 0; i < dienstleistungList.size(); i++)
		{
			if( dienstleistungList.get(i).dienstleistungsName == name)
			{
				erg = dienstleistungList.get(i);
				break;
			}
		}
		return erg;
	}
	
	public Mitarbeiter getMitarbeiter(int mitarbeiterID)
	{
		Mitarbeiter erg = null;
		for(int i = 0; i < mitarbeiterList.size(); i++)
		{
			if( mitarbeiterList.get(i).mitarbeiterID == mitarbeiterID)
			{
				erg = mitarbeiterList.get(i);
				break;
			}
		}
		return erg;
	}
	
	int zaehl;
	public Date getZieldatum(Auftrag a)
	{
		zaehl++;
		Date d = null;
		for(int i = 0; i<positionList.size();i++)
		{
			if(d == null || (positionList.get(i).positionAusfuehrungsdatum != null && positionList.get(i).positionAusfuehrungsdatum.after(d)))
			{
					d = positionList.get(i).positionAusfuehrungsdatum;
			}
		}
		Calendar c = Calendar.getInstance(); 
		c.setTime(d);
		if(mitarbeiterList.size()<= zaehl)
		{
			c.add(Calendar.DATE, 1);
			zaehl = 0;
		}
		d= c.getTime();
		return d;
	}
	
	//TODO zusatzverarbeitung bei mahnung?
	public void mahnen(String verwendungszweck)
	{
		instructDunnning(verwendungszweck);
	}
	
	//TODO an timepush anpassen da nicht in bank.jar drin
	//versendet einen timepush wenn stepverarbeitung abgeschlossen ist
	public void instructTimepush(int timestep)
	{
		BVWSImplService  timeService = new BVWSImplService();
		BVWebService timeWS = timeService.getBVWSImplPort();
		try
		{
			//0 ist rc für fehlerlos?
//			if(timeWS.pushTime(timestep) == 0)
//			{	
//				System.err.println("Timepush erfolgreich");
//			}
//			else
//				
//			{	
//				System.out.println("Timepush abgelehnt");
//			}
		}
		catch(Exception e)
		{
			System.err.println("Es ist ein Fehler beim Versenden der pushNachricht aufgetreten.");
		}
	}
	
	
	//TODO
	public void sendInvoice(String verwendungszweck, String sender, String rechnungsersteller, 
			String rechnungsempfaenger, double betrag, String rechnungsdatum, String zahlungsdatum)
	{
		SchnittstellenimplService  InvoiceService = new SchnittstellenimplService();
		SchnittstelleBH InvoiceWS = InvoiceService.getSchnittstellenimplPort();
		try
		{
//			if(InvoiceWS.erfasseRechnung(verwendungszweck, sender, rechnungsersteller, rechnungsempfaenger,  betrag 
//					, rechnungsdatum, zahlungsdatum) == "Rechnung wurde bearbeitet")
//			{	
//				System.err.println("Rechnungsversand erfolgreich");
//			}
//			else
//				
//			{	
//				System.out.println("Rechnung abgelehnt");
//			}
		}
		catch(Exception e)
		{
			System.err.println("Es ist ein Fehler beim Versenden der Rechnung aufgetreten.");
		}
	}
	
	public List<String> getOpenitems(String sender)
	{
		List<String> liste = null;
//		OffenePostenWSImplService  itemService = new OffenePostenWSImplService();
//		OffenePostenWS itemWS = timeService.getOffenePostenWSImplPort();
//		try
//		{
//			liste = itemWS.getOffenePosten(sender);
//		}
//		catch(Exception e)
//		{
//			System.err.println("Es ist ein Fehler beim anfragen der offenen Posten aufgetreten.");
//		}
		return liste;
	}
	
	public void instructDunnning(String verwendungszweck)
	{
		SchnittstellenimplService  dunningService = new SchnittstellenimplService();
		SchnittstelleBH dunningWS = dunningService.getSchnittstellenimplPort();
		try
		{
			if(dunningWS.uebergabeMahnauftrag(verwendungszweck) == "Mahnauftrag erhalten" )
			{	
				System.err.println("Mahnauftrag erfolgreich");
			}
			else
				
			{	
				System.out.println("Mahnauftrag abgelehnt");
			}
		}
		catch(Exception e)
		{
			System.err.println("Es ist ein Fehler beim Versenden des Mahnauftrages aufgetreten.");
		}
	}


	public static Verwaltung getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//zum export der schnittstellenmethoden:
	//Starten der eigenen Anwendung als Java Application
	//Öffnen der Konsole um wsimport zu nutzen: wsimport -keep -clientjar Gebaeudeservice.jar http://10.10.10.25:1000/ws?wsdl
	

}
