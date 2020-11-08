package app.database;
import app.database.base.DatabaseManager;
import app.database.interfaces.ISQLDatabaseAccount;
import app.entity.Account;
import app.entity.People;
import app.database.interfaces.ISQLDatabasePeople;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class MSSQLDatabase extends DatabaseManager implements ISQLDatabaseAccount ,ISQLDatabasePeople   {

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
            conn.close();
            
           
        } catch (Exception e) {
            System.out.println("Error when connect to DB:");
            System.out.println(e.getMessage());
            System.out.println("Connecting to DB Failed...");
        }
    }
}


