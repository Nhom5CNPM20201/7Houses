package app.database.interfaces;

import app.database.base.ISQLDatabase;
import app.database.config.AccountConfig;
import app.entity.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ISQLDatabaseAccount extends ISQLDatabase {

    @Override
    default void insertAccount(Account account) throws Exception{
        try {
            PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + AccountConfig.TABLE_NAME +
                    " (" + AccountConfig.ACCOUNT_USERNAME + "," + AccountConfig.ACCOUNT_PASSWORD +"," +
                    AccountConfig.ACCOUNT_NAME + "," + AccountConfig.ACCOUNT_POSITION + " VALUES (?, ?, ?, ?)");
            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getName());
            stmt.setInt(4, account.getAccountPosition());
            stmt.executeUpdate();

            ResultSet generatedIDs = stmt.getGeneratedKeys();
            if (generatedIDs.next()) {
                int insertedAccountID = generatedIDs.getInt(1);
                account.setId(insertedAccountID);
            }
        } catch (SQLDataException e) {

        }
    }

    @Override
    default List<Account> getAllAccounts() throws Exception {
        try {
            Statement stmt = getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
                    " FROM " + AccountConfig.TABLE_NAME + ";");

            List<Account> accountList = new ArrayList<>();

            while (rs.next()) {
                accountList.add(extractAccount(rs));
            }

            return accountList;
        } catch (java.sql.SQLException e) {
            throw new Exception("Error while getting all accounts", e);
        }
    }

    @Override
    default void updateAccount(Account account) throws Exception {

    }

    @Override
    default void removeAccount(Account account) throws Exception {

    }

    private Account extractAccount(ResultSet rs) throws SQLException {
        int accountID = rs.getInt(AccountConfig.ACCOUNT_ID);
        String accountName = rs.getString(AccountConfig.ACCOUNT_NAME);

        return new Account(accountID, accountName);
    }
}
