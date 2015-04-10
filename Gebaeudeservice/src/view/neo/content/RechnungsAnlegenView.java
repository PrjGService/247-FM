package view.neo.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import model.Rechnung;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

import controller.MainWindowController;
import controller.Verwaltung;
import database.DBManager;
import view.LayoutButton;

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

		final JXTextField textgeberid = new JXTextField("Auftragsgeber-ID");
		textgeberid.setFont(new Font("Arial", Font.PLAIN, 16));
		textgeberid.setForeground(Color.black);
		textgeberid.setBackground(Color.WHITE);
		textgeberid.setPreferredSize(t);
		textgeberid.setText("1");

		// Zeile Auftrags ID

		final JXTextField textauftragsid = new JXTextField("Auftrags-ID");
		textauftragsid.setFont(new Font("Arial", Font.PLAIN, 16));
		textauftragsid.setForeground(Color.black);
		textauftragsid.setBackground(Color.WHITE);
		textauftragsid.setPreferredSize(t);

		// Zeile Datum

		final JXTextField textdatum = new JXTextField("Datum");
		textdatum.setFont(new Font("Arial", Font.PLAIN, 16));
		textdatum.setForeground(Color.black);
		textdatum.setBackground(Color.WHITE);
		textdatum.setPreferredSize(t);
		if(Verwaltung.getInstance().tag != null)
		{
			textdatum.setText(Verwaltung.getInstance().tag.toLocaleString());
		}
		else
		{
			textdatum.setText((new Date()).toLocaleString());
		}

		// Zeile Preis

		final JXTextField textpreis = new JXTextField("Preis");
		textpreis.setFont(new Font("Arial", Font.PLAIN, 16));
		textpreis.setForeground(Color.black);
		textpreis.setBackground(Color.WHITE);
		textpreis.setPreferredSize(t);

		// Zeile Zahlungsziel

		final JXTextField textzahldat = new JXTextField("Zahlungsziel");
		textzahldat.setFont(new Font("Arial", Font.PLAIN, 16));
		textzahldat.setForeground(Color.black);
		textzahldat.setBackground(Color.WHITE);
		textzahldat.setPreferredSize(t);
		if(Verwaltung.getInstance().tag != null)
		{
			textzahldat.setText(Verwaltung.getInstance().tag.toLocaleString());
		}
		else
		{
			textzahldat.setText((new Date()).toLocaleString());
		}

		// Verwendungszweck

		final JXTextField textverzwe = new JXTextField("Verwendungszweck");
		textverzwe.setFont(new Font("Arial", Font.PLAIN, 16));
		textverzwe.setForeground(Color.black);
		textverzwe.setBackground(Color.WHITE);
		textverzwe.setPreferredSize(t);

		// Button

		LayoutButton button = new LayoutButton("Neue Rechnung anlegen!");
		button.setFont(new Font("Arial", Font.PLAIN, 16));
		button.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		button.setPreferredSize(t);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (textgeberid.getText().isEmpty()
						|| textauftragsid.getText().isEmpty()
						|| textpreis.getText().isEmpty()
						|| textdatum.getText().isEmpty()
						|| textzahldat.getText().isEmpty()
						|| textverzwe.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Bitte f�llen Sie alle Felder aus.");
				} else {

					// BITTE HIER LOGIK EINBAUEN!
					String geber = textgeberid.getText();
					int realgeber = Integer.parseInt(geber);
					String auftrag = textauftragsid.getText();
					int realauftrag = Integer.parseInt(auftrag);
					String preis = textpreis.getText();
					float realpreis = Float.parseFloat(preis);
					String datum = textdatum.getText();
					Date realdate = new Date();
					Date realzahl = new Date();
					try {
						realdate = DateFormat.getInstance().parse(datum);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						System.err.println("Kann nicht in Datum umwandeln");
						e1.printStackTrace();
					}
					String zahldat = textzahldat.getText();
					try {
						realzahl = DateFormat.getInstance().parse(zahldat);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						System.err.println("Kann nicht in Datum umwandeln");
						e1.printStackTrace();
					}

					String verzwe = textverzwe.getText();
					Rechnung r = new Rechnung(
							Verwaltung.getInstance().auftraggeber, realauftrag,
							realdate, Verwaltung.getInstance().getAuftrag(
									realauftrag), realpreis, realzahl, verzwe);
					Verwaltung.getInstance().rechnungList.add(r);
					System.out.println("Rechnung angelegt");
					MainWindowController.getInstance().addOrChangeRechnung(r);
					Verwaltung.getInstance().conn.writeRechnung(r);

				}
			}
		});

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
		fields.add(Box.createVerticalStrut(15));
		fields.add(button);

		JXPanel toge = new JXPanel();
		toge.setBackground(Color.white);
		toge.add(fields);

		rechPanel.add(header, BorderLayout.NORTH);
		rechPanel.add(toge, BorderLayout.CENTER);

		return rechPanel;
	}
}
