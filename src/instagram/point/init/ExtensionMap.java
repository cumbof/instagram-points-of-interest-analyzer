/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.init;

import java.util.ArrayList;

/**
 *
 * @author Fabio Cumbo
 */
public class ExtensionMap {
    
    private ArrayList<String> map;
    
    public ExtensionMap() {
        initMap();
    }
    
    public ArrayList<String> getMap() {
        return this.map;
    }
    
    private void initMap() {
        map = new ArrayList<>();
        map.add(".TXT");
        map.add(".XML");
    }
    
}
