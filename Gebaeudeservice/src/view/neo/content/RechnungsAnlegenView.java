package view.neo.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

public class RechnungsAnlegenView extends JXPanel {

	private static final long serialVersionUID = 6053023156165973649L;

	private JXPanel mainPanel;

	public RechnungsAnlegenView() {
		super();
		initUI();
	}

	private void initUI() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());

		mainPanel = createRechnungAnlegen();
		this.add(mainPanel, BorderLayout.CENTER);

	}

	private JXPanel createRechnungAnlegen() {

		JXPanel rechPanel = new JXPanel();
		rechPanel.setLayout(new BorderLayout());

		Dimension d = new Dimension();
		d.setSize(100, 50);

		Dimension t = new Dimension();
		t.setSize(300, 30);

		JXLabel title = new JXLabel();
		title.setText("Rechnung anlegen");
		title.setPreferredSize(d);
		title.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setForeground(Color.black);

		JXPanel header = new JXPanel();
		header.setBackground(Color.white);
		header.setLayout(new BorderLayout());
		header.add(title, BorderLayout.CENTER);

		JXPanel table = new JXPanel();
		table.setLayout(new GridLayout(6, 1));
		table.setBackground(Color.white);

		// Zeile Auftragsgeber ID

		JXLabel geberid = new JXLabel();
		geberid.setText("Auftragsgeber-ID: ");
		geberid.setFont(new Font("Arial", Font.PLAIN, 16));
		geberid.setForeground(Color.black);
		geberid.setPreferredSize(t);

		JXTextField textgeberid = new JXTextField("Auftragsgeber-ID");
		textgeberid.setFont(new Font("Arial", Font.PLAIN, 16));
		textgeberid.setForeground(Color.black);
		textgeberid.setBackground(Color.WHITE);
		textgeberid.setPreferredSize(t);

		// Zeile Auftrags ID

		JXLabel auftragsid = new JXLabel();
		auftragsid.setText("Auftrags-ID: ");
		auftragsid.setFont(new Font("Arial", Font.PLAIN, 16));
		auftragsid.setForeground(Color.black);
		auftragsid.setPreferredSize(t);

		JXTextField textauftragsid = new JXTextField("Auftrags-ID");
		textauftragsid.setFont(new Font("Arial", Font.PLAIN, 16));
		textauftragsid.setForeground(Color.black);
		textauftragsid.setBackground(Color.WHITE);
		textauftragsid.setPreferredSize(t);

		// Zeile Datum

		JXLabel datum = new JXLabel();
		datum.setText("Datum: ");
		datum.setFont(new Font("Arial", Font.PLAIN, 16));
		datum.setForeground(Color.black);
		datum.setPreferredSize(t);

		JXTextField textdatum = new JXTextField("Datum");
		textdatum.setFont(new Font("Arial", Font.PLAIN, 16));
		textdatum.setForeground(Color.black);
		textdatum.setBackground(Color.WHITE);
		textdatum.setPreferredSize(t);

		// Zeile Preis

		JXLabel preis = new JXLabel();
		preis.setText("Preis: ");
		preis.setFont(new Font("Arial", Font.PLAIN, 16));
		preis.setForeground(Color.black);
		preis.setPreferredSize(t);

		JXTextField textpreis = new JXTextField("Preis");
		textpreis.setFont(new Font("Arial", Font.PLAIN, 16));
		textpreis.setForeground(Color.black);
		textpreis.setBackground(Color.WHITE);
		textpreis.setPreferredSize(t);

		// Zeile Zahlungsziel

		JXLabel zahldat = new JXLabel();
		zahldat.setText("Zahlungsziel: ");
		zahldat.setFont(new Font("Arial", Font.PLAIN, 16));
		zahldat.setForeground(Color.black);
		zahldat.setPreferredSize(t);

		JXTextField textzahldat = new JXTextField("Zahlungsziel");
		textzahldat.setFont(new Font("Arial", Font.PLAIN, 16));
		textzahldat.setForeground(Color.black);
		textzahldat.setBackground(Color.WHITE);
		textzahldat.setPreferredSize(t);

		// Verwendungszweck

		JXLabel verzwe = new JXLabel();
		verzwe.setText("Verwendungszweck: ");
		verzwe.setFont(new Font("Arial", Font.PLAIN, 16));
		verzwe.setForeground(Color.black);
		verzwe.setPreferredSize(t);

		JXTextField textverzwe = new JXTextField("Verwendungszweck");
		textverzwe.setFont(new Font("Arial", Font.PLAIN, 16));
		textverzwe.setForeground(Color.black);
		textverzwe.setBackground(Color.WHITE);
		textverzwe.setPreferredSize(t);

		JXPanel fields = new JXPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
		fields.setBackground(Color.white);
		fields.add(textgeberid);
		fields.add(Box.createVerticalStrut(15));
		fields.add(textauftragsid);
		fields.add(Box.createVerticalStrut(15));
		fields.add(textpreis);
		fields.add(Box.createVerticalStrut(15));
		fields.add(textdatum);
		fields.add(Box.createVerticalStrut(15));
		fields.add(textzahldat);
		fields.add(Box.createVerticalStrut(15));
		fields.add(textverzwe);

		JXPanel toge = new JXPanel();
		toge.setBackground(Color.white);
		toge.setLayout(new FlowLayout());
		toge.add(fields);

		rechPanel.add(header, BorderLayout.NORTH);
		rechPanel.add(toge, BorderLayout.CENTER);

		return rechPanel;
	}
}
