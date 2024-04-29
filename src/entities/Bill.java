
package entities;

import java.util.ArrayList;
import java.util.List;
import conectorDB.conectorDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author 
 */
public class Bill {
    
    private int id;
    private Customer customer;
    private Register register;

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
    
    public static List<Bill>getAll(String filtro){
        List<Bill> bill = new ArrayList<>();
        try{
            Connection conexion = conectorDB.get();
            PreparedStatement statement =conexion.prepareStatement("SELECT * FROM bill WHERE id =?");
            
            
        }catch(Exception ex){
            System.err.print("Error> :"+ex);
        }
        return bill;
    }
    
}
