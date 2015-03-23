package model.abrechnungen;

import enums.Enums;

public class Reparaturabrechnung extends Wohnungsabrechnung {
	

	public Reparaturabrechnung(int dienstleistungsID, String dienstleistungsName,
			float dienstleistungspreis, float dienstleistungsaufwand,
			String mitgliedsName, Enums.Faktortyp dienstleistungFaktortyp) {
		super(dienstleistungsID, dienstleistungsName, dienstleistungspreis,
				dienstleistungsaufwand, mitgliedsName, dienstleistungFaktortyp);
		
		
	}

	public float preisermitteln(float wert, float wert2) {
		// TODO Auto-generated method stub
		return dienstleistungspreis;
	}

	public float aufwandermitteln(float wert, float wert2) {
		// TODO Auto-generated method stub
		return dienstleistungsaufwand;
	}

}
