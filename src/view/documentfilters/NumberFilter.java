package view.documentfilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * This class is used to restrict the input of text fields to only numbers. This class
 * is based on external code that can be found in it's original form here
 * http://stackoverflow.com/questions/5662651/how-to-implement-in-java-jtextfield-class-to-allow-entering-only-digits
 * @author Jorge A. Cano
 */
public class NumberFilter extends DocumentFilter {

    
    //when new strings are being insetrted restrict the content to only numbers
    @Override
    public void insertString(
        FilterBypass input_filterBypass,
        int input_offset,
        String input_string,
        AttributeSet input_attributes
    )throws BadLocationException {
        
        input_filterBypass.insertString( input_offset,
            input_string.replaceAll( "\\D++", "" ), input_attributes );
    }

    
    
    @Override
    public void replace(
        FilterBypass input_filterBypass,
        int input_offset,
        int input_length,
        String input_string,
        AttributeSet input_attrubutes
    )throws BadLocationException {
        
        input_filterBypass.replace( input_offset, input_length,
            input_string.replaceAll( "\\D++", "" ), input_attrubutes );
    }
}
