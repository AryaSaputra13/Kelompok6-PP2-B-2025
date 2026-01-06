/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Tubes.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Arya
 */
public class connectionDB {
    private static Connection mysqlconfig;
    
    public static Connection configDB() throws SQLException{
        try {
            String url = "jdbc:mysql://localhost:3306/tubes_pp2";
            String user = "root";
            String pass = "";
            
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: "+ e.getMessage());
        }
        return mysqlconfig;
    }
    
}
