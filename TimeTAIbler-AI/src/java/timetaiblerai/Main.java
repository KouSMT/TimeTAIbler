/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetaiblerai;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.jdbc.driver.OracleConnection;

/**
 *
 * @author Dennis
 */
public class Main {

    static String url;
    static String username;
    static String password;

    PreparedStatement statement = null;

    public static void main(String[] args) {

        Main main = new Main();
        try {
            main.run(args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("end");
    }

    OracleConnection connect() throws SQLException {
        url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        username = "APP";
        password = "APP";

        OracleDriver dr = new OracleDriver();

        Properties prop = new Properties();
        prop.setProperty("user", username);
        prop.setProperty("password", password);

        return (OracleConnection) dr.connect(url, prop);
    }

    public void run(String[] args) throws Exception {
        OracleConnection dbConnection = connect();
        Properties prop = new Properties();
        prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true");
        prop.setProperty(OracleConnection.DCN_QUERY_CHANGE_NOTIFICATION, "true");
        DatabaseChangeRegistration dcr = dbConnection.registerDatabaseChangeNotification(prop);
        try {
            dcr.addListener(new AI(this));

            Statement stmt = dbConnection.createStatement();
            ((OracleStatement) stmt).setDatabaseChangeRegistration(dcr);
            ResultSet rs = stmt.executeQuery("select * from PREFERENCE");
            while (rs.next()) {
            }
            String[] tableNames = dcr.getTables();
            for (int i = 0; i < tableNames.length; i++) {
                System.out.println(tableNames[i] + " is part of the registration.");
            }
            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            if (dbConnection != null) {
                dbConnection.unregisterDatabaseChangeNotification(dcr);
            }
            throw ex;
        } finally {
            try {
                dbConnection.close();
            } catch (Exception innerex) {
                innerex.printStackTrace();
            }
        }

        synchronized (this) {
            try {
                OracleConnection conn2 = connect();
                conn2.setAutoCommit(false);
                Statement stmt2 = conn2.createStatement();
                stmt2.executeUpdate("insert into PREFERENCE (PreferenceId,StudentId,Day,StartTime,Hours) values ('PRE1','STU1', 1, 00:00, 1)",
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet autoGeneratedKey = stmt2.getGeneratedKeys();
                if (autoGeneratedKey.next()) {
                    System.out.println("inserted one row with ROWID=" + autoGeneratedKey.getString(1));
                }
                stmt2.executeUpdate("insert into PREFERENCE (PreferenceId,StudentId,Day,StartTime,Hours) values ('PRE1','STU1', 1, 00:00, 1)",
                        Statement.RETURN_GENERATED_KEYS);
                autoGeneratedKey = stmt2.getGeneratedKeys();
                if (autoGeneratedKey.next()) {
                    System.out.println("inserted one row with ROWID=" + autoGeneratedKey.getString(1));
                }
                stmt2.close();
                conn2.commit();
                conn2.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // wait until we get the event
            try {
                this.wait();
            } catch (InterruptedException ie) {
            }
        }
    }
}

