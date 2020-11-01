package app.database.base;

import app.entity.Account;

import java.sql.Connection;
import java.util.List;
import app.entity.People;
public interface IDatabase {

//    Init database region

    void initializeDatabase() throws Exception;

    Connection getDatabase() throws Exception;

    void shutdown() throws Exception;

//    Account region

    void insertAccount(Account account) throws Exception;

    List<Account> getAllAccounts() throws Exception;

    void updateAccount(Account account) throws Exception;

    void removeAccount(Account account) throws Exception;

//    People region
    void insertPeople(People people) throws Exception;

    List<People> getAllPeoples() throws Exception;

    void updatePeople(People people) throws Exception;

    void removePeople(People people) throws Exception;

	List<People> getAllPeople() throws Exception;    

//    HouseHold region
}
