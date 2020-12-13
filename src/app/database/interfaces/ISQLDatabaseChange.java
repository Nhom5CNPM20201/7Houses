package app.database.interfaces;

import app.config.AppConfig;
import app.database.MSSQLDatabase;
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
		String changingTime = rs.getString(ChangeConfig.CHANGE_CHANGINGDATE);
		int type = rs.getInt(ChangeConfig.CHANGE_TYPE);
		String content = rs.getString(ChangeConfig.CHANGE_CONTENT);
		
		return new Change(id, idHouseHold, idPeople, changingTime, type, content);
	}
	
	@Override
	default void insertChange(Change change) throws Exception{
		PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + ChangeConfig.TABLE_NAME +
                " (" + ChangeConfig.CHANGE_IHOUSEHOLD +"," +ChangeConfig.CHANGE_IDPEOPLE + ","  
                + ChangeConfig.CHANGE_CHANGINGDATE + "," + ChangeConfig.CHANGE_TYPE + "," + ChangeConfig.CHANGE_CONTENT + ") " 
                + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS );
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
	default Change searchChange(int id) throws Exception {
		try {
			Statement stmt = this.getDatabase().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM " + ChangeConfig.TABLE_NAME + " WHERE "
					+ ChangeConfig.CHANGE_ID + " LIKE '%" + id + "%';");

			if(rs.next()) {
				return extractChange(rs);
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
	
	@Override
	default void removeChange(int id) throws Exception{
		try {
			PreparedStatement stmt = this.getDatabase().prepareStatement("Delete from " + ChangeConfig.TABLE_NAME +
					" where " + ChangeConfig.CHANGE_ID + " = " + id);
			stmt.executeUpdate();
		} catch (SQLDataException e) {
			throw e;
		}
	}
	public static void main(String []arg){
		try {
			System.out.println("Start connecting to DB...");
			MSSQLDatabase testConn = new MSSQLDatabase(AppConfig.databaseHostname, AppConfig.databaseName,
					AppConfig.databaseUsername, AppConfig.databasePassword);
			var conn = testConn.getDatabase();

			System.out.print("Try getting all accounts\n");
			Change change = new Change(3, 1, "2000-11-11", 5, "hi");
			testConn.insertChange(change);
			List<Change> changeList = testConn.getAllChange();
			for(var i: changeList){
				System.out.println(i.getId()+ " "+ i.getIdHouseHold()+ " " +i.getChangingDate());
			}
			System.out.println("Connected to DB successfully.");
			conn.close();


		} catch (Exception e) {
			System.out.println("Error when connect to DB:");
			System.out.println(e.getMessage());
			System.out.println("Connecting to DB Failed...");
		}
	}

}
