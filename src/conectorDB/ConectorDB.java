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
    
    public static Connection get(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("http://capufe.ctsk2cimm11s.us-east-1.rds.amazonaws.com", "root","123456789");
        }catch(Exception ex){
            System.err.print("Error: "+ex);
        }
        return connection;
    }
    /**
     * Nueva coneccion
     */    
}
