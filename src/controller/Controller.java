/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jorge A. Cano
 */
public abstract class Controller implements ActionListener{

    @Override
    public abstract void actionPerformed( ActionEvent e );
    
    
    protected abstract void addActionListeners();
}
