
package entities;

import java.util.Date;

/**
 *
 * @author 
 */
public class Register {
    
    private int id;
    private String licensePlate = new String();
    private Date dataTime;
    private String mark = new String();
    private String type = new String();
    private Stand stand;
    private Employee employee;
    
    /*
    public static List<Register> getAll(String filtro) {
        List<Register> register = new ArrayList<>();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM LV_clientes WHERE name LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            ResultSet resulSet = statement.executeQuery();

            while (resulSet.next()) {
                Register r = new Register();
                r.setId(resulSet.getInt(1));
                r.setName(resulSet.getString(2));
                r.setTariff(resulSet.getInt(3));
                r.setLocation(resulSet.getString(4));
                register.add(c);
            }
        } catch (SQLException ex) {
            System.err.print("Error: " + ex);
        }
        return register;
    }
    
    public boolean save(String name, int tariff, String location) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "INSERT  INTO LV_clientes (nombre, apellido, email) VALUES(?,?,?)";
            PreparedStatement statement= conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, tariff);
            statement.setString(3, location);
            statement.execute();
            
            result = statement.getUpdateCount()==1;
            
            conexion.close();
        } catch (Exception ex) {
            System.err.println("Error "+ex.getMessage());
        }
        return result;
    }
    
    public boolean update(int id, String name, int tariff, String location) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "UPDATE LV_clientes SET nombre=?, apellido=?, email=? WHERE id=?";
            PreparedStatement statement= conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, tariff);
            statement.setString(3, location);
            statement.setInt(4, id);
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
            Connection conexion = MySQLConnection.get();
            String query = "DELETE FROM LV_clientes WHERE id= ?";
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
    
    public static Caseta getById(int id) {
        Caseta caseta = null;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "SELECT * FROM LV_peliculas WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                caseta = new Customer();
                caseta.setId(resultSet.getInt("id"));
                caseta.setName(resultSet.getString("nombre"));
                caseta.setTariff(resultSet.getInt("tarifa"));
                caseta.setLocation(resultSet.getString("ubicacion"));
            }

            conexion.close();
        } catch (SQLException ex) {
            System.err.print("Error: " + ex);
        }
        return caseta;
    }
    */
    
    public Register() {
    }
    
    
    
}
