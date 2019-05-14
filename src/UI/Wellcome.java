package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

public class Wellcome {
	
	Wellcome() 
	{
		 final JFrame frame = new JFrame("Welcome Page");
		
		URL url = Wellcome.class.getResource("/image/6.jpg");
		ImageIcon icon = new ImageIcon(url);

		JLabel lbBackGroundImage = new JLabel(icon);
		frame.setContentPane(lbBackGroundImage);

		
		JButton b = new JButton("Lets Start Today");
		b.setBounds(580, 600, 270, 80);
		b.setBackground(Color.green);
		b.setOpaque(true);
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//frame.dispose();
				new AdminPage(frame);
			
				
			}
		});
		
		
		
		frame.add(b);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
