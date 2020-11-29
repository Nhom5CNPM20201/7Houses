package app.database.interfaces;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import app.database.base.ISQLDatabase;
import app.database.config.HouseHoldConfig;
import app.database.config.PeopleConfig;
import app.entity.HouseHold;
import app.entity.People;
import java.sql.Date;
public interface ISQLDatabasePeople extends ISQLDatabase {
	@Override
    default void insertPeople(People people) throws Exception{
		
		try {
			PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + PeopleConfig.TABLE_NAME +
                    " (" + PeopleConfig.PEOPLE_IDHOUSEHOLD +"," +
                    PeopleConfig.PEOPLE_FULLNAME + "," + PeopleConfig.PEOPLE_NICKNAME + "," + PeopleConfig.PEOPLE_BIRTHPLACE + "," +
                    PeopleConfig.PEOPLE_JOB  + "," + PeopleConfig.PEOPLE_DATEOFBIRTH + "," + PeopleConfig.PEOPLE_REGISDATE + "," +
                    PeopleConfig.PEOPLE_HOUSEHOLDERRELA + "," + PeopleConfig.PEOPLE_ETHNIC + "," + PeopleConfig.PEOPLE_NATIVEPLACE + "," +
                    PeopleConfig.PEOPLE_GENDER + "," + PeopleConfig.PEOPLE_WORKPLACE + "," +
                    PeopleConfig.PEOPLE_IDENTITYNO + "," + PeopleConfig.PEOPLE_IDENTITYMFG + "," + PeopleConfig.PEOPLE_IDENTITYORIGIN +
                    ")" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, people.getIdHouseHold());
			
			stmt.setString(2, people.getFullName());
			
			stmt.setString(3, people.getNickName());
			
			stmt.setString(4, people.getBirthPlace());
			
			stmt.setString(5, people.getJob());
			
			stmt.setDate(6, people.getDateOfBirth());
			
			stmt.setDate(7, people.getRegisDate());   
			
			stmt.setInt(8, people.getHouseHolderRela());
			
			stmt.setString(9, people.getEthnic());
			
			stmt.setString(10, people.getNativePlace());
			
			stmt.setInt(11, people.getGender());
			
			stmt.setString(12, people.getWorkPlace());
			
			stmt.setString(13, people.getIdentityNo());
			
			stmt.setDate(14, people.getIdentityMfg()  );
			
			stmt.setString(15, people.getIdentityOrigin());
			
			stmt.executeUpdate();
			
			
			
			
			ResultSet generatedIDs = stmt.getGeneratedKeys();
            if (generatedIDs.next()) {
                int insertedPeopleID = generatedIDs.getInt(1);
                people.setId(insertedPeopleID);
            }  
		} catch (SQLDataException e) {

        }
	}
	
    @Override
    default List<People> getAllPeoples() throws Exception {
        try {
            Statement stmt = getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
                    " FROM " + PeopleConfig.TABLE_NAME + ";");

            List<People> peopleList = new ArrayList<>();

            while (rs.next()) {
                peopleList.add(extractPeople(rs));
            }

            return peopleList;
        } catch (java.sql.SQLException e) {
            throw e;
        }
    }	
	
	@Override
    default void updatePeople(People people) throws Exception {
		try {
			PreparedStatement stmt = this.getDatabase().prepareStatement("Declare @P1BiDanh nvarchar "+"UPDATE " + PeopleConfig.TABLE_NAME + " SET "
					+ PeopleConfig.PEOPLE_IDHOUSEHOLD  +   "=?"   +  ","  + PeopleConfig.PEOPLE_FULLNAME + "=?" +  ","
					+ PeopleConfig.PEOPLE_NICKNAME  + "=? " + "," + PeopleConfig.PEOPLE_BIRTHPLACE + "=?" +  ","
					+ PeopleConfig.PEOPLE_JOB  + "=? " + "," + PeopleConfig.PEOPLE_DATEOFBIRTH + "=?" +  ","
					+ PeopleConfig.PEOPLE_REGISDATE  + "=? " + "," + PeopleConfig.PEOPLE_HOUSEHOLDERRELA + "=?" +  ","
					+ PeopleConfig.PEOPLE_ETHNIC  + "=? " + "," + PeopleConfig.PEOPLE_NATIVEPLACE + "=?" +  ","
					+ PeopleConfig.PEOPLE_GENDER  + "=? " + "," + PeopleConfig.PEOPLE_WORKPLACE + "=?" +  ","
					+ PeopleConfig.PEOPLE_IDENTITYNO  + "=? " + "," + PeopleConfig.PEOPLE_IDENTITYMFG + "=?"+  ","
					+ PeopleConfig.PEOPLE_IDENTITYORIGIN  + "=? " 
					+ " WHERE " + PeopleConfig.PEOPLE_ID + "= ? " 
					);
			
       //    
			
			stmt.setInt(1, people.getIdHouseHold());
			
			stmt.setString(2, people.getFullName());
			
			stmt.setString(3, people.getNickName() );
			
			stmt.setString(4, people.getBirthPlace());
			
			stmt.setString(5, people.getJob());
			
			stmt.setDate(6, people.getDateOfBirth());
			
			stmt.setDate(7, people.getRegisDate()   ); 
			
			stmt.setInt(8, people.getHouseHolderRela());
			
			stmt.setString(9, people.getEthnic());
			
			stmt.setString(10, people.getNativePlace());
			
			stmt.setInt(11, people.getGender());
			
			stmt.setString(12, people.getWorkPlace());
			
			stmt.setString(13, people.getIdentityNo());
			
			stmt.setDate(14, people.getIdentityMfg()  );
			
			stmt.setString(15, people.getIdentityOrigin());
			
			stmt.setInt(16, people.getId());
			
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			throw e;
		}
		
    }

    @Override
    default void removePeople(People people) throws Exception {
    	try {
    		PreparedStatement stmt = this.getDatabase().prepareStatement("DELETE FROM " + PeopleConfig.TABLE_NAME + " WHERE " 
					+ PeopleConfig.PEOPLE_ID + "=" + "?");
    		stmt.setInt(1, people.getId());
    		stmt.executeUpdate();
    	}
    	catch(SQLException e) {
    		throw e;
    	}
    }
    
    @Override
    default People searchPeople(String fullName) throws Exception {
    	try {
    		Statement stmt = this.getDatabase().createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM " + PeopleConfig.TABLE_NAME + " WHERE " 
    		+ PeopleConfig.PEOPLE_FULLNAME  + " LIKE '%" + fullName + "%';");
    		
    		if(rs.next()) {
    			return extractPeople(rs);
    		}
    		else {
    			System.out.println("khong ton tai du lieu!");
    			return null;
    		}
    	}
    	catch (SQLException e) {
    		throw e;
    	}
    }

    

    private People extractPeople(ResultSet rs) throws SQLException {
    	int peopleId = rs.getInt(PeopleConfig.PEOPLE_ID);
    	
    	int peopleIdHouseHold = rs.getInt(PeopleConfig.PEOPLE_IDHOUSEHOLD);
    	
    	String peopleFullName = rs.getString(PeopleConfig.PEOPLE_FULLNAME);
    	
    	String peopleNickName = rs.getString(PeopleConfig.PEOPLE_NICKNAME);
    	
    	String peopleIdBirthPlace = rs.getString(PeopleConfig.PEOPLE_BIRTHPLACE);
    	
    	String peopleJob = rs.getString(PeopleConfig.PEOPLE_JOB);
    	
    	Date peopleDateOfBirth = rs.getDate(PeopleConfig.PEOPLE_DATEOFBIRTH);
    	
    	Date peopleRegisDate = rs.getDate(PeopleConfig.PEOPLE_REGISDATE);
    	
    	int peopleHouseHolderRela = rs.getInt(PeopleConfig.PEOPLE_HOUSEHOLDERRELA);
    	
    	String peopleEthnic = rs.getString(PeopleConfig.PEOPLE_ETHNIC);
    	
    	String peopleNativePlace = rs.getString(PeopleConfig.PEOPLE_NATIVEPLACE);
    	
    	int peopleGender = rs.getInt(PeopleConfig.PEOPLE_GENDER);
    	
    	String peopleWorkPlace = rs.getString(PeopleConfig.PEOPLE_WORKPLACE);
    	
    	String peopleIdentityNo = rs.getString(PeopleConfig.PEOPLE_IDENTITYNO);
    	
    	Date peopleIdentityMfg = rs.getDate(PeopleConfig.PEOPLE_IDENTITYMFG);
    	
    	String peopleIdentityOrigin = rs.getString(PeopleConfig.PEOPLE_IDENTITYORIGIN);
    	

        return new People(peopleId, peopleIdHouseHold, peopleFullName, peopleNickName, peopleIdBirthPlace, peopleJob,peopleDateOfBirth,
        		peopleRegisDate, peopleHouseHolderRela, peopleEthnic, peopleNativePlace, peopleGender, peopleWorkPlace,
        		peopleIdentityNo, peopleIdentityMfg, peopleIdentityOrigin);
    }
}







