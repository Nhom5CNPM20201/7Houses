package app.database;

import app.database.base.DatabaseManager;
import app.database.interfaces.ISQLDatabaseAccount;
import app.database.interfaces.ISQLDatabaseHouseHold;
import app.database.interfaces.ISQLDatabasePeople;
import app.entity.Account;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MSSQLDatabase extends DatabaseManager implements ISQLDatabaseAccount, ISQLDatabaseHouseHold, ISQLDatabasePeople {

    private Connection databaseObject;

    public MSSQLDatabase(String hostName, String dbname, String user, String password) throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new Exception("Unable to find SQL Server Driver", e);
        }

        try {
            String url = "jdbc:sqlserver://" + hostName
                    + ";databaseName=" + dbname
//                    + ";user=" + user
//                    + ";password=" +password
                    + ";integratedSecurity=true";
            databaseObject = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw e;
        }
        initializeDatabase();
    }

    @Override
    public void initializeDatabase() throws Exception {

    }

    @Override
    public Connection getDatabase() {
        return databaseObject;
    }

    @Override
    public void shutdown() throws Exception {

    }

    public static void main(String[] args) {
        try {
            System.out.println("Start connecting to DB...");
            MSSQLDatabase testConn = new MSSQLDatabase("localhost", "QLNK_Quy", "", "");
            var conn = testConn.getDatabase();
            System.out.println("Connected to DB successfully.");
            System.out.println("Test get all accounts");
            List<Account> accounts = testConn.getAllAccounts();
            for(var item : accounts) {
                System.out.println(item.getId() + " | " + item.getUsername().trim()
                        + " | " + item.getName() + " | " + item.getPassword());
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error when connect to DB:");
            System.out.println(e.getMessage());
            System.out.println("Connecting to DB Failed...");
        }
    }
}


