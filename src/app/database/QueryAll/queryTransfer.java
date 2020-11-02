package app.database.QueryAll;


import app.entity.transfer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class queryTransfer {
    public static List<transfer> selectTransfer(Connection connection) throws SQLException {
        List<transfer> list= new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM DiChuyen");
        while (rs.next()){
            list.add(new transfer(
               rs.getInt(1),
               rs.getInt(2),
               rs.getInt(3),
               rs.getInt(4),
               rs.getInt(5),
               rs.getDate(6),
               rs.getInt(7)
            ));
        }

        return list;
    }
}
