package UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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

public class Paid extends JFrame {
	JButton btnDelete, btnUpdate, btnReturn, btnRefresh;
	DefaultTableModel tmodel;
	JPanel btnpanel;
	JPanel panel;
	JTable table;
	Connection c1;
	Statement stm;
	ResultSet rs;
	Container con = getContentPane();

	public Paid() {
		c1 = DbConnection.getConnection();
		try {

			stm = c1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		} catch (Exception e) {
			e.printStackTrace();
		}

		tmodel = new DefaultTableModel();
		table = new JTable(tmodel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		SetColHeader();
		setSize(1500, 750);
		setLocation(250, 300);
//	JScrollPane scrollPane = new JScrollPane(table);   //Try one
		new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED ); 
		con.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	

		try {
			rs = stm.executeQuery("select * from member where amount_status='Paid'");

			while (rs.next()) {
				tmodel.insertRow(0,
						new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),
								rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11),rs.getString(12),
								rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16),rs.getString(17),rs.getString(18)
				});
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "tush");
		}
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

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
		

		btnReturn = new JButton("Return");
		btnReturn.setBounds(100, 100, 80, 30);
		panel.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnRefresh= new JButton("Refresh");
		btnRefresh.setBounds(100, 100, 80, 30);
		panel.add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Btn call back refresh");
				
			}
		});
		

		add(panel);
	}
}
