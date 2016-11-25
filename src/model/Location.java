/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * This class represents the location of a client
 * @author Jorge A. Cano
 */
public class Location {
    
    private final String address;
    private final String references;

    
    public Location(String address, String references) {
        
        this.address = address;
        this.references = references;
    }

    
    /**
     * @return the address
     */
    public String getAddress() {
        
        return this.address;
    }

    
    /**
     * @return the references
     */
    public String getReferences() {
        
        return this.references;
    }
    
}
