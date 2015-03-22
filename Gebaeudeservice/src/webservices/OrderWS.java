package webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)

public interface OrderWS {
	@WebMethod String sendOrderToFm(String name, int apartmentID, int size, int orderID);
}
