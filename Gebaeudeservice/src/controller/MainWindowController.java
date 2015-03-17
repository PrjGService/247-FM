package controller;

import view.neo.MainWindowView;

public class MainWindowController {
	
	private static MainWindowController instance;
	private MainWindowView view;
	
	public static MainWindowController getInstance(){
		if(instance == null){
			MainWindowView view = new MainWindowView();
			instance = new MainWindowController(view);
		}
		return instance;
	}
	
	public MainWindowController(MainWindowView view){
		super();
		this.view = view;
		this.view.setVisible(true);
	}

}
