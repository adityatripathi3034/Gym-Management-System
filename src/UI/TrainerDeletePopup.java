package UI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import db.TrainerDAO;

public class TrainerDeletePopup {
	private JButton buttonDelete, buttonCancel;
	private JTextField textEmail;
	private JLabel labelId;
	private JFrame frame;

	public TrainerDeletePopup() {
		deletePopupStart();
	}

	private void deletePopupStart() {
		frame = new JFrame("Delete Trainer");
		labelId = new JLabel("Enter Trainer Email");
		labelId.setBounds(20, 38, 200, 30);
		frame.add(labelId);

		textEmail = new JTextField();
		textEmail.setBounds(120, 40, 150, 25);
		frame.add(textEmail);

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
		String email = textEmail.getText();
		boolean isDelete = TrainerDAO.delete(email);

		if (isDelete == true) {
			JOptionPane.showMessageDialog(null, "Data Deleted");
			textEmail.setText("");
			frame.dispose();
		}
	}

}

