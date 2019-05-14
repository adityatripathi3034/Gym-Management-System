package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Equipment;
import model.Trainer;

public class TrainerDAO {

	public static Trainer getTrainer(String emailID) throws Exception {

		Trainer trainer = null;
		Connection con = DbConnection.getConnection();
		String select = "SELECT * FROM trainer WHERE EmailAdd=?";

		PreparedStatement ps = con.prepareStatement(select);

		ps.setString(1, emailID);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			trainer = new Trainer();
			trainer.setName(rs.getString("name"));
			trainer.setAge(rs.getInt("age"));
			trainer.setGender(rs.getString("gender"));
			trainer.setPackageYearly(rs.getInt("packageYearly"));
			trainer.setMobileno(rs.getInt("mobileno"));
			trainer.setEmailAdd(rs.getString("emailadd"));
			trainer.setDateofjoining(rs.getString("dateofjoining"));

		}

		rs.close();
		ps.close();
		con.close();

		return trainer;

	}

	public static boolean updateTrainer(Trainer trainer) throws Exception {

		Connection con = DbConnection.getConnection();
		String select = "UPDATE trainer SET name = ?, age=?, gender=?, packageYearly=?, mobileno=?, emailadd=?, dateofjoining=? WHERE EmailAdd=?";

		PreparedStatement ps = con.prepareStatement(select);

		ps.setString(1, trainer.getName());
		ps.setInt(2, trainer.getAge());
		ps.setString(3, trainer.getGender());
		ps.setInt(4, trainer.getPackageYearly());
		ps.setInt(5, trainer.getMobileno());
		ps.setString(6, trainer.getEmailAdd());
		ps.setString(7, trainer.getDateofjoining());
		
		ps.setString(8, trainer.getEmailAdd());

		ps.executeUpdate();

		ps.close();
		con.close();
		return true;

	}
	
	// Delete by id for Employee.
		public static boolean delete(String email) {
			Connection con = DbConnection.getConnection();

			try {
				String delete = "DELETE FROM trainer where emailadd = ?";

				PreparedStatement ps = con.prepareStatement(delete);

				ps.setString(1, email);
				ps.executeUpdate();

				ps.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			return true;

		}

	
public static boolean insert(Trainer tr) {
	Connection con = DbConnection.getConnection();

	try {

		String insert = "INSERT INTO trainer(Name,Age,gender,PackageYearly,mobileno,EmailAdd,dateofjoining) VALUES(?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(insert);

		ps.setString(1, tr.getName());
		ps.setInt(2, tr.getAge());
		ps.setString(3, tr.getGender());
		ps.setInt(4, tr.getPackageYearly());
		ps.setInt(5, tr.getMobileno());
		ps.setString(6, tr.getEmailAdd());
		ps.setString(7, tr.getDateofjoining());
		
		
		
		ps.executeUpdate();

		ps.close();
		con.close();
	} catch (SQLException e) {

		e.printStackTrace();
	}
	return true;

}
}