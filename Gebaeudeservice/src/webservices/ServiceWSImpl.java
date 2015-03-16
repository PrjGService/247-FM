package webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "webservices.ServiceWS")
public class ServiceWSImpl  implements ServiceWS{

	
	/**
	 * Returns an state as String in case it finds the orderID
	 * in every other case the return will be an empty String
	 *  
	 * <p>
	 * This method always returns immediately, whether or not the 
	 * order exists. When this Method is called the current state is returned 
	 * independently of whether it will change before the next timepush
	 * The OrderID is known from passing the order to FM initially
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
	String getState(int orderID) {
		// TODO Auto-generated method stub
		return "";
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
	 * @param  name         an String that can identify the requested task clearly 
	 * @param  apartmentID  an Integer that can identify the apartment for the order 
	 *                      clearly. This Information will be droped internally
	 * @param  size         an Integer that will be used to quantify the order
	 * @param  orderID      an Integer that can identify an Order clearly 
	 * @return      the date of the expected execution date wrapped into a string 
	 * @see         getState
	 * @see         getUnpaidBills
	 */
	@Override
	@WebMethod
	public
	String sendOrderToFm(String name, int apartmentID, int size, int orderID) {
		// TODO Auto-generated method stub
		return "";
	}

	
	/**
	 * Returns all invoices that the FM have not received a payment yet.
	 * It will return the orderIDs of all orders in a String separated 
	 * by blank characters. In case all orders are paid for the method will
	 * return an empty string.
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
		// TODO Auto-generated method stub
		return "";
	}

}
