package app.database.QueryAll;

import app.entity.contribute;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class queryContribute {
    public static List<contribute> selectContribute(Connection connection) throws SQLException {
        List<contribute> list= new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM DongGop");
        while (rs.next()){
            list.add(new contribute(
                rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getDouble(4),
                rs.getDate(5),
                rs.getString(6)
            ));
        }

        return list;
    }

}
