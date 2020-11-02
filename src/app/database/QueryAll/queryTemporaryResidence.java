package app.database.QueryAll;

import app.entity.temporaryResidence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class queryTemporaryResidence {
    public static List<temporaryResidence> selectTemporaryResidence(Connection connection) throws SQLException {
        List<temporaryResidence> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM TamTruVang");
        while (rs.next()){
            list.add(new temporaryResidence(
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getDate(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6)
            ));
        }

        return list;
    }

}
