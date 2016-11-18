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
 *
 * @author Jorge A. Cano
 */
public class OptionsMenuBar extends JMenuBar{

    private final JMenuItem registerClient;
    private final JMenuItem monthlyReport;
    private final JMenuItem todaysDeliveries;
    
    private final String REPORT_OPTIONS = "Reportes";
    private final String CLIENT_OPTIONS = "Clientes";
    
    private final String REGISTER_CLIENT = "Registrar Cliente";
    private final String MONTHLY_REPORT = "Reporte Mensual";
    private final String TODAYS_DELIVERIES = "Entregas de Hoy";

    public OptionsMenuBar() {
        this.registerClient = new JMenuItem(this.REGISTER_CLIENT);
        this.monthlyReport = new JMenuItem(this.MONTHLY_REPORT);
        this.todaysDeliveries = new JMenuItem(this.TODAYS_DELIVERIES);
        
        JMenu clientOptions = new JMenu(this.CLIENT_OPTIONS);
        JMenu reportOptions = new JMenu(this.REPORT_OPTIONS);
        
        clientOptions.add(this.registerClient);
        
        reportOptions.add(this.monthlyReport);
        reportOptions.add(this.todaysDeliveries);
        
        this.add(clientOptions);
        this.add(reportOptions);
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
    
}
