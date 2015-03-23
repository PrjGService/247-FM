package view.neo.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import javax.swing.table.TableCellRenderer;

import model.Mitarbeiter;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTable;

import com.mysql.jdbc.RowDataDynamic;

import util.UIUtil;
import util.table.IRow;
import util.table.TableModel;
import view.neo.content.AuftragsUebersichtView.AuftragsModel;
import view.neo.content.AuftragsUebersichtView.AuftragsRow;
import view.neo.content.AuftragsUebersichtView.TableRowRenderer;
import database.DBManager;
import enums.Enums.Mitarbeiterstatus;

public class MitarbeiterView extends JXPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2270308353357370768L;

	private JScrollPane mainTablePane;
	private MitarbeiterModel tableModel;
	private JXTable mainTable;

	public MitarbeiterView() {
		super();
		initUI();
	}

	private void initUI() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());

		mainTable = createMitarbeiterTable();
		mainTablePane = new JScrollPane(mainTable);
		this.add(mainTablePane, BorderLayout.CENTER);

	}

	private JXTable createMitarbeiterTable() {
		tableModel = new MitarbeiterModel();
		JXTable mitarbeiterTable = new JXTable(tableModel);
		mitarbeiterTable.setFont(new Font("Arial", Font.PLAIN, 16));
		mitarbeiterTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		mitarbeiterTable.setDoubleBuffered(true);
		mitarbeiterTable.setRowSelectionAllowed(true);
		mitarbeiterTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mitarbeiterTable.setShowGrid(true);
		mitarbeiterTable.setFillsViewportHeight(true);
		mitarbeiterTable.setDragEnabled(false);
		mitarbeiterTable.setGridColor(Color.BLACK);
		mitarbeiterTable.setSelectionBackground(UIUtil.getStandardColor());
		mitarbeiterTable.setSelectionForeground(Color.WHITE);

		TableRowRenderer renderer = new TableRowRenderer(tableModel);

		mitarbeiterTable.getColumnModel().getColumn(0).setPreferredWidth(25);

		mitarbeiterTable.getTableHeader().resizeAndRepaint();
		mitarbeiterTable.setDefaultRenderer(Object.class, renderer);
		mitarbeiterTable.setDefaultRenderer(Long.class, renderer);

		DBManager dbmanager = new DBManager();
		List<Mitarbeiter> m = dbmanager.getAllMitarbeiter();
		for (Mitarbeiter mitarbeiter : m) {
			IRow MitarbeiterRow;
			tableModel.addRow(new MitarbeiterRow(
					mitarbeiter.getMitarbeiterID(), mitarbeiter
							.getMitarbeiterName(), mitarbeiter
							.getMitarbeiterStatus()));
		}

		return mitarbeiterTable;
	}

	public void addMitarbeiter(long id, String name, Mitarbeiterstatus status) {
		tableModel.addRow(new MitarbeiterRow(id, name, status));
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
		private MitarbeiterModel tableModel;

		public TableRowRenderer(MitarbeiterModel tableModel) {
			this.tableModel = tableModel;
			setOpaque(true);
		}

		Icon icon1 = new ImageIcon(new ImageIcon(("res/attention1.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		Icon icon2 = new ImageIcon(new ImageIcon(("res/constructor2.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		Icon icon3 = new ImageIcon(new ImageIcon(("res/construction3.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

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
				break;
			case 1:
				setHorizontalAlignment(SwingUtilities.LEFT);
				setText((String) value);
				break;
			case 2:
				setHorizontalAlignment(SwingUtilities.CENTER);
				Mitarbeiterstatus status = (Mitarbeiterstatus) value;
				if (status != null) {
					if (status.ordinal() == Mitarbeiterstatus.ARBEITET
							.ordinal()) {
						setIcon(icon3);
						setText("   Arbeitet.");
						break;
					} else if (status.ordinal() == Mitarbeiterstatus.VERFUEGBAR
							.ordinal()) {
						setIcon(icon2);
						setText("   Verfügbar.");
						break;
					} else if (status.ordinal() == Mitarbeiterstatus.UNVERFUEGBAR
							.ordinal()) {
						setIcon(icon1);
						setText("   Unverfügbar.");
						break;
					}
				} else {
					setIcon(icon1);
					setText("   Unverfügbar   ");
				}
				break;

			default:
				System.out.println("Spalte nicht gefunden und übersprungen: "
						+ col);
				break;
			}
			return this;
		}
	}

	static class MitarbeiterModel extends TableModel {

		private static final long serialVersionUID = -5574999980921824807L;

		private static Class[] columnClass = { Long.class, String.class,
				String.class };

		private static String[] columnNames = { "Mitarbeiter-ID", "Name",
				"Status" };

		public MitarbeiterModel() {
			super(columnClass, columnNames);
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			MitarbeiterRow row = (MitarbeiterRow) dataVector
					.elementAt(rowIndex);

			switch (columnIndex) {
			case 0:
				return row.getId();
			case 1:
				return row.getName();
			case 2:
				return row.getStatus();

			}
			return null;
		}

		public void setValue(Object value, int rowIndex, int columnIndex) {
			MitarbeiterRow row = (MitarbeiterRow) dataVector
					.elementAt(rowIndex);

			switch (columnIndex) {
			case 0:
				row.setId((long) value);
			case 1:
				row.setName((String) value);
			case 2:
				row.setStatus((Mitarbeiterstatus) value);

				fireTableCellUpdated(rowIndex, columnIndex);
			}
		}
	}

	class MitarbeiterRow implements IRow {

		private long id;
		private String name;
		private Mitarbeiterstatus status;

		public MitarbeiterRow(long id, String name, Mitarbeiterstatus status) {
			this.id = id;
			this.name = name;
			this.status = status;

		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Mitarbeiterstatus getStatus() {
			return status;
		}

		public void setStatus(Mitarbeiterstatus status) {
			this.status = status;
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
