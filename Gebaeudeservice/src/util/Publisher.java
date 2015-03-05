package util;

import javax.xml.ws.Endpoint;

import webservices.InvoicesWSImpl;
import webservices.OrderWSImpl;
import webservices.StatusWSImpl;
 
public class Publisher{
	
	private static Publisher instance;
 
	public Publisher(){
		Endpoint.publish("http://10.10.10.25:1000/ws", new InvoicesWSImpl());
		Endpoint.publish("http://10.10.10.25:1000/ws", new OrderWSImpl());
		Endpoint.publish("http://10.10.10.25:1000/ws", new StatusWSImpl());
	}
	
	public static Publisher getInstance(){
		if(instance == null){
			instance = new Publisher();
		}
		return instance;
	}

}