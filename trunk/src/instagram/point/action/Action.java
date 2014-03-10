/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.action;

import java.util.ArrayList;

/**
 *
 * @author Fabio Cumbo
 */
public abstract class Action {
    
    //RETURN DATA - boolean , String
    //DATA INFO - completed/notCompleted , messsage
    
    public abstract ArrayList<Object> execute(String command);
    
    public abstract ArrayList<Object> execute();
    
}
