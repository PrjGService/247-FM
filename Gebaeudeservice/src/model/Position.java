package model;

import java.util.Date;

import enums.Enums;


public class Position {

	Dienstleistung dienstleistung;
	Auftrag auftrag;
	Mitarbeiter mitarbeiter;
	float positionMenge;
	Date positionAusfuehrungsdatum;
	Enums.Auftragsstatus positionStatus;
	
	
	public Position(Dienstleistung dienstleistung, Auftrag auftrag,
			Mitarbeiter mitarbeiter, float positionMenge,
			Date positionAusfuehrungsdatum, Enums.Auftragsstatus positionStatus) {
		this.dienstleistung = dienstleistung;
		this.auftrag = auftrag;
		this.mitarbeiter = mitarbeiter;
		this.positionMenge = positionMenge;
		this.positionAusfuehrungsdatum = positionAusfuehrungsdatum;
		this.positionStatus = positionStatus;
	}
	
	
}
