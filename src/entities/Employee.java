
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
public class Employee {
    
    private int id;
    private String name = new String();
    private String degree = new String();
    private double salary;
    private Stand stand;
    
    public static List<Employee> getAll(String filtro) {
        List<Employee> employee = new ArrayList<>();
        try {
            Connection conexion = ConectorDB.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM employee WHERE name LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Employee e = new Employee();
                e.setId(resultSet.getInt("id"));   
                
                e.setName(resultSet.getString("name"));
                e.setDegree(resultSet.getString("degree"));
                e.setSalary(resultSet.getDouble("salary"));
                
                Stand stand = new Stand();
                stand.setId(resultSet.getInt("id_stand"));
                // Puedes cargar el resto de los atributos si es necesario
                e.setStand(stand);
                
                employee.add(e);
            }
        } catch (SQLException ex) {
            System.err.print("Error: " + ex);
        }
        return employee;
    }
    
    public boolean save(Employee employee) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "INSERT  INTO employee (name, degree, salary, id_stand ) VALUES(?,?,?,?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, degree);
            statement.setDouble(3, salary);
            statement.setInt(4, employee.getStand().getId());
            
            statement.execute();
            
            result = statement.getUpdateCount()==1;
            
            conexion.close();
        } catch (Exception ex) {
            System.err.println("Error "+ex.getMessage());
        }
        return result;
    }
     
    public boolean update(Employee employee) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "UPDATE employee SET name=?, degree=?, salary=?, id_stand=? WHERE id=?";
            PreparedStatement statement= conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, degree);
            statement.setDouble(3, salary);
            statement.setInt(4, employee.getStand().getId());
            statement.setInt(5, employee.getId());
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
            String query = "DELETE FROM employee WHERE id= ?";
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
    
    public static Employee getById(int id) {
        Employee employee = null;
        try {
            Connection conexion = ConectorDB.get();
            String query = "SELECT * FROM employee WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getInt("id"));   
                
                employee.setName(resultSet.getString("name"));
                employee.setDegree(resultSet.getString("degree"));
                employee.setSalary(resultSet.getDouble("salary"));
                
                Stand stand = new Stand();
                stand.setId(resultSet.getInt("id_stand"));
                // Puedes cargar el resto de los atributos si es necesario
                employee.setStand(stand);

            }

            conexion.close();
        } catch (SQLException ex) {
            System.err.print("Error: " + ex);
        }
        return employee;
    }
    
    public Employee() {
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }
    
    
    
}
