package app.database.QueryAll;


import app.entity.accountFee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class queryAccountFee {
    public static List<accountFee> selectAccountFee(Connection connection) throws SQLException {
        List<accountFee> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM TaiKhoan");
        while (rs.next()){
            list.add(new accountFee(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5)
            ));
        }

        return list;
    }

}
