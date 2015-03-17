package main;

import javax.swing.UIManager;

import controller.LoginWindowController;

public class Application {
	
	public static void main (String args[]){
		
		 try {
	            // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    }  catch (Exception e){
	    	
	    }
		
		LoginWindowController.getInstance();
		
	}

}
