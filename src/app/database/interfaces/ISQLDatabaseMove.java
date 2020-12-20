package app.database.interfaces;

import app.database.base.ISQLDatabase;
import app.database.config.MoveConfig;
import app.entity.Move;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public interface ISQLDatabaseMove extends ISQLDatabase{
	
	@Override
	default List<Move> getAllMove() throws Exception {
		try {
			Statement stmt = this.getDatabase().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + MoveConfig.TABLE_NAME);
			List<Move> moveList = new ArrayList<>();
			
			while(rs.next()) {
				moveList.add(extractMove(rs));
			}
			return moveList;
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	private Move extractMove(ResultSet rs) throws Exception {
		int id = rs.getInt(MoveConfig.MOVE_ID);
		int idHouseHold = rs.getInt(MoveConfig.MOVE_IHOUSEHOLD);
		int idNewAddress = rs.getInt(MoveConfig.MOVE_IDNEWADDRESS);
		int idOldAddress = rs.getInt(MoveConfig.MOVE_IDOLDADDRESS);
		Date movingTime = rs.getDate(MoveConfig.MOVE_MOVINGDATE);
		int type = rs.getInt(MoveConfig.MOVE_TYPE);
		
		return new Move(id, idHouseHold, idOldAddress, idNewAddress, movingTime, type);
	}
	
	@Override
	default void insertMove(Move move) throws Exception{
		PreparedStatement stmt = this.getDatabase().prepareStatement("INSERT INTO " + MoveConfig.TABLE_NAME +
                " (" + MoveConfig.MOVE_IHOUSEHOLD +"," + MoveConfig.MOVE_IDOLDADDRESS + ","
                + MoveConfig.MOVE_IDNEWADDRESS + "," + MoveConfig.MOVE_MOVINGDATE + "," + MoveConfig.MOVE_TYPE + ") " 
                + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS );
		stmt.setInt(1, move.getIdHouseHold());
		stmt.setInt(2, move.getIdOldAddress());
		stmt.setInt(3, move.getIdNewAddress());
		stmt.setDate(4, new Date(move.getMovingDate().getTime()));
		stmt.setInt(5, move.getType());
		stmt.executeUpdate();
		
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if (generatedKeys.next()) {
            move.setId(generatedKeys.getInt(1));
	    }
	    generatedKeys.close();
	    stmt.close();
	}
	
	@Override
	default void updateMove(Move move) throws Exception{
		PreparedStatement stmt = this.getDatabase().prepareStatement("UPDATE " + MoveConfig.TABLE_NAME + " SET "
				+ MoveConfig.MOVE_TYPE + "=?" + " WHERE " + MoveConfig.MOVE_ID + "=? ");
		stmt.setInt(1, move.getType());
		stmt.setInt(2, move.getId());
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	@Override
	default void removeMove(Move move) throws Exception{
		PreparedStatement stmt = this.getDatabase().prepareStatement("DELETE FROM " + MoveConfig.TABLE_NAME + " WHERE " 
				+ MoveConfig.MOVE_ID + "=" + "?");
		stmt.setInt(1, move.getId());
		stmt.executeUpdate();
		stmt.close();
	}
}
