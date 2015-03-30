package model.abrechnungen;

import enums.Enums;

public class Reparaturabrechnung extends Wohnungsabrechnung {
	

	public Reparaturabrechnung(int dienstleistungsID, String dienstleistungsName,
			float dienstleistungspreis, float dienstleistungsaufwand,
			String mitgliedsName, Enums.Faktortyp dienstleistungFaktortyp) {
		super(dienstleistungsID, dienstleistungsName, dienstleistungspreis,
				dienstleistungsaufwand, mitgliedsName, dienstleistungFaktortyp);
		
		
	}

	public float preisermitteln(float wert) {
		// TODO Auto-generated method stub
		return dienstleistungspreis*wert;
	}

	public float aufwandermitteln(float wert) {
		// TODO Auto-generated method stub
		return dienstleistungsaufwand*wert;
	}

}
