package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.generic.BALOAD;

import db.DbConnection;

public class BlanceFee {
	Connection con;
	Statement statement;
	ResultSet resultSet;

	private JComboBox cmbEmail;

	public BlanceFee() {
		con = DbConnection.getConnection();
		try {
			statement = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();

		}

		JFrame frame = new JFrame("Blance fee");
		Container con1 = frame.getContentPane();
		con1.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		JLabel lbfeesstuts = new JLabel("Fees Stutus");
		String comboContent[] = { "Please Select", "Paid", "Balance" };
		cmbEmail = new JComboBox(comboContent);

		cmbEmail.addActionListener(e -> {

			String item = cmbEmail.getSelectedItem().toString();

			if (item.equalsIgnoreCase("Paid")) {
				new Paid();
			} else {
				new Balance();
			}

		});

		gbc.gridx = 0;
		gbc.gridy = 0;
		con1.add(lbfeesstuts, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		con1.add(cmbEmail, gbc);
//	gbc.gridx=2;
//	gbc.gridy=0;
//	con1.add(btnOK,gbc);

		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		Container c = frame.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.CYAN);
	}
}
