package view.neo.content;

import java.awt.BorderLayout;

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
		statusPanel.setLayout(new BorderLayout());
		
		JXLabel label1 = new JXLabel();
		label1.setText("Hallo");
		label1.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		
		statusPanel.add(label1, BorderLayout.CENTER);
		
		return statusPanel;

	}
}
