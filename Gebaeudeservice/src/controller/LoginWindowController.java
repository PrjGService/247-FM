package controller;

import view.neo.LoginWindowView;

public class LoginWindowController {
	
	private static LoginWindowController instance;
	private LoginWindowView view;
	
	public static LoginWindowController getInstance(){
		if(instance == null){
			LoginWindowView view = new LoginWindowView();
			instance = new LoginWindowController(view);
		}
		return instance;
	}
	
	public LoginWindowController(LoginWindowView view) {
		super();
		this.view = view;
		this.view.setVisible(true);
	}
	
	public void handleLogin(String user, String password){
		
		// do something with login incrementals.
	
		MainWindowController.getInstance();
	}
}
