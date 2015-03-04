package model.abrechnungen;

import enums.Enums;
import model.Dienstleistung;

public class Wohnungsabrechnung extends Dienstleistung {
	
	int anzahlWohnung;

	public Wohnungsabrechnung(int dienstleistungsID, String dienstleistungsName,
			float dienstleistungspreis, float dienstleistungsaufwand,
			String mitgliedsName, Enums.Faktortyp dienstleistungFaktortyp, int anzahlWohnung) {
		super(dienstleistungsID, dienstleistungsName, dienstleistungspreis,
				dienstleistungsaufwand, mitgliedsName, dienstleistungFaktortyp);
		this.anzahlWohnung = anzahlWohnung;
		dienstleistungspreis = preisermitteln(anzahlWohnung);
		dienstleistungsaufwand = aufwandermitteln(anzahlWohnung);
		
		
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
