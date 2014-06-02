/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.init;

import java.util.HashMap;

/**
 *
 * @author Fabio Cumbo
 */
public class ControllerMap {
    
    private HashMap<String, String> map;
    
    public ControllerMap() {
        initMap();
    }
    
    public HashMap<String, String> getMap() {
        return this.map;
    }
    
    private void initMap() {
        map = new HashMap<>();
        map.put("connect_button", "instagram.point.action.ConnectInstagramAction");
        map.put("searchPoints_button", "instagram.point.action.SearchPointsOfInterestAction");
        map.put("exportData_button", "instagram.point.action.ExportDataAction");
        map.put("findCommonInterests_button", "instagram.point.action.FindCommonInterestsAction");
    }
    
}
