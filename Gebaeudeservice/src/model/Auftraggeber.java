package model;

import java.util.Date;

import controller.Verwaltung;
import enums.Enums.Auftragsstatus;


public class Auftraggeber {

	public int auftraggeberID;
	public String auftraggeberName;
	public String auftraggeberAdresse;
	
	public Auftraggeber(int auftraggeberID, String auftraggeberName,
			String auftraggeberAdresse) 
	{
		this.auftraggeberID = auftraggeberID;
		this.auftraggeberName = auftraggeberName;
		this.auftraggeberAdresse = auftraggeberAdresse;
	}
	

	

}
