package app.database.QueryAll;

import app.entity.People;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class queryPeople {
    public static List<People> selectPeople(Connection connection) throws SQLException {
        List<People> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM nhankhau");
        while (rs.next()){
         list.add(new People(
                 rs.getInt(1),
                 rs.getInt(2),
                 rs.getString(3),
                 rs.getString(4),
                 rs.getString(5),
                 rs.getString(6),
                 rs.getDate(7),
                 rs.getDate(8),
                 rs.getInt(9),
                 rs.getString(10),
                 rs.getString(11),
                 rs.getInt(12),
                 rs.getString(13),
                 rs.getString(14),
                 rs.getDate(15),
                 rs.getString(16)
         ));
        }
        return list;
    }

}
