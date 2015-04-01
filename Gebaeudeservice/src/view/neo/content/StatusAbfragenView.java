package view.neo.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

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
		d.setSize(1000, 100);

		Dimension t = new Dimension();
		t.setSize(2000, 300);

		JXLabel title = new JXLabel();
		title.setText("Auftragsstatus abfragen");
		title.setPreferredSize(d);
		title.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		title.setForeground(Color.black);

		JXPanel table = new JXPanel();
		table.setLayout(new GridLayout(6, 2));

		JXLabel label1 = new JXLabel();
		label1.setText("Auftrags-ID");
		label1.setFont(new Font("Arial", Font.PLAIN, 20));
		label1.setForeground(new Color(19, 123, 64));
		label1.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXTextField auftragid = new JXTextField();
		auftragid.setFont(new Font("Arial", Font.PLAIN, 20));
		auftragid.setForeground(new Color(19, 123, 64));
		auftragid.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		
		JXLabel label2 = new JXLabel();
		label2.setText("Abgelehnt");
		label2.setFont(new Font("Arial", Font.PLAIN, 20));
		label2.setForeground(new Color(19, 123, 64));
		label2.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label3 = new JXLabel();
		label3.setText("BILDCHEN");
		label3.setFont(new Font("Arial", Font.PLAIN, 20));
		label3.setForeground(new Color(19, 123, 64));
		label3.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label4 = new JXLabel();
		label4.setText("Angenommen");
		label4.setFont(new Font("Arial", Font.PLAIN, 20));
		label4.setForeground(new Color(19, 123, 64));
		label4.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label5 = new JXLabel();
		label5.setText("BILDCHEN");
		label5.setFont(new Font("Arial", Font.PLAIN, 20));
		label5.setForeground(new Color(19, 123, 64));
		label5.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label6 = new JXLabel();
		label6.setText("In Arbeit");
		label6.setFont(new Font("Arial", Font.PLAIN, 20));
		label6.setForeground(new Color(19, 123, 64));
		label6.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label7 = new JXLabel();
		label7.setText("BILDCHEN");
		label7.setFont(new Font("Arial", Font.PLAIN, 20));
		label7.setForeground(new Color(19, 123, 64));
		label7.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label8 = new JXLabel();
		label8.setText("Erledigt");
		label8.setFont(new Font("Arial", Font.PLAIN, 20));
		label8.setForeground(new Color(19, 123, 64));
		label8.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label9 = new JXLabel();
		label9.setText("BILDCHEN");
		label9.setFont(new Font("Arial", Font.PLAIN, 20));
		label9.setForeground(new Color(19, 123, 64));
		label9.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label10 = new JXLabel();
		label10.setText("Bezahlt");
		label10.setFont(new Font("Arial", Font.PLAIN, 20));
		label10.setForeground(new Color(19, 123, 64));
		label10.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		JXLabel label11 = new JXLabel();
		label11.setText("BILDCHEN");
		label11.setFont(new Font("Arial", Font.PLAIN, 20));
		label11.setForeground(new Color(19, 123, 64));
		label11.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		table.add(label1);
		table.add(auftragid);
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

		statusPanel.add(title, BorderLayout.NORTH);
		statusPanel.add(table, BorderLayout.CENTER);
		statusPanel.add(button, BorderLayout.SOUTH);

		return statusPanel;

	}
}
