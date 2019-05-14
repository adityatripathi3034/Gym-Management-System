package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login 
{
  public static void main(String[] args) 
  {
	   
	JFrame frame = new JFrame("GridBagLayout frmae");
	Container con = frame.getContentPane();
	con.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.insets=new Insets(5,5,5,5);
	JLabel lbEmail= new JLabel("Email");
	JLabel lbPass= new JLabel("Password");
	JTextField txtEmail = new JTextField(15);
	JTextField txtPass = new JPasswordField(15);
	JButton btnOK = new JButton("Ok");
	JButton btnExit = new JButton("Exit");
	JButton btnRegistration = new JButton("Registration");
	gbc.gridx=0;
	gbc.gridy=0;
	con.add(lbEmail,gbc);
	gbc.gridx=0;
	gbc.gridy=1;
	con.add(lbPass,gbc);
	gbc.gridx=0;
	gbc.gridy=2;
	con.add(btnOK,gbc);
	gbc.gridx=1;
	gbc.gridy=0;
	con.add(txtEmail,gbc);
	gbc.gridx=1;
	gbc.gridy=1;
	con.add(txtPass,gbc);
	gbc.gridx=1;
	gbc.gridy=2;
	con.add(btnExit,gbc);
	gbc.gridx=2;
	gbc.gridy=2;
	con.add(btnRegistration,gbc);
	frame.setSize(700, 500);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setVisible(true);
	Container c=frame.getContentPane(); 
    c.setLayout(null);
    c.setBackground(Color.CYAN); 
}
}

