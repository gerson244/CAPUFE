/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conectorDB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author vagui
 */
public class ConectorDB {
    public static Connection get() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://capufe.ctsk2cimm11s.us-east-1.rds.amazonaws.com:3306/capufe?allowPublicKeyRetrieval=true&useSSL=false","bdagms","q54D7dZgItlSNAsBVlGm");
        } catch (Exception ex) {
            System.err.print("Error: " + ex.getMessage());
        }
        return connection;
    }
}
