package visual;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		masterframe.setExtendedState(masterframe.MAXIMIZED_BOTH);
		masterframe.setDefaultCloseOperation(masterframe.EXIT_ON_CLOSE);
		
		// Content
		
		Container container1 = masterframe.getContentPane();
		container1.setBackground(Color.yellow);
		container1.setSize(100, 100);
		container1.setLayout(new FlowLayout());
		
		// JLabel

		JLabel label1 = new JLabel("1");
		label1.setFont(new Font("ARIAL", Font.BOLD, 22));
		JLabel label2 = new JLabel("2");
		JLabel label3 = new JLabel("3");
		JLabel label4 = new JLabel("4");
		JLabel label5 = new JLabel("5");

		 masterframe.add(label1);
		 masterframe.add(label2);
		 masterframe.add(label3);
		 masterframe.add(label4);
		 masterframe.add(label5);

		container1.add(label1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
