package view;

import java.awt.Font;
import javax.swing.JButton;

public class LayoutButton extends JButton {

	private static final long serialVersionUID = 1806363846909410144L;

	String title;
	JButton button;

	public LayoutButton(String title) {

		this.title = title;
		this.setText(title);
		// button = new JButton(title);
		// this.setBackground(Color.DARK_GRAY);
		this.setFont(new Font("ARIAL", Font.BOLD, 22));

	}

}
