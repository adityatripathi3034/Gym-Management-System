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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import db.DbConnection;

public class BankAccount 
{
	
	Connection con;
	Statement statement;
	ResultSet resultSet;

  public BankAccount()
  {
	  con = DbConnection.getConnection();
		try {
			statement = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	JFrame frame = new JFrame("Account info");
	Container cont = frame.getContentPane();
	cont.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.insets=new Insets(10,10,10,10);
	JLabel lbID= new JLabel("Name");
	JLabel lbName= new JLabel("Ammount");
	JLabel lbEmail= new JLabel("Date you Received");
	JLabel lbEmail1= new JLabel("Bank name");
	JLabel lbEmail2= new JLabel("Date to submitted");
	JLabel lbEmail3= new JLabel(" Check number");
    

	
	JTextField txtID = new JTextField(15);
	JTextField txtName = new JTextField(15);
	JTextField txtEmail = new JTextField(15);
	JTextField txtEmail1 = new JTextField(15);
	JTextField txtEmail2 = new JTextField(15);
	JTextField txtEmail3 = new JTextField(15);
	
	
	
	gbc.gridx=0;
	gbc.gridy=1;
	cont.add(lbID,gbc);
	
	
	gbc.gridx=0;
	gbc.gridy=2; 
	cont.add(lbName,gbc);
	
	
	gbc.gridx=0;
	gbc.gridy=3;
	cont.add(lbEmail,gbc);
	
	
	gbc.gridx=0;
	gbc.gridy=4;
	cont.add(lbEmail1,gbc);
	

	gbc.gridx=2;
	gbc.gridy=1;
	cont.add(lbEmail2,gbc);

	gbc.gridx=2;
	gbc.gridy=2;
	cont.add(lbEmail3,gbc);
	
	

	gbc.gridx=1;
	gbc.gridy=1;
	cont.add(txtID,gbc);
	
	
	gbc.gridx=1;
	gbc.gridy=2;
	cont.add(txtName,gbc);
	gbc.gridx=1;
	gbc.gridy=3;
	cont.add(txtEmail,gbc);


	gbc.gridx=1;
	gbc.gridy=4;
	cont.add(txtEmail1,gbc);	
	
	gbc.gridx=3;
	gbc.gridy=1;
	cont.add(txtEmail2,gbc);	

	gbc.gridx=3;
	gbc.gridy=2;
	cont.add(txtEmail3,gbc);	
	
	
	JButton btnOK = new JButton("SAVE");
	
	btnOK.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("button click  " + e.getSource() );
			
			String sql="INSERT INTO bank VALUES (?,?,?,?,?,?)";
			try {
				
				 PreparedStatement pdt =  con.prepareStatement(sql);
				 pdt.setString(1, txtID.getText());
				 pdt.setString(2, txtName.getText());
				 pdt.setString(3, txtEmail.getText());
				 pdt.setString(4, txtEmail1.getText());
				 pdt.setString(5, txtEmail2.getText());
				 pdt.setString(6, txtEmail3.getText());
				 
				 pdt.executeUpdate();
				 
				 pdt.close();
				 con.close();
				 JOptionPane.showMessageDialog(null, "Saved");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Not Saved");
				e2.printStackTrace();
			}

			}
		});

	
	
	gbc.gridx=1;
	gbc.gridy=5;
	cont.add(btnOK,gbc);
	
	
	frame.setSize(800, 600);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setVisible(true);
	Container c=frame.getContentPane(); 
    c.setLayout(null);
    c.setBackground(Color.cyan); 
}
}


