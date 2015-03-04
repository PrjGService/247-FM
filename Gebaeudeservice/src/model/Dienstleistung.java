package model;

import enums.Enums;

public abstract class Dienstleistung {
	
	 private int dienstleistungsID;
	 String dienstleistungsName;
	 float dienstleistungspreis;
	 float dienstleistungsaufwand;
	 String mitgliedsName;
	 Enums.Faktortyp DienstleistungFaktortyp;
	
	abstract public float preisermitteln(float wert);
	
	abstract public float aufwandermitteln(float wert);

	public Dienstleistung(int dienstleistungsID, String dienstleistungsName,
			float dienstleistungspreis, float dienstleistungsaufwand,
			String mitgliedsName, Enums.Faktortyp dienstleistungFaktortyp) {
		this.setDienstleistungsID(dienstleistungsID);
		this.dienstleistungsName = dienstleistungsName;
		this.dienstleistungspreis = dienstleistungspreis;
		this.dienstleistungsaufwand = dienstleistungsaufwand;
		this.mitgliedsName = mitgliedsName;
		DienstleistungFaktortyp = dienstleistungFaktortyp;
	}

	public int getDienstleistungsID() {
		return dienstleistungsID;
	}

	public void setDienstleistungsID(int dienstleistungsID) {
		this.dienstleistungsID = dienstleistungsID;
	}
	
	

}
