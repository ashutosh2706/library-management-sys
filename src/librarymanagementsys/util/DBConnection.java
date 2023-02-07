/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsys.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ashutosh
 */
public class DBConnection {
    static Connection conn = null;
    
    // the jdbc driver class and connection auth
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/libms", "root", "1953");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
