package model;

/**
 * This class represents the receipt of a completed sale
 * @author Evan-Ian-Ray
 */
public final class SalesReceipt {
    
    private final int folio;
    private final String phoneNumber;
    private final String productName;
    private final int productQuantity;
    private final double subtotal;
    private final String date;
    
    public SalesReceipt(
            int input_folio,
            String input_phoneNumber,
            String input_productName,
            int input_productQuantity,
            double input_subtotal,
            String input_date
        ){
        
        this.folio = input_folio;
        this.phoneNumber = input_phoneNumber;
        this.productName = input_productName;
        this.productQuantity = input_productQuantity;
        this.subtotal = input_subtotal;
        this.date = input_date;
    }
    
    /**
     * @return the folio
     */
    public int getFolio() {
        return folio;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @return the productQuantity
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     * @return the subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }
    
}
