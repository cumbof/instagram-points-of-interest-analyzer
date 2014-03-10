/*
 *	Software:		BiNAT - Biological Network Analysis Tool v2.0
 *	Authors:		Fabio Cumbo
 *	Organization:           IASI-CNR
 *	
 *	Distributed under Open Source License 
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point;

import instagram.point.action.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabio Cumbo
 */
public class Controller implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String commandKey = Main.getApp().getControllerMap().get(e.getSource());
            String actionClass = Main.getApp().getCommand2Action().getMap().get(commandKey);
            Action action = (Action)Class.forName(actionClass).newInstance();
            action.execute();
            System.out.println(actionClass);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
