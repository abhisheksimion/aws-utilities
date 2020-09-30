package com.aabingunz.fatjar;

import com.aabingunz.fatjar.common.ConfigProp;
import com.aabingunz.fatjar.common.Constants;

import java.io.Console;
import java.sql.*;

public class RedshiftConnection {
    private static Connection getDriverConnection() {
        Connection connection = null;
        try {
            Class.forName(ConfigProp.get(Constants.DRIVER_NAME));
            connection = DriverManager.getConnection(ConfigProp.get(Constants.JDBC_CONNECTION_STRING));
        } catch(SQLException sex) {
            System.err.println("ClassNotFoundException: " + sex.getMessage());
            sex.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFoundException: " + cnfe.getMessage());
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public static void executeQuery(String sqlQuery) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getDriverConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println("Result: ");
            while(rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("\n");
            }
            connection.close();
        }
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            System.out.println("Done with executeQuery function");
        }
    }

    public static void main(String args[]) {
        System.out.println("Amazon RedShift Connection example");
        System.out.println("Using Amazon JDBC url: "+ ConfigProp.get(Constants.JDBC_CONNECTION_STRING));
        System.out.println("Using Driver name: "+ ConfigProp.get(Constants.DRIVER_NAME));
        System.out.println("Using SQL query: "+ ConfigProp.get(Constants.QUERY));

        RedshiftConnection.executeQuery(ConfigProp.get(Constants.QUERY));
    }
}
