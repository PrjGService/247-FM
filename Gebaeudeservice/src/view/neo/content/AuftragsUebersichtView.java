package view.neo.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import model.Auftrag;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTable;

import controller.Verwaltung;
import util.UIUtil;
import util.table.IRow;
import util.table.TableModel;
import database.DBManager;
import enums.Enums;

public class AuftragsUebersichtView extends JXPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8971106081242840060L;

	private JScrollPane mainTablePane;
	private AuftragsModel tableModel;
	private JXTable mainTable;
	int i;

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
		auftragsTable.setFillsViewportHeight(false);
		auftragsTable.setDragEnabled(true);
		auftragsTable.setGridColor(Color.BLACK);
		auftragsTable.setSelectionBackground(UIUtil.getStandardColor());
		auftragsTable.setSelectionForeground(Color.WHITE);
		auftragsTable.setRowHeight(50);

		TableRowRenderer renderer = new TableRowRenderer(tableModel);

		auftragsTable.getColumnModel().getColumn(0).setPreferredWidth(25);

		auftragsTable.getTableHeader().resizeAndRepaint();
		JTableHeader header = auftragsTable.getTableHeader();
		header.setPreferredSize(new Dimension(100, 50));
		header.setFont(new Font("Arial", Font.BOLD, 16));
		auftragsTable.setTableHeader(header);
		auftragsTable.setDefaultRenderer(Object.class, renderer);
		auftragsTable.setDefaultRenderer(Long.class, renderer);

		List<Auftrag> l = Verwaltung.getInstance().auftragList;
		for (Auftrag auftrag : l) {
			String s = "";
			try{
			s = auftrag.getPositionen().get(0).dienstleistung.dienstleistungsName;
			}
			catch(Exception ex)
			{
				System.err.println("konnte eine Position nicht finden");
			}
			tableModel
					.addRow(new AuftragsRow(
							auftrag.getAuftragID(),
							" " + auftrag.getAuftraggeber().auftraggeberName,
							" " + Enums.getAStatus(auftrag.getAuftragstatus()),
							" " + auftrag.auftragdatum.toString(),
							" "
									+ s));
			
		}
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

			setFont(new Font("Arial", Font.PLAIN, 15));
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
				setFont(new Font("ARIAL", Font.BOLD, 15));
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
				System.out.println("Spalte nicht gefunden und �bersprungen: "
						+ col);
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
