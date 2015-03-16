package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LayoutButton extends JButton {

	String title;
	JButton button;

	public LayoutButton(String title) {

		this.title = title;
		this.setText(title);
		//button = new JButton(title);
		//this.setBackground(Color.DARK_GRAY);
		this.setFont(new Font("ARIAL", Font.BOLD, 22));

	}


}
