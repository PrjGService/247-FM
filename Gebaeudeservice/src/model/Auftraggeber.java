package model;

import java.util.Date;

import controller.Verwaltung;
import enums.Enums.Auftragsstatus;


public class Auftraggeber {

	int auftraggeberID;
	String auftraggeberName;
	String auftraggeberAdresse;
	
	public Auftraggeber(int auftraggeberID, String auftraggeberName,
			String auftraggeberAdresse) 
	{
		this.auftraggeberID = auftraggeberID;
		this.auftraggeberName = auftraggeberName;
		this.auftraggeberAdresse = auftraggeberAdresse;
	}
	
	//TODO multiaufträge zulassen?
	public void auftragErhalten(String name, int apartmentID, int size, int orderID)
	{
		//multiaufträge könnte mit counter geregelt werden sodass bei counter auftrag erstellt wird
		//oder diese methode erzeugt nur position und in der tickmethode im verwalter wird regelmäßig eine auftrag erteilt methode aufgerufen
		Verwaltung.verwaltung.auftragList.add(new Auftrag(this, orderID,  Auftragsstatus.ANGEKOMMEN, new Date(), Verwaltung.verwaltung.getDienstleistung(orderID), size));
	}
	

}
