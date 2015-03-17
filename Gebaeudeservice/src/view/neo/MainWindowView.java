package view.neo;

import java.awt.Toolkit;

import org.jdesktop.swingx.JXFrame;

public class MainWindowView extends JXFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8730460158991620638L;
	
	public MainWindowView(){
		initUI();
	}

	private void initUI() {
		this.setTitle("Gebäudeservice");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
}
