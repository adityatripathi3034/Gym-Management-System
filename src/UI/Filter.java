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

public class Filter extends JFrame {

	public Filter() {

		Container cont = getContentPane();
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.CYAN);
		cont.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		JLabel lbfullname = new JLabel("Date From");
		JLabel lbgender = new JLabel("Date To");

		gbc.gridx = 0;
		gbc.gridy = 0;
		cont.add(lbfullname, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		cont.add(lbgender, gbc);

		AbstractFormatter formatter = new MyAbstractFormatter();

		UtilDateModel modelStart = new UtilDateModel();
		JDatePanelImpl datePanelDate = new JDatePanelImpl(modelStart, new Properties());
		JDatePickerImpl datePickerDateFrom = new JDatePickerImpl(datePanelDate, formatter);

		UtilDateModel modelStart1 = new UtilDateModel();
		JDatePanelImpl datePanelDateFrom = new JDatePanelImpl(modelStart1, new Properties());
		JDatePickerImpl datePickerDateTo = new JDatePickerImpl(datePanelDateFrom, formatter);

		gbc.gridx = 0;
		gbc.gridy = 1;
		cont.add(datePickerDateFrom, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		cont.add(datePickerDateTo, gbc);

		JButton btnSAVE = new JButton("Serach");
		btnSAVE.setOpaque(true);
		btnSAVE.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dateFrom = datePickerDateFrom.getJFormattedTextField().getText();
				String dateTo = datePickerDateTo.getJFormattedTextField().getText();				
				new FilterByDate(dateFrom, dateTo);
			}
		});

		JButton btncancel = new JButton("Cancel");
		btnSAVE.setOpaque(true);
		btncancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 5;
		cont.add(btnSAVE, gbc);

		gbc.gridx = 2;
		gbc.gridy = 5;
		cont.add(btncancel, gbc);

		setSize(700, 400);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

}
