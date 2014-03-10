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

import instagram.point.init.ControllerMap;
import instagram.point.init.ExtensionMap;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.util.HashMap;
import javax.swing.*;

/**
 *
 * @author Fabio Cumbo
 */
public interface Application {
        
    public JTextField getAccessTokenTextField();
    
    public JButton getConnectInstagramButton();
    
    public JLabel getConnectionStatusLabel();
    
    public JTextField getLatitudeTextField();
    
    public JTextField getLongitudeTextField();
    
    public JTextField getDistanceTextField();
    
    public JButton getSearchPointsButton();
    
    public JTextArea getJSONOutputTextArea();
    
    
    
    public void initControllerMap();

    public HashMap<Object, String> getControllerMap();
    
    public ControllerMap getCommand2Action();
    
    public ExtensionMap getExtensionMap();
    
    public Frame getFrame();
    
    public void setVisibleOverride(boolean value);
    public void setDefaultCloseOperationOverride(int value);
    
}
