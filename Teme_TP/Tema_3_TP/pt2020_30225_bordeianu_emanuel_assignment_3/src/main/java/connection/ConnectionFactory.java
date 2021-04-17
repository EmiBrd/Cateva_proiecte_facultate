package connection;

import java.beans.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName() );
    // JDBC driver name and database URL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3307/temattp";
    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "root";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /***
     * ConnectionFactory
     */
    private ConnectionFactory() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /***
     * metoda pentru creare conexiune
     * @return
     */
    private static Connection createConnection() {
        Connection connect = null;
        try {
            //STEP 3: Open a connection
            connect = DriverManager.getConnection(DBURL, USER, PASS);
            if (connect != null) {
                //System.out.println("Connected to the database!");
                return connect;
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connect;
    }

    /***
     * getter pentru conexiune
     * @return singleInstance.createConnection()
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /***
     * metoda pentru inchidere conexiune
     * @param connection
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    /***
     * metoda pentru inchidere statement
     * @param preparedStatement
     */
    public static void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /***
     * metoda pentru inchidere ResultSet
     * @param resultSet
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }


}



