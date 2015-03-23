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


		Image logo = new ImageIcon("res/logo.png").getImage();

		this.setResizable(false);
		this.setIconImage(logo);
		this.setLocationRelativeTo(null);
		this.setSize(300, 255);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());

		JLabel label1 = new JLabel();
		label1.setText("<html><body><center> Anmeldung nicht erfolgreich! <br><br> Benutzername oder Passwort nicht korrekt. </body></html>");
		label1.setFont(new Font("Arial", Font.PLAIN, 18));
		label1.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		LayoutButton close = new LayoutButton("OK");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyChar() == KeyEvent.VK_ENTER) {

					dispose();
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

		this.add(label1, BorderLayout.CENTER);
		this.add(close, BorderLayout.SOUTH);
		this.setLocationRelativeTo(this.getOwner());
		this.setVisible(true);
		System.out.println("Warning generated.");

	}
}
