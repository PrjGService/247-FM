package visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Master_Frame implements ActionListener {

	public static void main(String[] args) {

		// Icon for frame.

		Image logo = new ImageIcon("res/logo.png").getImage()
				.getScaledInstance(3000, 2000, Image.SCALE_AREA_AVERAGING);

		// JFrame - Max. Size Window.

		JFrame masterframe = new JFrame("24/7 - Facility Management");
		masterframe.setResizable(false);
		masterframe.setIconImage(logo);
		masterframe.setVisible(true);
		masterframe.setExtendedState(Frame.MAXIMIZED_BOTH);
		masterframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Content

		Container container1 = masterframe.getContentPane();

		// JPanel

		JPanel window1 = new JPanel();

		// Layout-Manager

		GridLayout MyLayout = new GridLayout(1, 2);

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.green);
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.red);

		window1.add(panel1);
		window1.add(panel2);
		window1.setLayout(MyLayout);

		String head = new String ("<html><center> 24/7 Facility-Management</html>");
		
		JLabel label1 = new JLabel("24/7 Facility-Management");
		label1.setFont(new Font("ARIAL", Font.BOLD, 44));
		label1.setHorizontalAlignment(Component.CENTER_ALIGNMENT);

		panel1.add(label1);
		container1.add(window1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
