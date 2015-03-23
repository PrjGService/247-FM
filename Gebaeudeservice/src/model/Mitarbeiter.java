package model;

import java.util.Calendar;
import java.util.Date;

import controller.Verwaltung;

import enums.Enums;
import enums.Enums.Mitarbeiterstatus;


public class Mitarbeiter {

	public int mitarbeiterID;
	public String mitarbeiterName;
	public Mitarbeiterstatus mitarbeiterStatus;
	public Position aktuellePosition;
	
	
	
	
	public Mitarbeiter(int mitarbeiterID, String mitarbeiterName,
			Mitarbeiterstatus mitarbeiterStatus) {
		this.mitarbeiterID = mitarbeiterID;
		this.mitarbeiterName = mitarbeiterName;
		this.mitarbeiterStatus = mitarbeiterStatus;
	}



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
			String verwendungszweck = "GS"+aktuellePosition.auftrag.auftragID;
			Rechnung r = new Rechnung(aktuellePosition.auftrag.auftraggeber, aktuellePosition.auftrag.auftragID, new Date(), aktuellePosition.auftrag, aktuellePosition.auftrag.getCost(), ziel, verwendungszweck);
			r.versenden();
			Verwaltung.verwaltung.rechnungList.add(r);
		}
		
	}
	
}