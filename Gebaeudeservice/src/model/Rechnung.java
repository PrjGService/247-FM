package model;

import java.util.Date;

import controller.Verwaltung;
import enums.Enums;



public class Rechnung {

	public Auftraggeber auftraggeber;
	public int rechnungID;
	public Date rechnungDatum;
	public Auftrag auftrag;
	public float rechnungPreis;
	public Date rechnungZahlungsziel;
	public String rechnungVerwendungszweck;
	
	
	
	public Rechnung(Auftraggeber auftraggeber, int rechnungID,
			Date rechnungDatum, Auftrag auftrag, float rechnungPreis,
			Date rechnungZahlungsziel, String rechnungVerwendungszweck) {
		this.auftraggeber = auftraggeber;
		this.rechnungID = rechnungID;
		this.rechnungDatum = rechnungDatum;
		this.auftrag = auftrag;
		this.rechnungPreis = rechnungPreis;
		this.rechnungZahlungsziel = rechnungZahlungsziel;
		this.rechnungVerwendungszweck = rechnungVerwendungszweck;
	}


	//versenden methode
	public void versenden()
	{
		Verwaltung.getInstance().sendInvoice(rechnungVerwendungszweck, "GS","GS", auftraggeber.auftraggeberName, (double)auftrag.getCost(), rechnungDatum.toString(), rechnungZahlungsziel.toString());
		Verwaltung.getInstance().sendInvoiceGM(rechnungVerwendungszweck, "GS","GS", auftraggeber.auftraggeberName, (double)auftrag.getCost(), rechnungDatum.toString(), rechnungZahlungsziel.toString());
	}

}
