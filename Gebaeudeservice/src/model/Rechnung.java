package model;

import java.util.Date;

import controller.Verwaltung;



public class Rechnung {

	Auftraggeber auftraggeber;
	int rechnungID;
	Date rechnungDatum;
	Auftrag auftrag;
	float rechnungPreis;
	Date rechnungZahlungsziel;
	String rechnungVerwendungszweck;
	
	
	
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
		//TODO parameter an methode anpassen
		Verwaltung.verwaltung.sendInvoice("GS", auftraggeber.auftraggeberName, "GS", (double)auftrag.getCost(), rechnungVerwendungszweck, rechnungDatum, rechnungZahlungsziel, auftraggeber.auftraggeberAdresse);
	}

}
