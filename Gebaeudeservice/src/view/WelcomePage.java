package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WelcomePage extends JFrame {

	JFrame WelcomePage;

	public WelcomePage() {

		JFrame welcome = new JFrame("24/7 - Facility Management");
		Image icon = new ImageIcon("res/logo.png").getImage();

		welcome.setResizable(false);
		welcome.setIconImage(icon);
		welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BufferedImage myImage = null;
		try {
			myImage = ImageIO.read(new File("res/gebaeudeservice.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		welcome.setContentPane(new ImagePanel(myImage));

		JLabel hintergrund = new JLabel();
		hintergrund.setIcon(new ImageIcon("res/gebaeudeservice.jpg"));
		hintergrund.setBounds(0, 0, MAXIMIZED_BOTH, MAXIMIZED_BOTH);
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(icon));
		logo.setLayout(new BorderLayout());

		welcome.add(hintergrund);

		welcome.setVisible(true);
		welcome.setExtendedState(Frame.MAXIMIZED_BOTH);

		System.out.println(welcome);

	}
}
