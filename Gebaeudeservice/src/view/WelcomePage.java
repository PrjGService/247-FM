package view;

import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WelcomePage extends JFrame {

	JFrame WelcomePage;

	public WelcomePage() {

		JFrame welcome = new JFrame("24/7 - Facility Management");
		Image logo = new ImageIcon("res/logo.png").getImage();

		welcome.setResizable(false);
		welcome.setIconImage(logo);
		welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel hintergrund = new JLabel();
		hintergrund.setIcon(new ImageIcon("res/gebaeudeservice.jpg"));
		hintergrund.setBounds(0, 0, MAXIMIZED_BOTH, MAXIMIZED_BOTH);

		welcome.add(hintergrund);
		
		welcome.setVisible(true);
		welcome.setExtendedState(Frame.MAXIMIZED_BOTH);

		System.out.println(welcome);

	}
}
