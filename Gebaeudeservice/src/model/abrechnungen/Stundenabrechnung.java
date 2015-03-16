package model.abrechnungen;

import enums.Enums;
import model.Dienstleistung;

public class Stundenabrechnung extends Dienstleistung {
	

	public Stundenabrechnung(int dienstleistungsID, String dienstleistungsName,
			float dienstleistungspreis, float dienstleistungsaufwand,
			String mitgliedsName, Enums.Faktortyp dienstleistungFaktortyp) {
		super(dienstleistungsID, dienstleistungsName, dienstleistungspreis,
				dienstleistungsaufwand, mitgliedsName, dienstleistungFaktortyp);
		
		
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
