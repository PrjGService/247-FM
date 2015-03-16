package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LayoutButton extends JFrame implements ActionListener {

	String title;
	JButton button;

	public LayoutButton(String title) {

		this.title = title;
		button = new JButton(title);
		button.setBackground(Color.DARK_GRAY);
		button.setFont(new Font("ARIAL", Font.BOLD, 22));

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
