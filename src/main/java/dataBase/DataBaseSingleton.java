package dataBase;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseSingleton {
    private static ThreadLocal<DataBaseSingleton> instance = new ThreadLocal<>();
    private Connection connection;

    private DataBaseSingleton() throws SQLException {
        try {
            this.connection = DataBaseConnector.getDBConnectionFromClassConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseSingleton getInstance() throws SQLException {
        if (null == instance.get()) {
            instance.set(new DataBaseSingleton());
        }
        return instance.get();
    }

    public Connection getConnectionDB() {
        return connection;
    }

    public void closeDataBase() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            instance.remove();
        }
    }
}
