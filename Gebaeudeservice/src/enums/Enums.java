package enums;


public class Enums {
	
public enum Auftragsstatus {
	ANGEKOMMEN,INARBEIT,ERLEDIGT,ABGELEHNT,BEZAHLT
}

public enum Faktortyp {
	MATERIALPREIS,ANZAHLSTUNDE,LAENGE,ANZAHLMONAT,ANZAHLWOHNUNG,MATERIALKOSTEN,ANZAHLEINSATZ
}

public enum Mitarbeiterstatus {
	VERFUEGBAR,ARBEITET,UNVERFUEGBAR
}

public static String getFaktortyp(Faktortyp f)
{
	String erg = null;
	if(f == Faktortyp.MATERIALPREIS)
	{
		erg = "Materialpreis";
	}
	if(f == Faktortyp.MATERIALKOSTEN)
	{
		erg = "Stockwerke";
	}
	if(f == Faktortyp.LAENGE)
	{
		erg = "Länge";
	}
	if(f == Faktortyp.ANZAHLWOHNUNG)
	{
		erg = "Wohnungen";
	}
	if(f == Faktortyp.ANZAHLSTUNDE)
	{
		erg = "Stunden";
	}
	if(f == Faktortyp.ANZAHLMONAT)
	{
		erg = "Anzahlmonat";
	}
	if(f == Faktortyp.ANZAHLEINSATZ)
	{
		erg = "Standard";
	}
	return erg;
}

public static Mitarbeiterstatus getMStatus(String s)
{
	Mitarbeiterstatus erg = null;
	if(s == "Verfügbar")
	{
		erg = Mitarbeiterstatus.VERFUEGBAR;
	}
	if(s == "Arbeitet")
	{
		erg = Mitarbeiterstatus.ARBEITET;
	}
	if(s == "Unverfügbar")
	{
		erg = Mitarbeiterstatus.UNVERFUEGBAR;
	}
	return erg;
}

public static String getMStatus(Mitarbeiterstatus m)
{
	String erg = null;
	if(m == Mitarbeiterstatus.VERFUEGBAR)
	{
		erg = "Verfügbar";
	}
	if(m == Mitarbeiterstatus.ARBEITET)
	{
		erg = "Arbeitet";
	}
	if(m == Mitarbeiterstatus.UNVERFUEGBAR)
	{
		erg = "Unverfügbar";
	}
	return erg;
}

public static Auftragsstatus getAStatus(String s)
{
	Auftragsstatus erg = null;
	if(s == "Angekommen")
	{
		erg = Auftragsstatus.ANGEKOMMEN;
	}
	if(s == "In Arbeit")
	{
		erg = Auftragsstatus.INARBEIT;
	}
	if(s == "Erledigt")
	{
		erg = Auftragsstatus.ERLEDIGT;
	}
	if(s == "Abgelehnt")
	{
		erg = Auftragsstatus.ABGELEHNT;
	}
	if(s == "Bezahlt")
	{
		erg = Auftragsstatus.BEZAHLT;
	}
	return erg;
}

public static String getAStatus(Auftragsstatus a)
{
	String erg = null;
	if(a == Auftragsstatus.ANGEKOMMEN)
	{
		erg = "Angekommen";
	}
	if(a == Auftragsstatus.INARBEIT)
	{
		erg = "In Arbeit";
	}
	if(a == Auftragsstatus.ERLEDIGT)
	{
		erg = "Erledigt";
	}
	if(a == Auftragsstatus.ABGELEHNT)
	{
		erg = "Abgelehnt";
	}
	if(a == Auftragsstatus.BEZAHLT)
	{
		erg = "Bezahlt";
	}
	return erg;
}

}