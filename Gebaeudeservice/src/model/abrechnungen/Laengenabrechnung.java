package model.abrechnungen;

import enums.Enums;
import model.Dienstleistung;

public class Laengenabrechnung extends Dienstleistung {
	
	float laenge;

	public Laengenabrechnung(int dienstleistungsID, String dienstleistungsName,
			float dienstleistungspreis, float dienstleistungsaufwand,
			String mitgliedsName, Enums.Faktortyp dienstleistungFaktortyp, float laenge) {
		super(dienstleistungsID, dienstleistungsName, dienstleistungspreis,
				dienstleistungsaufwand, mitgliedsName, dienstleistungFaktortyp);
		this.laenge = laenge;
		dienstleistungspreis = preisermitteln(laenge);
		dienstleistungsaufwand = aufwandermitteln(laenge);
		
		
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
