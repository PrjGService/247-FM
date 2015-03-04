package model.abrechnungen;

import enums.Enums;

public class Reparaturabrechnung extends Wohnungsabrechnung {
	
	float materialkosten;

	public Reparaturabrechnung(int dienstleistungsID, String dienstleistungsName,
			float dienstleistungspreis, float dienstleistungsaufwand,
			String mitgliedsName, Enums.Faktortyp dienstleistungFaktortyp, float materialkosten,int anzahlWohnungen) {
		super(dienstleistungsID, dienstleistungsName, dienstleistungspreis,
				dienstleistungsaufwand, mitgliedsName, dienstleistungFaktortyp, anzahlWohnungen);
		this.materialkosten = materialkosten;
		dienstleistungspreis = preisermitteln(anzahlWohnungen, materialkosten);
		dienstleistungsaufwand = aufwandermitteln(anzahlWohnungen, materialkosten);
		
		
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
