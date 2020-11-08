package app.database.base;

import app.entity.Account;
import app.entity.HouseHold;
import app.entity.Move;

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
    
}
