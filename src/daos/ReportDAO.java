/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */
package daos;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import jxl.Cell;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * This class is used to create the file in which the report will be written
 * @author Evan-Ian-Ray
 */
public class ReportDAO {
    
    private final int DEFAULT_PAGE_NUMBER = 0;
    private final String DEFAULT_SHEET_NAME = "Reporte del Mes";
    
    private final String BOOK_NAME = "ReporteMensual.xls";
    
    private WritableWorkbook reportBook;
    private WritableSheet reportSheet;
    
    
    public ReportDAO() throws IOException, WriteException{
        
        openWorkbook();
        createNewSheet( DEFAULT_SHEET_NAME, DEFAULT_PAGE_NUMBER );
        
    }
    
    //creates the workbook, given a fileroute, and their settings
    private void openWorkbook() throws IOException{
        
        reportBook = Workbook.createWorkbook( getFile(), writeWorkbookInLocalLenguage() );        
    }
    
    //creates a sheet inside the workbook, given a sheet title and a sheet number
    public void createNewSheet(String title, int numberOfPage) throws IOException{
        
        reportSheet = reportBook.createSheet( title, numberOfPage);
    }
    
    
    private File getFile(){
        
        File file = new File( BOOK_NAME );
        return file;
    }
    
    
    private WorkbookSettings writeWorkbookInLocalLenguage() throws IOException{
        
        WorkbookSettings reportSettings = new WorkbookSettings();
        reportSettings.setLocale( Locale.ROOT );
        
        return reportSettings;
    }
    
    //this funciton is used to write down information in the cells 
    public void writeDownLabel( int input_column, int input_row, String input_textToInsert ) throws WriteException{
        
        Label labeledCell = new Label( input_column, input_row, input_textToInsert );
        this.reportSheet.addCell(labeledCell);
    }
    
    //does the same than writeDownLabel, except the information is treated as numbers
    public void writeDownNumber( int input_column, int input_row, double input_textToInsert ) throws WriteException{
        
        Number numberCell = new Number( input_column, input_row, input_textToInsert );
        this.reportSheet.addCell( numberCell );
    }
    
    
    public void finishReport() throws IOException, WriteException{
        
        this.reportBook.write();
        this.reportBook.close();
    }
    
}