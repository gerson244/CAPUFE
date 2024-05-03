/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conectorDB;
import java.sql.Connection;

/**
 *
 * @author vagui
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Use the ConectorDB class to get the connection
        Connection connection = ConectorDB.get();

        if (connection != null) {
            System.out.println("Conexión a la base de datos establecida correctamente!");
            // You can perform database operations here using the connection object
            // ...

            try {
                connection.close(); // Close the connection when finished
            } catch (Exception e) {
                System.err.println("Error cerrando la conexión: " + e.getMessage());
            }
        } else {
            System.err.println("Error al establecer la conexión a la base de datos.");
        }
    }
    
}
