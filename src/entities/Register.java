
package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conectorDB.ConectorDB;
import java.text.SimpleDateFormat;

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
    
    
    public static List<Register> getAll(String filtro) {
        List<Register> register = new ArrayList<>();
        try {
            Connection conexion = ConectorDB.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM register WHERE license_plate LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Register r = new Register();
                r.setId(resultSet.getInt("id"));   
                
                Stand stand = new Stand();
                stand.setId(resultSet.getInt("id_stand"));
                // Puedes cargar el resto de los atributos si es necesario
                r.setStand(stand);
                
                r.setLicensePlate(resultSet.getString("license_plate"));
                r.setDataTime(resultSet.getDate("date_time"));
                r.setMark(resultSet.getString("mark"));
                r.setType(resultSet.getString("type"));
                
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id_employee"));
                // Puedes cargar el resto de los atributos si es necesario
                r.setEmployee(employee);
       
                register.add(r);
            }
        } catch (SQLException ex) {
            System.err.print("Error: " + ex);
        }
        return register;
    }
    
    public boolean save(Register register) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "INSERT  INTO register (id_stand, license_plate, date_time, mark, type, id_employee ) VALUES(?,?,?,?,?,?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, register.getStand().getId());
            statement.setString(2, register.getLicensePlate());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            statement.setString(3, dateFormat.format(register.getDataTime()));
            
            statement.setString(4, register.getMark());
            statement.setString(5, register.getType());
            statement.setInt(6, register.getEmployee().getId());
            
            statement.execute();
            
            result = statement.getUpdateCount()==1;
            
            conexion.close();
        } catch (Exception ex) {
            System.err.println("Error "+ex.getMessage());
        }
        return result;
    }
     
    public boolean update(Register register) {
        boolean result = false;
        try {
            Connection conexion = ConectorDB.get();
            String query = "UPDATE register SET id_stand=?, license_plate=?, date_time=?, mark=?, type=?, id_employee=? WHERE id=?";
            PreparedStatement statement= conexion.prepareStatement(query);
            statement.setInt(1, register.getStand().getId());
            statement.setString(2, register.getLicensePlate());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            statement.setString(3, dateFormat.format(register.getDataTime()));
            
            statement.setString(4, register.getMark());
            statement.setString(5, register.getType());
            statement.setInt(6, register.getEmployee().getId());
            statement.setInt(7, register.getId());
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
            String query = "DELETE FROM register WHERE id= ?";
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
    
    public static Register getById(int id) {
        Register register = null;
        try {
            Connection conexion = ConectorDB.get();
            String query = "SELECT * FROM register WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                register = new Register();
                register.setId(resultSet.getInt("id"));   
                
                Stand stand = new Stand();
                stand.setId(resultSet.getInt("id_stand"));
                // Puedes cargar el resto de los atributos si es necesario
                register.setStand(stand);
                
                register.setLicensePlate(resultSet.getString("license_plate"));
                register.setDataTime(resultSet.getDate("date_time"));
                register.setMark(resultSet.getString("mark"));
                register.setType(resultSet.getString("type"));
                
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id_employee"));
                // Puedes cargar el resto de los atributos si es necesario
                register.setEmployee(employee);
            }

            conexion.close();
        } catch (SQLException ex) {
            System.err.print("Error: " + ex);
        }
        return register;
    }
    
    
    public Register() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
    
}
