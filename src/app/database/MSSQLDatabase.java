package app.database;

import app.config.AppConfig;
import app.database.base.DatabaseManager;
import app.database.interfaces.ISQLDatabaseAccount;
import app.database.interfaces.ISQLDatabaseHouseHold;
import app.database.interfaces.ISQLDatabasePeople;
import app.database.interfaces.ISQLDatabaseMove;
import app.entity.Account;
import app.entity.HouseHold;
import app.entity.Move;
import app.entity.People;
import java.sql.*;
import java.util.List;

public class MSSQLDatabase extends DatabaseManager implements
        ISQLDatabaseAccount,
        ISQLDatabaseHouseHold,
        ISQLDatabasePeople,
        ISQLDatabaseMove {

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
            System.out.println("Connected to DB successfully.");
            System.out.println("Test get all accounts");


          //  People people = new People();
          //  testConn.insertPeople(people);
           
          // testConn.updatePeople(people);
           
            List<Account> accounts = testConn.getAllAccounts();
            List<People> peoples = testConn.getAllPeoples();      
          //  testConn.removePeople(peoples.get(8));
            
            for(var item : accounts) {
                System.out.println(item.getId() + " | " + item.getUsername().trim()
                       + " | " + item.getName() + " | " + item.getPassword());
            }
            
            System.out.println(" \n Test get all peoples ");
            for(var j : peoples) {
                System.out.println(j.getId() 
                        + " | " + j.getFullName() + " | " + j.getDateOfBirth());
            }
//          HouseHold houseHold = new HouseHold(4, "LK103", 3, "@@@");
//          testConn.insertHouseHold(houseHold);
//          testConn.updateHouseHold(houseHold);
         	
            List<HouseHold> houseHolds = testConn.getAllHouseHold();
//            testConn.removeHouseHold(houseHolds.get(4));
            for(var i : houseHolds) {
            	System.out.println(i.getId() + "\t" + i.getIdAddress() + "\t" + i.getHouseHoldBook() + "\t"
            			+ i.getIdOwner() + "\t" + i.getName());
            }
//			Move
//            Move move = new Move(6, 2, 3, 2, 4, Date.valueOf("2019-05-10"), 1);
//            testConn.updateMove(move);
//            Move move = new Move(2, 3, 2, 4, Date.valueOf("2019-05-10"), 1);
//            testConn.insertMove(move);
            List<Move> moves = testConn.getAllMove();
//            testConn.removeMove(moves.get(2));
            for(Move i : moves) {
            	System.out.println(i.getId() + "\t" + i.getIdHouseHold() + "\t" + i.getIdPeople() + "\t" 
            			+ i.getIdNewAddress() + "\t" + i.getIdOldAddress() + "\t" + i.getMovingDate() + "\t"
            			+ i.getType());
            }
            
            conn.close();
            
           
        } catch (Exception e) {
            System.out.println("Error when connect to DB:");
            System.out.println(e.getMessage());
            System.out.println("Connecting to DB Failed...");
        }
    }
}


