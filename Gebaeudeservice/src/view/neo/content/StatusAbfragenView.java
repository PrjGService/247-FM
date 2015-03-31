package view.neo.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;

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
		statusPanel.setLayout(new GridLayout(2,4));
		
		JXLabel label1 = new JXLabel();
		label1.setText("Status abfragen");
		
		JXLabel label2 = new JXLabel();
		label2.setText("Auftrags-ID");
		
		statusPanel.add(label1);
		statusPanel.add(label2);
		
		return statusPanel;

	}
}
