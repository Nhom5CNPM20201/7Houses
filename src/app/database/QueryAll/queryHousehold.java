package app.database.QueryAll;

import app.entity.HouseHold;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class queryHousehold {
    public static List<HouseHold> selectHousehold(Connection connection) throws SQLException {
        List<HouseHold> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM HoKhau");
        while (rs.next()){
            list.add(new HouseHold(
               rs.getInt(1),
               rs.getInt(2),
               rs.getInt(3),
               rs.getString(4),
               rs.getString(5)
            ));
        }

        return list;
    }
}
