package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.aady.util.MyAbstractFormatter;
import com.mysql.jdbc.PreparedStatement;

import db.DbConnection;
import db.EquipmentDAO;
import model.Equipment;
import model.Trainer;
import db.TrainerDAO;

public class AddTrainer {

	Connection con;
	Statement statement;
	ResultSet resultSet;

	final JTextField txtTrainername, txtAge, txtGender, txtPackageyearly, txtmobileno, txtEmailAdd;
	private JDatePickerImpl datePickerDateofjoining;

	public AddTrainer() {
		con = DbConnection.getConnection();
		try {
			statement = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();

		}

		JFrame frame = new JFrame(" Add Trainer");
		Container cont = frame.getContentPane();
		cont.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		JLabel lbTrainername = new JLabel("Trainer name:");
		JLabel lbAge = new JLabel("Age:");
		JLabel lbGender = new JLabel("Gender:");
		JLabel lbPackageYearly = new JLabel("Package yearly:");
		JLabel lbmobileno = new JLabel("Mobile Number:");
		JLabel lbEmailadd = new JLabel("Email Add:");
		JLabel lbDateofjoining = new JLabel("Date of joining:");

		final JRadioButton radMale = new JRadioButton("Male", true);
		final JRadioButton radFemale = new JRadioButton("Female");
		ButtonGroup group = new ButtonGroup();
		group.add(radMale);
		group.add(radFemale);

		txtTrainername = new JTextField(15);
		txtAge = new JTextField(15);
		txtGender = new JTextField(15);
		txtPackageyearly = new JTextField(15);
		txtmobileno = new JTextField(15);
		txtEmailAdd = new JTextField(15);

		gbc.gridx = 0;
		gbc.gridy = 1;
		cont.add(lbTrainername, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		cont.add(lbAge, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		cont.add(lbGender, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		cont.add(lbPackageYearly, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		cont.add(lbmobileno, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		cont.add(lbEmailadd, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		cont.add(lbDateofjoining, gbc);

		AbstractFormatter formatter = new MyAbstractFormatter();

		UtilDateModel modelStart = new UtilDateModel();
		JDatePanelImpl datePanelDateofjoining = new JDatePanelImpl(modelStart, new Properties());
		datePickerDateofjoining = new JDatePickerImpl(datePanelDateofjoining, formatter);

		gbc.gridx = 1;
		gbc.gridy = 1;
		cont.add(txtTrainername, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		cont.add(txtAge, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		cont.add(radMale, gbc);
		

		gbc.gridx = 2;
		gbc.gridy = 3;
		cont.add(radFemale, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		cont.add(txtPackageyearly, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		cont.add(txtmobileno, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		cont.add(txtEmailAdd, gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		cont.add(datePickerDateofjoining, gbc);

		JButton btnSAVE = new JButton("SAVE");
		btnSAVE.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				validationTrainer();
				// TODO Auto-generated method stub
				System.out.println("button click" + e.getSource());
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");

				String gender = "Male";

				if (radFemale.isSelected()) {
					gender = "Female";
				}

				String Date = format.format(datePickerDateofjoining.getModel().getValue());
				String name = txtTrainername.getText();
				String age = txtAge.getText();
				String gen = gender;
				String pkg=txtPackageyearly.getText();
				String mob=txtmobileno.getText();
				String email=txtEmailAdd.getText();
				String date=Date;
				
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "please enter the name");

					return;
				}

				else if (age.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the  age");
					return;
				}

				else if (gen.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the gender");
					return;
					
				} else if (pkg.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the packageYearly");
					return;

				} else if (mob.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the mobile no");
					return;
				} else if (email.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the Email");
					return;
					
				}
				else if (date.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the Date");
					return;
				}
				
				Trainer trainer= new Trainer();
				trainer.setName(name);
				trainer.setAge(Integer.parseInt(age));
				trainer.setGender(gender);
				trainer.setPackageYearly(Integer.parseInt(pkg));
				trainer.setMobileno(Integer.parseInt(mob));
				trainer.setEmailAdd(email);
				trainer.setDateofjoining(date);
				
				boolean isInsert = TrainerDAO.insert(trainer);
				if (isInsert == true) {
					JOptionPane.showMessageDialog(null, "Trainer inserted Successfull");
					nullAfterSaveData();
				}else {
					JOptionPane.showMessageDialog(null, "Trainer inserted failed");
					nullAfterSaveData();
				}
			}
		});
				
				
		gbc.gridx = 0;
		gbc.gridy = 8;
		cont.add(btnSAVE, gbc);

		JButton btnupdate = new JButton("UPDATE");
		gbc.gridx = 1;
		gbc.gridy = 8;
		cont.add(btnupdate, gbc);
		btnupdate.addActionListener(e -> {

			Trainer trainer = new Trainer();

			String name = txtTrainername.getText();
			String gender = "male";
			if (radFemale.isSelected()) {
				gender = "Female";
			}
			int age = Integer.parseInt(txtAge.getText());
			int packageYearly = Integer.parseInt(txtPackageyearly.getText());
			int mobileno = Integer.parseInt(txtmobileno.getText());
			String emailadd = txtEmailAdd.getText();

			DateModel<?> model = datePickerDateofjoining.getModel();

			String dateofjoining = model.getYear() + "-" + model.getMonth() + "-" + model.getDay();

			trainer.setName(name);
			trainer.setAge(age);
			trainer.setGender(gender);
			trainer.setPackageYearly(packageYearly);
			trainer.setMobileno(mobileno);
			trainer.setEmailAdd(emailadd);
			trainer.setDateofjoining(dateofjoining);

			try {
				boolean isUpdate = TrainerDAO.updateTrainer(trainer);
				if (isUpdate == true) {
					JOptionPane.showMessageDialog(null, "Equipment Update Successfully");
					txtTrainername.setText("");
					txtAge.setText("");
					txtPackageyearly.setText("");
					txtmobileno.setText("");
					txtEmailAdd.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Equipment Update failed");
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});

		JButton btnfetch = new JButton("FETCH");
		gbc.gridx = 2;
		gbc.gridy = 8;
		cont.add(btnfetch, gbc);
		btnfetch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					String email = txtEmailAdd.getText();
					Trainer existingEmployee = TrainerDAO.getTrainer(email);
					
				
					
					if (existingEmployee != null ) {
						txtTrainername.setText(existingEmployee.getName());
						txtAge.setText("" + existingEmployee.getAge());
						txtGender.setText(existingEmployee.getGender());
						txtPackageyearly.setText("" + existingEmployee.getPackageYearly());
						txtmobileno.setText("" + existingEmployee.getMobileno());
						txtEmailAdd.setText("" + existingEmployee.getEmailAdd());
						String[] date = existingEmployee.getDateofjoining().split("-");
						datePanelDateofjoining.getModel().setDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
								Integer.parseInt(date[2]));
						
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Email address is not present in database!!");
					}

					

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		Container c = frame.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.CYAN);
	}
	private void nullAfterSaveData() {
		txtTrainername.setText("");
		txtAge.setText("");
		txtPackageyearly.setText("");
		txtmobileno.setText("");
		txtEmailAdd.setText("");
	}
	
	private void validationTrainer() {
		String moblileNumber = txtmobileno.getText();
		if (moblileNumber =="" ) {
			JOptionPane.showMessageDialog(null, "Mobile number must be Numaric value!!");
		}
	}

}
