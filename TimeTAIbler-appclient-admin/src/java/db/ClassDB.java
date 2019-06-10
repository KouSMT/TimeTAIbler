/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Dennis
 */
public class ClassDB {
    
    public static Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        String url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        String username = "APP";
        String password = "APP";
        return DriverManager.getConnection(url, username, password);
    }
    
    public void createClassTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE CLASS ( "
                    + " ClassId CHAR(8) CONSTRAINT PK_CLASS PRIMARY KEY, "
                    + " SubjectId CHAR(8), ClassType CHAR(1), StartTime Time(8), "
                    + " Hours INTEGER(10), Day SMALLINT(5))");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }
    
    public void dropClassTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("DROP TABLE CLASS");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }
    
    public void addRecords(ArrayList<ClassObject> classes) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "INSERT INTO CLASS VALUES (?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            for (ClassObject classObject : classes) {
                pStmnt.setString(1, classObject.getClassid());
                pStmnt.setString(2, classObject.getSubjectid());
                pStmnt.setString(3, String.valueOf(classObject.getClasstype()));
                pStmnt.setDate(4, classObject.getStartTime());
                pStmnt.setInt(5, classObject.getHours());
                pStmnt.setInt(6, classObject.getDay());

                int rowCount = pStmnt.executeUpdate();
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }
    
    public boolean createRecord(ClassObject classObject) {
        Connection cnnct = null;
        String findExistRecordSQL = "SELECT * FROM CLASS WHERE ClassId = ? ";
        String addRecordSQL
                    = "INSERT INTO CLASS VALUES (?, ?, ?, ?, ?, ?)";
        ResultSet rsltSet;
        boolean rslt = false;
        PreparedStatement pStmnt = null; 
        try {
            cnnct = getConnection();
            pStmnt = cnnct.prepareStatement(findExistRecordSQL);
            pStmnt.setString(1, classObject.getClassid());
            rsltSet = pStmnt.executeQuery();
            if (!rsltSet.next()) {
                pStmnt.setString(1, classObject.getClassid());
                pStmnt.setString(2, classObject.getSubjectid());
                pStmnt.setString(3, String.valueOf(classObject.getClasstype()));
                pStmnt.setDate(4, classObject.getStartTime());
                pStmnt.setInt(5, classObject.getHours());
                pStmnt.setInt(6, classObject.getDay());
                
                int rowCount = pStmnt.executeUpdate();
                rslt = true;
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert record!");
                }
            } else {
                rslt = false;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return rslt;
    }
    
    public boolean updateRecord(ClassObject classObject) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        String preQueryStatement = "UPDATE CLASS " +
                " SET SubjectId = ?, ClassType = ?, StartTime = ?, Hours = ?, Day = ? WHERE ClassId = ?";
        boolean rslt = false;
        
        try {
            cnnct = getConnection();
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            pStmnt.setString(1, classObject.getSubjectid());
            pStmnt.setString(2, String.valueOf(classObject.getClasstype()));
            pStmnt.setDate(3, classObject.getStartTime());
            pStmnt.setInt(4, classObject.getHours());
            pStmnt.setInt(5, classObject.getDay());
            pStmnt.setString(6, classObject.getClassid());
            
            int rowCount = pStmnt.executeUpdate();
            if (rowCount == 0) {
                throw new SQLException("Cannot modify the record"); 
            }
            rslt = true;
        } catch (SQLException ex) {
            while (ex != null) {
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
        } finally {
            // close Prepared Statement object
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return rslt;
    }
    
    public boolean deleteRecord(String classId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        String preQueryStatement = "DELETE FROM CLASS WHERE ClassId = ?";
        boolean rslt = false;
        
        try {
            cnnct = getConnection();
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            pStmnt.setString(1, classId);
            
            int rowCount = pStmnt.executeUpdate();
            if (rowCount == 0) {
                throw new SQLException("Cannot delete the record"); 
            }
            rslt = true;
        } catch (SQLException ex) {
            while (ex != null) {
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
        } finally {
            // close Prepared Statement object
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return rslt;
    }
}
