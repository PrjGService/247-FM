package webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

import controller.Verwaltung;


@WebService(endpointInterface = "webservices.OrderWS")
public class OrderWSImpl implements OrderWS {


	@Override
	@WebMethod
	public
	String sendOrderToFm(String name, String apartmentID, int size, int orderID) {
		// TODO Auto-generated method stub
		//Verwaltung.verwaltung.auftraggeber.auftragErhalten(name, apartmentID, size, orderID);
		//return datum
		return null;
	}

}