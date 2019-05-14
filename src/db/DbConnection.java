package db;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DbConnection {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost/gms?user=root&password=root");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Driver not loaded successfully...");
			e.printStackTrace();
		}

		return connection;
	}

}