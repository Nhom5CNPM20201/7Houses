package app.database.QueryAll;

import app.entity.permanentAddress;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class queryPermanentAddress {
    //truyvan csdl
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
    //them dc
    public static void addNew(Connection conn, permanentAddress permanentAddress)throws  Exception {
        try {
            String query = "INSERT INTO DiaChiTT(id, sonha, duongpho, phuong, quan, thanhpho)"
                    + " values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, permanentAddress.getId());
            preparedStatement.setInt(2, permanentAddress.getNumberHouse());
            preparedStatement.setString(3, permanentAddress.getStreet());
            preparedStatement.setString(4, permanentAddress.getSubDistrict());
            preparedStatement.setString(5, permanentAddress.getDistrict());
            preparedStatement.setString(6, permanentAddress.getCity());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                int insertedAccountID = rs.getInt(1);
                permanentAddress.setId(insertedAccountID);
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //suadiachi
    public static void updateAddress(Connection connection ,permanentAddress permanentAddress) throws Exception{
            String query = "Update DiaChiTT set sonha = ? , duongpho = ?, phuong = ?, quan = ?, thanhpho = ? where id = "+ permanentAddress.getId();
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, permanentAddress.getNumberHouse());
                preparedStatement.setString(2, permanentAddress.getStreet());
                preparedStatement.setString(3, permanentAddress.getSubDistrict());
                preparedStatement.setString(4, permanentAddress.getDistrict());
                preparedStatement.setString(5, permanentAddress.getCity());
                preparedStatement.executeUpdate();
            preparedStatement.close();
            }
        catch (SQLServerException e){
        }
    }
        //xoa dia chi
        public static void remoteAddress(Connection connection ,int id) throws Exception{
            try {
                String query = "Delete from DiaChiTT where id = "+ id;
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());

            }
        }
}
