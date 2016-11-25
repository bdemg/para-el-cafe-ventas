package controller;

import java.awt.event.ActionEvent;

import view.PricesBoard;

/**
 *
 * @author (c) Copyright 2016 José A. Soto. All Rights Reserved.
 */
public class PricesManager extends Controller{
    
    private final PricesBoard pricesBoard;
    
    public PricesManager(){
        
        this.pricesBoard = new PricesBoard();
        
        this.addActionListeners();
    }
    
    @Override
    protected void addActionListeners() {
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    
    
}
