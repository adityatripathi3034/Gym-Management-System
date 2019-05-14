package UI;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.UIManager;

import invoice.InvoicePDF;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			 
			//new Wellcome();
			// new TestPanel();
			// new HomeFrame();
			// new AdminPage(null);
			//new Enquiry();
			// new AddTrainer();
		    new NewMember();
			// new UpdateMember();
			// new AddEqiupment();
			// new Subscription();
			// new ViewallEnq();
		//	new Defaulter();
			//new BlanceFee();
			// new Facility();
			// new UpdateTrainer();
			// new BankAccount();
			//new ViewallMember();
			// new ViewAllTrainer();
		//	new Blance();
			//new Paid();
//			new InvoicePDF();
		//	new Filter();
			//new FilterByDate();
			//new ViewAllEquipment();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
