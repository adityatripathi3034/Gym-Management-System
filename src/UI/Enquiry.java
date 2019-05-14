package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.aady.util.MyAbstractFormatter;

import db.DbConnection;

public class Enquiry extends JFrame {

	Connection con;
	Statement statement;
	ResultSet resultSet;

	final JTextField txtfullname,txtcontactno, txtEmail, txtDate, txtoccupation;

	public Enquiry() {
		
		con = DbConnection.getConnection();
		try {
			statement = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
//		super("Enquiry frame");
		Container cont = getContentPane();
		Container c=getContentPane(); 
	    c.setLayout(null);
	    c.setBackground(Color.CYAN); 
		cont.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		JLabel lbfullname = new JLabel("Full Name");
		JLabel lbgender = new JLabel("Gender");
		JLabel lbcontactno = new JLabel("Contact Number");
		JLabel lbEmail = new JLabel("Email add");
		JLabel lbDate = new JLabel("Date");
		JLabel lboccupation = new JLabel("Occupation");
		final JRadioButton radMale = new JRadioButton("Male", true);
	    final JRadioButton radFemale = new JRadioButton("Female");
		txtfullname = new JTextField(15);
		//txtgender = new JTextField(15);
		 txtcontactno = new JTextField(15);
		 txtEmail = new JTextField(15);
		 txtDate = new JTextField(15);
		 txtoccupation = new JTextField(15);

		gbc.gridx = 0;
		gbc.gridy = 1;
		cont.add(lbfullname, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		cont.add(lbgender, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		cont.add(lbcontactno, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		cont.add(lbEmail, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		cont.add(lbDate, gbc);
		AbstractFormatter formatter = new MyAbstractFormatter();

		UtilDateModel modelStart = new UtilDateModel();
		JDatePanelImpl datePanelDateDate = new JDatePanelImpl(modelStart, new Properties());
		JDatePickerImpl datePickerDate = new JDatePickerImpl(datePanelDateDate, formatter);
		

		gbc.gridx = 2;
		gbc.gridy = 3;
		cont.add(lboccupation, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		cont.add(txtfullname, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		cont.add(radMale, gbc);
		
		gbc.gridx=4;
		gbc.gridy=1;
		cont.add(radFemale,gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		cont.add(txtcontactno, gbc);

		gbc.gridx = 3;
		gbc.gridy = 2;
		cont.add(txtEmail, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		cont.add(datePickerDate, gbc);

		gbc.gridx = 3;
		gbc.gridy = 3;
		cont.add(txtoccupation, gbc);

		JButton btnSAVE = new JButton("SAVE");
		btnSAVE.setBackground(Color.RED);
		btnSAVE.setOpaque(true);
		btnSAVE.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("button click  " + e.getSource() );
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");

				
			
	
				String sql="INSERT INTO enquiry_frame VALUES (?,?,?,?,?,?)";
				String gender = "Male";

				if (radFemale.isSelected()) {
					gender = "Female";
				}
				
				
				
				String date = format.format(datePickerDate.getModel().getValue());
				String fullname=txtfullname.getText();
				String contactno=txtcontactno.getText();
				String email=txtEmail.getText();
				String occupation=txtoccupation.getText();
				
				
				
			try {
				
			
				if (fullname.equals("")) {
					JOptionPane.showMessageDialog(null, "please enter tthe fullname");

					return;
				}

				else if (contactno.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the  contactno");
					return;
				}

				else if (email.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the email");
					return;
					
				} else if (occupation.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the occupation");
					return;

				} else if (date.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the date");
					return;
				} else if (gender.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the gender");
					return;
					
				}
				 PreparedStatement pdt =  con.prepareStatement(sql);
				 pdt.setString(1,fullname);
				 pdt.setString(2,gender);
				 pdt.setString(3,contactno);
				 pdt.setString(4,email);
				 pdt.setString(5, date);
				 pdt.setString(6,occupation);
				 
				 pdt.executeUpdate();
				 
				 pdt.close();
				 con.close();
				
				JOptionPane.showMessageDialog(null, "Saved");
				nullAfterSaveData();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Not Saved");
				e2.printStackTrace();
				nullAfterSaveData();
			}

			}
		});

		gbc.gridx = 1;
		gbc.gridy = 9;
		cont.add(btnSAVE, gbc);
		
		setSize(700, 400);
	//	setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		
	}
	private void nullAfterSaveData() {
		txtfullname.setText("");
		txtcontactno.setText("");
		txtEmail.setText("");
		txtoccupation.setText("");
		
	}
}
