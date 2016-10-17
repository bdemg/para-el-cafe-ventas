/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Jorge A. Cano
 */
public class NumberFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int off, String str, AttributeSet attr)
            throws BadLocationException {
        fb.insertString(off, str.replaceAll("\\D++", ""), attr);  // remove non-digits
    }

    @Override
    public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr)
            throws BadLocationException {
        fb.replace(off, len, str.replaceAll("\\D++", ""), attr);  // remove non-digits
    }
}
