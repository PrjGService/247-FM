package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.Verwaltung;
import enums.Enums;


public class Auftrag {
	
	 public int auftragID;
	 public Auftraggeber auftraggeber;
	 public Enums.Auftragsstatus auftragstatus;
	 public Date auftragdatum;
	 public List<Position> positionen;
	
	
	public Auftrag(Auftraggeber auftraggeber, int auftragID, Enums.Auftragsstatus auftragstatus, Date auftragdatum, Dienstleistung dienstleistung, int menge)
	{
		this.auftragdatum = auftragdatum;
		this.auftraggeber  = auftraggeber;
		this.auftragID = auftragID;
		this.auftragstatus = auftragstatus;
		positionen = new ArrayList<Position>();
		positionErzeugen(dienstleistung,menge);
	}
	
	public Auftrag(Auftraggeber auftraggeber, int auftragID, Enums.Auftragsstatus auftragstatus, Date auftragdatum, ArrayList<Position> positionen)
	{
		this.auftragdatum = auftragdatum;
		this.auftraggeber  = auftraggeber;
		this.auftragID = auftragID;
		this.auftragstatus = auftragstatus;
		//es gibt mehrere positionen
		this.positionen = positionen;
	}
	
	
	public void positionErzeugen(Dienstleistung dienstleistung, int menge)
	{
		Date zieldatum = Verwaltung.verwaltung.getZieldatum(this);
		positionen.add(new Position(dienstleistung, this, null, menge, zieldatum, auftragstatus));
	}
	
	public boolean checkForReady()
	{
		boolean isOK = true;
		for(int i = 0; i < positionen.size();i++)
		{
			if(positionen.get(i).positionStatus != Enums.Auftragsstatus.ERLEDIGT)
			{
				isOK = false;
				break;
			}
		}
			return isOK;
	}
	
	public float getCost()
	{
		float preis = 0f;
		for(int i = 0; i < positionen.size();i++)
		{
			preis+=positionen.get(i).positionMenge*positionen.get(i).dienstleistung.dienstleistungspreis;
		}
		return preis;
	}
	
	
	
	public void suchePositionen()
	{
		//TODO positionen zu auftrag suchen (nur fallsmehrere posis per auftrag)
	}
	
	public Date getZieldatum()
	{
		return auftragdatum;
		
	}

}