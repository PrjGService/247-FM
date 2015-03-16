package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Verwaltung;

public class WelcomePage extends JFrame {

	JFrame WelcomePage;
	String username;
	String pwname;
	JTextField pw;
	JTextField user;
	Verwaltung verwaltung;

	public WelcomePage() {

		verwaltung = new Verwaltung();
		JFrame welcome = new JFrame("24/7 - Facility Management");
		Image icon = new ImageIcon("res/logo.png").getImage();
		Image logo1 = new ImageIcon("res/logo1.png").getImage();

		welcome.setResizable(false);
		welcome.setIconImage(icon);
		welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Hintergrund �ber ImagePanel

		BufferedImage myImage = null;
		try {
			myImage = ImageIO.read(new File("res/gebaeudeservice.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		welcome.setContentPane(new ImagePanel(myImage));
		welcome.setLayout(new BorderLayout());

		JLabel logo = new JLabel();
		logo.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		logo.setIcon(new ImageIcon(logo1));

		GridLayout loginmask = new GridLayout(3, 2, 0, 10);
		GridLayout buttonlayout = new GridLayout(1, 1, 0, 10);

		JPanel login = new JPanel();
		login.setLayout(loginmask);

		JLabel name = new JLabel("Name:   ");
		name.setFont(new Font("ARIAL", Font.BOLD, 22));
		JLabel password = new JLabel("Passwort:   ");
		password.setFont(new Font("ARIAL", Font.BOLD, 22));

		user = new JTextField();
		user.setFont(new Font("ARIAL", Font.BOLD, 22));
		
		
		pw = new JTextField();
		pw.setFont(new Font("ARIAL", Font.BOLD, 22));

		LayoutButton button1 = new LayoutButton("Anmelden");
		button1.setLayout(new BorderLayout());

		button1.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    Verwaltung.verwaltung.login(user.getText(), pw.getText());
		  }
		});

		login.add(name);
		login.add(user);
		login.add(password);
		login.add(pw);
		login.add(button1, BorderLayout.CENTER);

		welcome.add(logo, BorderLayout.CENTER);
		welcome.add(login, BorderLayout.SOUTH);

		welcome.pack();
		welcome.setVisible(true);
		welcome.setExtendedState(Frame.MAXIMIZED_BOTH);

		System.out.println(welcome);

	}
}
