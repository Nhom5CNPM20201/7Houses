package app.database.interfaces;

import app.config.AppConfig;
import app.database.MSSQLDatabase;
import app.database.base.ISQLDatabase;
import app.database.config.AddressConfig;
import app.database.config.TemporaryResidentConfig;
import app.entity.Address;
import app.entity.TemporaryResident;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ISQLDatabaseTemporaryResident extends ISQLDatabase {
    @Override
    default void insertTS(TemporaryResident ts) throws Exception {
        try {//INSERT INTO table_name (column1, column2, column3, ...)
            //VALUES (value1, value2, value3, ...);
            PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + TemporaryResidentConfig.TABLE_NAME +
                    "("
                    + TemporaryResidentConfig.TS_IDPEOPLE + " , "
                    + TemporaryResidentConfig.TS_IDADDRESS + " , "
                    + TemporaryResidentConfig.TS_TIME + " , "
                    + TemporaryResidentConfig.TS_START + " , "
                    + TemporaryResidentConfig.TS_DURATION + " , "
                    + TemporaryResidentConfig.TS_CAGETORY + " , "
                    + TemporaryResidentConfig.TS_INFOR + ") VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, ts.getIdPeople());
            stmt.setInt(1, ts.getIdAddress());
            stmt.setDate(2, ts.getTime());
            stmt.setDate(3, ts.getStart());
            stmt.setInt(4, ts.getDuration());
            stmt.setInt(5, ts.getCagetory());
            stmt.setString(6, ts.getInformation());
            stmt.executeUpdate();
        } catch (SQLDataException e) {
            throw e;
        }
    }

    private TemporaryResident extractTS(ResultSet rs) throws SQLException {
        int ts_idpeople = rs.getInt(TemporaryResidentConfig.TS_IDPEOPLE);
        int ts_id = rs.getInt(TemporaryResidentConfig.TS_IDADDRESS);
        Date ts_time = rs.getDate(TemporaryResidentConfig.TS_TIME);
        Date ts_start = rs.getDate(TemporaryResidentConfig.TS_START);
        int ts_duration = rs.getInt(TemporaryResidentConfig.TS_DURATION);
        int ts_cagetory = rs.getInt(TemporaryResidentConfig.TS_CAGETORY);
        String ts_infor = rs.getString(TemporaryResidentConfig.TS_INFOR);


        return new TemporaryResident(ts_idpeople, ts_id, ts_time, ts_start, ts_duration, ts_cagetory, ts_infor);
    }

    @Override
    default List<TemporaryResident> getAllTS() throws Exception {
        try {
            Statement stmt = getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
                    " FROM " + TemporaryResidentConfig.TABLE_NAME + ";");

            List<TemporaryResident> tsList = new ArrayList<>();

            while (rs.next()) {
                tsList.add(extractTS(rs));
            }

            return tsList;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    default void updateTS(TemporaryResident ts) throws Exception {
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("Update " + TemporaryResidentConfig.TABLE_NAME
                    + " set "
                    + TemporaryResidentConfig.TS_IDADDRESS + " =? "
                    + " , " + TemporaryResidentConfig.TS_TIME + " =? "
                    + " , " + TemporaryResidentConfig.TS_START + " =? "
                    + " , " + TemporaryResidentConfig.TS_DURATION + " =?"
                    + " , " + TemporaryResidentConfig.TS_CAGETORY + " =?"
                    + " , " + TemporaryResidentConfig.TS_INFOR +" =? " + " WHERE " + TemporaryResidentConfig.TS_IDPEOPLE + " = " +  ts.getIdPeople());
            stmt.setInt(1, ts.getIdPeople());
            stmt.setInt(2, ts.getIdAddress());
            stmt.setDate(3, ts.getTime());
            stmt.setDate(4, ts.getStart());
            stmt.setInt(5, ts.getDuration());
            stmt.setInt(6, ts.getCagetory());
            stmt.setString(7, ts.getInformation());
            stmt.executeUpdate();
        } catch (SQLDataException e) {
            throw e;
        }

    }

    @Override
    default void removeTS(int id) throws Exception {
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("Delete from " + TemporaryResidentConfig.TABLE_NAME +
                    " where " + TemporaryResidentConfig.TS_IDPEOPLE + " = " + id);
            stmt.executeUpdate();
        } catch (SQLDataException e) {
            throw e;
        }
    }
    @Override
    default TemporaryResident searchTS(int id) throws Exception {
        try {
            Statement stmt = this.getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM " + TemporaryResidentConfig.TABLE_NAME + " WHERE "
                    + TemporaryResidentConfig.TS_IDPEOPLE + " LIKE '%" + id + "%';");

            if(rs.next()) {
                return extractTS(rs);
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
    public static void main(String[] args) {
        try {
            System.out.println("Start connecting to DB...");
            MSSQLDatabase testConn = new MSSQLDatabase(AppConfig.databaseHostname, AppConfig.databaseName,
                    AppConfig.databaseUsername, AppConfig.databasePassword);
            var conn = testConn.getDatabase();

            System.out.print("Try getting all accounts\n");
         //   TemporaryResident ts = new TemporaryResident(2,3,Date.valueOf("11-11-2000"), Date.valueOf("02-02-1999"), 7, 1 , "no");
         //   testConn.insertTS(ts);
            testConn.searchTS(3);
         //   testConn.updateTS(new TemporaryResident(2,3,Date.valueOf("11-11-2000"), Date.valueOf("02-02-1999"), 7, 1 , "no"));

            List<TemporaryResident> tsList = testConn.getAllTS();
            for(var i: tsList){
                System.out.println(i.getIdPeople() + "\t " + i.getIdAddress() + ", " + i.getTime() + ", " + i.getStart()
                        + ", " + i.getDuration() + ", " + i.getCagetory());
            }
            testConn.getAllTS();
            System.out.println("Connected to DB successfully.");
            conn.close();


        } catch (Exception e) {
            System.out.println("Error when connect to DB:");
            System.out.println(e.getMessage());
            System.out.println("Connecting to DB Failed...");
        }
    }
}
