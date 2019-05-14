package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

import db.DbConnection;
import db.EquipmentDAO;
import model.Equipment;

public class AddEqiupment extends JFrame implements ActionListener {

	Connection con;
	Statement statement;
	ResultSet resultSet;
	final JTextField txtID, txtName, txtQuantity, txtPrice, txttotalcost;
	  
	 
	
	public AddEqiupment() {
		con = DbConnection.getConnection();
		
		try {
			statement = (Statement) con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// super("Add Equipment frame");
		JFrame frame = new JFrame("Add Eqiupment");
		Container cont = frame.getContentPane();
		cont.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		JLabel lbID = new JLabel("ID");
		JLabel lbName = new JLabel("Name");
		JLabel lbQuntity = new JLabel("Quntity");
		JLabel lbperunitcost = new JLabel("Per unit cost");
		JLabel lbtotalcost = new JLabel("Total cost");
		txtID = new JTextField(15);
		txtName = new JTextField(15);
		txtQuantity = new JTextField(15);
		txtPrice = new JTextField(15);
		txttotalcost = new JTextField(15);

		gbc.gridx = 0;
		gbc.gridy = 1;
		cont.add(lbID, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		cont.add(lbName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		cont.add(lbQuntity, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		cont.add(lbperunitcost, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		cont.add(lbtotalcost, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		cont.add(txtID, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		cont.add(txtName, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		cont.add(txtQuantity, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		cont.add(txtPrice, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		cont.add(txttotalcost, gbc);
		JButton btnSAVE = new JButton("SAVE");
		btnSAVE.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			//	System.out.println("button click" + e.getSource());
                String id=txtID.getText();
				String name = txtName.getText();
				String price = txtPrice.getText();
				String qty = txtQuantity.getText();
				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "please enter tthe ID");

					return;
				}

				else if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the  name");
					return;
				}

				else if (price.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the price");
					return;
					
				} else if (qty.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill the Quntity");
					return;

				
				}	
				
				Equipment equipment = new Equipment();
				equipment.setName(name);
				equipment.setPrice(Float.parseFloat(price));
				equipment.setQuantity(Integer.parseInt(qty));
				txttotalcost.setText(String.valueOf(equipment.getPrice() * equipment.getQuantity()));
				
				
				
				boolean isInsert = EquipmentDAO.insert(equipment);
				if (isInsert == true) {
					JOptionPane.showMessageDialog(null, "Equipment inserted Successfull");
					nullAfterSaveData();
				}else {
					JOptionPane.showMessageDialog(null, "Equipment inserted failed");
					nullAfterSaveData();
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 6;
		cont.add(btnSAVE, gbc);

		JButton btnFetch = new JButton("Fetch");
		gbc.gridx = 1;
		gbc.gridy = 6;
		cont.add(btnFetch, gbc);
		JButton btnupdate = new JButton("Update");

		btnupdate.addActionListener(e -> {

			Equipment equipment = new Equipment();

			long id = Long.parseLong(txtID.getText());
			int quantity = Integer.parseInt(txtQuantity.getText());
			float price = Float.parseFloat(txtPrice.getText());

			equipment.setId(id);
			equipment.setName(txtName.getText());
			equipment.setPrice(price);
			equipment.setQuantity(quantity);

			try {
				EquipmentDAO.updateEquipment(equipment);
				

				JOptionPane.showMessageDialog(null, "Equipment Update Successfull");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
		gbc.gridx = 2;
		gbc.gridy = 6;
		cont.add(btnupdate, gbc);
		btnFetch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				long id = Long.parseLong(txtID.getText());

				try {
					Equipment equipment = EquipmentDAO.getEquipment(id);
                    if(equipment!=null) {
					txtName.setText(equipment.getName());
					txtPrice.setText("" + equipment.getPrice());
					txtQuantity.setText("" + equipment.getQuantity());
					txttotalcost.setText(String.valueOf(equipment.getPrice() * equipment.getQuantity()));
                    }else {
                    	JOptionPane.showMessageDialog(null,"id is not present in database");
                    }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

		Container c = frame.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.CYAN);
		


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void nullAfterSaveData() {
		txtID.setText("");
		txtName.setText("");
		txtPrice.setText("");
		txtQuantity.setText("");
		txttotalcost.setText("");
	}
}