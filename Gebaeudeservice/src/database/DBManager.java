package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.Verwaltung;
import enums.Enums;
import enums.Enums.Faktortyp;

import model.Auftrag;
import model.Auftraggeber;
import model.Dienstleistung;
import model.Mitarbeiter;
import model.Position;
import model.Rechnung;
import model.User;
import model.abrechnungen.Einsatzabrechnung;
import model.abrechnungen.Laengenabrechnung;
import model.abrechnungen.Materialabrechnung;
import model.abrechnungen.Pauschalabrechnung;
import model.abrechnungen.Reparaturabrechnung;
import model.abrechnungen.Stundenabrechnung;
import model.abrechnungen.Wohnungsabrechnung;

public class DBManager {

	Connection conn;
	PreparedStatement statement;
	ResultSet result;

	public DBManager() {
		conn = DBUtil.getConnection();
		System.out.println("Verbindung zur DB hergestellt");

	}

	public void close() {

		try {
			conn.close();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Auftrag readAuftrag(int id) {
		try {
			statement = conn
					.prepareStatement("SELECT * FROM auftrag WHERE id = " + id
							+ ";");
			result = statement.executeQuery();
			if (result.next()) {
				return new Auftrag(Verwaltung.getInstance().auftraggeber, id,
						Enums.getAStatus(result.getString(3)),
						result.getDate(4), null);
			} else {
				System.out.println("No Auftrag read");
				return null;
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void writeAuftrag(Auftrag a) {
		try {
			statement = conn.prepareStatement("DELETE FROM auftrag WHERE id = "
					+ a.auftragID + ";");
			statement.execute();
			statement = conn.prepareStatement(
					"INSERT INTO auftrag (ID, auftraggeber.ID,Status, Datum "
							+ ") VALUES (?,?,??);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, a.auftragID);
			statement.setInt(2, a.auftraggeber.auftraggeberID);
			statement.setString(3, Enums.getAStatus(a.auftragstatus));
			statement.setDate(4, (Date) a.auftragdatum);
			statement.execute();
			result = statement.getGeneratedKeys();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Auftrag> getAllAuftrag() {
		List<Auftrag> l = new ArrayList<Auftrag>();
		List<Integer> helper = new ArrayList<Integer>();
		try {
			statement = conn.prepareStatement("SELECT * FROM auftrag;");
			result = statement.executeQuery();
			while (result.next()) {
				helper.add(result.getInt(1));

			}
			for (int i = 0; i < helper.size(); i++) {
				l.add(readAuftrag(helper.get(i)));
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return l;

	}

	public Mitarbeiter readMitarbeiter(int id) {
		try {
			statement = conn
					.prepareStatement("SELECT * FROM mitarbeiter WHERE id = "
							+ id + ";");
			result = statement.executeQuery();
			result.next();
			return new Mitarbeiter(id, result.getString(2),
					Enums.getMStatus(result.getString(3)));
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void writeMitarbeiter(Mitarbeiter m) {
		try {
			statement = conn
					.prepareStatement("DELETE FROM mitarbeiter WHERE id = "
							+ m.mitarbeiterID + ";");
			statement.execute();
			statement = conn.prepareStatement(
					"INSERT INTO mitarbeiter (ID, Name, Status"
							+ ") VALUES (?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, m.mitarbeiterID);
			statement.setString(2, m.mitarbeiterName);
			statement.setString(3, Enums.getMStatus(m.mitarbeiterStatus));
			statement.execute();
			result = statement.getGeneratedKeys();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Mitarbeiter> getAllMitarbeiter() {
		List<Mitarbeiter> l = new ArrayList<Mitarbeiter>();
		List<Integer> helper = new ArrayList<Integer>();
		try {
			statement = conn.prepareStatement("SELECT * FROM mitarbeiter;");
			result = statement.executeQuery();
			while (result.next()) {
				helper.add(result.getInt(1));

			}
			for (int i = 0; i < helper.size(); i++) {
				l.add(readMitarbeiter(helper.get(i)));
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return l;

	}

	public Auftraggeber readAuftraggeber(int id) {
		try {
			statement = conn
					.prepareStatement("SELECT * FROM auftraggeber WHERE id = "
							+ id + ";");
			result = statement.executeQuery();
			result.next();
			return new Auftraggeber(id, result.getString(6),
					result.getString(3) + " " + result.getString(5) + " "
							+ result.getString(2) + " " + result.getString(4));
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void writeAuftraggeber(Auftraggeber a) {
		try {
			statement = conn
					.prepareStatement("DELETE FROM auftraggeber WHERE id = "
							+ a.auftraggeberID + ";");
			statement.execute();
			statement = conn.prepareStatement(
					"INSERT INTO dienstleistung (ID, PLZ, Strasse, Ort, Hausnummer, Name"
							+ ") VALUES (?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, a.auftraggeberID);
			String[] s = a.auftraggeberAdresse.split(" ");
			statement.setString(2, s[2]);
			statement.setString(3, s[0]);
			statement.setString(4, s[3]);
			statement.setString(5, s[1]);
			statement.setString(3, a.auftraggeberName);
			statement.execute();
			result = statement.getGeneratedKeys();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Dienstleistung readDienstleistung(int id) {
		try {
			statement = conn
					.prepareStatement("SELECT * FROM dienstleistung WHERE id = "
							+ id + ";");
			result = statement.executeQuery();
			result.next();
			Dienstleistung d = null;
			switch (result.getString(4).trim()) {
			case "Standard":
				d = new Einsatzabrechnung(id, result.getString(2),
						result.getFloat(3), result.getInt(5),
						"Einsatzabrechnung", Enums.Faktortyp.ANZAHLEINSATZ);
				break;
			case "Laenge":
				d = new Laengenabrechnung(id, result.getString(2),
						result.getFloat(3), result.getInt(5),
						"Längenabrechnung", Enums.Faktortyp.LAENGE);
				break;
			case "Materialpreis":
				d = new Materialabrechnung(id, result.getString(2),
						result.getFloat(3), result.getInt(5),
						"Materialabrechnung", Enums.Faktortyp.MATERIALPREIS);
				break;
			case "Anzahlmonat":
				d = new Pauschalabrechnung(id, result.getString(2),
						result.getFloat(3), result.getInt(5),
						"Pauschalabrechnung", Enums.Faktortyp.ANZAHLMONAT);
				break;
			case "Stockwerke":
				d = new Reparaturabrechnung(id, result.getString(2),
						result.getFloat(3), result.getInt(5),
						"Reparaturabrechnung", Enums.Faktortyp.MATERIALKOSTEN);
				break;
			case "Stunden":
				d = new Stundenabrechnung(id, result.getString(2),
						result.getFloat(3), result.getInt(5),
						"Stundenabrechnung", Enums.Faktortyp.ANZAHLSTUNDE);
				break;
			case "Wohnungen":
				d = new Wohnungsabrechnung(id, result.getString(2),
						result.getFloat(3), result.getInt(5),
						"Wohnungsabrechnung", Enums.Faktortyp.ANZAHLWOHNUNG);
				break;
			}
			return d;
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void writeDienstleistung(Dienstleistung d) {
		try {
			statement = conn
					.prepareStatement("DELETE FROM dienstleistung WHERE id = "
							+ d.getDienstleistungsID() + ";");
			statement.execute();
			statement = conn.prepareStatement(
					"INSERT INTO dienstleistung (ID, Name, Preis, Faktortyp, Dauer Tage"
							+ ") VALUES (?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, d.getDienstleistungsID());
			statement.setString(2, d.dienstleistungsName);
			statement.setFloat(3, d.dienstleistungspreis);
			statement.setString(4,
					Enums.getFaktortyp(d.DienstleistungFaktortyp));
			statement.setFloat(5, d.dienstleistungsaufwand);
			statement.execute();
			result = statement.getGeneratedKeys();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Dienstleistung> getAllDienstleistung() {
		List<Integer> helper = new ArrayList<Integer>();
		List<Dienstleistung> l = new ArrayList<Dienstleistung>();
		try {
			statement = conn.prepareStatement("SELECT * FROM dienstleistung;");
			result = statement.executeQuery();
			while (result.next()) {
				helper.add(result.getInt(1));

			}
			for (int i = 0; i < helper.size(); i++) {
				l.add(readDienstleistung(helper.get(i)));
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return l;

	}

	public User readUser(String name) {
		try {
			statement = conn
					.prepareStatement("SELECT * FROM user WHERE Benutzername = '"
							+ name + "';");
			result = statement.executeQuery();
			result.next();
			return new User(name, result.getString(2));
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void writeUser(User u) {
		try {
			statement = conn
					.prepareStatement("DELETE FROM user WHERE Benutzername = '"
							+ u.name + "';");
			statement.execute();
			statement = conn.prepareStatement(
					"INSERT INTO user (Benutzername, Passwort"
							+ ") VALUES (?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, u.name);
			statement.setString(2, u.password);
			statement.execute();
			result = statement.getGeneratedKeys();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<User> getAllUser() {
		List<User> l = new ArrayList<User>();
		List<String> helper = new ArrayList<String>();
		try {
			statement = conn.prepareStatement("SELECT * FROM user;");
			result = statement.executeQuery();
			while (result.next()) {
				helper.add(result.getString(1));

			}
			for (int i = 0; i < helper.size(); i++) {
				l.add(readUser(helper.get(i)));
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return l;

	}

	public Position readPosition(int id1, int id2, int id3) {
		try {
			statement = conn
					.prepareStatement("SELECT * FROM position WHERE `dienstleistung.ID` = "
							+ id1
							+ " AND `auftrag.ID` = "
							+ id2
							+ " AND `mitarbeiter.ID` = " + id3 + ";");

			result = statement.executeQuery();
			if (result.next()) {
				return new Position(Verwaltung.getInstance().getDienstleistung(
						id1), Verwaltung.getInstance().getAuftrag(id2),
						Verwaltung.getInstance().getMitarbeiter(id3),
						result.getFloat(4), result.getDate(5),
						Enums.getAStatus(result.getString(6)));
			} else {
				System.out.println("No Position found");
				return null;
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void writePosition(Position p) {
		try {
			statement = conn
					.prepareStatement("DELETE FROM position WHERE `dienstleistung.ID` = "
							+ p.dienstleistung.getDienstleistungsID()
							+ " AND `auftrag.ID` = "
							+ p.auftrag.auftragID
							+ " AND `mitarbeiter.ID` = "
							+ p.mitarbeiter.mitarbeiterID + ";");
			statement.execute();
			statement = conn
					.prepareStatement(
							"INSERT INTO position (dienstleistung.ID, auftrag.ID, mitarbeiter.ID, Menge, Ausführungsdatum, Status"
									+ ") VALUES (?,?,?,?,?,?);",
							Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, p.dienstleistung.getDienstleistungsID());
			statement.setInt(2, p.auftrag.auftragID);
			statement.setInt(3, p.mitarbeiter.mitarbeiterID);
			statement.setFloat(4, p.positionMenge);
			statement.setDate(5, (Date) p.positionAusfuehrungsdatum);
			statement.setString(6, Enums.getAStatus(p.positionStatus));
			statement.execute();
			result = statement.getGeneratedKeys();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Position> getAllPosition() {
		List<Position> l = new ArrayList<Position>();
		List<Integer[]> helper = new ArrayList<Integer[]>();
		try {
			statement = conn.prepareStatement("SELECT * FROM position;");
			result = statement.executeQuery();
			while (result.next()) {
				Integer[] i = { result.getInt(1), result.getInt(2),
						result.getInt(3) };
				helper.add(i);
			}
			for (int i = 0; i < helper.size(); i++) {
				l.add(readPosition(helper.get(i)[0], helper.get(i)[1],
						helper.get(i)[2]));
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return l;

	}

	public Rechnung readRechnung(int id) {
		try {
			statement = conn
					.prepareStatement("SELECT * FROM rechnung WHERE id = " + id
							+ ";");
			result = statement.executeQuery();
			result.next();
			return new Rechnung(Verwaltung.getInstance().auftraggeber, id,
					result.getDate(4), Verwaltung.getInstance().getAuftrag(
							result.getInt(3)), result.getFloat(5),
					result.getDate(6), result.getString(7));
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void writeRechnung(Rechnung r) {
		try {
			statement = conn
					.prepareStatement("DELETE FROM rechnung WHERE id = "
							+ r.rechnungID + ";");
			statement.execute();
			statement = conn
					.prepareStatement(
							"INSERT INTO rechnung (ID, auftraggeber.ID, auftrag.ID, Datum, Preis, Zahlungsziel, Verwendungszweck"
									+ ") VALUES (?,?,?,?,?,?,?);",
							Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, r.rechnungID);
			statement.setInt(2, r.auftraggeber.auftraggeberID);
			statement.setInt(3, r.auftrag.auftragID);
			statement.setDate(4, (Date) r.rechnungDatum);
			statement.setFloat(5, r.rechnungPreis);
			statement.setDate(6, (Date) r.rechnungZahlungsziel);
			statement.setString(7, r.rechnungVerwendungszweck);
			statement.execute();
			result = statement.getGeneratedKeys();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Rechnung> getAllRechnung() {
		List<Rechnung> l = new ArrayList<Rechnung>();
		List<Integer> helper = new ArrayList<Integer>();
		try {
			statement = conn.prepareStatement("SELECT * FROM rechnung;");
			result = statement.executeQuery();
			while (result.next()) {
				helper.add(result.getInt(1));

			}
			for (int i = 0; i < helper.size(); i++) {
				l.add(readRechnung(helper.get(i)));
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return l;

	}

}
