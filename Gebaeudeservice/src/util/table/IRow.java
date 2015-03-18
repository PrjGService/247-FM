package util.table;

import java.util.Vector;

public interface IRow {
	public IRow copyRow();
	public Vector<Object> toVector();
}
