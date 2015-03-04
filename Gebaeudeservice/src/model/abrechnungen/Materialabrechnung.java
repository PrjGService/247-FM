package model.abrechnungen;

import enums.Enums;


public class Materialabrechnung extends Stundenabrechnung {
	
	float materialpreis;

	public Materialabrechnung(int dienstleistungsID, String dienstleistungsName,
			float dienstleistungspreis, float dienstleistungsaufwand,
			String mitgliedsName, Enums.Faktortyp dienstleistungFaktortyp, float materialpreis, int anzahlStunde) {
		super(dienstleistungsID, dienstleistungsName, dienstleistungspreis,
				dienstleistungsaufwand, mitgliedsName, dienstleistungFaktortyp, anzahlStunde);
		this.materialpreis = materialpreis;
		dienstleistungspreis = preisermitteln(anzahlStunde, materialpreis);
		dienstleistungsaufwand = aufwandermitteln(anzahlStunde, materialpreis);
		
		
	}


	public float preisermitteln(float wert, float wert2) {
		// TODO Auto-generated method stub
		return 0;
	}


	public float aufwandermitteln(float wert, float wert2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
