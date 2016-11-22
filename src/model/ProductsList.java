/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JComboBox;

/**
 *This class contains the list of products offered by the bakery.
 * @author Jorge A. Cano
 */
public class ProductsList extends JComboBox {

    private final static String[] PRODUCTS = {
        "Pastel de Naranja",
        "Brownies",
        "Roles de Canela",
        "Merengue Italiano",
        "Bisquet de Queso Paula",
        "Cupcake perfecto",
        "Crema de Mantequilla",
        "Pastel de Terciopelo Rojo",
        "Pay de Limón",
        "Bolitas de Nuez",
        "Glaseado de queso",
        "Pay de Queso",
        "Bolillo"
    };

    public ProductsList() {
        super( ProductsList.PRODUCTS );
    }
}
