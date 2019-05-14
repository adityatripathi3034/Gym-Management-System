package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.aady.util.Mail;
import com.aady.util.MyAbstractFormatter;
import com.mysql.jdbc.Statement;

import db.DbConnection;
import db.MemberDAO;
import db.TrainerDAO;
import invoice.InvoicePDF;
import model.Member;
import model.Trainer;

public class NewMember {
	JDatePanelImpl datePanelDateofbirth, datePanelStart, datePanelEnd;
	Connection con;
	Statement statement;
	ResultSet resultSet;

	JTextField txtRegis, txtFirstName, txtLastName, txtContactNO, txtAlternateNO, txtEmailAdd, txtOccupation,
			txtAddress, txtPersonalTrainer, txtSubscripation, txtAmmont, txtPendingAmmount, txtDateofBirth,
			txtStartDate, txtEndDate;

	private JComboBox cmbFacility, cmbGender, cmbSubscripation, cmbAmmontStutus;
	SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
	JDatePickerImpl datePickerDateofbirth;
	JDatePickerImpl datePickerStart;
	JDatePickerImpl datePickerEnd;
	JPanel panelFirst;

	public NewMember() {
		con = DbConnection.getConnection();
		try {
			statement = (Statement) con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		JFrame frame = new JFrame("Welcome Page");

		JLabel lbPhoto = new JLabel(" ");

		URL url = this.getClass().getResource("/image/logo.jpg");
		ImageIcon icon = new ImageIcon(url);
		lbPhoto.setIcon(icon);

		lbPhoto.setBounds(80, 50, 250, 250);
		frame.add(lbPhoto);
		// frame.add(panelFirst);

		JPanel panelFirst1 = new JPanel();
		panelFirst1.setBounds(30, 350, 350, 300);

		GridBagConstraints gbc0 = new GridBagConstraints();
		gbc0.insets = new Insets(5, 5, 5, 5);
		JLabel lbRegis = new JLabel("Registration no");

		txtRegis = new JTextField(15);

		JButton btnfetch = new JButton("Fetch");
		btnfetch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					String registrationNumber = txtRegis.getText();
					Member existingEmployee = MemberDAO.getMember(registrationNumber);

					if (existingEmployee != null) {
						txtRegis.setText(existingEmployee.getRegis());
						txtFirstName.setText(existingEmployee.getFirstName());
						txtLastName.setText(existingEmployee.getLastName());
						txtContactNO.setText(existingEmployee.getContactNo());
						txtAlternateNO.setText(existingEmployee.getAlternateNo());
						txtEmailAdd.setText(existingEmployee.getEmailAdd());
						txtOccupation.setText(existingEmployee.getOccupation());
						txtAddress.setText(existingEmployee.getAddress());
						txtPersonalTrainer.setText(existingEmployee.getPersonalTrainer());
						cmbSubscripation.setToolTipText(existingEmployee.getSubscripation());
						txtAmmont.setText(existingEmployee.getAmmount());
						txtPendingAmmount.setText(existingEmployee.getPendingAmmount());
						cmbFacility.setToolTipText(existingEmployee.getFacility());
						cmbGender.setToolTipText(existingEmployee.getGender());
						cmbAmmontStutus.setToolTipText(existingEmployee.getAmmountStutus());

						String[] date = existingEmployee.getDateofBirth().split("-");
						datePanelDateofbirth.getModel().setDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
								Integer.parseInt(date[2]));

						String[] date1 = existingEmployee.getStartDate().split("-");
						datePanelStart.getModel().setDate(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]),
								Integer.parseInt(date1[2]));

						String[] date2 = existingEmployee.getEndDate().split("-");
						datePanelEnd.getModel().setDate(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]),
								Integer.parseInt(date2[2]));
					} else {
						JOptionPane.showMessageDialog(null, "Registration address is not present in database!!");
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		gbc0.gridx = 0;
		gbc0.gridy = 0;
		panelFirst1.add(lbRegis, gbc0);
		gbc0.gridx = 1;
		gbc0.gridy = 0;
		panelFirst1.add(txtRegis, gbc0);
		gbc0.gridx = 1;
		gbc0.gridy = 1;
		panelFirst1.add(btnfetch, gbc0);

		frame.add(panelFirst1);

		/*-----------------------Second Panel Left START-----------------------------------------------*/

		JPanel panelSecond = new JPanel();
		panelSecond.setBounds(400, 40, 475, 300);
		panelSecond.setBackground(Color.cyan);

		panelSecond.setBorder(BorderFactory.createTitledBorder("Personol Info"));

		panelSecond.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel lbFirstName = new JLabel("First Name");
		JLabel lbLastName = new JLabel("Last Name");
		txtFirstName = new JTextField(15);
		txtLastName = new JTextField(15);

		JLabel lbContactNO = new JLabel("Contact NO");
		JLabel lbAlternateNO = new JLabel("Alternate NO");
		txtContactNO = new JTextField(15);
		txtAlternateNO = new JTextField(15);

		JLabel lbDateOfbirth = new JLabel("Date of Birth");
		// txtDateofbirth = new JTextField(15);
		AbstractFormatter formatter1 = new MyAbstractFormatter();

		UtilDateModel modelDateofbirth = new UtilDateModel();
		modelDateofbirth.setValue(new Date());
		datePanelDateofbirth = new JDatePanelImpl(modelDateofbirth, new Properties());
		datePickerDateofbirth = new JDatePickerImpl(datePanelDateofbirth, formatter1);

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelSecond.add(lbFirstName, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelSecond.add(txtFirstName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelSecond.add(lbLastName, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelSecond.add(txtLastName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelSecond.add(lbContactNO, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelSecond.add(txtContactNO, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelSecond.add(lbAlternateNO, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelSecond.add(txtAlternateNO, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panelSecond.add(lbDateOfbirth, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panelSecond.add(datePickerDateofbirth, gbc);

		frame.add(panelSecond);

		/*-----------------------Second Panel Left END-----------------------------------------------*/

		/*-----------------------Second PanelRight START-----------------------------------------------*/

		JPanel panelSecondLeft = new JPanel();
		panelSecondLeft.setBounds(875, 40, 475, 300);
		panelSecondLeft.setBackground(Color.cyan);
		panelSecondLeft.setBorder(BorderFactory.createTitledBorder(" "));

		panelSecondLeft.setLayout(new GridBagLayout());
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.insets = new Insets(5, 5, 5, 5);

		JLabel lbGender = new JLabel("Gender");

		// txtGender = new JTextField(15);
		String comboContent[] = { "Please Select", "Male", "Female" };
		cmbGender = new JComboBox(comboContent);
		cmbGender.setMinimumSize(new Dimension(300, 50));
		cmbGender.setMaximumSize(new Dimension(300, 50));
		JLabel lbEmail = new JLabel("E-mail");
		txtEmailAdd = new JTextField(15);

		JLabel lbOccupation = new JLabel("Occupation");
		JLabel lbAddress = new JLabel("Address");
		txtOccupation = new JTextField(15);
		txtAddress = new JTextField(15);

		gbc1.gridx = 0;
		gbc1.gridy = 0;
		panelSecondLeft.add(lbGender, gbc1);
		gbc1.gridx = 0;
		gbc1.gridy = 1;
		panelSecondLeft.add(lbEmail, gbc1);

		gbc1.gridx = 1;
		gbc1.gridy = 0;
		gbc1.gridwidth = GridBagConstraints.REMAINDER;
		panelSecondLeft.add(cmbGender, gbc1);

		gbc1.gridx = 1;
		gbc1.gridy = 1;
		panelSecondLeft.add(txtEmailAdd, gbc1);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelSecondLeft.add(lbOccupation, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelSecondLeft.add(txtOccupation, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelSecondLeft.add(lbAddress, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelSecondLeft.add(txtAddress, gbc);

		frame.add(panelSecondLeft);

		/*-----------------------Second Panel Right END-----------------------------------------------*/

		/*-----------------------Third Panel Left START-----------------------------------------------*/

		JPanel panelThird = new JPanel();
		panelThird.setBounds(875, 350, 475, 300);
		panelThird.setBackground(Color.cyan);
		panelThird.setBorder(BorderFactory.createTitledBorder(" "));

		panelThird.setLayout(new GridBagLayout());
		GridBagConstraints gbcThird = new GridBagConstraints();
		gbcThird.insets = new Insets(5, 5, 5, 5);

		JLabel lbStartDate = new JLabel("Start Date");
		JLabel lbEndDate = new JLabel("End Date");

		JLabel lbPersonalTrainer = new JLabel("Personal Trainer");
		JLabel lbFacility = new JLabel("Facility");
		txtPersonalTrainer = new JTextField(15);

		String comboContent1[] = { "Please Select", "Strength", "Cardio", "Aerobics", "Combo" };
		cmbFacility = new JComboBox(comboContent1);
		cmbFacility.setMinimumSize(new Dimension(300, 50));
		cmbFacility.setMaximumSize(new Dimension(300, 50));
		// cmbFacility.setBounds(50, 50, 90, 20);

		gbcThird.gridx = 0;
		gbcThird.gridy = 0;
		panelThird.add(lbStartDate, gbcThird);
		gbcThird.gridx = 0;
		gbcThird.gridy = 1;
		panelThird.add(lbEndDate, gbcThird);

//		String datePattern = "yyyy-MM-dd";
//		SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		AbstractFormatter formatter = new MyAbstractFormatter();

		UtilDateModel modelStart = new UtilDateModel();
		modelStart.setValue(new Date());

		JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStart, new Properties());
		datePickerStart = new JDatePickerImpl(datePanelStart, formatter);

		UtilDateModel modelEnd = new UtilDateModel();
		modelEnd.setValue(new Date());
		JDatePanelImpl datePanelEnd = new JDatePanelImpl(modelEnd, new Properties());
		datePickerEnd = new JDatePickerImpl(datePanelEnd, formatter);

		gbcThird.gridx = 1;
		gbcThird.gridy = 0;
		panelThird.add(datePickerStart, gbcThird);
		gbcThird.gridx = 1;
		gbcThird.gridy = 1;
		panelThird.add(datePickerEnd, gbcThird);

		gbcThird.gridx = 0;
		gbcThird.gridy = 2;
		panelThird.add(lbPersonalTrainer, gbcThird);
		gbcThird.gridx = 0;
		gbcThird.gridy = 3;
		panelThird.add(lbFacility, gbcThird);

		gbcThird.gridx = 1;
		gbcThird.gridy = 2;
		panelThird.add(txtPersonalTrainer, gbcThird);
		gbcThird.gridx = 1;
		gbcThird.gridy = 3;

		gbcThird.gridwidth = GridBagConstraints.REMAINDER;
		panelThird.add(cmbFacility, gbcThird);

		frame.add(panelThird);

		/*-----------------------Third Panel Left END-----------------------------------------------*/

		/*-----------------------Third PanelRight START-----------------------------------------------*/

		JPanel panelThirdRight = new JPanel();
		panelThirdRight.setBounds(400, 350, 475, 300);
		panelThirdRight.setBackground(Color.cyan);
		panelThirdRight.setBorder(BorderFactory.createTitledBorder("GYM Info"));

		panelThirdRight.setLayout(new GridBagLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.insets = new Insets(5, 5, 5, 5);

		JLabel lbSubscripation = new JLabel("Subscripation");
		JLabel lbAmmont = new JLabel("Ammont");
		txtSubscripation = new JTextField(15);
		String comboContent2[] = { "Please Select", "Monthly", "3 month", "6 month", "1 yearly" };
		cmbSubscripation = new JComboBox(comboContent2);
		cmbSubscripation.setMinimumSize(new Dimension(300, 50));
		cmbSubscripation.setMaximumSize(new Dimension(300, 50));

		txtAmmont = new JTextField(15);

		JLabel lbAmmontStutus = new JLabel("Ammont Stutus");
		JLabel lbPendingAmmount = new JLabel("Pending Ammount");
		String comboContent3[] = { "Please Select", "Blance", "Paid" };
		cmbAmmontStutus = new JComboBox(comboContent3);
		cmbAmmontStutus.setMinimumSize(new Dimension(300, 50));
		cmbAmmontStutus.setMaximumSize(new Dimension(300, 50));
		txtPendingAmmount = new JTextField(15);

		gbc2.gridx = 0;
		gbc2.gridy = 0;
		panelThirdRight.add(lbSubscripation, gbc2);
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		panelThirdRight.add(lbAmmont, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 0;
		// gbc2.gridwidth = GridBagConstraints.REMAINDER;
		panelThirdRight.add(cmbSubscripation, gbc2);
		gbc2.gridx = 1;
		gbc2.gridy = 1;
		panelThirdRight.add(txtAmmont, gbc2);

		gbc2.gridx = 0;
		gbc2.gridy = 2;
		panelThirdRight.add(lbAmmontStutus, gbc2);
		gbc2.gridx = 0;
		gbc2.gridy = 3;
		panelThirdRight.add(lbPendingAmmount, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 2;
		panelThirdRight.add(cmbAmmontStutus, gbc2);
		gbc2.gridx = 1;
		gbc2.gridy = 3;
		panelThirdRight.add(txtPendingAmmount, gbc2);

		frame.add(panelThirdRight);

		/*-----------------------Third Panel Right END-----------------------------------------------*/

		JButton jbtn = new JButton("Save");
		jbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("button click" + e.getSource());

				String sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				String registeration = txtRegis.getText();
				String FirstName = txtFirstName.getText();
				String LastName = txtLastName.getText();
				String ContactNO = txtContactNO.getText();
				String AlternateNO = txtAlternateNO.getText();
				String EmailAdd = txtEmailAdd.getText();

				String Dateofbirth = format.format(datePickerDateofbirth.getModel().getValue());

				String Gender = cmbGender.getSelectedItem().toString();
				String Occupation = txtOccupation.getText();
				String Address = txtAddress.getText();

				String StartDate = format.format(datePickerStart.getModel().getValue());

				String EndDate = format.format(datePickerEnd.getModel().getValue());

				String PersonalTrainer = txtPersonalTrainer.getText();
				String Facility = cmbFacility.getSelectedItem().toString();
				String Subscripation = cmbSubscripation.getSelectedItem().toString();
				String Ammount = txtAmmont.getText();
				String AmmountStutus = cmbAmmontStutus.getSelectedItem().toString();
				;
				String PendingAmmount = txtPendingAmmount.getText();

				try {
					if (registeration.equals("")) {
						JOptionPane.showMessageDialog(null, "please enter the Registration");

						return;
					}

					else if (FirstName.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the first name");
						return;
					}

					else if (LastName.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the last name");
						return;

					} else if (ContactNO.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the contact number");
						return;

					} else if (AlternateNO.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Alternate number");
						return;

					} else if (EmailAdd.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Email Address");
						return;

					} else if (Dateofbirth.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Date of birth");
						return;

					} else if (Gender.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Gender");
						return;

					} else if (Occupation.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Occupation");
						return;

					} else if (Address.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Address");
						return;

					} else if (StartDate.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Start date");
						return;

					} else if (EndDate.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the End date");
						return;

					} else if (PersonalTrainer.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Personal Trainer");
						return;

					} else if (Facility.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Facility");
						return;

					} else if (Subscripation.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Subscripation");
						return;

					} else if (Ammount.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Ammont");
						return;

					} else if (AmmountStutus.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Ammount Stutus");
						return;

					} else if (PendingAmmount.equals("")) {
						JOptionPane.showMessageDialog(null, "please fill the Pending Ammount");
						return;

					}

					PreparedStatement pdt = con.prepareStatement(sql);
					pdt.setString(1, registeration);
					pdt.setString(2, FirstName);
					pdt.setString(3, LastName);
					pdt.setString(4, ContactNO);
					pdt.setString(5, AlternateNO);
					pdt.setString(6, EmailAdd);
					pdt.setString(7, Dateofbirth);
					pdt.setString(8, Gender);
					pdt.setString(9, Occupation);
					pdt.setString(10, Address);
					pdt.setString(11, StartDate);
					pdt.setString(12, EndDate);
					pdt.setString(13, PersonalTrainer);
					pdt.setString(14, Facility);
					pdt.setString(15, Subscripation);
					pdt.setString(16, Ammount);
					pdt.setString(17, AmmountStutus);
					pdt.setString(18, PendingAmmount);

					pdt.executeUpdate();
					pdt.close();
					con.close();

					Mail.sendEmailNewMamber(EmailAdd,registeration);

					// System.out.println("Button CLick 2 " +pdt);
					JOptionPane.showMessageDialog(null, "saved");
					nullAfterSaveData();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "not saved");
					e2.printStackTrace();
				}
			}
		});
		jbtn.setBounds(550, 660, 70, 30);
		frame.add(jbtn);

		JButton btnPrint = new JButton("Print Bill");
		 btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   new InvoicePopup();
				
			}// 
		});

		
		btnPrint.setBounds(700, 660, 70, 30);
		JButton btnupdate = new JButton("Update");
		btnupdate.setBounds(850, 660, 70, 30);
		btnupdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateMember();
					JOptionPane.showMessageDialog(null, "Equipment Update Successfully");

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Equipment Update failed");

				}

			}
		});
		frame.add(btnupdate);

		frame.add(btnPrint);
		JButton btndelete = new JButton("Delete");
		btndelete.setBounds(990, 660, 70, 30);
		frame.add(btndelete);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void updateMember() throws Exception {

		DateModel<?> model = datePickerDateofbirth.getModel();
		String datePickerDateofbirth = model.getYear() + "-" + model.getMonth() + "-" + model.getDay();

		DateModel<?> model1 = datePickerStart.getModel();
		String datePickerStart = model1.getYear() + "-" + model1.getMonth() + "-" + model1.getDay();

		DateModel<?> model2 = datePickerEnd.getModel();
		String datePickerEnd = model2.getYear() + "-" + model2.getMonth() + "-" + model2.getDay();

		String registeration = txtRegis.getText();
		String FirstName = txtFirstName.getText();
		String LastName = txtLastName.getText();
		String ContactNO = txtContactNO.getText();
		String AlternateNO = txtAlternateNO.getText();
		String EmailAdd = txtEmailAdd.getText();

		// String Dateofbirth = format.format(model);

		String Gender = cmbGender.getSelectedItem().toString();
		String Occupation = txtOccupation.getText();
		String Address = txtAddress.getText();

		// String StartDate = format.format(datePickerStart.getModel().getValue());

		// String EndDate = format.format(datePickerEnd.getModel().getValue());

		String PersonalTrainer = txtPersonalTrainer.getText();
		String Facility = cmbFacility.getSelectedItem().toString();
		String Subscripation = cmbSubscripation.getSelectedItem().toString();
		String Ammount = txtAmmont.getText();
		String AmmountStutus = cmbAmmontStutus.getSelectedItem().toString();
		String PendingAmmount = txtPendingAmmount.getText();

		Member member = new Member();
		member.setsRegis(registeration);
		member.setFirstName(FirstName);
		member.setLastName(LastName);
		member.setGender(Gender);
		member.setContactNo(ContactNO);
		member.setAlternateNo(AlternateNO);
		member.setEmailAdd(EmailAdd);
		member.setDateofBirth(datePickerDateofbirth);
		member.setOccupation(Occupation);
		member.setAddress(Address);
		member.setStartDate(datePickerStart);
		member.setEndDate(datePickerEnd);
		member.setPersonalTrainer(PersonalTrainer);
		member.setFacility(Facility);
		member.setSubscripation(Subscripation);
		member.setAmmount(Ammount);
		member.setAmmountStutus(AmmountStutus);
		member.setPendingAmmount(PendingAmmount);

		boolean isUpdate = MemberDAO.updateMember(member);

		if (isUpdate == true) {
			JOptionPane.showMessageDialog(null, "Data updatd");
			nullAfterSaveData();
		} else {
			JOptionPane.showMessageDialog(null, "Update failed");
			nullAfterSaveData();
		}
	}

	private void nullAfterSaveData() {
		// Set Null after save data.
		txtFirstName.setText("");
		txtLastName.setText("");
		cmbGender.setToolTipText("");
		txtContactNO.setText("");
		txtAlternateNO.setText("");
		txtEmailAdd.setText("");
		txtOccupation.setText("");
		txtAddress.setText("");
		txtPersonalTrainer.setText("");
		txtAmmont.setText("");
		txtPendingAmmount.setText("");
	}

}
