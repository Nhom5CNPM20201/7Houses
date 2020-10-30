package app.database.base;

import app.entity.Account;
import app.entity.HouseHold;
import app.entity.Person;

import java.sql.Connection;
import java.util.List;

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

//    void insertPerson(Person people) throws Exception;
//
//    List<Person> getAllPersons() throws Exception;
//
//    void updatePerson(Person person) throws Exception;
//
//    void removePerson(Person person) throws Exception;

//    HouseHold region

//    void insertHouseHold(HouseHold houseHold) throws Exception;
//
//    List<HouseHold> getAllHouseHolds() throws Exception;
//
//    void updateHouseHold(HouseHold houseHold) throws Exception;
//
//    void removeHouseHold(HouseHold houseHold) throws Exception;
}
