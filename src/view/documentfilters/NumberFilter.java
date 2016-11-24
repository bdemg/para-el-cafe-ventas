package view.documentfilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * This class is based on external code that can be found in it's original form here
 * http://stackoverflow.com/questions/5662651/how-to-implement-in-java-jtextfield-class-to-allow-entering-only-digits
 * @author Jorge A. Cano
 */
public class NumberFilter extends DocumentFilter {

    @Override
    public void insertString(
        FilterBypass fb,
        int off,
        String str,
        AttributeSet attr
    )throws BadLocationException {
        
        fb.insertString( off, str.replaceAll( "\\D++", "" ), attr );
    }

    @Override
    public void replace(
        FilterBypass fb,
        int off,
        int len,
        String str,
        AttributeSet attr
    )throws BadLocationException {
        
        fb.replace( off, len, str.replaceAll( "\\D++", "" ), attr );
    }
}
