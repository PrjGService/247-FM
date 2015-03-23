package view;

import javax.swing.UIManager;

public class Launcher {

	public static void main(String[] args) {
		
		 try {
	            // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    }  catch (Exception e){
	    	
	    }

		System.out.println("24/7 FM started.");
		new WelcomePage();

	}

}
