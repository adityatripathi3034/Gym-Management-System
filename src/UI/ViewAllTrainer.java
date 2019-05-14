package UI;

import java.awt.Color;
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

public class ViewAllTrainer extends JFrame {
	DefaultTableModel tmodel;
	JPanel btnpanel;
	JPanel panel;
	JTable table;
	Connection c1;
	Statement stm;
	ResultSet rs;
	Container con = getContentPane();
	JButton btnDelete, btnUpdate, btnReturn, btnRefresh;

	public ViewAllTrainer() {
		c1 = DbConnection.getConnection();
		try {

			stm = c1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		} catch (Exception e) {
			e.printStackTrace();
		}

		tmodel = new DefaultTableModel();
		table = new JTable(tmodel);

		SetColHeader();
		setSize(800, 650);
		setLocation(250, 300);

		con.add(new JScrollPane(table));

		try {
			rs = stm.executeQuery("select * from trainer");

			while (rs.next()) {
				tmodel.insertRow(0, new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7) });
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "tush");
		}
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

	}

	private void SetColHeader() {
		tmodel.addColumn("Name");
		tmodel.addColumn("Age");
		tmodel.addColumn("Gender");
		tmodel.addColumn("Package Yearly");
		tmodel.addColumn("Mobile");
		tmodel.addColumn("Email");
		tmodel.addColumn("Date of Joining");
		addButtons();

	}

	private void addButtons() {
		JPanel panel = new JPanel();
		panel.setBounds(40, 570, 700, 50);
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(50, 100, 80, 30);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TrainerDeletePopup();
			}
		});

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(100, 100, 80, 30);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Btn call back update");
				new AddTrainer();
			}
		});

		btnReturn = new JButton("Return");
		btnReturn.setBounds(100, 100, 80, 30);
		panel.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Btn call back return");
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
