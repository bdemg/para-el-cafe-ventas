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
 *
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
    
    
    private void openWorkbook() throws IOException{
        
        reportBook = Workbook.createWorkbook( getFile(), writeWorkbookInLocalLenguage() );        
    }
    
    
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
    
    
    public void writeDownLabel(int column, int row, String textToInsert) throws WriteException{
        
        Label labeledCell = new Label(column, row, textToInsert);
        reportSheet.addCell(labeledCell);
    }
    
    
    public void writeDownNumber(int column, int row, double textToInsert) throws WriteException{
        
        Number numberCell = new Number(column, row, textToInsert);
        reportSheet.addCell(numberCell);
    }
    
    
    public void finishReport() throws IOException, WriteException{
        
        this.reportBook.write();
        this.reportBook.close();
    }
    
}