package app.database.interfaces;

import app.entity.HouseHold;
import app.database.config.HouseHoldConfig;
import app.database.base.ISQLDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ISQLDatabaseHouseHold extends ISQLDatabase {
	
	@Override
	default void insertHouseHold(HouseHold houseHold) throws Exception{
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + HouseHoldConfig.TABLE_NAME +
                    " (" + HouseHoldConfig.HOUSEHOLD_IDADDRESS +"," +
                    HouseHoldConfig.HOUSEHOLD_HOUSEHOLDBOOK + "," + HouseHoldConfig.HOUSEHOLD_IDOWNER + "," 
                    + HouseHoldConfig.HOUSEHOLD_NAME + "," + HouseHoldConfig.HOUSEHOLD_CREATEDDATE + ") " + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, houseHold.getIdAddress());
            stmt.setString(2, houseHold.getHouseHoldBook());
            stmt.setInt(3, houseHold.getIdOwner());
            stmt.setString(4, houseHold.getName());
            stmt.setDate(5, new java.sql.Date(houseHold.getCreatedDate().getTime()));
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                    houseHold.setId(generatedKeys.getInt(1));
            }
            generatedKeys.close();
            stmt.close();
            
        } catch (SQLDataException e) {
            throw e;
        }
    }
	
	@Override
    default List<HouseHold> getAllHouseHold() throws Exception {
        Statement stmt = this.getDatabase().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT *" +
                " FROM " + HouseHoldConfig.TABLE_NAME + ";");

        List<HouseHold> houseHoldList = new ArrayList<>();

        while (rs.next()) {
            houseHoldList.add(extractHouseHold(rs));
        }

        return houseHoldList;
    }

    default int countAllHouseHold() throws Exception {
        Statement stmt = getDatabase().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(" +  HouseHoldConfig.HOUSEHOLD_ID + ")" +
                " FROM " + HouseHoldConfig.TABLE_NAME + ";");

        int count = 0;
        if (rs.next()) count = rs.getInt(1);

        return count;
    }

    default int countHouseHoldFromTo(Date begin, Date end) throws Exception {
        Statement stmt = getDatabase().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(" +  HouseHoldConfig.HOUSEHOLD_ID + ")" +
                " FROM " + HouseHoldConfig.TABLE_NAME + " WHERE "  + ";");

        int count = 0;
        if (rs.next()) count = rs.getInt(1);

        return count;
    }
	
	@Override
    default void updateHouseHold(HouseHold houseHold) throws Exception {
        PreparedStatement stmt = this.getDatabase().prepareStatement("UPDATE " + HouseHoldConfig.TABLE_NAME + " SET "
                + HouseHoldConfig.HOUSEHOLD_IDOWNER + "=? ,"
                + HouseHoldConfig.HOUSEHOLD_NAME + "=?, "
                + HouseHoldConfig.HOUSEHOLD_IDADDRESS + "=? ,"
                + HouseHoldConfig.HOUSEHOLD_HOUSEHOLDBOOK + "=? "
                + " WHERE " + HouseHoldConfig.HOUSEHOLD_ID + "=? ;");
        stmt.setInt(1, houseHold.getIdOwner());
        stmt.setString(2, houseHold.getName());
        stmt.setInt(3, houseHold.getIdAddress());
        stmt.setString(4, houseHold.getHouseHoldBook());
        stmt.setInt(5, houseHold.getId());
        stmt.executeUpdate();
    }

    @Override
    default void removeHouseHold(HouseHold houseHold) throws Exception {
        PreparedStatement stmt = this.getDatabase().prepareStatement("DELETE FROM " + HouseHoldConfig.TABLE_NAME + " WHERE "
                + HouseHoldConfig.HOUSEHOLD_ID + "=" + "?");
        stmt.setInt(1, houseHold.getId());
        stmt.executeUpdate();
    }
    
    @Override
    default HouseHold searchHouseHold(String houseHoldBook) throws Exception {
        Statement stmt = this.getDatabase().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM " + HouseHoldConfig.TABLE_NAME + " WHERE "
                + HouseHoldConfig.HOUSEHOLD_HOUSEHOLDBOOK + " LIKE '%" + houseHoldBook + "%';");

        if(rs.next()) {
            return extractHouseHold(rs);
        }
        else {
            System.out.println("khong ton tai du lieu!");
            return null;
        }
    }

    private HouseHold extractHouseHold(ResultSet rs) throws SQLException {
    	int id = rs.getInt(HouseHoldConfig.HOUSEHOLD_ID);
        int idAddress = rs.getInt(HouseHoldConfig.HOUSEHOLD_IDADDRESS);
        int idOwner = rs.getInt(HouseHoldConfig.HOUSEHOLD_IDOWNER);
        String houseBook = rs.getString(HouseHoldConfig.HOUSEHOLD_HOUSEHOLDBOOK);
        String name = rs.getString(HouseHoldConfig.HOUSEHOLD_NAME);
        Date createdDate = new Date(rs.getDate(HouseHoldConfig.HOUSEHOLD_CREATEDDATE).getTime());

        HouseHold houseHold = new HouseHold(id, idAddress, houseBook, idOwner, name);
        houseHold.setCreatedDate(createdDate);

        return houseHold;
    }
}
