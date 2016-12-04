/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Thes class represents a client of the bakery
 * @author Jorge A. Cano
 */
public class Client {
    
    private final String phonenumber;
    private final String name;
    private final Location location;

    public Client(
        String input_phonenumber,
        String input_name,
        String input_address,
        String input_references
    ) {
        
        this.phonenumber = input_phonenumber;
        this.name = input_name;
        this.location = new Location(input_address, input_references);
    }

    
    /**
     * @return the phonenumber
     */
    public String getPhonenumber() {
        
        return this.phonenumber;
    }

    
    /**
     * @return the name
     */
    public String getName() {
        
        return this.name;
    }

    
    /**
     * @return the address
     */
    public String getAddress() {
        
        return this.location.getAddress();
    }
    
    
    /**
     * @return the address
     */
    public String getReferences() {
        
        return this.location.getReferences();
    }
}
