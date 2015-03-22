package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LoginWarning extends JFrame {

	private static final long serialVersionUID = -2919771881955889317L;

	JFrame loginwarning;

	public LoginWarning() {

		final JFrame loginwarning = new JFrame("Login fehlgeschlagen.");

		Image logo = new ImageIcon("res/logo.png").getImage();

		loginwarning.setResizable(false);
		loginwarning.setIconImage(logo);
		loginwarning.setLocationRelativeTo(null);
		loginwarning.setSize(300, 255);
		loginwarning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loginwarning.setLayout(new BorderLayout());

		JLabel label1 = new JLabel();
		label1.setText("<html><body><center> Anmeldung nicht erfolgreich! <br><br> Benutzername oder Passwort nicht korrekt. </body></html>");
		label1.setFont(new Font("Arial", Font.PLAIN, 18));
		label1.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		LayoutButton close = new LayoutButton("OK");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginwarning.dispose();
			}
		});
		close.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyChar() == KeyEvent.VK_ENTER) {

					loginwarning.dispose();
					System.out.println("Warning closed.");

				} else {

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		loginwarning.add(label1, BorderLayout.CENTER);
		loginwarning.add(close, BorderLayout.SOUTH);

		loginwarning.setVisible(true);
		System.out.println("Warning generated.");

	}
}
