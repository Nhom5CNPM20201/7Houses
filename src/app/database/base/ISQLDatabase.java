package app.database.base;

import app.entity.Address;

import java.sql.Connection;
import java.util.List;

public interface ISQLDatabase extends IDatabase  {
    @Override
    void initializeDatabase() throws Exception;

    @Override
    Connection getDatabase() throws Exception;

    @Override
    void shutdown() throws Exception;

    void insertAddress(Address address) throws Exception;

    List<Address> getAllAddress() throws Exception;

    void updateAddress(Address address) throws Exception;

    void removeAddress(int id) throws Exception;

    Address searchAddress(int id) throws Exception;
}
