package app.database.interfaces;

import app.database.base.ISQLDatabase;
import app.database.config.ChangeConfig;
import app.database.config.ContributeConfig;
import app.entity.Change;
import app.entity.Contribute;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ISQLDatabaseContribute extends ISQLDatabase {

    default List<Contribute> getAllContribute() throws Exception {
        try {
            Statement stmt = this.getDatabase().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + ContributeConfig.TABLE_NAME);
            List<Contribute> changeList = new ArrayList<>();

            while(rs.next()) {
                changeList.add(extractChange(rs));
            }
            return changeList;
        }
        catch(SQLException e) {
            throw e;
        }
    }

    default void insertContribute(Contribute contribute) throws Exception{
        PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + ContributeConfig.TABLE_NAME +
                " (" + ContributeConfig.CONTRIBUTE_IDFEE +"," +ContributeConfig.CONTRIBUTE_IHOUSEHOLD + ","
                + ContributeConfig.CONTRIBUTE_AMOUNT + "," + ContributeConfig.CONTRIBUTE_CREATEDDATE + "," + ContributeConfig.CONTRIBUTE_NOTE + ") "
                + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS );

        stmt.setInt(1, contribute.getIdFee());
        stmt.setInt(2, contribute.getIdHouseHold());
        stmt.setInt(3, contribute.getAmount());
        stmt.setDate(4, new java.sql.Date(contribute.getCreateDate().getTime()));
        stmt.setString(5, contribute.getNote());
        stmt.executeUpdate();

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            contribute.setId(generatedKeys.getInt(1));
        }
        generatedKeys.close();
        stmt.close();
    }

//    default void updateContribute(Change change) throws Exception{
//        PreparedStatement stmt = this.getDatabase().prepareStatement("UPDATE " + ChangeConfig.TABLE_NAME + " SET "
//                + ChangeConfig.CHANGE_TYPE + "=?" + " WHERE " + ChangeConfig.CHANGE_ID + "=? ");
//        stmt.setInt(1, change.getType());
//        stmt.setInt(2, change.getId());
//        stmt.executeUpdate();
//
//        stmt.close();
//    }

//    default void removeContribute(Change change) throws Exception{
//        PreparedStatement stmt = this.getDatabase().prepareStatement("DELETE FROM " + ChangeConfig.TABLE_NAME + " WHERE "
//                + ChangeConfig.CHANGE_ID + "=" + "?");
//        stmt.setInt(1, change.getId());
//        stmt.executeUpdate();
//        stmt.close();
//    }

    private Contribute extractChange(ResultSet rs) throws Exception {
        int id = rs.getInt(ContributeConfig.CONTRIBUTE_ID);
        int idHouseHold = rs.getInt(ContributeConfig.CONTRIBUTE_IHOUSEHOLD);
        int idFee = rs.getInt(ContributeConfig.CONTRIBUTE_IDFEE);
        int amount = rs.getInt(ContributeConfig.CONTRIBUTE_AMOUNT);
        Date createdDate = new Date(rs.getDate(ContributeConfig.CONTRIBUTE_CREATEDDATE).getTime());
        String note = rs.getString(ContributeConfig.CONTRIBUTE_NOTE);

        Contribute contribute = new Contribute();
        contribute.setId(id);
        contribute.setIdHouseHold(idHouseHold);
        contribute.setIdFee(idFee);
        contribute.setAmount(amount);
        contribute.setCreateDate(createdDate);
        contribute.setNote(note);

        return contribute;
    }
}
