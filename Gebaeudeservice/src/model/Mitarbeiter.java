package model;

import java.util.Calendar;
import java.util.Date;

import enums.Enums;
import enums.Enums.Mitarbeiterstatus;


public class Mitarbeiter {

	public int mitarbeiterID;
	public String mitarbeiterName;
	public Mitarbeiterstatus mitarbeiterStatus;
	Position aktuellePosition;
	
	
	
	
	public Mitarbeiter(int mitarbeiterID, String mitarbeiterName,
			Mitarbeiterstatus mitarbeiterStatus) {
		this.mitarbeiterID = mitarbeiterID;
		this.mitarbeiterName = mitarbeiterName;
		this.mitarbeiterStatus = mitarbeiterStatus;
	}



	//TODO
	public void positionAusfuehren(Position position)
	{
		//Zahlungsziel = 30 Tage
		Date ziel = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(ziel); 
		c.add(Calendar.DATE, 30);
		ziel = c.getTime();
		mitarbeiterStatus = Enums.Mitarbeiterstatus.VERFUEGBAR;
		aktuellePosition.positionStatus = Enums.Auftragsstatus.ERLEDIGT;
		if(aktuellePosition.auftrag.checkForReady())
		{
			aktuellePosition.auftrag.auftragstatus = Enums.Auftragsstatus.ERLEDIGT;
			//status anpassen von ma und position (auftrag)
			String verwendungszweck = ""; //TODO füllen
			new Rechnung(aktuellePosition.auftrag.auftraggeber, aktuellePosition.auftrag.auftragID, new Date(), aktuellePosition.auftrag, aktuellePosition.auftrag.getCost(), ziel, verwendungszweck).versenden();
		}
		
	}
	
}
