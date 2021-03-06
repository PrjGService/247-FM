package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import controller.Verwaltung;

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

	// versenden methode
	public void versenden() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Verwaltung.getInstance().sendInvoice(rechnungVerwendungszweck, "GS",
				"GS", "GM", (double) auftrag.getCost(),
				sdf.format(rechnungDatum), sdf.format(rechnungZahlungsziel));
		Verwaltung.getInstance().sendInvoiceGM(rechnungVerwendungszweck, "GS",
				"GS", "GM", (double) auftrag.getCost(),
				sdf.format(rechnungDatum), sdf.format(rechnungZahlungsziel));
	}

}
