package webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

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
	 * @param  orderID  an Integer that can identify an Order clearly 
	 * @return      the state of the order belonging to the orderID passed as String
	 * @see         sendOrderToFM
	 */
	@Override
	@WebMethod
	public
	String getState(long orderID) {
		String s = "";
		try {
			s= Enums.getAStatus(Verwaltung.verwaltung.getAuftrag((int)orderID).auftragstatus);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} 
		
		
		return s;
	}

	
	//wasser 3000-5000
	//strom 200-300
	//gas 1000-1500
	
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
	 * @param  orderID      an Integer that can identify an Order clearly 
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
			s= Verwaltung.verwaltung.addAuftrag(name, size, orderID).getZieldatum().toString();
		} catch (NullPointerException e) {
			e.printStackTrace();
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
		return Verwaltung.verwaltung.unbezahlteRechnungen();
	}


	@Override
	@WebMethod
	public
	int pushDate(int year, int month, int day) {

		if(Verwaltung.verwaltung.tag == null)
		{
			Verwaltung.verwaltung.tag = new java.util.Date();
			Verwaltung.verwaltung.tag.setMonth(month);
			Verwaltung.verwaltung.tag.setDate(day);
			Verwaltung.verwaltung.tag.setYear(year);
		}
		else
		{
		Verwaltung.verwaltung.zieltag.setMonth(month);
		Verwaltung.verwaltung.zieltag.setDate(day);
		Verwaltung.verwaltung.zieltag.setYear(year);
		Verwaltung.verwaltung.timestep();
        System.out.println("localDate - zu Beginn der Methode: "+Verwaltung.verwaltung.tag.toString() );
        System.out.println("Zieltag: "+Verwaltung.verwaltung.zieltag.toString() );
		}
   


       


        return 100;
	}

}