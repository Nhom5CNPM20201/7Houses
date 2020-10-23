package app.database;

import app.database.base.DatabaseManager;
import app.database.interfaces.ISQLDatabaseAccount;
import app.entity.Account;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MSSQLDatabase extends DatabaseManager implements ISQLDatabaseAccount {

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
            MSSQLDatabase testDB = new MSSQLDatabase("localhost", "QLDC", "", "");
            var conn = testDB.getDatabase();
            System.out.println("Connected to DB successfully.");
            conn.close();
        } catch (Exception e) {
            System.out.println("Error when connect to DB:");
            System.out.println(e.getMessage());
            System.out.println("Connecting to DB Failed...");
        }
    }
}


