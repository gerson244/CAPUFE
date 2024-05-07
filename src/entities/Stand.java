package entities;

import conectorDB.ConectorDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class Stand {

    private int id;
    private String name = new String();
    private String location = new String();

    public static List<Stand> getAll(String filtro) {
        List<Stand> stand = new ArrayList<>();
        try {
            Connection conexion = ConectorDB.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM stand WHERE name LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Stand s = new Stand();
                s.setId(resultSet.getInt("id"));
                s.setName(resultSet.getString("name"));
                s.setLocation(resultSet.getString("location"));

                stand.add(s);
            }
        } catch (SQLException ex) {
            System.err.print("Error: " + ex);
        }
        return stand;
    }

    public boolean save(Stand stand) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "INSERT  INTO stand (name, location ) VALUES(?,?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, location);

            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception ex) {
            System.err.println("Error " + ex.getMessage());
        }
        return result;
    }

    public boolean update(Stand stand) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "UPDATE stand SET name=?, location=? WHERE id=?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, stand.getName());
            statement.setString(2, stand.getLocation());
            statement.setInt(3, stand.getId());
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
            String query = "DELETE FROM stand WHERE id= ?";
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

    public static Stand getById(int id) {
        Stand stand = null;
        try {
            Connection conexion = ConectorDB.get();
            String query = "SELECT * FROM stand WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                stand = new Stand();
                stand.setId(resultSet.getInt("id"));
                stand.setName(resultSet.getString("name"));
                stand.setLocation(resultSet.getString("location"));

            }

            conexion.close();
        } catch (SQLException ex) {
            System.err.print("Error: " + ex);
        }
        return stand;
    }

    public Stand() {
    }
    
    public Stand(String name, String location){
        this.name = name;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
