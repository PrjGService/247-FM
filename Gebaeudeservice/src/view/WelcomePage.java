package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.LoginWindowController;
import controller.MainWindowController;
import controller.Verwaltung;

public class WelcomePage extends JFrame {

	private static final long serialVersionUID = 3557864012470377221L;

	String username;
	String pwname;
	JPasswordField pw;
	JTextField user;
	Verwaltung verwaltung;

	public WelcomePage() {

		verwaltung = Verwaltung.getInstance();
		this.setTitle("24/7 - Facility Management");
		Image icon = new ImageIcon("res/logo.png").getImage();
		Image logo1 = new ImageIcon("res/logo1.png").getImage();

		this.setResizable(false);
		this.setIconImage(icon);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Hintergrund ImagePanel

		BufferedImage myImage = null;
		try {
			myImage = ImageIO.read(new File("res/gebaeudeservice.jpg"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		this.setContentPane(new ImagePanel(myImage));
		this.setLayout(new BorderLayout());

		JLabel logo = new JLabel();
		logo.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		logo.setIcon(new ImageIcon(logo1));

		GridLayout loginmask = new GridLayout(4, 2, 0, 10);

		JPanel login = new JPanel();
		login.setLayout(loginmask);

		JLabel name = new JLabel("Benutzername:");
		name.setFont(new Font("ARIAL", Font.PLAIN, 16));
		name.setForeground(Color.white);
		name.setBackground(new Color(19, 123, 64));

		JLabel password = new JLabel("Passwort:");
		password.setFont(new Font("ARIAL", Font.PLAIN, 16));
		password.setForeground(Color.white);
		password.setBackground(new Color(19, 123, 64));

		user = new JTextField();
		user.setFont(new Font("ARIAL", Font.PLAIN, 16));
		user.setForeground(Color.white);
		user.setBackground(new Color(19, 123, 64));
		user.setText("");

		pw = new JPasswordField();
		pw.setFont(new Font("ARIAL", Font.PLAIN, 16));
		pw.setForeground(Color.white);
		pw.setBackground(new Color(19, 123, 64));
		pw.setText("");

		pw.addKeyListener(new KeyListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {

				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					LoginWindowController.getInstance().handleLogin(
							user.getText(), pw.getText().toString());

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		LayoutButton button1 = new LayoutButton("Anmelden");
		button1.setLayout(new BorderLayout());

		button1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				LoginWindowController.getInstance().handleLogin(user.getText(),
						pw.getText().toString());
			}
		});

		JLabel ph1 = new JLabel();
		JLabel ph2 = new JLabel();

		login.add(ph1);
		login.add(ph2);
		login.add(name);
		login.add(user);
		login.add(password);
		login.add(pw);
		login.setBackground(new Color(19, 123, 64));

		JPanel south = new JPanel();
		south.setLayout(new BorderLayout());
		south.add(login, BorderLayout.CENTER);
		south.add(button1, BorderLayout.SOUTH);
		south.add(new JLabel("      "), BorderLayout.WEST);
		south.add(new JLabel("      "), BorderLayout.EAST);
		south.setBackground(new Color(19, 123, 64));

		this.add(logo, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);

		this.pack();
		this.setLocationRelativeTo(getParent());
		this.setExtendedState(Frame.NORMAL);

		System.out.println("Loginpage generated.");

	}

	public void showError() {
		new LoginWarning();
	}
}
