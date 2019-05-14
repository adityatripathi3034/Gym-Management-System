package UI;


import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import db.MemberDAO;
import db.TrainerDAO;
import invoice.InvoicePDF;
import model.Member;

public class InvoicePopup {
	private JButton buttonPrintbill, buttonCancel;
	private JTextField txtRegis;
	private JLabel labelId;
	private JFrame frame;

	public InvoicePopup() {
		deletePopupStart();
	}

	private void deletePopupStart() {
		frame = new JFrame("Genrate Invoice");
		labelId = new JLabel("Enter Registration no ");
		labelId.setBounds(20, 38, 200, 30);
		frame.add(labelId);

		txtRegis = new JTextField();
		txtRegis.setBounds(130, 40, 150, 25);
		frame.add(txtRegis);

		buttonPrintbill = new JButton("Print Bill");
		buttonPrintbill.setBounds(30, 100, 80, 30);
		frame.add(buttonPrintbill);
		buttonPrintbill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getMemberByRegis();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		buttonCancel = new JButton("Cancel");
		buttonCancel.setBounds(150, 100, 80, 30);
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
	private void getMemberByRegis() throws Exception {
		
		String regis = txtRegis.getText();
		System.out.println("Test regis" + MemberDAO.getMember(regis).getRegis());
		try {
			if (regis.equals(MemberDAO.getMember(regis).getRegis())) {
				
				Member memberData = MemberDAO.getMember(regis);
				InvoicePDF.startGUI(memberData);
				JOptionPane.showMessageDialog(null, "Invoice bill print Successfully!");
				frame.dispose();
				
				openPDF();
				
			}else {
				JOptionPane.showMessageDialog(null, "Some Error occured!!");
				
			}
			
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Please enter the valid Regis!");
		}
	}
	
	private void openPDF() {
		if (Desktop.isDesktopSupported()) {
            try {
            	String path = "C:\\Users\\aditya\\AppData\\Local\\Genuitec\\MyEclipse-8.6\\GMS\\GMS_Invoice.pdf";
                File myFile = new File(path);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
            	JOptionPane.showMessageDialog(null, "Error!!!!!!!!!!!");
            }
        }
	}

}

