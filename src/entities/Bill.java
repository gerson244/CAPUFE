
package entities;

import conectorDB.ConectorDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author 
 */
public class Bill {
    
    private int id;
    private Customer customer;
    private Register register;

    public static List<Bill> getAll(String filtro) {
        List<Bill> bill = new ArrayList<>();
        try {
            Connection conexion = ConectorDB.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM bill WHERE id LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Bill b = new Bill();
                b.setId(resultSet.getInt("id"));   
                
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id_customer"));
                // Puedes cargar el resto de los atributos si es necesario
                b.setCustomer(customer);
          
                Register register = new Register();
                register.setId(resultSet.getInt("id_register"));
                // Puedes cargar el resto de los atributos si es necesario
                b.setRegister(register);
       
                bill.add(b);
            }
        } catch (SQLException ex) {
            System.err.print("Error: " + ex);
        }
        return bill;
    }
    
    public boolean save(Bill bill) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "INSERT  INTO bill (id_customer, id_register ) VALUES(?,?)";
            PreparedStatement statement = conexion.prepareStatement(query);

            statement.setInt(1, bill.getCustomer().getId());
            statement.setInt(2, bill.getRegister().getId());
            
            statement.execute();
            
            result = statement.getUpdateCount()==1;
            
            conexion.close();
        } catch (Exception ex) {
            System.err.println("Error "+ex.getMessage());
        }
        return result;
    }
     
    public boolean update(Bill bill) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "UPDATE bill SET id_customer=? id_register=? WHERE id=?";
            PreparedStatement statement= conexion.prepareStatement(query);
            statement.setInt(1, bill.getCustomer().getId());
            statement.setInt(2, bill.getRegister().getId());
            statement.setInt(3, bill.getId());
            statement.execute();
            
            result = statement.getUpdateCount()==1;
            
            conexion.close();
        } catch (Exception ex) {
            System.err.println("Error "+ex.getMessage());
        }
        return result;
    }
    
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "DELETE FROM bill WHERE id= ?";
            PreparedStatement statement= conexion.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            
            result = statement.getUpdateCount()==1;
            
            conexion.close();
        } catch (Exception ex) {
            System.err.println("Error "+ex.getMessage());
        }
        return result;
    }
    
    public static Bill getById(int id) {
        Bill bill = null;
        try {
            Connection conexion = ConectorDB.get();
            String query = "SELECT * FROM bill WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                bill = new Bill();
                bill.setId(resultSet.getInt("id"));   
                
               Customer customer = new Customer();
                customer.setId(resultSet.getInt("id_customer"));
                // Puedes cargar el resto de los atributos si es necesario
                bill.setCustomer(customer);
          
                Register register = new Register();
                register.setId(resultSet.getInt("id_register"));
                // Puedes cargar el resto de los atributos si es necesario
                bill.setRegister(register);
            }

            conexion.close();
        } catch (SQLException ex) {
            System.err.print("Error: " + ex);
        }
        return bill;
    }
    
    public Bill(int id, Customer customer, Register register) {
        this.id = id;
        this.customer = customer;
        this.register = register;
    }
    
    
    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
    
      
}
