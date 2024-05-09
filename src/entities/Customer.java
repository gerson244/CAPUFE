package entities;


import java.util.ArrayList;
import java.util.List;
import conectorDB.ConectorDB;
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
    private String user = new String();
    private String password = new String();

    
    
    public Customer() {
    }
    
    public Customer(String name, String direction, String phone) {
        this.name = name;
        this.direction=direction;
        this.phone=phone;
    }
    public Customer(String name, String direction, String phone, String user, String password) {
        this.name = name;
        this.direction=direction;
        this.phone=phone;
        this.user=user;
        this.password=password;
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
            Connection conexion = ConectorDB.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM Customer WHERE name LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                Customer c = new Customer();
                c.setId(resultset.getInt("id"));
                c.setName(resultset.getString("name"));
                c.setPhone(resultset.getString("phone"));
                c.setDirection(resultset.getString("direction"));
                c.setUser(resultset.getString("user"));
                c.setPassword(resultset.getString("password"));
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
            Connection conexion = ConectorDB.get();
            String query = "INSERT INTO Customer (name, phone, direction, user, password) VALUES (?,?,?,?,?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, direction);
            statement.setString(4, user);
            statement.setString(5, password);

            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception ex) {
            System.err.print("Error: " + ex);
        }
        return result;
    }

    public boolean update(int id, String name, String phone, String direction, String user, String password) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "UPDATE Customer SET name=?, phone=?, direction=?, user=?, password=? WHERE id=?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, direction);
            statement.setString(4, user);
            statement.setString(5, password);
            statement.setInt(6, id);
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
            Connection conexion = ConectorDB.get();
            String query = "DELETE FROM Customer WHERE id=?";
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
            Connection conexion = ConectorDB.get();
            String query = "SELECT * FROM Customer WHERE id =?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();

            if (resultset.next()) {
                customer = new Customer();
                customer.setId(resultset.getInt("id"));
                customer.setName(resultset.getString("name"));
                customer.setPhone(resultset.getString("phone"));
                customer.setDirection(resultset.getString("direction"));
                customer.setUser(resultset.getString("user"));
                customer.setPassword(resultset.getString("password"));

            }
        } catch (Exception ex) {
            System.err.print("Error: " + ex);
        }
        return customer;
    }
    public static Customer login(String user, String password) {
        try(Connection connection = ConectorDB.get();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM Customer WHERE user=? AND password=?")
                ){
            statement.setString(1, user);
            statement.setString(2, password);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setDirection(resultSet.getString("direction"));
                    customer.setPhone(resultSet.getString("phone"));
                    customer.setUser(resultSet.getString("user"));
                    customer.setPassword(resultSet.getString("password"));
                    return customer;
                }
            }
            
        } catch (SQLException ex) {
            // log the exception or throw a custom exception
            System.err.print("Error: " + ex);
        }
        return null;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
