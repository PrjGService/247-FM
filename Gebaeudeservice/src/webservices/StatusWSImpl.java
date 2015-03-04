package webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService(endpointInterface = "webservices.StatusWS")
public class StatusWSImpl implements StatusWS {
	
	@Override
	@WebMethod
	public
	String getState(int orderID) {
		// TODO Auto-generated method stub
		
		//verwaltung.statusliefern aufrufen
		
		//return order
		return null;
	}

}
