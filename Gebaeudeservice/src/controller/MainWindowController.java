package controller;

import model.Auftrag;
import model.Mitarbeiter;
import model.Rechnung;
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
	
	public void addOrChangeAuftrag(Auftrag auftrag){
		view.addOrChangeAuftrag(auftrag);
	}
	
	public void deleteAuftrag(Auftrag auftrag){
		view.deleteAuftrag(auftrag);
	}
	
	public void addOrChangeRechnung(Rechnung rechnung){
		view.addOrChangeRechnung(rechnung);
	}
	
	public void deleteRechnung(Rechnung rechnung){
		view.deleteRechnung(rechnung);
	}
	
	public void addOrChangeMitarbeiter(Mitarbeiter mitarbeiter){
		view.addOrChangeMitarbeiter(mitarbeiter);
	}
	
	public void deleteMitarbeiter(Mitarbeiter mitarbeiter){
		view.deleteMitarbeiter(mitarbeiter);
	}

}
