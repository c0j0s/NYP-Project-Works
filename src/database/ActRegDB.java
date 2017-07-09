package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.ActReg;
public class ActRegDB extends DBAO{
	
	public ActRegDB(){
		super();
	}
	public ArrayList<ActReg> getRegistration(String statement){
		ArrayList<ActReg> regList = new ArrayList<ActReg>();
		try {
			if(statement == null){
				statement = "SELECT * FROM "+ schema +".registration ORDER BY registrationId DESC";
			}
			PreparedStatement ps;
			ps = con.prepareStatement(statement);
			
			System.out.println("log Registration.java :" + ps);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ActReg reg = new ActReg();
				reg.setRegistrationId(rs.getString("registrationId"));
				reg.setRegistrationDate(java.sql.Timestamp.valueOf(rs.getString("registrationDate")));
				reg.setRegistrationAmtPaid(rs.getDouble("registrationAmountPaid"));
				reg.setParticipantNo(rs.getInt("participantNo"));
				reg.setUserAccountId(rs.getString("useraccountId"));
				reg.setParticipantNo(rs.getInt("participantNo"));
				reg.setActivityactivityId(rs.getString("ActivityactivityId"));
				System.out.println("record retrieve");
				regList.add(reg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regList;
	}

	
}
