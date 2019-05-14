
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import db.DbConnection;

public class Facility
{
	Connection con;
	Statement statement;
	ResultSet resultSet;
	 public Facility() {
		 
		con= DbConnection.getConnection();
		try{
			statement=(Statement) con.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	
	  {
		JFrame frame = new JFrame("Facility");
		Container cont = frame.getContentPane();
		cont.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		JLabel lbEmail= new JLabel("Facility");
		
		final JTextField txtFacility = new JTextField(15);
		
		JButton btnOK = new JButton("Save");
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button click" +e.getSource());
				
				String sql="INSERT INTO facility VALUES(?)";
				try
				{
					PreparedStatement pdt = (PreparedStatement) con.prepareStatement(sql);
					pdt.setString(1, txtFacility.getText());
					pdt.executeUpdate();
					pdt.close();
					con.close();
				    JOptionPane.showMessageDialog(null, "saved");
				}
				catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null, "not saved");
					e2.printStackTrace();
				}
				
			}
		});
			
		
		gbc.gridx=0;
		gbc.gridy=0;
		cont.add(lbEmail,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		cont.add(txtFacility,gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		cont.add(btnOK,gbc);
		
		
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		Container c=frame.getContentPane(); 
	     c.setLayout(null);
	     c.setBackground(Color.CYAN); 
	}
	}

}
