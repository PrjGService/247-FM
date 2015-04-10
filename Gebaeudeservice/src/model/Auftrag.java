package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.MainWindowController;
import controller.Verwaltung;
import enums.Enums;

public class Auftrag {

	public int auftragID;
	
	public Dienstleistung dienstleistung;

	public int getAuftragID() {
		return auftragID;
	}

	public void setAuftragID(int auftragID) {
		this.auftragID = auftragID;
	}

	public Auftraggeber getAuftraggeber() {
		return auftraggeber;
	}

	public void setAuftraggeber(Auftraggeber auftraggeber) {
		this.auftraggeber = auftraggeber;
	}

	public Enums.Auftragsstatus getAuftragstatus() {
		return auftragstatus;
	}

	public void setAuftragstatus(Enums.Auftragsstatus auftragstatus) {
		this.auftragstatus = auftragstatus;
		MainWindowController.getInstance().addOrChangeAuftrag(this);
	}

	public Date getAuftragdatum() {
		return auftragdatum;
	}

	public void setAuftragdatum(Date auftragdatum) {
		this.auftragdatum = auftragdatum;
	}

	public List<Position> getPositionen() {
		return positionen;
	}

	public void setPositionen(List<Position> positionen) {
		this.positionen = positionen;
	}

	public Auftraggeber auftraggeber;
	private Enums.Auftragsstatus auftragstatus;
	public Date auftragdatum;
	public List<Position> positionen = new ArrayList<Position>();
	public int menge = 0;

	public Auftrag(Auftraggeber auftraggeber, int auftragID,
			Enums.Auftragsstatus auftragstatus, Date auftragdatum,
			Dienstleistung dienstleistung, int menge) {
		this.auftragdatum = auftragdatum;
		this.auftraggeber = auftraggeber;
		this.auftragID = auftragID;
		this.auftragstatus = auftragstatus;
		this.menge = menge;
		positionen = new ArrayList<Position>();
		this.dienstleistung = dienstleistung;
		//positionErzeugen(dienstleistung, menge);
	}

	public Auftrag(Auftraggeber auftraggeber, int auftragID,
			Enums.Auftragsstatus auftragstatus, Date auftragdatum,
			ArrayList<Position> positionen) {
		this.auftragdatum = auftragdatum;
		this.auftraggeber = auftraggeber;
		this.auftragID = auftragID;
		this.auftragstatus = auftragstatus;
		// es gibt mehrere positionen
		positionen = new ArrayList<Position>();
		if(this.positionen == null)
		{
			positionen = new ArrayList<Position>();
		}
	}

	public void positionErzeugen(Dienstleistung dienstleistung, int menge) {
		Date zieldatum = Verwaltung.getInstance().getZieldatum(this);
		Position p = new Position(dienstleistung, this, null, menge,
				zieldatum, auftragstatus);
		if(this.positionen == null)
		{
			positionen = new ArrayList<Position>();
		}
		positionen.add(p);
		try
		{
		Verwaltung.getInstance().conn.writePosition(p);
		}
		catch (Exception ex)
		{
			System.err.println("Eine Position konnte nicht angelegt werden");
		}
	}

	public boolean checkForReady() {
		boolean isOK = true;
		for (int i = 0; i < positionen.size(); i++) {
			if (positionen.get(i).positionStatus != Enums.Auftragsstatus.ERLEDIGT) {
				isOK = false;
				break;
			}
		}
		return isOK;
	}

	public float getCost() {
		float preis = 0f;
		for (int i = 0; i < positionen.size(); i++) {
			preis += positionen.get(i).dienstleistung.aufwandermitteln(positionen.get(i).positionMenge);
		}
		return preis;
	}



	public Date getZieldatum() {
		return positionen.get(0).positionAusfuehrungsdatum;

	}

}