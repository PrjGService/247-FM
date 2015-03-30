package webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


@WebService
@SOAPBinding(style = Style.RPC)
public interface RechnungWS {
	
	@WebMethod
	String erfasseRechnung(String verwendungszweck, String sender, String rechnungsersteller, String rechnungsempfaenger, double betrag, String rechnungsdatum, String zahlungsdatum); 

}
