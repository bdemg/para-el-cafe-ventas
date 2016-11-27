package model.pricemodels;

import javax.swing.SpinnerNumberModel;

/**
 *
 * @author (c) Copyright 2016 José A. Soto. All Rights Reserved.
 */
public class ProductPriceTemplate extends SpinnerNumberModel{
    
    private static final double START_PRICE = 1.0;
    private static final double MIN_PRICE = 1.0;
    private static final double MAX_PRICE = 99999.50;
    private static final double STEP_BY = 0.50;
    
    public ProductPriceTemplate(){
        
        super(
            ProductPriceTemplate.START_PRICE,
            ProductPriceTemplate.MIN_PRICE,
            ProductPriceTemplate.MAX_PRICE,
            ProductPriceTemplate.STEP_BY
        );
    }
    
    @Override
    public void setValue(Object value){
        
        boolean isPriceInRange = MIN_PRICE <= (Double)value || (Double)value >= MAX_PRICE;
        if(isPriceInRange){
            
            boolean isPriceStepByCorrect = (Double)value % STEP_BY == 0;
            
            if(isPriceStepByCorrect){
                
                super.setValue(value);
            }
            else{
                
                this.setValue( ProductPriceTemplate.START_PRICE );
            }
        }
    }
    
}
