package view.neo.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

import util.UIUtil;
import view.LayoutButton;

public class StatusAbfragenView extends JXPanel {

	private static final long serialVersionUID = 5095540863501946587L;

	private JXPanel mainPanel;

	public StatusAbfragenView() {
		super();
		initUI();
	}

	private void initUI() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());

		mainPanel = createStatusAbfragen();
		this.add(mainPanel, BorderLayout.CENTER);

	}

	private JXPanel createStatusAbfragen() {

		JXPanel statusPanel = new JXPanel();
		statusPanel.setLayout(new BorderLayout());

		Dimension d = new Dimension();
		d.setSize(100, 50);

		Dimension t = new Dimension();
		t.setSize(150, 30);

		JXLabel title = new JXLabel();
		title.setText("Auftragsstatus abfragen");
		title.setPreferredSize(d);
		title.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setForeground(Color.black);

		JXLabel tfname = new JXLabel();
		tfname.setText("Bitte Auftrags-ID eingeben:  ");
		tfname.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		tfname.setFont(new Font("Arial", Font.PLAIN, 16));
		tfname.setForeground(Color.black);

		JTextField auftragsid = new JTextField();
		auftragsid.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		auftragsid.setFont(new Font("Arial", Font.PLAIN, 16));
		auftragsid.setForeground(Color.black);
		auftragsid.setBackground(Color.WHITE);
		auftragsid.setPreferredSize(t);

		JXPanel id = new JXPanel();
		id.setLayout(new FlowLayout());
		id.setPreferredSize(d);
		id.add(tfname);
		id.add(auftragsid);
		id.setBackground(Color.white);

		JXPanel north = new JXPanel();
		north.setLayout(new BorderLayout());
		north.setBackground(Color.white);

		north.add(title, BorderLayout.NORTH);
		north.add(id, BorderLayout.SOUTH);

		Icon icon1 = new ImageIcon(new ImageIcon(("res/empty40.png"))
				.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
		Icon icon2 = new ImageIcon(new ImageIcon(("res/checked18.png"))
				.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));

		JXPanel table = new JXPanel();
		table.setLayout(new GridLayout(5, 2));
		table.setBackground(Color.white);

		JXLabel label2 = new JXLabel();
		label2.setText("                         Abgelehnt");
		label2.setFont(new Font("Arial", Font.PLAIN, 15));
		label2.setForeground(Color.black);

		JXLabel label3 = new JXLabel();
		label3.setIcon(icon2);
		label3.setFont(new Font("Arial", Font.PLAIN, 15));
		label3.setForeground(Color.black);
		label3.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label4 = new JXLabel();
		label4.setText("                         Angenommen");
		label4.setFont(new Font("Arial", Font.PLAIN, 15));
		label4.setForeground(Color.black);
		label4.setBackground(Color.white);

		JXLabel label5 = new JXLabel();
		label5.setIcon(icon1);
		label5.setFont(new Font("Arial", Font.PLAIN, 15));
		label5.setForeground(Color.black);
		label5.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		label5.setBackground(Color.white);

		JXLabel label6 = new JXLabel();
		label6.setText("                         In Arbeit");
		label6.setFont(new Font("Arial", Font.PLAIN, 15));
		label6.setForeground(Color.black);

		JXLabel label7 = new JXLabel();
		label7.setIcon(icon1);
		label7.setFont(new Font("Arial", Font.PLAIN, 15));
		label7.setForeground(Color.black);
		label7.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label8 = new JXLabel();
		label8.setText("                         Erledigt");
		label8.setFont(new Font("Arial", Font.PLAIN, 15));
		label8.setForeground(Color.black);

		JXLabel label9 = new JXLabel();
		label9.setIcon(icon1);
		label9.setFont(new Font("Arial", Font.PLAIN, 20));
		label9.setForeground(new Color(19, 123, 64));
		label9.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label10 = new JXLabel();
		label10.setText("                         Bezahlt");
		label10.setFont(new Font("Arial", Font.PLAIN, 15));
		label10.setForeground(Color.black);

		JXLabel label11 = new JXLabel();
		label11.setIcon(icon1);
		label11.setFont(new Font("Arial", Font.PLAIN, 15));
		label11.setForeground(new Color(19, 123, 64));
		label11.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		// table.add(label1);
		// table.add(text);
		table.add(label2);
		table.add(label3);
		table.add(label4);
		table.add(label5);
		table.add(label6);
		table.add(label7);
		table.add(label8);
		table.add(label9);
		table.add(label10);
		table.add(label11);

		LayoutButton button = new LayoutButton("OK");
		button.setPreferredSize(t);
		
		

		statusPanel.add(north, BorderLayout.BEFORE_FIRST_LINE);
		statusPanel.add(table, BorderLayout.CENTER);
		statusPanel.add(button, BorderLayout.SOUTH);

		return statusPanel;

	}
}
