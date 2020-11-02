package app.database.QueryAll;

import app.entity.permanentAddress;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class queryPermanentAddress {
    public static List<permanentAddress> selectPermanentAddress(Connection connection) throws SQLException {
        List<permanentAddress> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM DiaChiTT");
        while (rs.next()){
            list.add(new permanentAddress(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            ));

        }
        return list;
    }

}
