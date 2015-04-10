package webservices;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebService;

import baldoapp.Zeitsprung;

import model.Auftrag;

import controller.Verwaltung;
import enums.Enums;

@WebService(endpointInterface = "webservices.ServiceWS")
public class ServiceWSImpl  implements ServiceWS{

	
	/**
	 * Returns an state as String in case it finds the orderID
	 * in every other case the return will be an empty String
	 *  
	 * <p>
	 * This method always returns immediately, whether or not the 
	 * order exists. When this Method is called the current state is returned 
	 * independently of whether it will change before the next timepush.
	 * The OrderID is known from passing the order to FM initially. 
	 * It is the ID that is passed to FM when you send a new Order to FM.
	 * For example when you send us an Order with the ID 1234 you can get the state by passing 1234 for this Method.
	 * The states that can possibly be returned are:
	 * Angekommen, In Arbeit, Erledigt, Abgelehnt, Bezahlt
	 * 
	 * @param  orderID  an Long that can identify an Order clearly 
	 * @return      the state of the order belonging to the orderID passed as String
	 * @see         sendOrderToFM
	 */
	@Override
	@WebMethod
	public
	String getState(long orderID) {
		String s = "";
		try {
			s= Enums.getAStatus(Verwaltung.getInstance().getAuftrag((int)orderID).getAuftragstatus());
			System.out.println("Der Status des Auftrages "+orderID+" wurde abgefragt und lieferte: "+s);
		} catch (NullPointerException e) {
			System.err.println("Die Auftragnummer "+orderID+" konnte nicht gefunden werden");
			//e.printStackTrace();
		} 
		
		
		return s;
	}

	
	/**
	 * Returns a value for the requested type of order
	 *  
	 * <p>
	 * This method always returns immediately.
	 * It returns a value for the requested type of order as an Integer
	 * 
	 * @param  type	The type if value you want to retrieve there are 3 kinds:
	 * 			0 = Wasser (value between 3000 and 5000)
	 * 			1 = Strom (value between 200 and 300)
	 * 			2 = Gas (value between 1000 and 1500)
	 * @return      The Value of the type as an Integer
	 */
	@Override
	@WebMethod
	public
	int getValue(int type) {
		int i = 0;
		String s = "";
		if(type == 0)
		{
			Random rand = new Random();
			i = rand.nextInt(2000)+3001;
			s= "Wasser";
		}
		else if(type == 1)
		{
			Random rand = new Random();
			i = rand.nextInt(100)+201;
			s= "Strom";
		}
		else if(type == 2)
		{
			Random rand = new Random();
			i = rand.nextInt(500)+1001;
			s= "Gas";
		}
		if(i == 0)
		{
			System.err.println("Es wurden die Ablesedaten einer nicht bekannten Ableseart bestellt");
		}
		else
		{
			System.out.println("Es wurden die Ablesedaten für "+s+" bestellt und folgende Daten mitgeteilt: "+i);
		}
		
		return i;
	}

	
	/**
	 * Returns a Date as String in case the Order will be accepted
	 * in every other case the return will be an empty String.
	 * Generally every order will be accepted as long as the passed
	 * parameters are flawless
	 *  
	 * <p>
	 * This method always returns immediately, whether or not the 
	 * order will be accepted. When this Method is called a new order will 
	 * be created and the expected execution date will be returned.
	 * Orders will be executed in the order they were received
	 * The OrderID can be used to inquire the current orderstate
	 *
	 * @param  name         an String that can identify the requested task clearly. 
	 * 						For example "Rasen mähen" when you want to order "Rasen mähen".
	 * 						All types of tasks can be found in the Dropbox in the file "Produktkatalog"
	 * @param  apartmentID  an String that can identify the apartment for the order 
	 *                      clearly. This Information will be droped internally
	 * @param  size         an Integer that will be used to quantify the order.
	 * 						For example when you pass 10 for Rasen mähen it means the size is 10 square meters
	 * @param  orderID      an Long that can identify an Order clearly 
	 * 						The orderID can be used to get the state of an Order
	 * @return      the date of the expected execution date wrapped into a string (its a string)
	 * @see         getState
	 * @see         getUnpaidBills
	 */
	@Override
	@WebMethod
	public
	String sendOrderToFm(String name, String apartmentID, int size, long orderID) {

		String s = "";
		try {
			Auftrag a = Verwaltung.getInstance().addAuftrag(name, size, (int) orderID);
			s= a.getZieldatum().toLocaleString().split(" ")[0];
			System.out.println("Auftrag erhalten: "+name+" unter der Nummer: "+orderID+ ". Vorraussichtliches Ausführungsdatum: "+s);
		} catch (NullPointerException e) {
			//e.printStackTrace();
			System.err.println("Ein Auftrag konnte nicht angenommen werden");
		} 
		
		
		return s;
	}

	
	/**
	 * Returns all invoices that the FM have not received a payment yet.
	 * It will return the orderIDs of all orders in a String separated 
	 * by blank characters. In case all orders are paid for the method will
	 * return an empty string.
	 * The OrderID is known from passing the order to FM initially. 
	 * It is the ID that is passed to FM when you send a new Order to FM.
	 *  
	 * <p>
	 * This method always returns immediately.
	 * When this Method is called all Unpaid invoices will be returned as 
	 * known by FM independently of how they are recorded in the accounting
	 * The OrderIDs are known from passing the orders to FM initially
	 *
	 * @return      a String containing the IDs of all unpaid orders
	 * @see         sendOrderToFM
	 */
	@Override
	@WebMethod
	public
	String getUnpaidBills() {
		String s = "";
		try
		{
		s = Verwaltung.getInstance().unbezahlteRechnungen();
		System.out.println("Die unbezahlten Rechnungen wurden abgefragt und geliefert: "+s);
		}
		catch(Exception ex)
		{
			System.err.println("Die unbezahlten Rechnungen konnten nicht geliefert werden");
		}
		return s;
	}


	@Override
	@WebMethod
	public
	int pushDate(int year, int month, int day) {
		
		
		
		int returncode = 0;
		
		

		if(Verwaltung.getInstance().tag != null)
		{
			Date d = null;
			String sprungArt = null;
			Timestamp sprungDate = new Timestamp(year - 1900, month, day, 0, 0, 0, 0);
			d =new Date(Verwaltung.getInstance().tag.getTime());
			d.setYear(d.getYear() - 1900);
			Zeitsprung zs = new Zeitsprung(d);
			Object vorgehen = zs.bestimmeVorgehen(new Date(sprungDate.getTime()));

			if (vorgehen instanceof Integer) {
				returncode = (int) vorgehen;
			} else if (vorgehen instanceof String) {
				sprungArt = (String) vorgehen;
				System.out.println("Sprungart des Zeitsprunges: " + sprungArt);
				try
				{
					
					Verwaltung.getInstance().zieltag = new java.util.Date();
					Verwaltung.getInstance().zieltag.setMonth(month);
					Verwaltung.getInstance().zieltag.setDate(day);
					Verwaltung.getInstance().zieltag.setYear(year);
					System.out.println("localDate - zu Beginn der Methode: "+Verwaltung.getInstance().tag.toLocaleString() );
					Verwaltung.getInstance().timestep();
					System.out.println("Zieltag war: "+Verwaltung.getInstance().zieltag.toLocaleString().split(" ")[0] );
					returncode = 100;
				} catch (NullPointerException e) {
					e.printStackTrace();
					returncode = 450;
				}
			} else {
				// Object o ist weder vom Typ Integer noch von String
				returncode = 402;
			}
		}
		else
		{
			
				try{
				Verwaltung.getInstance().tag = new java.util.Date();
				Verwaltung.getInstance().tag.setMonth(month);
				Verwaltung.getInstance().tag.setDate(day);
				Verwaltung.getInstance().tag.setYear(year);
				System.out.println("localDate wurde gesetzt: "+Verwaltung.getInstance().tag.toLocaleString() );
			} catch (NullPointerException e) {
				e.printStackTrace();
				
			}
			returncode = 101;
		}
		

		return returncode;
		
		
		
		
		
		

       


	}


	@Override
	@WebMethod
	public
	String erfasseRechnung(String verwendungszweck, String sender,
			String rechnungsersteller, String rechnungsempfaenger,
			double betrag, String rechnungsdatum, String zahlungsdatum) {
		// TODO Auto-generated method stub
		String s = "true";
		try
		{
		System.out.println("Rechnung erhalten: "+verwendungszweck);
		Verwaltung.getInstance().sendInvoice(verwendungszweck, "GS", rechnungsersteller, rechnungsempfaenger, betrag, rechnungsdatum, zahlungsdatum);
		}
		catch(Exception ex)
		{
			System.err.println("Rechnung konnte nicht zurückgeleitet werden.");
			s = "false";
		}
		return s;
	}


	@Override
	@WebMethod
	public
	int mahnungEmpfangen(String verwendungszweck) {
		// TODO Auto-generated method stub
		System.out.println("Mahnung erhalten: "+verwendungszweck);
		return 0;
	}



}