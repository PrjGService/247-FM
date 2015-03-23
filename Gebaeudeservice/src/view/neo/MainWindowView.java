package view.neo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.UIManager;

import model.Auftrag;

import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXHeader;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import controller.Verwaltung;
import util.UIUtil;
import view.neo.content.AuftragsUebersichtView;
import view.neo.content.ImpressumView;
import view.neo.content.MahnauftragsView;
import view.neo.content.MitarbeiterView;
import view.neo.content.RechnungsAnlegenView;
import view.neo.content.RechnungsUebersichtView;
import view.neo.content.StatusAbfragenView;

public class MainWindowView extends JXFrame{
	
	private JXHeader header;
	private JXPanel contentPanel;
	private JXPanel mainPanel;
	
	private JSplitPane centerPanel;
	JXTaskPaneContainer centerNavigatorData;
	JScrollPane centerContent;
	JScrollPane centerNavigator;
	JXPanel centerContentData;
	CardLayout centerContentDataLayout;
	
	private JXTaskPane auftraegeTaskPane;
	
	private AbstractAction auftraegeUebersichtAction;
	private AuftragsUebersichtView auftreageUebersichtPanel;
	
	private AbstractAction statusAbfragenAction;
	private JXPanel statusAbfragenPanel; 
	
	private JXTaskPane mitarbeiterTaskPane;
	
	private AbstractAction mitarbeiterAction;
	private JXPanel mitarbeiterPanel;
	
	private JXTaskPane rechnungTaskPane;
	
	private AbstractAction rechnungAction;
	private JXPanel rechnungPanel;
	
	private AbstractAction rechnungAnlegenAction;
	private JXPanel rechnungAnlegenPanel;
	
	private AbstractAction mahnauftragAction;
	private JXPanel mahnauftragPanel;
	
	private JXTaskPane adminTaskPane;
	
	private AbstractAction impressumAction;
	private JXPanel impressumPanel;
	
	private AbstractAction logoutAction;
	
	Verwaltung verwaltung;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8730460158991620638L;
	
	public MainWindowView(){
		verwaltung = Verwaltung.getInstance();
		UIManager.put("TaskPane.titleBackgroundGradientStart", Color.gray);
		UIManager.put("TaskPane.titleBackgroundGradientEnd", Color.gray);
		UIManager.put("TaskPane.background", Color.white);
		UIManager.put("TaskPane.foreground", Color.white);
		UIManager.put("TaskPane.titleForeground", Color.black);
		UIManager.put("TaskPane.titleOver", UIUtil.getStandardColor());
		initUI();
		
	}

	private void initUI() {
		this.setTitle("24/7 FM - Facility Management");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(false);
		
		contentPanel = new JXPanel(new BorderLayout());
		contentPanel.setBackground(Color.white);
		this.setContentPane(contentPanel);
		
		header = new JXHeader("24/7 FM - Facility Management","Hier wird Ihnen geholfen!");
		header.setTitleFont(new Font("Arial", Font.BOLD, 22));
		header.setTitleForeground(Color.white);
		header.setDescriptionFont(new Font("Arial", Font.PLAIN, 16));
		header.setDescriptionForeground(Color.white);
		header.setBackground(UIUtil.getStandardColor());
		header.setIcon(new ImageIcon(new ImageIcon("res/logo.png").getImage().
				getScaledInstance(-1, 60, Image.SCALE_SMOOTH)));
		contentPanel.add(header, BorderLayout.NORTH);
		
		mainPanel = new JXPanel(new BorderLayout());
		mainPanel.setOpaque(false);

		centerNavigatorData = new JXTaskPaneContainer();
		centerNavigatorData.setBackground(Color.darkGray);
		centerNavigator = new JScrollPane(centerNavigatorData);
		centerNavigator.setLayout(new ScrollPaneLayout());
		
		centerContentDataLayout = new CardLayout();
		centerContentData = new JXPanel();
		centerContentData.setLayout(centerContentDataLayout);	
		centerContentData.setBackground(Color.white);
		centerContent = new JScrollPane(centerContentData);
		centerContent.setLayout(new ScrollPaneLayout());
		centerContent.setBorder(BorderFactory.createEmptyBorder(3, 3, 0, 0));
		centerContent.setBackground(Color.white);
		
		centerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerNavigator, centerContent);
		centerPanel.setOpaque(true);
		centerPanel.setEnabled(false);
		centerPanel.setDividerLocation(300);
		centerPanel.setDividerSize(0);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		auftraegeTaskPane = new JXTaskPane("Auftr�ge");
		
		auftreageUebersichtPanel = new AuftragsUebersichtView();
		centerContentData.add(auftreageUebersichtPanel, auftreageUebersichtPanel.getClass().getName());
		
		auftraegeUebersichtAction = new AbstractAction() {
			private static final long serialVersionUID = -1035106393148369106L;

			{
			    putValue(Action.NAME, "�bersicht");
			    putValue(Action.SHORT_DESCRIPTION, "�bersichtsseite Auftr�ge");
			  }

			  public void actionPerformed(ActionEvent e) {
				  showContent(auftreageUebersichtPanel.getClass().getName());
			  }

			};

		auftraegeTaskPane.add(auftraegeUebersichtAction);
		
		statusAbfragenPanel = new StatusAbfragenView();
		centerContentData.add(statusAbfragenPanel, statusAbfragenPanel.getClass().getName());
		
		statusAbfragenAction = new AbstractAction() {
			private static final long serialVersionUID = -1035106393148369106L;

			{
			    putValue(Action.NAME, "Status abfragen");
			    putValue(Action.SHORT_DESCRIPTION, "Status der Auftr�ge abfragen");
			  }

			  public void actionPerformed(ActionEvent e) {
				  showContent(statusAbfragenPanel.getClass().getName());
			  }

			};

		auftraegeTaskPane.add(statusAbfragenAction);
		
		mitarbeiterTaskPane = new JXTaskPane("Mitarbeiter");
		
		mitarbeiterPanel = new MitarbeiterView();
		centerContentData.add(mitarbeiterPanel, mitarbeiterPanel.getClass().getName());
		
		mitarbeiterAction = new AbstractAction() {
			private static final long serialVersionUID = -1035106393148369106L;

			{
			    putValue(Action.NAME, "Mitarbeiter");
			    putValue(Action.SHORT_DESCRIPTION, "Mitarbeiter anzeigen");
			  }

			  public void actionPerformed(ActionEvent e) {
				  showContent(mitarbeiterPanel.getClass().getName());
			  }

			};

		mitarbeiterTaskPane.add(mitarbeiterAction);
		
		rechnungTaskPane = new JXTaskPane("Rechnung");
		
		rechnungPanel = new RechnungsUebersichtView();
		centerContentData.add(rechnungPanel, rechnungPanel.getClass().getName());
		
		rechnungAction = new AbstractAction() {
			private static final long serialVersionUID = -1035106393148369106L;

			{
			    putValue(Action.NAME, "�bersicht");
			    putValue(Action.SHORT_DESCRIPTION, "Rechnungs�bersicht anzeigen");
			  }

			  public void actionPerformed(ActionEvent e) {
				  showContent(rechnungPanel.getClass().getName());
			  }

			};

		rechnungTaskPane.add(rechnungAction);
		
		rechnungAnlegenPanel = new RechnungsAnlegenView();
		centerContentData.add(rechnungAnlegenPanel, rechnungAnlegenPanel.getClass().getName());
		
		rechnungAnlegenAction = new AbstractAction() {
			private static final long serialVersionUID = -1035106393148369106L;

			{
			    putValue(Action.NAME, "Neu anlegen");
			    putValue(Action.SHORT_DESCRIPTION, "Rechnung anlegen");
			  }

			  public void actionPerformed(ActionEvent e) {
				  showContent(rechnungAnlegenPanel.getClass().getName());
			  }

			};

		rechnungTaskPane.add(rechnungAnlegenAction);
		
		mahnauftragPanel = new MahnauftragsView();
		centerContentData.add(mahnauftragPanel, mahnauftragPanel.getClass().getName());
		
		mahnauftragAction = new AbstractAction() {
			private static final long serialVersionUID = -1035106393148369106L;

			{
			    putValue(Action.NAME, "Mahnauftrag erteilen");
			    putValue(Action.SHORT_DESCRIPTION, "Mahnung anlegen");
			  }

			  public void actionPerformed(ActionEvent e) {
				  showContent(mahnauftragAction.getClass().getName());
			  }

			};

		rechnungTaskPane.add(mahnauftragAction);
		
		adminTaskPane = new JXTaskPane("Administrativ");
		
		impressumPanel = new ImpressumView();
		centerContentData.add(impressumPanel, impressumPanel.getClass().getName());
		
		impressumAction = new AbstractAction() {
			private static final long serialVersionUID = -1035106393148369106L;

			{
			    putValue(Action.NAME, "Impressum");
			    putValue(Action.SHORT_DESCRIPTION, "Impressum anzeigen");
			  }

			  public void actionPerformed(ActionEvent e) {
				  showContent(impressumPanel.getClass().getName());
			  }

			};

		adminTaskPane.add(impressumAction);
		
		logoutAction = new AbstractAction() {
			private static final long serialVersionUID = -1035106393148369106L;

			{
			    putValue(Action.NAME, "Abmelden");
			    putValue(Action.SHORT_DESCRIPTION, "Abmelden von der Session.");
			  }

			  public void actionPerformed(ActionEvent e) {
				  System.exit(0);
			  }

			};

		adminTaskPane.add(logoutAction);
		
		centerNavigatorData.add(auftraegeTaskPane);
		centerNavigatorData.add(mitarbeiterTaskPane);
		centerNavigatorData.add(rechnungTaskPane);
		centerNavigatorData.add(adminTaskPane);
		contentPanel.add(mainPanel, BorderLayout.CENTER);
	}
	
	public void showContent(String key){
		centerContentDataLayout.show(centerContentData, key);
		centerContentData.repaint();
	}
	
	public void addOrChangeAuftrag(Auftrag auftrag){
		auftreageUebersichtPanel.addAuftrag(Long.valueOf(auftrag.auftragID), auftrag.auftraggeber.auftraggeberName, 
				auftrag.auftragstatus.toString(), auftrag.auftragdatum.toString(), "??");
	}

	public void deleteAuftrag(Auftrag auftrag) {
		auftreageUebersichtPanel.deleteAuftrag(auftrag.auftragID);	
	}
}
