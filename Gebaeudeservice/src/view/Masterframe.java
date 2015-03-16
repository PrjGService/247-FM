package view;

import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Masterframe extends JFrame {

	JFrame masterframe;

	public Masterframe() {

		JFrame masterframe = new JFrame("24/7 - Facility Management");

		Image logo = new ImageIcon("res/logo.png").getImage();

		masterframe.setResizable(false);
		masterframe.setIconImage(logo);
		masterframe.setVisible(true);
		masterframe.setExtendedState(Frame.MAXIMIZED_BOTH);
		masterframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.println(masterframe);

	}

}
