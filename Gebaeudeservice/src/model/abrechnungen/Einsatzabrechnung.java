package model.abrechnungen;

import enums.Enums;
import model.Dienstleistung;

public class Einsatzabrechnung extends Dienstleistung {
	
	int anzahlEinsatz;

	public Einsatzabrechnung(int dienstleistungsID, String dienstleistungsName,
			float dienstleistungspreis, float dienstleistungsaufwand,
			String mitgliedsName, Enums.Faktortyp dienstleistungFaktortyp, int anzahlEinsatz) {
		super(dienstleistungsID, dienstleistungsName, dienstleistungspreis,
				dienstleistungsaufwand, mitgliedsName, dienstleistungFaktortyp);
		this.anzahlEinsatz = anzahlEinsatz;
		dienstleistungspreis = preisermitteln(anzahlEinsatz);
		dienstleistungsaufwand = aufwandermitteln(anzahlEinsatz);
		
		
	}

	@Override
	public float preisermitteln(float wert) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float aufwandermitteln(float wert) {
		// TODO Auto-generated method stub
		return 0;
	}

}
