package app.database.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DatabaseManager {

    public abstract Connection getDatabase();

    public void executeList(List<String> statements) throws Exception {
        try {
            for (String statement : statements) {
                Statement stmt = getDatabase().createStatement();
                stmt.execute(statement);
            }
        } catch (SQLException e) {
            throw new Exception("Unable to Create Table", e);
        }
    }
}
