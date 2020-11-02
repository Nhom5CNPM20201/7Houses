package app.database.QueryAll;

import app.entity.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class queryAccount {
    public static List<Account> selectAccount(Connection connection) throws SQLException {
        List<Account> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM TaiKhoan");
        while (rs.next()){
            list.add(new Account(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5)
            ));
        }

        return list;
    }
}
