package view.renders;

import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This class is used to obtain certian formaters that format only numbers.
 * @author Jorge A. Cano
 */

public class NumberRenderer extends DefaultTableCellRenderer {

    public static final Locale MEXICAN_LOCALE = Locale.forLanguageTag("es-MX");
    private final Format formatter;
    
    /*
     *  Use the specified number formatter and right align the text
     */
    public NumberRenderer( NumberFormat formatter ) {
        
        this.formatter = formatter;
        setHorizontalAlignment( SwingConstants.RIGHT );
    }

    
    /*
     *  Use the default currency formatter for the mexican locale
     */
    public static NumberRenderer getMXNCurrencyRenderer() {
        
        return new NumberRenderer( NumberFormat.
                getCurrencyInstance( NumberRenderer.MEXICAN_LOCALE ) );
    }

    
    /*
     *  Use the default integer formatter for the default locale
     */
    public static NumberRenderer getIntegerRenderer() {
        
        return new NumberRenderer( NumberFormat.getIntegerInstance() );
    }

    
    /*
     *  Use the default percent formatter for the default locale
     */
    public static NumberRenderer getPercentRenderer() {
        
        return new NumberRenderer( NumberFormat.getPercentInstance() );
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
}
