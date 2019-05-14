package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import model.Member;

public class MemberDAO {

	public static Member getMember(String regis) throws Exception {

		Member member = null;
		Connection con = DbConnection.getConnection();
		String select = "SELECT * FROM member WHERE regis=?";

		PreparedStatement pdt = con.prepareStatement(select);

		pdt.setString(1, regis);

		ResultSet rs = pdt.executeQuery();

		if (rs.next()) {
			member = new Member();
			member.setsRegis(rs.getString("regis"));
			member.setFirstName(rs.getString("firstname"));
			member.setLastName(rs.getString("lastname"));
			member.setGender(rs.getString("gender"));
			member.setContactNo(rs.getString("contactno"));
			member.setAlternateNo(rs.getString("alternateno"));
			member.setEmailAdd(rs.getString("emailadd"));
			member.setDateofBirth(rs.getString("dateofbirth"));
			member.setOccupation(rs.getString("occupation"));
			member.setAddress(rs.getString("address"));
			member.setStartDate(rs.getString("startdate"));
			member.setEndDate(rs.getString("enddate"));
			member.setPersonalTrainer(rs.getString("personaltrainer"));
			member.setFacility(rs.getString("facility"));
			member.setSubscripation(rs.getString("subscripation"));
			member.setAmmount(rs.getString("Ammount"));
			member.setAmmountStutus(rs.getString("amount_status"));
			member.setPendingAmmount(rs.getString("PendingAmmount"));

		}

		rs.close();
		pdt.close();
		con.close();

		return member;

	}

	public static boolean updateMember(Member member) throws Exception {

		Connection con = DbConnection.getConnection();
		String select = "UPDATE member SET  firstname =?, lastname=?, contactno=?,alternateno=?,emailadd=?,dateofbirth=?,gender=? ,occupation=?,address=?,startdate=?,enddate=?,personaltrainer=?,facility=?,subscripation=?,ammount=?,amount_status=?,pendingammount=? WHERE Regis=?";
		PreparedStatement pdt = con.prepareStatement(select);
		pdt.setString(1, member.getRegis());
		pdt.setString(2, member.getFirstName());
		pdt.setString(3, member.getLastName());
		pdt.setString(4, member.getContactNo());
		pdt.setString(5, member.getAlternateNo());
		pdt.setString(6, member.getEmailAdd());
		pdt.setString(7, member.getDateofBirth());
		pdt.setString(8, member.getGender());
		pdt.setString(9, member.getOccupation());
		pdt.setString(10, member.getAddress());
		pdt.setString(11, member.getStartDate());
		pdt.setString(12, member.getEndDate());
		pdt.setString(13, member.getPersonalTrainer());
		pdt.setString(14, member.getFacility());
		pdt.setString(15, member.getSubscripation());
		pdt.setString(16, member.getAmmount());
		pdt.setString(17, member.getAmmountStutus());
		pdt.setString(18, member.getPendingAmmount());

		pdt.executeUpdate();

		pdt.close();
		con.close();
		return true;

	}

	// Delete by id for Employee.
	public static int delete(String regis) {
		Connection con = DbConnection.getConnection();

		try {
			String delete = "DELETE FROM member where regis = ?";

			PreparedStatement ps = con.prepareStatement(delete);

			ps.setString(1, regis);
		int changes=	ps.executeUpdate();

			ps.close();
			con.close();
			
			return changes;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;

	}

}