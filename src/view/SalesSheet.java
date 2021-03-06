/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.text.PlainDocument;
import model.NumberFilter;
import model.OrdersTaker;
import model.Time;
import view.editors.JComboBoxTableEditor;
import view.renders.JComboBoxTableRenderer;
import view.editors.SpinnerEditor;
import view.renders.JSpinnerTableRenderer;

/**
 *
 * @author Jorge A. Cano
 */
public class SalesSheet extends javax.swing.JFrame implements FocusListener{

    /**
     * Creates new form SalesSheet
     */
    
    private final int STEP_BY = 1;
    
    public SalesSheet() {
        initComponents();
        
        this.setEditorsAndRenderers();
        
        PlainDocument format = new PlainDocument();
        format.setDocumentFilter(new NumberFilter());
        this.clientPhoneNumber.setDocument(format);
        
        this.resetDueDate();
        
        this.dueDay.addFocusListener(this);
        this.dueHour.addFocusListener(this);
        this.dueMonth.addFocusListener(this);
        this.dueMinute.addFocusListener(this);
        this.dueYear.addFocusListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        clientName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        clientAddress = new javax.swing.JLabel();
        clientSearchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        addProductButton = new javax.swing.JButton();
        removeProductButton = new javax.swing.JButton();
        storeOrderButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        totalSale = new javax.swing.JLabel();
        calculateSaleButton = new javax.swing.JButton();
        clientPhoneNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dueDay = new javax.swing.JSpinner();
        dueMonth = new javax.swing.JSpinner();
        dueYear = new javax.swing.JSpinner();
        dueHour = new javax.swing.JSpinner();
        dueMinute = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Para el Café");
        setResizable(false);

        jLabel1.setText("Teléfono:");

        jLabel2.setText("Nombre:");

        clientName.setText("------------------------------------------------");

        jLabel3.setText("Dirección:");

        clientAddress.setText("----------------------------------------------------------------------------");

        clientSearchButton.setText("Buscar Cliente");

        ordersTable.setModel(new OrdersTaker(0));
        ordersTable.setEnabled(false);
        jScrollPane1.setViewportView(ordersTable);

        addProductButton.setText("Añadir producto");
        addProductButton.setEnabled(false);

        removeProductButton.setText("Eliminar producto");
        removeProductButton.setEnabled(false);

        storeOrderButton.setText("Registrar orden");
        storeOrderButton.setEnabled(false);

        jLabel4.setText("Costo Total:");

        totalSale.setText("---------");

        calculateSaleButton.setText("Calcular costo");
        calculateSaleButton.setEnabled(false);

        jLabel6.setText("Entrega:");

        dueDay.setEnabled(false);

        dueMonth.setEnabled(false);

        dueYear.setEnabled(false);

        dueHour.setEnabled(false);

        dueMinute.setEnabled(false);

        jLabel7.setText("Dia");

        jLabel8.setText("Mes");

        jLabel9.setText("Año");

        jLabel10.setText("Hora");

        jLabel11.setText("Minuto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clientPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clientSearchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(clientAddress)
                        .addGap(100, 100, 100))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clientName)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(dueDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalSale)
                                .addGap(6, 6, 6)))
                        .addGap(3, 3, 3)
                        .addComponent(dueMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(dueYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dueHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(dueMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 83, Short.MAX_VALUE)
                                .addComponent(calculateSaleButton)
                                .addGap(18, 18, 18)
                                .addComponent(addProductButton)
                                .addGap(18, 18, 18)
                                .addComponent(removeProductButton)
                                .addGap(18, 18, 18)
                                .addComponent(storeOrderButton)
                                .addGap(32, 32, 32))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(61, 61, 61))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(clientAddress)
                    .addComponent(jLabel3)
                    .addComponent(clientSearchButton)
                    .addComponent(clientPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(clientName))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dueDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dueMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dueYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dueHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dueMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProductButton)
                    .addComponent(removeProductButton)
                    .addComponent(storeOrderButton)
                    .addComponent(jLabel4)
                    .addComponent(totalSale)
                    .addComponent(calculateSaleButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {

            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException ex) {


        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                SalesSheet sales = new SalesSheet();
                sales.setVisible(true);
            }
        }
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProductButton;
    private javax.swing.JButton calculateSaleButton;
    private javax.swing.JLabel clientAddress;
    private javax.swing.JLabel clientName;
    private javax.swing.JTextField clientPhoneNumber;
    private javax.swing.JButton clientSearchButton;
    private javax.swing.JSpinner dueDay;
    private javax.swing.JSpinner dueHour;
    private javax.swing.JSpinner dueMinute;
    private javax.swing.JSpinner dueMonth;
    private javax.swing.JSpinner dueYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable ordersTable;
    private javax.swing.JButton removeProductButton;
    private javax.swing.JButton storeOrderButton;
    private javax.swing.JLabel totalSale;
    // End of variables declaration//GEN-END:variables

    public OrdersTaker getOrdersTaker() {
        return (OrdersTaker) this.ordersTable.getModel();
    }
  
    
    public void setOrdersTaker(OrdersTaker inputOrdersTaker){
        
        this.ordersTable.setModel(inputOrdersTaker);
        //this.setEditorsAndRenderers();
    }
    
    public void cleanOrdersTaker(){
        
        ordersTable = new javax.swing.JTable();
        ordersTable.setModel(new OrdersTaker(0));
        ordersTable.setEnabled(false);
        jScrollPane1.setViewportView(ordersTable);
        this.setEditorsAndRenderers();
    }
    
    public JLabel getClientAddress() {
        
        return clientAddress;
    }

    
    public JLabel getClientName() {
        
        return clientName;
    }

    
    public JLabel getTotalSale() {
        
        return totalSale;
    }

    
    public JButton getAddProductButton() {
        
        return addProductButton;
    }

    
    public JButton getCalculateSaleButton() {
        
        return calculateSaleButton;
    }
    

    public JButton getClientSearchButton() {
        
        return clientSearchButton;
    }

    
    public JTextField getClientPhoneNumber() {
        
        return clientPhoneNumber;
    }

    
    public JButton getRemoveProductButton() {
        
        return removeProductButton;
    }

    
    public JButton getStoreOrderButton() {
        
        return storeOrderButton;
    }

    
    public JTable getOrdersTable() {
        
        return ordersTable;
    }

    
    public JSpinner getDueDay() {
        
        return dueDay;
    }

    
    public JSpinner getDueHour() {
        
        return dueHour;
    }

    
    public JSpinner getDueMinute() {
        
        return dueMinute;
    }

    
    public JSpinner getDueMonth() {
        
        return dueMonth;
    }

    
    public JSpinner getDueYear() {
        
        return dueYear;
    }

    
    public void resetDueDate() {
        
        Calendar today = Calendar.getInstance();
        
        this.dueDay.setModel(new SpinnerNumberModel(today.get( Calendar.DAY_OF_MONTH ),
                Time.FIRST_DAY, Time.MAX_DAYS_IN_MONTH, this.STEP_BY));
        
        this.dueMonth.setModel(new SpinnerNumberModel( (today.get( Calendar.MONTH ) + 1),
                Time.FIRST_MONTH, Time.MONTHS_IN_YEAR, this.STEP_BY));
        
        this.dueYear.setModel(new SpinnerNumberModel( today.get( Calendar.YEAR ),
                today.get( Calendar.YEAR ), ( today.get(Calendar.YEAR) + 1 ), this.STEP_BY));
        
        this.dueHour.setModel(new SpinnerNumberModel( today.get(Calendar.HOUR_OF_DAY),
                Time.FIRST_HOUR_IN_DAY, Time.LAST_HOUR_IN_DAY, this.STEP_BY));
        
        this.dueMinute.setModel(new SpinnerNumberModel( today.get(Calendar.MINUTE),
                Time.FIRST_MINUTE_IN_HOUR, Time.LAST_MINUTE_IN_HOUR, this.STEP_BY));
    }

    private void setEditorsAndRenderers() {
        
        this.ordersTable.getColumnModel().getColumn(OrdersTaker.PRODUCT_NAME).
                setCellEditor(new JComboBoxTableEditor());
        this.ordersTable.getColumnModel().getColumn(OrdersTaker.PRODUCT_QUANTITY).
                setCellEditor(new SpinnerEditor());

        this.ordersTable.getColumnModel().getColumn(OrdersTaker.PRODUCT_NAME).
                setCellRenderer(new JComboBoxTableRenderer());
        this.ordersTable.getColumnModel().getColumn(OrdersTaker.PRODUCT_QUANTITY).
                setCellRenderer(new JSpinnerTableRenderer());
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.saveChangesInOrdersTable();
    }

    @Override
    public void focusLost(FocusEvent e) {
        //
    }
    
    public void saveChangesInOrdersTable(){
        if(this.ordersTable.isEditing()){
            this.ordersTable.getCellEditor().stopCellEditing();
        }
    }
    
    
    
}
