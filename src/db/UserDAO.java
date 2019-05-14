package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	public static boolean authenticate(String admin, String password) throws Exception {

		boolean result = false;
		Connection con = DbConnection.getConnection();
		String select = "SELECT * FROM login WHERE username=? AND password=?";

		PreparedStatement ps = con.prepareStatement(select);

		ps.setString(1, admin);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			result = true;
		}

		rs.close();
		ps.close();
		con.close();

		return result;
	}

	public static String GetAllMembers() throws Exception {
		String result = null;

		Connection con = DbConnection.getConnection();
		String select = "SELECT * FROM new_member";

		PreparedStatement ps = con.prepareStatement(select);
		ResultSet rs = ps.executeQuery();

		rs.close();
		ps.close();
		con.close();

		return result;
	}

	public static String getPassword(String username) throws Exception {

		String password = null;
		Connection con = DbConnection.getConnection();
		String select = "SELECT password FROM login WHERE username=?";

		PreparedStatement ps = con.prepareStatement(select);

		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			password = rs.getString("password");
		}

		rs.close();
		ps.close();
		con.close();

		return password;
	}
}
