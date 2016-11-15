package view.documentfilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Esta clase se usa para restringir la entrada de
 * caracteres en un campo de texto a sólo numeros
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
