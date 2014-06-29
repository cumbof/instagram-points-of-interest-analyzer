/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.util;

import java.util.HashMap;

/**
 *
 * @author Fabio Cumbo
 */
public class LocationData {
    
    private HashMap<String, Integer> tag2freq = new HashMap<>();
    private long locID;
    private double locLatitude;
    private double locLongitude;
    
    public LocationData() {}
    
    public void setLocID(long id) {
        this.locID = id;
    }
    
    public void setLocLatitude(double lat) {
        this.locLatitude = lat;
    }
    
    public void setLocLongitude(double lon) {
        this.locLongitude = lon;
    }
    
    public void setTag2Freq(HashMap<String, Integer> t2f) {
        this.tag2freq = t2f;
    }
    
    public long getLocID() {
        return this.locID;
    }
    
    public double getLocLatitude() {
        return this.locLatitude;
    }
    
    public double getLocLongitude() {
        return this.locLongitude;
    }
    
    public HashMap<String, Integer> getTag2Freq() {
        return this.tag2freq;
    }
    
    public void addTag(String tag, int freq) {
        this.tag2freq.put(tag, freq);
    }
    
}
