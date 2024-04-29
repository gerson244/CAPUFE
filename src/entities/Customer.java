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
public class Customer {

    private int id;
    private String name = new String();
    private String direction = new String();
    private String phone = new String();

    
    
    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static List<Customer> getAll(String filtro) {
        List<Customer> customer = new ArrayList<>();
        try {
            Connection conexion = conectorDB.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM Customer WHERE name LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                Customer c = new Customer();
                c.setId(resultset.getInt("id"));
                c.setName(resultset.getString("name"));
                c.setPhone(resultset.getString("phone"));
                c.setDirection(resultset.getString("direction"));
                customer.add(c);

            }
        } catch (SQLException ex) {
            System.err.print("Error: " + ex);
        }
        return customer;
    }

    /**
     * Metodo para guardar
     *
     * @param customer atrae todos los atributos para guadarlos
     * @return
     */
    public boolean save(Customer customer) {
        boolean result = false;
        try {
            Connection conexion = conectorDB.get();
            String query = "INSERT INTO customer (name, phone, direction) VALUES (?,?,?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, direction);

            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception ex) {
            System.err.print("Error: " + ex);
        }
        return result;
    }

    public boolean update(int id, String name, String phone, String direction) {
        boolean result = false;
        try {
            Connection conexion = conectorDB.get();
            String query = "UPDATE customer SET name=?, phone=?, direction=? WHERE id=?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, direction);
            statement.setInt(4, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception ex) {
            System.err.print("Error: " + ex);
        }
        return result;
    }

    /**
     *
     */
    public int delete(int id) {
        int result = 0;
        try {
            Connection conexion = conectorDB.get();
            String query = "DELETE FROM customer WHERE id=?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeUpdate();
            conexion.close();
        } catch (Exception ex) {
            System.err.print("Error: " + ex);
        }
        return result;
    }

    public static Customer getById(int id) {
        Customer customer = null;
        try {
            Connection conexion = conectorDB.get();
            String query = "SELECT * FROM customer WHERE id =?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();

            if (resultset.next()) {
                customer = new Customer();
                customer.setId(resultset.getInt("id"));
                customer.setName(resultset.getString("name"));
                customer.setPhone(resultset.getString("phone"));
                customer.setDirection(resultset.getString("direction"));

            }
        } catch (Exception ex) {
            System.err.print("Error: " + ex);
        }
        return customer;
    }
}
