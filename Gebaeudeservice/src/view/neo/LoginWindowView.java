package view.neo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXHeader;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;

import util.SpringUtilities;
import controller.LoginWindowController;

public class LoginWindowView extends JXFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6242508615880797583L;
	
	
	public LoginWindowView(){
		initUI();
	}
	
	public void initUI(){
		this.setSize(new Dimension(400,210));
		this.setLayout(new BorderLayout());
		this.setTitle("Gebäudeservice");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JXHeader header = new JXHeader("Anmeldung", "Bitte geben Sie Ihre Benutzerkennung ein.");
		header.setBackground(new Color(19,123,64));
		header.setForeground(Color.white);
		header.setTitleForeground(Color.white);
		header.setDescriptionForeground(Color.white);
		header.setDescriptionFont(new Font("Arial", Font.PLAIN, 12));
		header.setTitleFont(new Font("Arial", Font.PLAIN, 16));
		
		JXPanel mainPanel = new JXPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setLayout(new SpringLayout());
		
		JXLabel accLbl = new JXLabel("Benutzername:");
		accLbl.setFont(new Font("Arial", Font.PLAIN, 12));
		final JTextField accName = new JTextField();
		JXLabel passLbl = new JXLabel("Passwort:");
		passLbl.setFont(new Font("Arial", Font.PLAIN, 12));
		final JPasswordField passName = new JPasswordField();
		
		mainPanel.add(accLbl);
		mainPanel.add(accName);
		mainPanel.add(passLbl);
		mainPanel.add(passName);
		
		SpringUtilities.makeCompactGrid(mainPanel, 2, 2, 5, 5, 5, 5);
		
		JXPanel buttonPanel = new JXPanel();
		buttonPanel.setLayout(new GridLayout(1,3));
		buttonPanel.setBackground(Color.white);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JXButton login = new JXButton("Anmelden");
		login.setFont(new Font("Arial", Font.PLAIN, 14));
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginWindowController.getInstance().handleLogin(accName.getText(), passName.getPassword().toString());
			}
		});
		
		JXButton cancel = new JXButton("Abbrechen");
		cancel.setFont(new Font("Arial", Font.PLAIN, 14));
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		buttonPanel.add(new JLabel());
		buttonPanel.add(cancel);
		buttonPanel.add(login);
		
		this.add(header, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setLocationRelativeTo(this.getOwner());
	}
	
}
