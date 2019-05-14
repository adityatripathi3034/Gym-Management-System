package UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import db.DbConnection;

public class ViewallMember extends JFrame {
	JButton btnDelete, btnUpdate, btnReturn, btnRefresh, btnPrintbill;
	DefaultTableModel tmodel;
	JPanel btnpanel;
	JPanel panel;
	JTable table;

	Container con = getContentPane();

	public ViewallMember() {

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		populateTable();
		setSize(1500, 750);
		setLocation(250, 300);
//	JScrollPane scrollPane = new JScrollPane(table);   //Try one
		new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		con.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

	}

	private void populateTable() {

		tmodel = new DefaultTableModel();

		SetColHeader();

		try {
			Connection connection = DbConnection.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from member");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				tmodel.insertRow(0, new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14),
						rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18) });
			}

			rs.close();
			ps.close();
			connection.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "tush");
		}

		table.setModel(tmodel);

	}

	private void SetColHeader() {
		tmodel.addColumn("Registration No");
		tmodel.addColumn("First NAME");
		tmodel.addColumn("Last Name");
		tmodel.addColumn("Contact No");
		tmodel.addColumn("Alternate No");
		tmodel.addColumn("Email");
		tmodel.addColumn("DOB");
		tmodel.addColumn("Sex");
		tmodel.addColumn("Occupation");
		tmodel.addColumn("Address");

		tmodel.addColumn("Start Date");
		tmodel.addColumn("End Date");
		tmodel.addColumn("Personal Trainer");
		tmodel.addColumn("Facility");
		tmodel.addColumn("Subscription");
		tmodel.addColumn("Amount");
		tmodel.addColumn("Amount Status");
		tmodel.addColumn("Pending Amount");
		addButtons();
	}

	private void addButtons() {
		JPanel panel = new JPanel();
		panel.setBounds(370, 670, 700, 50);
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(50, 100, 80, 30);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MemberDeletePopup();
			}
		});

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(100, 100, 80, 30);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println("Btn call back update");
//				new AddTrainer();
			}
		});

		btnReturn = new JButton("Return");
		btnReturn.setBounds(100, 100, 80, 30);
		panel.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(100, 100, 80, 30);
		panel.add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				populateTable();
				// System.out.println("Btn call back refresh");

			}
		});

		btnPrintbill = new JButton("Print Bill");
		btnPrintbill.setBounds(100, 100, 80, 30);
		panel.add(btnPrintbill);
		btnPrintbill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println("Btn call back print bill");
				new InvoicePopup();
			}
		});

		add(panel);
	}
}
