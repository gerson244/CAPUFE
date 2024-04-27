
package entities;

/**
 *
 * @author 
 */
public class Stand {
    
    private int id;
    private String name = new String();
    private String location = new String();
    
    public Stand() {
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
