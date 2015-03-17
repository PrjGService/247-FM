package view;

import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class LoginWarning extends JFrame {
	
	JFrame loginwarning;
	
	public LoginWarning() {
		
		JFrame loginwarning = new JFrame("Login fehlgeschlagen.");
		
		Image logo = new ImageIcon("res/logo.png").getImage();
		
		loginwarning.setResizable(false);
		loginwarning.setIconImage(logo);
		loginwarning.setVisible(true);
		loginwarning.setSize(400, 200);
		loginwarning.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println(loginwarning);
		
		
	}

}
