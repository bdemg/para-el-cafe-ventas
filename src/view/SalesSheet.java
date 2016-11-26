/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.PlainDocument;
import view.documentfilters.NumberFilter;
import model.OrdersList;
import model.duedatemodels.DueDayTemplate;
import model.duedatemodels.DueHourTemplate;
import model.duedatemodels.DueMinuteTemplate;
import model.duedatemodels.DueMonthTemplate;
import model.duedatemodels.DueYearTemplate;
import view.editors.JComboBoxTableEditor;
import view.renders.JComboBoxTableRenderer;
import view.editors.JSpinnerTableEditor;
import view.renders.JSpinnerTableRenderer;
import view.renders.NumberRenderer;

/**
 *
 * @author Jorge A. Cano
 */
public class SalesSheet extends javax.swing.JFrame{

    /**
     * Creates new form SalesSheet
     */
    
    public SalesSheet() {
        initComponents();
        
        this.setTableLook();
        
        PlainDocument format = new PlainDocument();
        format.setDocumentFilter( new NumberFilter() );
        this.clientPhoneNumber.setDocument( format );
        this.setupSalesSheet();
        
    }
    
    private void setupSalesSheet(){
        
        this.setVisible( true );
        this.setLocationRelativeTo( null );
        this.setResizable( false );
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
        clientSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        addProduct = new javax.swing.JButton();
        removeProduct = new javax.swing.JButton();
        storeOrder = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        totalSale = new javax.swing.JLabel();
        calculateSale = new javax.swing.JButton();
        clientPhoneNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dueDay = new javax.swing.JSpinner();
        dueDay.setModel(new DueDayTemplate());
        dueMonth = new javax.swing.JSpinner();
        dueMonth.setModel(new DueMonthTemplate());
        dueYear = new javax.swing.JSpinner();
        dueYear.setModel(new DueYearTemplate());
        dueHour = new javax.swing.JSpinner();
        dueHour.setModel(new DueHourTemplate());
        dueMinute = new javax.swing.JSpinner();
        dueMinute.setModel(new DueMinuteTemplate());
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cancelOrder = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        references = new javax.swing.JTextArea();
        serviceWindow = new view.SupervisorServiceWindow();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Para el Café");

        jLabel1.setText("Teléfono:");

        jLabel2.setText("Nombre:");

        clientName.setText(" ");

        jLabel3.setText("Dirección:");

        clientAddress.setText(" ");

        clientSearch.setText("Buscar Cliente");

        ordersTable.setModel(new model.OrdersList(0));
        ordersTable.setEnabled(false);
        jScrollPane1.setViewportView(ordersTable);

        addProduct.setText("Añadir producto");
        addProduct.setEnabled(false);

        removeProduct.setText("Eliminar producto");
        removeProduct.setEnabled(false);

        storeOrder.setText("Registrar orden");
        storeOrder.setEnabled(false);

        jLabel4.setText("Costo Total:");

        totalSale.setText(NumberFormat.getCurrencyInstance(NumberRenderer.MEXICAN_LOCALE).format(0.0));

        calculateSale.setText("Calcular costo");
        calculateSale.setEnabled(false);

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

        cancelOrder.setText("Cancelar Orden");
        cancelOrder.setEnabled(false);

        jLabel12.setText("Referencias:");

        references.setEditable(false);
        references.setColumns(20);
        references.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        references.setRows(5);
        jScrollPane2.setViewportView(references);

        setJMenuBar(serviceWindow);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(dueDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(dueMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(dueYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(dueHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(dueMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calculateSale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(removeProduct)
                                .addGap(18, 18, 18)
                                .addComponent(addProduct)
                                .addGap(30, 30, 30)
                                .addComponent(cancelOrder)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(storeOrder)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalSale)))
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(clientPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clientSearch)
                                .addGap(189, 189, 189)
                                .addComponent(jLabel12))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clientAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clientName, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(clientSearch)
                            .addComponent(clientPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(clientAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(clientName, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel6)
                .addGap(3, 3, 3)
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
                    .addComponent(jLabel11)
                    .addComponent(calculateSale)
                    .addComponent(jLabel4)
                    .addComponent(totalSale))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProduct)
                    .addComponent(removeProduct)
                    .addComponent(storeOrder)
                    .addComponent(cancelOrder))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] ) {
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
        java.awt.EventQueue.invokeLater( new Runnable() {

            public void run() {
                SalesSheet sales = new SalesSheet();
                sales.setVisible( true );
            }
        }
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProduct;
    private javax.swing.JButton calculateSale;
    private javax.swing.JButton cancelOrder;
    private javax.swing.JLabel clientAddress;
    private javax.swing.JLabel clientName;
    private javax.swing.JTextField clientPhoneNumber;
    private javax.swing.JButton clientSearch;
    private javax.swing.JSpinner dueDay;
    private javax.swing.JSpinner dueHour;
    private javax.swing.JSpinner dueMinute;
    private javax.swing.JSpinner dueMonth;
    private javax.swing.JSpinner dueYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable ordersTable;
    private javax.swing.JTextArea references;
    private javax.swing.JButton removeProduct;
    private javax.swing.JMenuBar serviceWindow;
    private javax.swing.JButton storeOrder;
    private javax.swing.JLabel totalSale;
    // End of variables declaration//GEN-END:variables

    public OrdersList getOrdersList() {
        return ( OrdersList ) this.ordersTable.getModel();
    }
  
    
    public void setOrdersList( OrdersList input_ordersList ){
        
        this.ordersTable.setModel( input_ordersList );
        this.setTableLook();
    }
    
    
    public JLabel getClientAddress() {
        
        return clientAddress;
    }

    
    public JLabel getClientName() {
        
        return clientName;
    }

    public void setTotalSale( double input_totalSale ){
        
        this.totalSale.setText( NumberFormat.
                getCurrencyInstance( NumberRenderer.MEXICAN_LOCALE ).
                format( input_totalSale ) );
    }
    
    public JButton getAddProduct() {
        
        return addProduct;
    }

    
    public JButton getCalculateSale() {
        
        return calculateSale;
    }
    

    public JButton getClientSearch() {
        
        return clientSearch;
    }

    
    public JTextField getClientPhoneNumber() {
        
        return clientPhoneNumber;
    }

    
    public JButton getRemoveProduct() {
        
        return removeProduct;
    }

    
    public JButton getStoreOrder() {
        
        return storeOrder;
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

    
    public JButton getCancelOrder() {
        return cancelOrder;
    }

    
    public JTextArea getReferences() {
        return references;
    }
    
    
    private void setTableLook() {
        
        this.ordersTable.getColumnModel().getColumn( OrdersList.PRODUCT_NAME ).
            setCellEditor( new JComboBoxTableEditor() );
        this.ordersTable.getColumnModel().getColumn( OrdersList.PRODUCT_QUANTITY ).
            setCellEditor( new JSpinnerTableEditor() );

        this.ordersTable.getColumnModel().getColumn( OrdersList.PRODUCT_NAME ).
            setCellRenderer( new JComboBoxTableRenderer() );
        this.ordersTable.getColumnModel().getColumn( OrdersList.PRODUCT_QUANTITY ).
            setCellRenderer( new JSpinnerTableRenderer() );
        
        this.ordersTable.getColumnModel().getColumn( OrdersList.PRODUCT_PRICE ).
            setCellRenderer( NumberRenderer.getMXNCurrencyRenderer() );
    }
    
    
    public SupervisorServiceWindow getServiceWindow() {
        return (SupervisorServiceWindow) serviceWindow;
    }
}
