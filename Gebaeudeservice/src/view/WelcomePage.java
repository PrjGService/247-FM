package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class WelcomePage extends JFrame {

	JFrame WelcomePage;

	public WelcomePage() {

		JFrame welcome = new JFrame("24/7 - Facility Management");
		Image icon = new ImageIcon("res/logo.png").getImage();
		Image logo1 = new ImageIcon("res/logo1.png").getImage();

		welcome.setResizable(false);
		welcome.setIconImage(icon);
		welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Hintergrund über ImagePanel

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

		JTextField user = new JTextField();
		user.setFont(new Font("ARIAL", Font.BOLD, 22));
		user.getText();
		
		JTextField pw = new JTextField();
		pw.setFont(new Font("ARIAL", Font.BOLD, 22));
		pw.getText();

		JButton button1 = new JButton("Anmelden");
		button1.setLayout(new BorderLayout());

		login.add(name);
		login.add(user);
		login.add(password);
		login.add(pw);
		
		Container south = new Container();
		south.setLayout(new BorderLayout());
		south.add(login, button1);

		welcome.add(logo, BorderLayout.CENTER);
		welcome.add(south, BorderLayout.SOUTH);

		welcome.pack();
		welcome.setVisible(true);
		welcome.setExtendedState(Frame.MAXIMIZED_BOTH);

		System.out.println(welcome);

	}
}
