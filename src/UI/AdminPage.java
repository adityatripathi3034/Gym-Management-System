package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.aady.util.Mail;

import db.UserDAO;

public class AdminPage extends JFrame {

	AdminPage(JFrame framewell) {

		final JFrame frame = new JFrame("Login Page");
		Container con = frame.getContentPane();
		Container c = frame.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.CYAN);
		con.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		JLabel lbuser = new JLabel("Admin");
		JLabel lbpass = new JLabel("Password");
		final JTextField txtEmail = new JTextField(15);
		final JTextField txtPass = new JPasswordField(15);

		gbc.gridx = 0;
		gbc.gridy = 1;
		con.add(lbuser, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		con.add(lbpass, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		con.add(txtEmail, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		con.add(txtPass, gbc);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String username = txtEmail.getText();
				String password = txtPass.getText();

				boolean isAuthenticate;
				try {
					if (txtEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "please enter the user");
					}
					if (txtPass.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "please enter the password");
					}
					isAuthenticate = UserDAO.authenticate(username, password);

					if (isAuthenticate) {

						frame.dispose();
						framewell.dispose();
						new HomeFrame();
					} else {
						JOptionPane.showMessageDialog(frame, "Login failed");
						return;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Wellcome();

			}
		});

		gbc.gridx = 0;
		gbc.gridy = 5;
		con.add(btnLogin, gbc);

		gbc.gridx = 2;
		gbc.gridy = 5;
		con.add(btnExit, gbc);
		JButton btnForget = new JButton("Forget password");

		gbc.gridx = 1;
		gbc.gridy = 5;
		con.add(btnForget, gbc);
		btnForget.addActionListener(e -> {

			String useername = txtEmail.getText();

			try {
				String password = UserDAO.getPassword(useername);

				if (password != null) {
					
					Mail.sendEmailForgotPassword(password);
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		frame.setSize(700, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	private void forgetPassword() {
		// TODO Auto-generated method stub

	}

};
