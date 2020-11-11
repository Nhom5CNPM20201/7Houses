package app;

import app.database.QueryAll.queryAccount;
import app.database.QueryAll.queryPermanentAddress;
import app.entity.Account;
import app.entity.permanentAddress;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.util.List;

public class testConnection {
        public static void main(String[] args) throws Exception {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("sa");
            ds.setPassword("123456");
            ds.setServerName("DESKTOP-G3N4US4");
            ds.setPortNumber(1433);
            ds.setDatabaseName("QLNK");
            Connection connection = ds.getConnection();
            List<Account> list = queryAccount.selectAccount(connection);
            System.out.println(list.get(0).getName());
            queryPermanentAddress.addNew(connection,new permanentAddress(1,4,"hh", "gg" , "aa", "kk"));
            queryPermanentAddress.updateAddress(connection,new permanentAddress(1,5,
                    "hh", "gg" , "aa", "kk" ));
            queryPermanentAddress.remoteAddress(connection, 1);
    }

}
