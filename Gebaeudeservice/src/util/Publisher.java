package util;

import javax.xml.ws.Endpoint;

import webservices.InvoicesWSImpl;
import webservices.OrderWSImpl;
import webservices.ServiceWSImpl;
import webservices.StatusWSImpl;
 
public class Publisher{
	
	private static Publisher instance;
 
	public Publisher(){
		Endpoint.publish("http://10.10.10.25:1000/ws", new ServiceWSImpl());
	}
	
	public static Publisher getInstance(){
		if(instance == null){
			instance = new Publisher();
		}
		return instance;
	}

}