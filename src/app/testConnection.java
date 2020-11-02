package app;

import app.database.QueryAll.queryAccount;
import app.entity.Account;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class testConnection {
        public static void main(String[] args) throws SQLException {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("sa");
            ds.setPassword("123456");
            ds.setServerName("DESKTOP-G3N4US4");
            ds.setPortNumber(1433);
            ds.setDatabaseName("QLNK");
            Connection connection = ds.getConnection();
            List<Account> list = queryAccount.selectAccount(connection);
            System.out.println(list.get(0).getName());
    }

}
