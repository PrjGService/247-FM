package view.neo.content;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;

public class ImpressumView extends JXPanel {

	private static final long serialVersionUID = 6053023156165973649L;
	
	JXPanel mainpanel;

	public ImpressumView() {
		super();
		initUI();
	}

	private void initUI() {
		this.setOpaque(false);

		mainpanel = createJXPanel();
		this.add(mainpanel, BorderLayout.CENTER);

	}

	public JXPanel createJXPanel() {
		JXPanel impress = new JXPanel();
		impress.add(new JXLabel(new ImageIcon("res/gebaeudeservice.jpg")), BorderLayout.CENTER);
		
	

		return impress;

	}

}
