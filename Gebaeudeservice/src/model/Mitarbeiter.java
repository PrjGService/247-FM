package model;

import java.util.Calendar;
import java.util.Date;

import controller.MainWindowController;
import controller.Verwaltung;
import enums.Enums;
import enums.Enums.Mitarbeiterstatus;

public class Mitarbeiter {

	public int mitarbeiterID;
	public String mitarbeiterName;
	private Mitarbeiterstatus mitarbeiterStatus;
	public Position aktuellePosition;

	public int getMitarbeiterID() {
		return mitarbeiterID;
	}

	public void setMitarbeiterID(int mitarbeiterID) {
		this.mitarbeiterID = mitarbeiterID;
	}

	public String getMitarbeiterName() {
		return mitarbeiterName;
	}

	public void setMitarbeiterName(String mitarbeiterName) {
		this.mitarbeiterName = mitarbeiterName;
	}

	public Mitarbeiterstatus getMitarbeiterStatus() {
		return mitarbeiterStatus;
	}

	public void setMitarbeiterStatus(Mitarbeiterstatus mitarbeiterStatus) {
		this.mitarbeiterStatus = mitarbeiterStatus;
		MainWindowController.getInstance().addOrChangeMitarbeiter(this);
	}

	public Position getAktuellePosition() {
		return aktuellePosition;
	}

	public void setAktuellePosition(Position aktuellePosition) {
		this.aktuellePosition = aktuellePosition;
	}

	public Mitarbeiter(int mitarbeiterID, String mitarbeiterName,
			Mitarbeiterstatus mitarbeiterStatus) {
		this.mitarbeiterID = mitarbeiterID;
		this.mitarbeiterName = mitarbeiterName;
		this.mitarbeiterStatus = mitarbeiterStatus;
	}

	public void positionAusfuehren(Position position) {
		// Zahlungsziel = 30 Tage
		Date ziel = Verwaltung.getInstance().tag;
		Calendar c = Calendar.getInstance();
		c.setTime(ziel);
		c.add(Calendar.DATE, 30);
		ziel = c.getTime();
		setMitarbeiterStatus(Enums.Mitarbeiterstatus.VERFUEGBAR);
		aktuellePosition.positionStatus = Enums.Auftragsstatus.ERLEDIGT;
		System.out.println("Auftrag " +aktuellePosition.auftrag.auftragID+ " wurde von "+mitarbeiterName+" ausgefuehrt");
		if (aktuellePosition.auftrag.checkForReady()) {
			aktuellePosition.auftrag.setAuftragstatus(Enums.Auftragsstatus.ERLEDIGT);
			String s = "" +aktuellePosition.auftrag.auftragID;
			while (s.length() < 8)
			{
				s = "0"+s;
			}
			String verwendungszweck = "GS" + s;
			Rechnung r = new Rechnung(aktuellePosition.auftrag.auftraggeber,
					aktuellePosition.auftrag.auftragID, Verwaltung.getInstance().tag,
					aktuellePosition.auftrag,
					aktuellePosition.auftrag.getCost(), ziel, verwendungszweck);
			Verwaltung.getInstance().conn.writeRechnung(r);
			Verwaltung.getInstance().rechnungversendenList.add(r);
			Verwaltung.getInstance().rechnungList.add(r);
			System.out.println("Rechnung wurde erzeugt: "+r.rechnungVerwendungszweck);
			MainWindowController.getInstance().addOrChangeRechnung(r);
		}

	}

}