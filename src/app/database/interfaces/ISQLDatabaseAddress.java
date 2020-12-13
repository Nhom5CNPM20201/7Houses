package app.database.interfaces;

import app.database.config.AddressConfig;
import app.config.AppConfig;
import app.database.MSSQLDatabase;
import app.database.base.ISQLDatabase;
import app.entity.Address;
import app.services.common.LogService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ISQLDatabaseAddress extends ISQLDatabase {
    @Override
    default void insertAddress(Address address) throws Exception {
        try {//INSERT INTO table_name (column1, column2, column3, ...)
            //VALUES (value1, value2, value3, ...);
            PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + AddressConfig.TABLE_NAME +
                    "(" + AddressConfig.ADDRESS_NUMBERHOUSE + " , "
                    + AddressConfig.ADDRESS_STREET + " , "
                    + AddressConfig.ADDRESS_SUBDISTRICT + " , "
                    + AddressConfig.ADDRESS_DISTRICT + " , "
                    + AddressConfig.ADDRESS_CITY + " , "
                    + AddressConfig.ADDRESS_INFORMATION + ") VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, address.getNumberHouse());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getSubDistrict());
            stmt.setString(4, address.getDistrict());
            stmt.setString(5, address.getCity());
            stmt.setString(6, address.getInformation());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                address.setId(generatedKeys.getInt(1));
            }
            generatedKeys.close();
            stmt.close();
        } catch (SQLDataException e) {
            throw e;
        }
    }

    private Address extractAddress(ResultSet rs) throws SQLException {
        int addressid = rs.getInt(AddressConfig.ADDRESS_ID);
        int addressnumber = rs.getInt(AddressConfig.ADDRESS_NUMBERHOUSE);
        String addressstreet = rs.getString(AddressConfig.ADDRESS_STREET);
        String addresssubdistrict = rs.getString(AddressConfig.ADDRESS_SUBDISTRICT);
        String addressdistrict = rs.getString(AddressConfig.ADDRESS_DISTRICT);
        String addresscity = rs.getString(AddressConfig.ADDRESS_CITY);
        String addressinfor = rs.getString(AddressConfig.ADDRESS_INFORMATION);


        return new Address(addressid, addressnumber, addressstreet, addresssubdistrict, addressdistrict, addresscity,addressinfor);
    }

    @Override
    default List<Address> getAllAddress() throws Exception {
        try {
            Statement stmt = getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
                    " FROM " + AddressConfig.TABLE_NAME + ";");

            List<Address> addressList = new ArrayList<>();

            while (rs.next()) {
                addressList.add(extractAddress(rs));
            }

            return addressList;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    default void updateAddress(Address address) throws Exception {
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("Update " + AddressConfig.TABLE_NAME
                    + " set "
                    + AddressConfig.ADDRESS_NUMBERHOUSE + " =? " + " , " + AddressConfig.ADDRESS_STREET +" =? "
                    + " , " + AddressConfig.ADDRESS_SUBDISTRICT + " =? "
                    + " , " + AddressConfig.ADDRESS_DISTRICT + " =? "
                    + " , " + AddressConfig.ADDRESS_CITY + " =?"
                    + " , " + AddressConfig.ADDRESS_INFORMATION + " =?"+ " WHERE " + AddressConfig.ADDRESS_ID + " = " +  address.getId());
            stmt.setInt(1, address.getNumberHouse());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getSubDistrict());
            stmt.setString(4, address.getDistrict());
            stmt.setString(5, address.getCity());
            stmt.setString(6, address.getInformation());
            stmt.executeUpdate();
        } catch (SQLDataException e) {
            throw e;
        }

    }

    @Override
    default void removeAddress(int id) throws Exception {
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("Delete from " + AddressConfig.TABLE_NAME +
                    " where " + AddressConfig.ADDRESS_ID + " = " + id);
            stmt.executeUpdate();
        } catch (SQLDataException e) {
            throw e;
        }
    }
    @Override
    default Address searchAddress(int id) throws Exception {
        try {
            Statement stmt = this.getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM " + AddressConfig.TABLE_NAME + " WHERE "
                    + AddressConfig.ADDRESS_ID + " LIKE '%" + id + "%';");

            if(rs.next()) {
                return extractAddress(rs);
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
    // test
    public static void main(String[] args) {
        try {
            System.out.println("Start connecting to DB...");
            MSSQLDatabase testConn = new MSSQLDatabase(AppConfig.databaseHostname, AppConfig.databaseName,
                    AppConfig.databaseUsername, AppConfig.databasePassword);
            var conn = testConn.getDatabase();

            System.out.print("Try getting all accounts\n");
            Address address = new Address(29, "H3PO4", "C2H2OH", "CH3Cl", "H2O", "abc");
            testConn.updateAddress(new Address(5, 7, "aa", "bb", "cc", "dd","hang"));
            //testConn.removeAddress(1);
            testConn.insertAddress(address);
            testConn.searchAddress(5);
            List<Address> addressList = testConn.getAllAddress();
            for(var item : addressList) {
                System.out.println(item.getId() + ", " + item.getNumberHouse()+ ", " +item.getStreet()
                        + ", " + item.getSubDistrict() + ", " + item.getDistrict() + ", " + item.getCity());
            }
            System.out.println("Connected to DB successfully.");
            conn.close();


        } catch (Exception e) {
            LogService.error("Error when connect to DB:");
            System.out.println(e.getMessage());
            LogService.error("Connecting to DB Failed...");
        }
    }

}
