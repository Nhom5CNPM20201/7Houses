package app.database.base;

import app.entity.*;
import entity.Address;

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

    Account searchAccount(String username) throws Exception;

//    People region
    void insertPeople(People people) throws Exception;

    void updatePeople(People people) throws Exception;

    void removePeople(People people) throws Exception;
    
	List<People> getAllPeoples() throws Exception;  
	
	HouseHold searchHouseHold(String houseHoldBook) throws Exception;

//    void insertPerson(Person people) throws Exception;
//
//    List<Person> getAllPersons() throws Exception;
//
//    void updatePerson(Person person) throws Exception;
//
//    void removePerson(Person person) throws Exception;

//    HouseHold region
    
    void insertHouseHold(HouseHold houseHold) throws Exception;
    
    void updateHouseHold(HouseHold houseHold) throws Exception;
    
    void removeHouseHold(HouseHold houseHold) throws Exception;
    
    List<HouseHold> getAllHouseHold() throws Exception;
    
// Move region
    void insertMove(Move move) throws Exception;
    
    void updateMove(Move move) throws Exception;
    
    void removeMove(Move move) throws Exception;
    
    List<Move> getAllMove() throws Exception;

// Address region

    void insertAddress(Address address) throws Exception;

    void updateAddress(Address address) throws Exception;

    void removeAddress(int id) throws Exception;

    List<Address> getAllAddress() throws Exception;
}
