package controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import javax.jws.WebMethod;
import javax.swing.JOptionPane;

import database.DBManager;

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
import model.User;

public class Verwaltung {

	/**
	 * @param args
	 */
	public static Verwaltung verwaltung;
	
	//startmethode um schnittstellen zu publishen und anwendung zu starten
	public static void main(String[] args) {
		Publisher.getInstance();
		

	}
	
	
	
	public List<User> userList;
	public List<Mitarbeiter> mitarbeiterList;
	public List<Auftrag> auftragList;
	public List<Dienstleistung> dienstleistungList;
	public Queue<Position> positionQueue;
	public Auftraggeber auftraggeber;
	public DBManager conn;
	
	public int tag;
	public int zieltag;
	
	//TODO Startinitialisierung
	public Verwaltung()
	{
		System.out.println("Gebaeudeserviceanwendung wurde gestartet");
		conn = new DBManager();
		userList = conn.getAllUser();
		auftraggeber = conn.readAuftraggeber(1);
		mitarbeiterList = conn.getAllMitarbeiter();
		dienstleistungList = conn.getAllDienstleistung();
		auftragList = new ArrayList<Auftrag>();
		positionQueue = new ArrayDeque<Position>();
		//positionqueue und listen initial füllen 
		//db einlesen
		//listen füllen
		
		
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
	
	public void login(String user,String pw)
	{
		boolean login = false;
		for(int i = 0; i < userList.size(); i++)
		{
			
			if(userList.get(i).name == user && userList.get(i).password == pw)
			{
				login = true;
				break;
			}
		}
		if(login)
		{
			System.out.println("Login erfolgreich, willkommen "+user);
		}
		else
		{
			System.out.println("Login fehlgeschlagen für user "+user);
		}

	}
	
	
	
	//TODO verarbeitung eines Tages
	public void tag()
	{
		//positionen wenn zieldatum dann abschließen und ma freigeben
		//ma position zuordnen falls unbelegt und status und zugeord. posi anpassen
		tag++;
		//TODO jeden tag vergeht zeit?
		try {
			//pausiert 1 sekunde
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//monatsaufgaben beachten? und hier sonderfall eintragen?
	}
	
	//verarbeitungen nach jeden timestep
	public void timestep()
	{
		//TODO verarbeitungen per step
		//1 = code für 1 step?
		while(tag < zieltag)
		{
			tag();
		}
		instructTimepush(1);
	}
	
	//TODO
	public void auftragAbschliessen()
	{
		
	}
	
	//TODO
	public void positionVergeben()
	{
		
	}
	
	//TODO
	public String statusLiefern()
	{
		return null;
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
	
	//TODO ausrechnen wenn auftrag ausgeführt sein wird
	public Date getZieldatum(Auftrag auftrag)
	{
		return null;
	}
	
	//TODO zusatzverarbeitung bei mahnung?
	public void mahnen(String verwendungszweck)
	{
		instructDunnning(verwendungszweck);
	}
	
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
	
	public void sendInvoice(String rechnungsersteller, String rechnungsempfaenger, String sender
								,double betrag, String verwendungszweck, Date rechnungsdatum
								, Date zahlungsdatum, String rechnungsadresse )
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
	
	//zum export der schnittstellenmethoden:
	//Starten der eigenen Anwendung als Java Application
	//Öffnen der Konsole um wsimport zu nutzen: wsimport -keep -clientjar Gebaeudeservice.jar http://10.10.10.25:1000/ws?wsdl
	

}
