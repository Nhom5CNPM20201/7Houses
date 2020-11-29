package app.database.interfaces;

import app.config.AppConfig;
import app.database.MSSQLDatabase;
import app.database.base.ISQLDatabase;
import app.database.config.FeeConfig;
import app.entity.Fee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ISQLDatabaseFee extends ISQLDatabase {
    @Override
    default void insertFee(Fee fee) throws Exception{
        try {//INSERT INTO table_name (column1, column2, column3, ...)
            //VALUES (value1, value2, value3, ...);
            PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + FeeConfig.TABLE_NAME +
                    "(" + FeeConfig.FEE_CATEGORY + " , "
                    + FeeConfig.FEE_NAMEFEE + " , "
                    + FeeConfig.FEE_MONEY + " , "
                    + FeeConfig.FEE_OTHEINFOMATION + ") VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, fee.getCategory());
            stmt.setString(2, fee.getNameFee());
            stmt.setInt(3, fee.getMoney());
            stmt.setString(4, fee.getOtherInformation());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                fee.setId(generatedKeys.getInt(1));
            }
            generatedKeys.close();
            stmt.close();
        } catch (SQLDataException e) {
            throw e;
        }
    }
    @Override
    default List<Fee> getAllFee() throws Exception {
        try {
            Statement stmt = getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
                    " FROM " + FeeConfig.TABLE_NAME + ";");

            List<Fee>feeList = new ArrayList<>();

            while (rs.next()) {
               feeList.add(extractFee(rs));
            }

            return feeList;
        } catch (SQLException e) {
            throw e;
        }
    }
    @Override
    default Fee searchFee(int id) throws Exception {
        try {
            Statement stmt = this.getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM " + FeeConfig.TABLE_NAME + " WHERE "
                    + FeeConfig.FEE_ID + " LIKE '%" + id + "%';");

            if(rs.next()) {
                return extractFee(rs);
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
    private Fee extractFee(ResultSet rs) throws SQLException {
        int feeid = rs.getInt(FeeConfig.FEE_ID);
        int feecategory = rs.getInt(FeeConfig.FEE_CATEGORY);
        String feename = rs.getString(FeeConfig.FEE_NAMEFEE);
        int feemoney = rs.getInt(FeeConfig.FEE_MONEY);
        String feeother = rs.getString(FeeConfig.FEE_OTHEINFOMATION);
        return new Fee(feeid, feecategory, feename, feemoney, feeother);
    }
    @Override
    default void removeFee(int id) throws Exception {
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("Delete from " + FeeConfig.TABLE_NAME +
                    " where " + FeeConfig.FEE_ID + " = " + id);
            stmt.executeUpdate();
        } catch (SQLDataException e) {
            throw e;
        }
    }
    @Override
    default void updateFee(Fee fee) throws Exception {
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("Update " + FeeConfig.TABLE_NAME
                    + " set "
                    + FeeConfig.FEE_CATEGORY + " =? " + " , " + FeeConfig.FEE_NAMEFEE +" =? "
                    + " , " + FeeConfig.FEE_MONEY + " =? "
                    + " , " + FeeConfig.FEE_OTHEINFOMATION + " =? " + " WHERE " + FeeConfig.FEE_ID + " = " +  fee.getId());
            stmt.setInt(1, fee.getCategory());
            stmt.setString(2, fee.getNameFee());
            stmt.setInt(3, fee.getMoney());
            stmt.setString(4, fee.getOtherInformation());
            stmt.executeUpdate();

        } catch (SQLDataException e) {
            throw e;
        }
    }
    public static void main(String[] args) {
        try {
            System.out.println("Start connecting to DB...");
            MSSQLDatabase testConn = new MSSQLDatabase(AppConfig.databaseHostname, AppConfig.databaseName,
                    AppConfig.databaseUsername, AppConfig.databasePassword);
            var conn = testConn.getDatabase();

            System.out.print("Try getting all accounts\n");
           Fee fee = new Fee(5, "rac", 6000, "khong co" );
           testConn.insertFee(fee);
           testConn.updateFee(new Fee(5, 0, "vesinh", 10000, "khong"));
//           testConn.removeFee(10);
            List<Fee> feeList = testConn.getAllFee();
            for(var item : feeList) {
                System.out.println(item.getId() + ", " + item.getCategory()+ ", " +item.getNameFee()
                        + ", " + item.getMoney()+ ", " + item.getOtherInformation());
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
