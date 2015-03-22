package webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "webservices.InvoicesWS")
public class InvoicesWSImpl implements InvoicesWS {


	@Override
	@WebMethod
	public
	String getUnpaidBills() {
		// TODO Auto-generated method stub
		
		//return liste der namen nachrangig (war festgelegt?)
		return null;
	}
	
}
