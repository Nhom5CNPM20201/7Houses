package app.database.base;

import java.sql.Connection;



public interface ISQLDatabase extends IDatabase  {
    @Override
    void initializeDatabase() throws Exception;

    @Override
    Connection getDatabase() throws Exception;

    @Override
    void shutdown() throws Exception;

	
}
