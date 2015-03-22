package view.neo.content;

import java.awt.BorderLayout;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;

public class ImpressumView extends JXPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6053023156165973649L;
	
	public ImpressumView(){
		super();
		initUI();
	}

	private void initUI() {
		this.setOpaque(false);
		
		JXPanel mainpanel = createJXPanel();
		this.add(mainpanel, BorderLayout.CENTER);
		
		
	}	
	 public JXPanel createJXPanel(){
		 JXPanel panel1 = new JXPanel();
		 
		 
		return panel1;
		 
	 }
	
	
	
	
	
	

}
