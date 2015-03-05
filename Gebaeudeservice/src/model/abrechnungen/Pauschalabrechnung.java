package model.abrechnungen;

import enums.Enums;
import model.Dienstleistung;

public class Pauschalabrechnung extends Dienstleistung {
	
	int anzahlMonat;

	public Pauschalabrechnung(int dienstleistungsID, String dienstleistungsName,
			float dienstleistungspreis, float dienstleistungsaufwand,
			String mitgliedsName, Enums.Faktortyp dienstleistungFaktortyp, int anzahlMonat) {
		super(dienstleistungsID, dienstleistungsName, dienstleistungspreis,
				dienstleistungsaufwand, mitgliedsName, dienstleistungFaktortyp);
		this.anzahlMonat = anzahlMonat;
		dienstleistungspreis = preisermitteln(anzahlMonat);
		dienstleistungsaufwand = aufwandermitteln(anzahlMonat);
		
		
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
