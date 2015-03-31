package view.neo.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

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
		statusPanel.setLayout(new GridLayout(6, 2));

		JXLabel label1 = new JXLabel();
		label1.setText("Auftrags-ID");
		label1.setFont(new Font("Arial", Font.PLAIN, 20));
		label1.setForeground(new Color(19, 123, 64));
		label1.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		
		JXTextField auftragid = new JXTextField();
		auftragid.setFont(new Font("Arial", Font.PLAIN, 20));
		auftragid.setForeground(new Color(19, 123, 64));
		auftragid.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		//Feld auf normale Größe verkleinern + Action Listener bei ENTER!
		

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

		statusPanel.add(label1);
		statusPanel.add(auftragid);
		statusPanel.add(label2);
		statusPanel.add(label3);
		statusPanel.add(label4);
		statusPanel.add(label5);
		statusPanel.add(label6);
		statusPanel.add(label7);
		statusPanel.add(label8);
		statusPanel.add(label9);
		statusPanel.add(label10);
		statusPanel.add(label11);
	

		return statusPanel;

	}
}
