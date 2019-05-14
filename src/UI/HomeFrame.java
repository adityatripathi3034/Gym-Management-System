package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import com.sun.org.apache.bcel.internal.generic.BALOAD;

public class HomeFrame extends JFrame implements ActionListener {
	public HomeFrame() {
		super("GYMS");
		
        setJMenuBar(getMyMenuBar());
		URL url = Wellcome.class.getResource("/image/7.jpg");
		ImageIcon icon = new ImageIcon(url);

		JLabel lbBackGroundImage = new JLabel(icon);
		setContentPane(lbBackGroundImage);

		Container con = getContentPane();
		con.setLayout(new GridBagLayout());

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);


		
	}

	private JMenuBar getMyMenuBar() {

		JMenuBar menuBar = new JMenuBar();
     	UIManager.put("Menubar.background", Color.cyan);
		UIManager.put("Menu.opaque", true);
//		menuBar.setOpaque(false);
//		menuBar.setBackground(Color.GREEN);

		JMenu mnuMembers = new JMenu("Members");
		JMenu mnuTransection = new JMenu("Transection");
		JMenu mnuEqiupment = new JMenu("Eqiupment");
		JMenu mnuManageAccount = new JMenu("Manage Account");
		JMenu mnuTrainer = new JMenu("Trainer");
		JMenu mnuReports = new JMenu("Filter");
	//	JMenu mnuViewallMember = new JMenu("View All Member");
		JMenu mnuAccount = new JMenu("Account");
		JMenu mnuLogout = new JMenu("LogOut");
		
		JMenuItem itemLogOut = new JMenuItem("LogOut");
		itemLogOut.addActionListener(this);
		
	
//		JMenuItem itemBankinfo = new JMenuItem("Bank info");
//		itemBankinfo.addActionListener(this);

		JMenuItem itemAddnewMember = new JMenuItem("Add new Member");
		itemAddnewMember.addActionListener(this);
		

		JMenuItem itemviewallmember=new JMenuItem("View All Member");
		itemviewallmember.addActionListener(this);
		
		JMenuItem itemEnquiryForm = new JMenuItem("Enquiry Form");
		itemEnquiryForm.addActionListener(this);
		JMenuItem itemViewallEnquiryForm = new JMenuItem("View all Enquiry Form");
		 itemViewallEnquiryForm.addActionListener(this);

		JMenuItem itemBlancefee = new JMenuItem("Blancefee");
		itemBlancefee.addActionListener(this);
		JMenuItem itemAddEqiupment = new JMenuItem("Add Eqiupment");
		itemAddEqiupment.addActionListener(this);
		JMenuItem itemviewallequipment = new JMenuItem("View all Equipment");
		itemviewallequipment.addActionListener(this);
		
		JMenuItem itemAddnewsubscription = new JMenuItem("Add new subscription");
		itemAddnewsubscription.addActionListener(this);
		JMenuItem itemAddnewfacility = new JMenuItem("Add new facility");
		itemAddnewfacility.addActionListener(this);

		JMenuItem itemAddnewTrainer = new JMenuItem("Add new Trainer");
		itemAddnewTrainer.addActionListener(this);
		JMenuItem itemUpdateDeleteTrainer = new JMenuItem("View All Trainer");
		itemUpdateDeleteTrainer.addActionListener(this);
		//JMenuItem itemViewclientundertrainer = new JMenuItem("item View client under trainer");

		JMenuItem itemcollectionReport = new JMenuItem("Filter by Date");
		itemcollectionReport.addActionListener(this);
		JMenuItem itemdefaulter = new JMenuItem("Defaulter");
		itemdefaulter.addActionListener(this);

		menuBar.add(mnuMembers);
		mnuMembers.add(itemAddnewMember);
		mnuMembers.addSeparator();
		mnuMembers.add(itemviewallmember);
		mnuMembers.addSeparator();
		mnuMembers.add(itemEnquiryForm);
		mnuMembers.addSeparator();
		mnuMembers.add(itemViewallEnquiryForm);

		menuBar.add(mnuTransection);
		mnuTransection.add(itemBlancefee);

		menuBar.add(mnuEqiupment);
		mnuEqiupment.add( itemAddEqiupment);
		mnuEqiupment.addSeparator();
		mnuEqiupment.add(itemviewallequipment);

		menuBar.add(mnuManageAccount);
	
		
		mnuManageAccount.add(itemAddnewsubscription);
		mnuManageAccount.addSeparator();
		mnuManageAccount.add(itemAddnewfacility);

		menuBar.add(mnuTrainer);
		mnuTrainer.add(itemAddnewTrainer);
		mnuTrainer.addSeparator();
		mnuTrainer.add(itemUpdateDeleteTrainer);
		mnuTrainer.addSeparator();
		//mnuTrainer.add(itemViewclientundertrainer);

		menuBar.add(mnuReports);
		mnuReports.add(itemcollectionReport);
		mnuReports.addSeparator();
		mnuReports.add(itemdefaulter);

	//	menuBar.add(mnuRemainder);
		menuBar.add(mnuAccount);
		
		menuBar.add(mnuLogout);
		
		mnuLogout.add(itemLogOut);
//		mnuAccount.add(itemBankinfo);
//		
//		

		return menuBar;
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
	//	System.out.println(cmd);

		
		if ("LogOut".equals(cmd)) {
			super.dispose();
			new Wellcome();
			
		} 
		if ("Add new Member".equals(cmd)) {

			new NewMember();
		} 
		else if("View All Member".equals(cmd)){
			new ViewallMember();
		}
		
		
		else if("Enquiry Form".equals(cmd)){
			
			new Enquiry();
		}
		else if("View all Enquiry Form".equals(cmd)){
			new ViewallEnq();
			
		}
		else if("Blance fee".equals(cmd)){
			new BlanceFee();
		}
		else if("Add new Trainer".equals(cmd)){
			new AddTrainer();
		}
		else if("Add Eqiupment".equals(cmd)){
            new AddEqiupment();
	   }
		else if("View all Equipment".equals(cmd)){
		
			new ViewAllEquipment();
			
		}
		else if("View All Trainer".equals(cmd)){
            new ViewAllTrainer();
          
		}
     	else if("Add new subscription".equals(cmd)){
           new Subscription();
     	}
     	else if("Blancefee".equals(cmd)){
            new BlanceFee();
     	}
     	else if("Add new facility".equals(cmd)) {
     		new Facility();   
       }
     	else if("Bank info".equals(cmd)) {
     		new BankAccount();
     	
     	}
     	else if("All Registration".equals(cmd)) {
     		new ViewallMember();
     	}else if("Defaulter".equals(cmd)) {
     		new Defaulter();
     	}else if("Filter by Date".equals(cmd)) {
     		new Filter();
     		
     	}else {
     		new Wellcome();
     		
		}
}
}