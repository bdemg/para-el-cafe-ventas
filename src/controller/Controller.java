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

    //obtain the events caused by the relevant components in the window
    @Override
    public abstract void actionPerformed( ActionEvent input_event );
    
    //add observers to the relevant components of the window
    protected abstract void addActionListeners();
}
