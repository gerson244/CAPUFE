
package entities;

/**
 *
 * @author 
 */
public class Bill {
    
    private int id;
    private Customer customer;
    private Register register;
    
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
