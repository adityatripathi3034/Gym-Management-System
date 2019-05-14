package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Equipment;

public class EquipmentDAO {

	public static Equipment getEquipment(long id) throws Exception {

		Equipment equipment = null;
		Connection con = DbConnection.getConnection();
		String select = "SELECT * FROM equipment WHERE id=?";

		PreparedStatement ps = con.prepareStatement(select);

		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			equipment = new Equipment();
			equipment.setId(id);
			equipment.setName(rs.getString("name"));
			equipment.setPrice(rs.getFloat("price"));
			equipment.setQuantity(rs.getInt("Quantity"));

		}

		rs.close();
		ps.close();
		con.close();

		return equipment;

	}

	public static void updateEquipment(Equipment equipment) throws Exception {

		Connection con = DbConnection.getConnection();
		String select = "UPDATE equipment SET name=?, price=?, quantity=? WHERE id=?";

		PreparedStatement ps = con.prepareStatement(select);

		ps.setString(1, equipment.getName());
		ps.setFloat(2, equipment.getPrice());
		ps.setInt(3, equipment.getQuantity());
		ps.setLong(4, equipment.getId());

		ps.executeUpdate();

		ps.close();
		con.close();

	}
	
	//Insert method save data in database.
		public static boolean insert(Equipment eqp) {
			Connection con = DbConnection.getConnection();

			try {

				String insert = "INSERT INTO equipment(name, quantity,price) VALUES(?,?,?)";

				PreparedStatement ps = con.prepareStatement(insert);

				ps.setString(1, eqp.getName());
				ps.setInt(2, eqp.getQuantity());
				ps.setFloat(3, eqp.getPrice());
				
				ps.executeUpdate();

				ps.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			return true;

		}

}
