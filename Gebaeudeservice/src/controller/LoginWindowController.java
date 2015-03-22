package controller;

import view.WelcomePage;

public class LoginWindowController {

	private static LoginWindowController instance;
	private WelcomePage view;

	public static LoginWindowController getInstance() {
		if (instance == null) {
			WelcomePage view = new WelcomePage();
			instance = new LoginWindowController(view);
		}
		return instance;
	}

	public LoginWindowController(WelcomePage view) {
		super();
		this.view = view;
		this.view.setVisible(true);
	}

	public void handleLogin(String user, String password) {
		if (Verwaltung.getInstance().login(user, password)) {
			MainWindowController.getInstance();
			view.dispose();
		}else{
			view.showError();
		}
	}
}
