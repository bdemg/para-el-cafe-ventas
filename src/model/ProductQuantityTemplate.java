/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.SpinnerNumberModel;

/**
 * This class represents the quantity of a product that can be written in the sales sheet.
 * @author Jorge A. Cano
 */
public class ProductQuantityTemplate extends SpinnerNumberModel{

    
    private final static int STARTING_QUANTITY_OF_PRODUCT = 1;
    private final static int MINIMUM_QUANTITY_OF_PRODUCT = 1;
    private final static int MAXIMUM_QUANTYTY_OF_PRODUCT = 100;
    private final static int STEP_BY = 1;
    
    public ProductQuantityTemplate() {
        super(
            ProductQuantityTemplate.STARTING_QUANTITY_OF_PRODUCT,
            ProductQuantityTemplate.MINIMUM_QUANTITY_OF_PRODUCT,
            ProductQuantityTemplate.MAXIMUM_QUANTYTY_OF_PRODUCT,
            ProductQuantityTemplate.STEP_BY
        );
    }
    
}
