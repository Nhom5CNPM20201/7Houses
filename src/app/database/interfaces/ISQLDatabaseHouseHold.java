package app.database.interfaces;

import app.entity.Account;
import app.entity.HouseHold;
import app.database.config.AccountConfig;
import app.database.config.HouseHoldConfig;
import app.database.base.ISQLDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ISQLDatabaseHouseHold extends ISQLDatabase {
	
	@Override
	default void insertHouseHold(HouseHold houseHold) throws Exception{
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + HouseHoldConfig.TABLE_NAME +
                    " (" + HouseHoldConfig.HOUSEHOLD_IDADDRESS +"," +
                    HouseHoldConfig.HOUSEHOLD_HOUSEHOLDBOOK + "," + HouseHoldConfig.HOUSEHOLD_IDOWNER + "," 
                    + HouseHoldConfig.HOUSEHOLD_NAME + ") " + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, houseHold.getIdAddress());
            stmt.setString(2, houseHold.getHouseHoldBook());
            stmt.setInt(3, houseHold.getIdOwner());
            stmt.setString(4, houseHold.getName());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                    houseHold.setId(generatedKeys.getInt(1));
            }
            generatedKeys.close();
            stmt.close();
            
        } catch (SQLDataException e) {

        }
    }
	
	@Override
    default List<HouseHold> getAllHouseHold() throws Exception {
        try {
            Statement stmt = this.getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
                    " FROM " + HouseHoldConfig.TABLE_NAME + ";");

            List<HouseHold> houseHoldList = new ArrayList<>();

            while (rs.next()) {
                houseHoldList.add(extractHouseHold(rs));
            }

            return houseHoldList;
        } catch (java.sql.SQLException e) {
            throw e;
        }
    }
	
	@Override
    default void updateHouseHold(HouseHold houseHold) throws Exception {
		try {
			PreparedStatement stmt = this.getDatabase().prepareStatement("UPDATE " + HouseHoldConfig.TABLE_NAME + " SET "
					+ HouseHoldConfig.HOUSEHOLD_IDOWNER + "=? " + "," + HouseHoldConfig.HOUSEHOLD_NAME + "=?" 
					+ " WHERE " + HouseHoldConfig.HOUSEHOLD_HOUSEHOLDBOOK + "=? " + "AND " 
					+ HouseHoldConfig.HOUSEHOLD_IDADDRESS + "=?");
			stmt.setInt(1, houseHold.getIdOwner());
			stmt.setString(2, houseHold.getName());
			stmt.setString(3, houseHold.getHouseHoldBook());
			stmt.setInt(4, houseHold.getIdAddress());
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			throw e;
		}
		
    }

    @Override
    default void removeHouseHold(HouseHold houseHold) throws Exception {
    	try {
    		PreparedStatement stmt = this.getDatabase().prepareStatement("DELETE FROM " + HouseHoldConfig.TABLE_NAME + " WHERE " 
					+ HouseHoldConfig.HOUSEHOLD_ID + "=" + "?");
    		stmt.setInt(1, houseHold.getId());
    		stmt.executeUpdate();
    	}
    	catch(SQLException e) {
    		throw e;
    	}
    }

    private HouseHold extractHouseHold(ResultSet rs) throws SQLException {
    	int id = rs.getInt(HouseHoldConfig.HOUSEHOLD_ID);
        int idAddress = rs.getInt(HouseHoldConfig.HOUSEHOLD_IDADDRESS);
        int idOwner = rs.getInt(HouseHoldConfig.HOUSEHOLD_IDOWNER);
        String houseBook = rs.getString(HouseHoldConfig.HOUSEHOLD_HOUSEHOLDBOOK);
        String name = rs.getString(HouseHoldConfig.HOUSEHOLD_NAME);

        return new HouseHold(id, idAddress, houseBook, idOwner, name);
    }
}
