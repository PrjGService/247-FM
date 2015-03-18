package view.neo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.UIManager;

import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXHeader;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import util.UIUtil;

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
	
	private JXTaskPane firstTaskPane;
	private AbstractAction firstAction;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8730460158991620638L;
	
	public MainWindowView(){
		UIManager.put("TaskPane.titleBackgroundGradientStart", Color.gray);
		UIManager.put("TaskPane.titleBackgroundGradientEnd", Color.gray);
		UIManager.put("TaskPane.background", Color.white);
		UIManager.put("TaskPane.foreground", Color.white);
		UIManager.put("TaskPane.titleForeground", Color.black);
		UIManager.put("TaskPane.titleOver", UIUtil.getStandardColor());
		initUI();
	}

	private void initUI() {
		this.setTitle("Gebäudeservice");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(false);
		
		contentPanel = new JXPanel(new BorderLayout());
		contentPanel.setBackground(Color.white);
		this.setContentPane(contentPanel);
		
		header = new JXHeader("Gebäudeservice","Hier wird Ihnen geholfen!");
		header.setTitleFont(new Font("Arial", Font.BOLD, 22));
		header.setTitleForeground(Color.white);
		header.setDescriptionFont(new Font("Arial", Font.PLAIN, 16));
		header.setDescriptionForeground(Color.white);
		header.setBackground(UIUtil.getStandardColor());
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
		centerContent = new JScrollPane(centerContentData);
		centerContent.setLayout(new ScrollPaneLayout());
		centerContent.setBorder(BorderFactory.createEmptyBorder(3, 3, 0, 0));
		centerContent.setBackground(Color.white);
		
		centerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerNavigator, centerContent);
		centerPanel.setOpaque(true);
		centerPanel.setEnabled(false);
		centerPanel.setDividerLocation(250);
		centerPanel.setDividerSize(0);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		firstTaskPane = new JXTaskPane("Erste Navigurationsgruppe");
		
		firstAction = new AbstractAction() {

			  {
			    putValue(Action.NAME, "task pane item 2 : an action");
			    putValue(Action.SHORT_DESCRIPTION, "perform an action");
			  }

			  public void actionPerformed(ActionEvent e) {
				  // do something.
			  }

			};
		
		firstTaskPane.add(firstAction);
		
		centerNavigatorData.add(firstTaskPane);
		contentPanel.add(mainPanel, BorderLayout.CENTER);
	}
	
	
}
