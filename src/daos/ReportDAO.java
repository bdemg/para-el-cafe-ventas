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
    
    private WritableWorkbook reportBook;
    private WritableSheet reportSheet;
    
    public ReportDAO() throws IOException, WriteException{
        ;
    }
    
    private WritableWorkbook openWorkbook() throws IOException{
        
        reportBook = Workbook.createWorkbook( fileRoute(), setWorkbookSettings() );        
        return reportBook;
    }
    
    private File fileRoute(){
        File file = new File( "MonthlyReport.xls" );
        return file;
    }
    
    private WorkbookSettings setWorkbookSettings() throws IOException{
        
        WorkbookSettings reportSettings = new WorkbookSettings();
        reportSettings.setLocale( Locale.ENGLISH );
        
        return reportSettings;
    }
    
    public WritableSheet createSheet(String title, int numberOfPage) throws IOException{
        
        reportSheet = openWorkbook().createSheet( title, numberOfPage);
        return reportSheet;
    }
    
    public void writeDownLabeledCells(int column, int row, String textToInsert) throws WriteException{
        
        Label labeledCell = new Label(column, row, textToInsert);
        reportSheet.addCell(labeledCell);
    }
    
    public void writeDownNumberCells(int column, int row, double textToInsert) throws WriteException{
        
        Number numberCell = new Number(column, row, textToInsert);
        reportSheet.addCell(numberCell);
    }
    
    public void closeReportWorkbook() throws IOException, WriteException{
        this.reportBook.write();
        this.reportBook.close();
    }
}