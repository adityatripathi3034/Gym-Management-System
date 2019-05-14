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

import javax.swing.table.DefaultTableModel;

import db.DbConnection;

public class ViewAllEquipment extends JFrame {
	DefaultTableModel tmodel;
	JPanel btnpanel;
	JPanel panel;
	JTable table;
	Connection c1;
	Statement stm;
	ResultSet rs;
	Container con = getContentPane();
	JButton  btnRefresh;

	public ViewAllEquipment() {
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
			rs = stm.executeQuery("select * from equipment");

			while (rs.next()) {
				tmodel.insertRow(0, new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
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
		tmodel.addColumn("ID");
		tmodel.addColumn("Name");
		tmodel.addColumn("Quntity");
		tmodel.addColumn("Price");
			

	}

	
	
		
}
