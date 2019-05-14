package UI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import db.MemberDAO;;

public class MemberDeletePopup {
	private JButton buttonDelete, buttonCancel;
	private JTextField textRegis;
	private JLabel labelId;
	private JFrame frame;

	public MemberDeletePopup() {
		deletePopupStart();
	}

	private void deletePopupStart() {
		frame = new JFrame("Delete Member");
		labelId = new JLabel("Enter Member Regis");
		labelId.setBounds(20, 38, 200, 30);
		frame.add(labelId);

		textRegis = new JTextField();
		textRegis.setBounds(120, 40, 150, 25);
		frame.add(textRegis);

		buttonDelete = new JButton("Delete");
		buttonDelete.setBounds(30, 100, 80, 30);
		frame.add(buttonDelete);
		buttonDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteEmployeeById();
			}
		});

		buttonCancel = new JButton("Cancel");
		buttonCancel.setBounds(130, 100, 80, 30);
		frame.add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		frame.setSize(300, 200);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//Database Operation call by this methods.
	private void deleteEmployeeById() {
		String regis = textRegis.getText();
		int changes = MemberDAO.delete(regis);

		if (changes != 0) {
			JOptionPane.showMessageDialog(null, "Data Deleted");
			textRegis.setText("");
			frame.dispose();
		
		}else {
			JOptionPane.showMessageDialog(null, "Registration no. does not exists");
		}
	}

}


