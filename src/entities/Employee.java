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
    private String user = new String();
    private String password = new String();

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
                e.setUser(resultSet.getString("user"));
                e.setPassword(resultSet.getString("password"));

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
            String query = "INSERT INTO employee (name, degree, salary, id_stand, user, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, employee.getName());
        statement.setString(2, employee.getDegree());
        statement.setDouble(3, employee.getSalary());
        statement.setInt(4, employee.getStand().getId());
        statement.setString(5, employee.getUser());
        statement.setString(6, employee.getPassword());

            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception ex) {
            System.err.println("Error " + ex.getMessage());
        }
        return result;
    }

    public boolean update(Employee employee) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "UPDATE employee SET name=?, degree=?, salary=?, id_stand=?, user=?, password=? WHERE id=?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, degree);
            statement.setDouble(3, salary);
            statement.setInt(4, employee.getStand().getId());
            statement.setString(5, user);
            statement.setString(6, password);
            statement.setInt(7, employee.getId());
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception ex) {
            System.err.println("Error " + ex.getMessage());
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "DELETE FROM employee WHERE id= ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception ex) {
            System.err.println("Error " + ex.getMessage());
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
                employee.setUser(resultSet.getString("user"));
                employee.setPassword(resultSet.getString("password"));

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

    public Employee login(String user, String password) {
        try (Connection connection = ConectorDB.get(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE user =? AND password =?")) {
            statement.setString(1, user);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setId(resultSet.getInt("id"));
                    employee.setName(resultSet.getString("name"));
                    employee.setDegree(resultSet.getString("degree"));
                    employee.setSalary(resultSet.getDouble("salary"));
                    Stand stand = new Stand();
                    stand.setId(resultSet.getInt("id_stand"));
                    employee.setStand(stand);
                    return employee;
                }
            }
        } catch (SQLException ex) {
            // log the exception or throw a custom exception
            System.err.print("Error: " + ex);
        }
        return null; // invalid credentials
    }

    public Employee() {
    }

    public Employee(String name, String degree, double salary, int idCaseta, String user, String password) {
        this.name = name;
        this.degree = degree;
        this.salary = salary;
        this.stand = new Stand(); // Supongamos que Stand es una clase que representa una caseta
        this.stand.setId(idCaseta);
        this.user = user;
        this.password = password;
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
