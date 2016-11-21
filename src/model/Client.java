/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jorge A. Cano
 */
public class Client {
    private final String phonenumber;
    private final String name;
    private Location location;

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
        
        return phonenumber;
    }

    
    /**
     * @return the name
     */
    public String getName() {
        
        return name;
    }

    
    /**
     * @return the location
     */
    public Location getLocation() {
        
        return location;
    }
}
