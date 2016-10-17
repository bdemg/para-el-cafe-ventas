/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JComboBox;

/**
 *
 * @author Jorge A. Cano
 */
public class ProductsList extends JComboBox {

    private final static String[] PRODUCTS = {
        "Pastel de Naranja",
        "Pan de Platano",
        "Concha",
        "Pastel de limón",
    };

    public ProductsList() {
        super(ProductsList.PRODUCTS);
    }
}
