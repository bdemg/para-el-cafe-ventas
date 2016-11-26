/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This class represents the service window of the supervisor, where you can interact
 * with him.
 * @author Jorge A. Cano
 */
public class SupervisorServiceWindow extends JMenuBar{

    private final JMenuItem registerClient;
    private final JMenuItem monthlyReport;
    private final JMenuItem todaysDeliveries;
    private final JMenuItem changePrice;
    
    private final String REPORT_OPTIONS = "Reportes";
    private final String CLIENT_OPTIONS = "Clientes";
    private final String PRODUCT_OPTIONS = "Productos";
    
    private final String REGISTER_CLIENT = "Registrar Cliente";
    private final String MONTHLY_REPORT = "Reporte Mensual";
    private final String TODAYS_DELIVERIES = "Entregas de Hoy";
    private final String CHANGE_PRICE = "Cambiar Precio a Producto";

    public SupervisorServiceWindow() {
        
        this.registerClient = new JMenuItem( this.REGISTER_CLIENT );
        this.monthlyReport = new JMenuItem( this.MONTHLY_REPORT );
        this.todaysDeliveries = new JMenuItem( this.TODAYS_DELIVERIES );
        this.changePrice = new JMenuItem( this.CHANGE_PRICE );
        
        this.setupClientRelatedOptions();
        this.setupReportRelatedOptions();
        this.setupProductRelatedOptions();
    }
    

    private void setupClientRelatedOptions(){
        
        JMenu clientOptions = new JMenu( this.CLIENT_OPTIONS );
        clientOptions.add( this.registerClient );
        this.add( clientOptions );
    }
    
    
    private void setupReportRelatedOptions(){
        
        JMenu reportOptions = new JMenu( this.REPORT_OPTIONS );
        reportOptions.add( this.monthlyReport );
        reportOptions.add( this.todaysDeliveries );
        this.add( reportOptions );
    }
    
    private void setupProductRelatedOptions(){
        
        JMenu productOptions = new JMenu( this.PRODUCT_OPTIONS );
        productOptions.add( this.changePrice );
        this.add( productOptions );
    }
    
    public JMenuItem getRegisterClient() {
        
        return registerClient;
    }

    
    public JMenuItem getMonthlyReport() {
        
        return monthlyReport;
    }

    
    public JMenuItem getTodaysDeliveries() {
        
        return todaysDeliveries;
    }

    
    public JMenuItem getChangePrice() {
        
        return changePrice;
    }
}
