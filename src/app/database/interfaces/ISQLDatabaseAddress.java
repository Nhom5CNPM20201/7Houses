package app.database.interfaces;

import app.database.base.ISQLDatabase;
import app.database.config.AddressConfig;
import app.entity.permanentAddress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ISQLDatabaseAddress extends ISQLDatabase {
    @Override
    default void insertAddress(permanentAddress permanentAddress) throws Exception {
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + AddressConfig.TABLE_NAME +
                    " (" + AddressConfig.ADDRESS_NUMBERHOUSE + "," + AddressConfig.ADDRESS_STREET + "," +
                    AddressConfig.ADDRESS_SUBDISTRICT + "," + AddressConfig.ADDRESS_DISTRICT +
                    AddressConfig.ADDRESS_CITY + " VALUES (?, ?, ?, ?)");
            stmt.setInt(1, permanentAddress.getNumberHouse());
            stmt.setString(2, permanentAddress.getStreet());
            stmt.setString(3, permanentAddress.getSubDistrict());
            stmt.setString(4, permanentAddress.getDistrict());
            stmt.setString(5, permanentAddress.getCity());
            stmt.executeUpdate();

            ResultSet generatedIDs = stmt.getGeneratedKeys();
            if (generatedIDs.next()) {
                int insertedAccountID = generatedIDs.getInt(1);
                permanentAddress.setId(insertedAccountID);
            }
        } catch (SQLDataException e) {

        }
    }

    private permanentAddress extractAddress(ResultSet rs) throws SQLException {
        int addressnumber = rs.getInt(AddressConfig.ADDRESS_NUMBERHOUSE);
        String addressstreet = rs.getString(AddressConfig.ADDRESS_STREET);
        String addresssubdistrict = rs.getString(AddressConfig.ADDRESS_SUBDISTRICT);
        String addressdistrict = rs.getString(AddressConfig.ADDRESS_DISTRICT);
        String addresscity = rs.getString(AddressConfig.ADDRESS_CITY);

        return new permanentAddress(numberHouse, street, subDistrict, district, city);
    }

    @Override
    default List<permanentAddress> getAllAddress() throws Exception {
        try {
            Statement stmt = getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
                    " FROM " + AddressConfig.TABLE_NAME + ";");

            List<permanentAddress> addressList = new ArrayList<>();

            while (rs.next()) {
                addressList.add(extractAddress(rs));
            }

            return addressList;
        } catch (java.sql.SQLException e) {
            throw e;
        }
    }

    @Override
    default void updateAddress(permanentAddress permanentAddress) throws Exception {
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("Update " + AddressConfig.TABLE_NAME +
                    " (" + AddressConfig.ADDRESS_NUMBERHOUSE + "," + AddressConfig.ADDRESS_STREET + "," +
                    AddressConfig.ADDRESS_SUBDISTRICT + "," + AddressConfig.ADDRESS_DISTRICT +
                    AddressConfig.ADDRESS_CITY + " VALUES (?, ?, ?, ?)");
            stmt.setInt(1, permanentAddress.getNumberHouse());
            stmt.setString(2, permanentAddress.getStreet());
            stmt.setString(3, permanentAddress.getSubDistrict());
            stmt.setString(4, permanentAddress.getDistrict());
            stmt.setString(5, permanentAddress.getCity());
            stmt.executeUpdate();

            ResultSet generatedIDs = stmt.getGeneratedKeys();
            if (generatedIDs.next()) {
                int insertedAccountID = generatedIDs.getInt(1);
                permanentAddress.setId(insertedAccountID);
            }
        } catch (SQLDataException e) {

        }

    }

    @Override
    default void removeAddress(int id) throws Exception {
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("Delete" + AddressConfig.TABLE_NAME +
                    "where" + AddressConfig.ADDRESS_ID = id);
            stmt.executeUpdate();
        } catch (SQLDataException e) {

        }
    }
}

