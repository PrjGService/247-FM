package view.neo.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Date;
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
import model.Auftraggeber;
import model.Rechnung;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTable;

import util.UIUtil;
import util.table.IRow;
import util.table.TableModel;
import view.neo.content.AuftragsUebersichtView.AuftragsModel;
import view.neo.content.AuftragsUebersichtView.AuftragsRow;
import view.neo.content.AuftragsUebersichtView.TableRowRenderer;
import controller.Verwaltung;
import database.DBManager;
import enums.Enums;

public class RechnungsUebersichtView extends JXPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6053023156165973649L;

	private JScrollPane mainTablePane;
	private RechModel tableModel;

	public RechnungsUebersichtView() {
		super();
		initUI();
	}

	private void initUI() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());

		JXTable mainTable = createRechTable();
		mainTablePane = new JScrollPane(mainTable);
		this.add(mainTablePane, BorderLayout.CENTER);

	}

	private JXTable createRechTable() {
		tableModel = new RechModel();
		JXTable rechTable = new JXTable(tableModel);
		rechTable.setFont(new Font("Arial", Font.PLAIN, 16));
		rechTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		rechTable.setDoubleBuffered(true);
		rechTable.setRowSelectionAllowed(true);
		rechTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rechTable.setShowGrid(true);
		rechTable.setFillsViewportHeight(false);
		rechTable.setDragEnabled(true);
		rechTable.setGridColor(Color.BLACK);
		rechTable.setSelectionBackground(UIUtil.getStandardColor());
		rechTable.setSelectionForeground(Color.WHITE);
		rechTable.setRowHeight(50);

		TableRowRenderer renderer = new TableRowRenderer(tableModel);

		rechTable.getColumnModel().getColumn(0).setPreferredWidth(25);

		rechTable.getTableHeader().resizeAndRepaint();
		JTableHeader header = rechTable.getTableHeader();
		header.setPreferredSize(new Dimension(100, 50));
		header.setFont(new Font("Arial", Font.BOLD, 16));
		rechTable.setTableHeader(header);
		rechTable.setDefaultRenderer(Object.class, renderer);
		rechTable.setDefaultRenderer(Long.class, renderer);
		rechTable.setDefaultRenderer(Float.class, renderer);

		List<Rechnung> r = Verwaltung.getInstance().rechnungList;
		for (Rechnung rechnung : r) {
			tableModel.addRow(new RechRow(rechnung.rechnungID, " "
					+ rechnung.auftraggeber.auftraggeberName, " "
					+ Enums.getAStatus(rechnung.auftrag.getAuftragstatus()),
					rechnung.rechnungPreis, rechnung.rechnungDatum.toString(),
					rechnung.rechnungZahlungsziel.toString()));
		}
		return rechTable;
	}

	public void addRechnung(long id, String auftraggeber,
			String rechnungsstatus, Float preis, String rechnungsdatum,
			String zahlungsziel) {
		tableModel.addRow(new RechRow(id, " " + auftraggeber, " " + rechnungsstatus, preis,
				rechnungsdatum, zahlungsziel));
		tableModel.fireTableDataChanged();
	}

	public void deleteRech(long id) {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			RechRow r = (RechRow) tableModel.getRow(i);
			if (r.getId() == id)
				tableModel.removeRow(i);
		}
		tableModel.fireTableDataChanged();
	}

	class TableRowRenderer extends JLabel implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		public TableRowRenderer(RechModel tableModel) {
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
				setHorizontalAlignment(SwingUtilities.CENTER);
				DecimalFormat df = new DecimalFormat("0.00");
				setText(String.valueOf(df.format((Float) value)));
				break;
			case 4:
				setHorizontalAlignment(SwingUtilities.CENTER);
				setText((String) value);
				break;
			case 5:
				setHorizontalAlignment(SwingUtilities.CENTER);
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

	static class RechModel extends TableModel {

		private static final long serialVersionUID = -5574999980921824807L;

		private static Class[] columnClass = { Long.class, String.class,
				String.class, Float.class, String.class, String.class };

		private static String[] columnNames = { "Rechnung-ID", "Auftraggeber",
				"Rechnungsstatus", "Preis", "Rechnungsdatum", "Zahlungsziel" };

		public RechModel() {
			super(columnClass, columnNames);
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			RechRow row = (RechRow) dataVector.elementAt(rowIndex);

			switch (columnIndex) {
			case 0:
				return row.getId();
			case 1:
				return row.getAuftraggeber();
			case 2:
				return row.getRechnungsstatus();
			case 3:
				return row.getPreis();
			case 4:
				return row.getRechnungsdatum();
			case 5:
				return row.getZahlungsziel();

			}
			return null;

		}

		public void setValue(Object value, int rowIndex, int columnIndex) {
			Vector<IRow> dataVector = null;
			RechRow row = (RechRow) dataVector.elementAt(rowIndex);

			switch (columnIndex) {
			case 0:
				row.setId((long) value);
			case 1:
				row.setAuftraggeber((String) value);
			case 2:
				row.setRechnungsstatus((String) value);
			case 3:
				row.setPreis((Float) value);
			case 4:
				row.setRechnungsdatum((String) value);
			case 5:
				row.setZahlungsziel((String) value);

				fireTableCellUpdated(rowIndex, columnIndex);
			}
		}
	}

	class RechRow implements IRow {

		private long id;
		private String auftraggeber;
		private String rechnungsstatus;
		private Float preis;
		private String rechnungsdatum;
		private String zahlungsziel;

		public RechRow(long id, String auftraggeber, String rechnungsstatus,
				Float preis, String rechnungsdatum, String zahlungsziel) {
			this.id = id;
			this.auftraggeber = auftraggeber;
			this.rechnungsstatus = rechnungsstatus;
			this.preis = preis;
			this.rechnungsdatum = rechnungsdatum;
			this.zahlungsziel = zahlungsziel;

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

		public String getRechnungsstatus() {
			return rechnungsstatus;
		}

		public void setRechnungsstatus(String rechnungsstatus) {
			this.rechnungsstatus = rechnungsstatus;
		}

		public Float getPreis() {
			return preis;
		}

		public void setPreis(Float preis) {
			this.preis = preis;
		}

		public String getRechnungsdatum() {
			return rechnungsdatum;
		}

		public void setRechnungsdatum(String rechnungsdatum) {
			this.rechnungsdatum = rechnungsdatum;
		}

		public String getZahlungsziel() {
			return zahlungsziel;
		}

		public void setZahlungsziel(String zahlungsziel) {
			this.zahlungsziel = zahlungsziel;
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
