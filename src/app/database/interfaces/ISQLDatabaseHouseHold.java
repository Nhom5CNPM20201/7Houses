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
                    " (" + HouseHoldConfig.HouseHold_IdAddress +"," +
                    HouseHoldConfig.HouseHold_RegistrationBook + "," + HouseHoldConfig.HouseHold_IdOwner + "," 
                    + HouseHoldConfig.HouseHold_OwnerName + ") " + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, houseHold.getIdAddress());
            stmt.setString(2, houseHold.getRegistrationBook());
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
//		Statement stmt = this.getDatabase().prepareStatement()
    }

    @Override
    default void removeHouseHold(HouseHold houseHold) throws Exception {
    	try {
    		PreparedStatement stmt = this.getDatabase().prepareStatement("DELETE FROM " + HouseHoldConfig.TABLE_NAME + " WHERE " 
					+ HouseHoldConfig.HouseHold_Id + "=" + "?");
    		stmt.setInt(1, houseHold.getId());
    		stmt.executeUpdate();
    	}
    	catch(SQLException e) {
    		throw e;
    	}
    }

    private HouseHold extractHouseHold(ResultSet rs) throws SQLException {
    	int id = rs.getInt(HouseHoldConfig.HouseHold_Id);
        int idAddress = rs.getInt(HouseHoldConfig.HouseHold_IdAddress);
        int idOwner = rs.getInt(HouseHoldConfig.HouseHold_IdOwner);
        String houseBook = rs.getString(HouseHoldConfig.HouseHold_RegistrationBook);
        String name = rs.getString(HouseHoldConfig.HouseHold_OwnerName);

        return new HouseHold(id, idAddress, houseBook, idOwner, name);
    }
}
