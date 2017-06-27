package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.Registration;
public class RegistrationDB extends DBAO{
	
	public RegistrationDB(){
		super();
	}
	public ArrayList<Registration> getRegistration(String statement){
		ArrayList<Registration> regList = new ArrayList<Registration>();
		try {
			if(statement == null){
				statement = "SELECT * FROM "+ schema +".registration ORDER BY registrationId DESC";
			}
			PreparedStatement ps;
			ps = con.prepareStatement(statement);
			
			System.out.println("log Registration.java :" + ps);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Registration reg = new Registration();
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
