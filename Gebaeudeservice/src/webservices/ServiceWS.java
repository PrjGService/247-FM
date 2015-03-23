package webservices;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)

public interface ServiceWS extends InvoicesWS,OrderWS,StatusWS,TimeWS{

	String sendOrderToFm(String name, String apartmentID, int size, long orderID);

}