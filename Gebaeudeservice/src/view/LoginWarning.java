package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LoginWarning extends JFrame {

	JFrame loginwarning;

	public LoginWarning() {

		JFrame loginwarning = new JFrame("Login fehlgeschlagen.");

		Image logo = new ImageIcon("res/logo.png").getImage();

		loginwarning.setResizable(false);
		loginwarning.setIconImage(logo);
		loginwarning.setLocationRelativeTo(null);
		loginwarning.setSize(400, 250);
		loginwarning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loginwarning.setLayout(new BorderLayout());

		JLabel label1 = new JLabel();
		label1.setText("<html><body><center> Anmeldung nicht erfolgreich! <br><br> Benutzername oder Passwort nicht bekannt. </body></html>");
		label1.setFont(new Font("Arial", Font.BOLD, 18));
		label1.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		loginwarning.add(label1,BorderLayout.CENTER);

		loginwarning.setVisible(true);
		System.out.println("Warning erzeugt.");

	}

}
