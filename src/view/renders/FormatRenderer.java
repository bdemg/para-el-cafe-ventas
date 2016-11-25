package view.renders;

import java.text.Format;
import java.text.DateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This class is used to format certian values.
 * This class is based on external code that can be found in it's original form here
 * https://tips4java.wordpress.com/2008/10/11/table-format-renderers/
 * @author  Jorge A. Cano
 */
public class FormatRenderer extends DefaultTableCellRenderer {

    private final Format formatter;

    
    /*
     *   Use the specified formatter to format the Object
     */
    public FormatRenderer( Format input_formatter ) {
        
        this.formatter = input_formatter;
    }

    
    //  Format the Object before setting its value in the renderer
    @Override
    public void setValue( Object input_value ) {

        try {
            
            if ( input_value != null ) {
                
                input_value = this.formatter.format( input_value );
            }
        } catch ( IllegalArgumentException e ) {
            e.printStackTrace();
        }

        super.setValue( input_value );
    }

    
    /*
     *  Use the default date/time formatter for the default locale
     */
    public static FormatRenderer getDateTimeRenderer() {
        
        return new FormatRenderer( DateFormat.getDateTimeInstance() );
    }

    
    /*
     *  Use the default time formatter for the default locale
     */
    public static FormatRenderer getTimeRenderer() {
        
        return new FormatRenderer( DateFormat.getTimeInstance() );
    }
}
