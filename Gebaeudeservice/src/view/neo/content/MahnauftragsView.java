package view.neo.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

import controller.Verwaltung;
import view.LayoutButton;

public class MahnauftragsView extends JXPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6053023156165973649L;

	JXPanel mainPanel;

	public MahnauftragsView() {
		super();
		initUI();
	}

	private void initUI() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());

		mainPanel = createMahnauftrag();
		this.add(mainPanel, BorderLayout.CENTER);

	}

	private JXPanel createMahnauftrag() {

		JXPanel mahnPanel = new JXPanel();
		mahnPanel.setLayout(new BorderLayout());

		Dimension d = new Dimension();
		d.setSize(100, 50);

		Dimension t = new Dimension();
		t.setSize(300, 30);

		JXLabel title = new JXLabel();
		title.setText("Mahnauftrag starten");
		title.setPreferredSize(d);
		title.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setForeground(Color.black);

		JXPanel header = new JXPanel();
		header.setBackground(Color.white);
		header.setLayout(new BorderLayout());
		header.add(title, BorderLayout.CENTER);

		final JXTextField verzwe = new JXTextField("Verwendungszweck");
		verzwe.setFont(new Font("Arial", Font.PLAIN, 16));
		verzwe.setForeground(Color.black);
		verzwe.setBackground(Color.WHITE);
		verzwe.setPreferredSize(t);

		LayoutButton button = new LayoutButton("Mahnauftrag starten");
		button.setFont(new Font("Arial", Font.PLAIN, 16));
		button.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		button.setPreferredSize(t);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (verzwe.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Bitte füllen Sie alle Felder aus.");
				} else {

					String eingabe = verzwe.getText();
					Verwaltung.getInstance().mahnen(eingabe);

					JOptionPane.showMessageDialog(null,
							"Mahnauftrag zur Rechnung mit dem Verwendungszweck "
									+ eingabe + " wurde abgeschickt!");

				}

			}
		});

		JXPanel fields = new JXPanel();
		fields.setBackground(Color.white);
		fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
		fields.add(verzwe);
		fields.add(Box.createVerticalStrut(15));
		fields.add(button);

		JXPanel toge = new JXPanel();
		toge.setBackground(Color.white);
		toge.add(fields);

		mahnPanel.add(header, BorderLayout.NORTH);
		mahnPanel.add(toge, BorderLayout.CENTER);

		return mahnPanel;

	}

}
