package view.neo.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTable;

import util.UIUtil;
import util.table.IRow;
import util.table.TableModel;
import database.DBManager;

public class AuftragsUebersichtView extends JXPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8971106081242840060L;

	private JScrollPane mainTablePane;
	private AuftragsModel tableModel;
	private JXTable mainTable;

	public AuftragsUebersichtView() {
		super();
		initUI();
	}

	private void initUI() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());

		mainTable = createAuftragsTable();
		mainTablePane = new JScrollPane(mainTable);
		this.add(mainTablePane, BorderLayout.CENTER);
	}

	private JXTable createAuftragsTable() {
		tableModel = new AuftragsModel();
		JXTable auftragsTable = new JXTable(tableModel);
		auftragsTable.setFont(new Font("Arial", Font.PLAIN, 16));
		auftragsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		auftragsTable.setDoubleBuffered(true);
		auftragsTable.setRowSelectionAllowed(true);
		auftragsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		auftragsTable.setShowGrid(true);
		auftragsTable.setFillsViewportHeight(true);
		auftragsTable.setDragEnabled(false);
		auftragsTable.setGridColor(Color.BLACK);
		auftragsTable.setSelectionBackground(UIUtil.getStandardColor());
		auftragsTable.setSelectionForeground(Color.WHITE);
		
		TableRowRenderer renderer = new TableRowRenderer(tableModel);

		auftragsTable.getColumnModel().getColumn(0).setPreferredWidth(25);

		auftragsTable.getTableHeader().resizeAndRepaint();
		auftragsTable.setDefaultRenderer(Object.class, renderer);
		auftragsTable.setDefaultRenderer(Long.class, renderer);
		
		
		DBManager dbmanager = new DBManager();
		// List<Auftrag> l = dbmanager.getAllAuftrag();
		// for (Auftrag auftrag : l) {
<<<<<<< HEAD
		tableModel.addRow(new AuftragsRow(1, "SHIFT Gebï¿½udemanagement",
				"bezahlt", "12.01.2015", "Rasen mahen"));
		tableModel.addRow(new AuftragsRow(2, "SHIFT Gebï¿½udemanagement",
=======
		tableModel.addRow(new AuftragsRow(1, "SHIFT Gebäudemanagement",
				"bezahlt", "12.01.2015", "Rasen mï¿½hen"));
		tableModel.addRow(new AuftragsRow(2, "SHIFT Gebäudemanagement",
>>>>>>> branch 'master' of https://github.com/PrjGService/247-FM
				"bezahlt", "23.01.2015", "Reparatur"));
		tableModel.addRow(new AuftragsRow(3, "SHIFT Gebäudemanagement",
				"erledigt", "08.02.2015", "Gas"));
		tableModel.addRow(new AuftragsRow(4, "SHIFT Gebäudemanagement",
				"erledigt", "10.02.2015", "Wasser"));
		tableModel.addRow(new AuftragsRow(5, "SHIFT Gebäudemanagement",
				"offen", "26.03.2015", "Hecke schneiden"));
		tableModel.addRow(new AuftragsRow(6, "SHIFT Gebäudemanagement",
				"offen", "01.04.2015", "Treppenreinigung"));
		// }
		return auftragsTable;
	}

	public void addAuftrag(long id, String auftraggeber, String auftragsstatus,
			String auftragsdatum, String dienstleistung) {
		tableModel.addRow(new AuftragsRow(id, auftraggeber, auftragsstatus,
				auftragsdatum, dienstleistung));
		tableModel.fireTableDataChanged();
	}

	public void deleteAuftrag(long id) {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			AuftragsRow r = (AuftragsRow) tableModel.getRow(i);
			if (r.getId() == id)
				tableModel.removeRow(i);
		}
		tableModel.fireTableDataChanged();
	}

	class TableRowRenderer extends JLabel implements TableCellRenderer {

		private static final long serialVersionUID = 1L;
		private AuftragsModel tableModel;

		public TableRowRenderer(AuftragsModel tableModel) {
			this.tableModel = tableModel;
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int col) {

			setFont(new Font("Arial", Font.PLAIN, 14));
			setHorizontalAlignment(SwingUtilities.CENTER);
			setIcon(null);

			if (hasFocus || isSelected) {
				setBackground(UIUtil.getStandardColor());
				setForeground(Color.WHITE);
			} else {
				if (row % 2 == 0)
					setBackground(UIUtil.getSecondColor());
				else
					setBackground(Color.WHITE);
				setForeground(Color.BLACK);
			}

			switch (col) {
			case 0:
				setHorizontalAlignment(SwingUtilities.LEFT);
				setHorizontalTextPosition(SwingUtilities.LEFT);
				setText(" " + String.valueOf((long) value));
				System.out.println("test");
				break;
			case 1:
				setHorizontalAlignment(SwingUtilities.LEFT);
				setText((String) value);
				break;
			case 2:
				setHorizontalAlignment(SwingUtilities.LEFT);
				setText((String) value);
				break;
			case 3:
				setHorizontalAlignment(SwingUtilities.LEFT);
				setText((String) value);
				break;
			case 4:
				setHorizontalAlignment(SwingUtilities.LEFT);
				setText((String) value);
				break;

			default:
				System.out.println("Spalte nicht gefunden und Ã¼bersprungen: " + col);
				break;
			}
			return this;
		}
	}

	static class AuftragsModel extends TableModel {

		private static final long serialVersionUID = -5574999980921824807L;

		private static Class[] columnClass = { Long.class, String.class,
				String.class, String.class, String.class, };

		private static String[] columnNames = { "Auftrags-ID", "Auftraggeber",
				"Auftragsstatus", "Auftragsdatum", "Dienstleistung" };

		public AuftragsModel() {
			super(columnClass, columnNames);
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			AuftragsRow row = (AuftragsRow) dataVector.elementAt(rowIndex);

			switch (columnIndex) {
			case 0:
				return row.getId();
			case 1:
				return row.getAuftraggeber();
			case 2:
				return row.getAuftragsstatus();
			case 3:
				return row.getAuftragsdatum();
			case 4:
				return row.getDienstleistung();
			}
			return null;
		}

		public void setValue(Object value, int rowIndex, int columnIndex) {
			AuftragsRow row = (AuftragsRow) dataVector.elementAt(rowIndex);

			switch (columnIndex) {
			case 0:
				row.setId((long) value);
			case 1:
				row.setAuftraggeber((String) value);
			case 2:
				row.setAuftragsstatus((String) value);
			case 3:
				row.setAuftragsdatum((String) value);
			case 4:
				row.setDienstleistung((String) value);

				fireTableCellUpdated(rowIndex, columnIndex);
			}
		}
	}

	class AuftragsRow implements IRow {

		private long id;
		private String auftraggeber;
		private String auftragsstatus;
		private String auftragsdatum;
		private String dienstleistung;

		public AuftragsRow(long id, String auftraggeber, String auftragsstatus,
				String auftragsdatum, String dienstleistung) {
			this.id = id;
			this.auftraggeber = auftraggeber;
			this.auftragsstatus = auftragsstatus;
			this.auftragsdatum = auftragsdatum;
			this.dienstleistung = dienstleistung;

		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getAuftraggeber() {
			return auftraggeber;
		}

		public void setAuftraggeber(String auftraggeber) {
			this.auftraggeber = auftraggeber;
		}

		public String getAuftragsstatus() {
			return auftragsstatus;
		}

		public void setAuftragsstatus(String auftragsstatus) {
			this.auftragsstatus = auftragsstatus;
		}

		public String getAuftragsdatum() {
			return auftragsdatum;
		}

		public void setAuftragsdatum(String auftragsdatum) {
			this.auftragsdatum = auftragsdatum;
		}

		public String getDienstleistung() {
			return dienstleistung;
		}

		public void setDienstleistung(String dienstleistung) {
			this.dienstleistung = dienstleistung;
		}

		@Override
		public IRow copyRow() {
			return null;
		}

		@Override
		public Vector<Object> toVector() {
			return null;
		}

	}

}
