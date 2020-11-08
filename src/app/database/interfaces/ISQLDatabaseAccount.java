package app.database.interfaces;

import app.database.base.ISQLDatabase;
import app.database.config.AccountConfig;
import app.entity.Account;
import app.services.common.LogService;

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
            stmt.setString(1, account.getUsername().toLowerCase());
            stmt.setString(2, account.getPassword().toLowerCase());
            stmt.setString(3, account.getName().toLowerCase());
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
            Statement stmt = this.getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
                    " FROM " + AccountConfig.TABLE_NAME + ";");

            List<Account> accountList = new ArrayList<>();

            while (rs.next()) {
                accountList.add(extractAccount(rs));
            }

            return accountList;
        } catch (java.sql.SQLException e) {
            throw e;
        }
    }

    @Override
    default void updateAccount(Account account) throws Exception {

    }

    @Override
    default void removeAccount(Account account) throws Exception {

    }

    @Override
    default Account searchAccount(String username) throws Exception {
        try {
            Statement stmt = this.getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 1 * " +
                    "FROM " + AccountConfig.TABLE_NAME + " WHERE " + AccountConfig.ACCOUNT_USERNAME + " LIKE '%" + username + "%';");

            if (rs.next()) {
                return extractAccount(rs);
            } else {
                return null;
            }
        } catch (java.sql.SQLException e) {
            LogService.error("[DBAccount] " + e.getMessage());
            return null;
        }
    }

    private Account extractAccount(ResultSet rs) throws SQLException {
        int accountID = rs.getInt(AccountConfig.ACCOUNT_ID);
        int accountPosition = rs.getInt(AccountConfig.ACCOUNT_POSITION);
        String accountName = rs.getString(AccountConfig.ACCOUNT_NAME).trim();
        String accountUsername = rs.getString(AccountConfig.ACCOUNT_USERNAME).trim();
        String accountPassword = rs.getString(AccountConfig.ACCOUNT_PASSWORD).trim();

        return new Account(accountID, accountPosition, accountName, accountUsername, accountPassword);
    }
}
