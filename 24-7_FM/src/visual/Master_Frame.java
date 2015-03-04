package visual;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Master_Frame {

	public static void main(String[] args) {
		
		//Icon for frame.
		
		Image logo = new ImageIcon("res/logo.png").getImage().getScaledInstance(3000, 2000, Image.SCALE_AREA_AVERAGING);
		
		//JFrame - Max. Size Window.
		
		JFrame masterframe = new JFrame("24/7 - Facility Management");
		masterframe.setResizable(false);
		masterframe.setIconImage(logo);
		masterframe.setVisible(true);		
		masterframe.setExtendedState(masterframe.MAXIMIZED_BOTH);		
		masterframe.setDefaultCloseOperation(masterframe.EXIT_ON_CLOSE);
		
		//JLabel
		
		JLabel label1 = new JLabel("Test1234");
		JLabel label2 = new JLabel("Test1234");
		JLabel label3 = new JLabel("Test1234");
		JLabel label4 = new JLabel("Test1234");
		JLabel label5 = new JLabel("Test1234");
				
		masterframe.add(label1);
		masterframe.add(label2);
		masterframe.add(label3);
		masterframe.add(label4);
		masterframe.add(label5);
	
	}

}
