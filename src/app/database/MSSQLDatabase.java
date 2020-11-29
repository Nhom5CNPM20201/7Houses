package app.database;

import app.config.AppConfig;
import app.database.base.DatabaseManager;
import app.database.interfaces.*;
import app.entity.Account;
import app.database.interfaces.ISQLDatabaseHouseHold;
import app.entity.HouseHold;
import app.entity.Move;
import app.entity.People;

import java.sql.*;
import java.util.List;

public class MSSQLDatabase extends DatabaseManager implements
        ISQLDatabaseAccount,
        ISQLDatabaseHouseHold,
        ISQLDatabasePeople,
        ISQLDatabaseMove,
        ISQLDatabaseAddress,
        ISQLDatabaseFee,
        ISQLDatabaseChange{

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
//            System.out.println(databaseObject);
        } catch (SQLException e) {
            throw e;
        }
        initializeDatabase();
    }

    private static MSSQLDatabase instance;
    public static MSSQLDatabase getInstance() throws Exception  {
        if (instance == null) instance = new MSSQLDatabase(AppConfig.databaseHostname, AppConfig.databaseName, AppConfig.databaseUsername, AppConfig.databasePassword);
        return instance;
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
            MSSQLDatabase testConn = new MSSQLDatabase(AppConfig.databaseHostname, AppConfig.databaseName, AppConfig.databaseUsername, AppConfig.databasePassword);
            var conn = testConn.getDatabase();

            System.out.print("Try getting all accounts");
            List<Account> accounts = testConn.getAllAccounts();
            for(var item : accounts) {
                System.out.println(item.getId() + " | " + item.getUsername().trim()
                       + " | " + item.getName() + " | " + item.getPassword());
            }
//            People people = testConn.searchPeople("mt");
//            System.out.println(people.getId());
            System.out.println("Connected to DB successfully.");
            conn.close();
            
           
        } catch (Exception e) {
            System.out.println("Error when connect to DB:");
            System.out.println(e.getMessage());
            System.out.println("Connecting to DB Failed...");
        }
    }
}


