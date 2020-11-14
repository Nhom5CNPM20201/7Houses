package app.database.interfaces;

import app.database.config.ChangeConfig;
import app.database.base.ISQLDatabase;
import app.entity.Change;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public interface ISQLDatabaseChange extends ISQLDatabase  {
	@Override
	default List<Change> getAllChange() throws Exception {
		try {
			Statement stmt = this.getDatabase().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + ChangeConfig.TABLE_NAME);
			List<Change> changeList = new ArrayList<>();
			
			while(rs.next()) {
				changeList.add(extractChange(rs));
			}
			return changeList;
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	private Change extractChange(ResultSet rs) throws Exception {
		int id = rs.getInt(ChangeConfig.CHANGE_ID);
		int idHouseHold = rs.getInt(ChangeConfig.CHANGE_IHOUSEHOLD);
		int idPeople = rs.getInt(ChangeConfig.CHANGE_IDPEOPLE);
		Date changingTime = rs.getDate(ChangeConfig.CHANGE_CHANGINGDATE);
		int type = rs.getInt(ChangeConfig.CHANGE_TYPE);
		String content = rs.getString(ChangeConfig.CHANGE_CONTENT);
		
		return new Change(id, idHouseHold, idPeople, changingTime, type, content);
	}
	
	@Override
	default void insertChange(Change change) throws Exception{
		PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + ChangeConfig.TABLE_NAME +
                " (" + ChangeConfig.CHANGE_IHOUSEHOLD +"," +ChangeConfig.CHANGE_IDPEOPLE + ","  
                + ChangeConfig.CHANGE_CHANGINGDATE + "," + ChangeConfig.CHANGE_TYPE + "," + ChangeConfig.CHANGE_CONTENT + ") " 
                + " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS );
		stmt.setInt(1, change.getIdHouseHold());
		stmt.setInt(2, change.getIdPeople());
		stmt.setDate(3, change.getChangingDate());
		stmt.setInt(4, change.getType());
		stmt.setString(5, change.getContent());
		stmt.executeUpdate();
		
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if (generatedKeys.next()) {
            change.setId(generatedKeys.getInt(1));
	    }
	    generatedKeys.close();
	    stmt.close();
	}
	
	@Override
	default void updateChange(Change change) throws Exception{
		PreparedStatement stmt = this.getDatabase().prepareStatement("UPDATE " + ChangeConfig.TABLE_NAME + " SET "
				+ ChangeConfig.CHANGE_TYPE + "=?" + " WHERE " + ChangeConfig.CHANGE_ID + "=? ");
		stmt.setInt(1, change.getType());
		stmt.setInt(2, change.getId());
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	@Override
	default void removeChange(Change change) throws Exception{
		PreparedStatement stmt = this.getDatabase().prepareStatement("DELETE FROM " + ChangeConfig.TABLE_NAME + " WHERE " 
				+ ChangeConfig.CHANGE_ID + "=" + "?");
		stmt.setInt(1, change.getId());
		stmt.executeUpdate();
		stmt.close();
	}

}
