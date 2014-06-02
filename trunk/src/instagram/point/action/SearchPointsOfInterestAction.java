/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.action;

import instagram.point.Main;
import instagram.point.util.InstagramData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jinstagram.entity.common.Location;
import org.jinstagram.entity.common.User;
import org.jinstagram.entity.locations.LocationSearchFeed;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;

/**
 *
 * @author Fabio Cumbo
 */
public class SearchPointsOfInterestAction extends Action {

    @Override
    public ArrayList<Object> execute(String command) {
        return null;
    }

    @Override
    public ArrayList<Object> execute() {

        HashMap<Location, List<MediaFeedData>> location2mediaFeeds = new HashMap<Location, List<MediaFeedData>>();
        HashMap<MediaFeedData, User> mediaFeeds2user = new HashMap<MediaFeedData, User>();
        
        
        Main.getApp().getJSONOutputTextArea().setText("");
        
        String message = "";
        
        String latitude = Main.getApp().getLatitudeTextField().getText();
        String longitude = Main.getApp().getLongitudeTextField().getText();
        String distance = Main.getApp().getDistanceTextField().getText();
        
        if (latitude!=null && !latitude.equals("")) {
            if (longitude!=null && !longitude.equals("")) {
                if (distance!=null && !distance.equals("")) {
                    
                    double d_latitude = Double.valueOf(latitude);
                    double d_longitude = Double.valueOf(longitude);
                    int i_distance = Integer.valueOf(distance);
                    
                    try {
                        
                        LocationSearchFeed searchLocationFeed = Main.getInstagram().searchLocation(d_latitude, d_longitude, i_distance);
                        
                        for (Location location: searchLocationFeed.getLocationList()) {
                            double media_latitude = location.getLatitude();
                            double media_longitude = location.getLongitude();
                            MediaFeed searchMediaFeed = Main.getInstagram().searchMedia(media_latitude, media_longitude); 
                            List<MediaFeedData> mediaFeeds = searchMediaFeed.getData();
                            location2mediaFeeds.put(location, mediaFeeds);
                            
                            for (MediaFeedData media: mediaFeeds) {
                                mediaFeeds2user.put(media, media.getUser());
                                message += "MEDIA ID: " + media.getId() + "\nLOCATION LATITUDE: " + media.getLocation().getLatitude()
                                        + "\nLOCATION LONGITUDE: " + media.getLocation().getLongitude() + "\nLOCATION NAME: " + media.getLocation().getName()
                                        + "\nUSER ID: " + media.getUser().getId() + "\nUSER NAME: " + media.getUser().getUserName() 
                                        + "\nUSER FULL NAME: " + media.getUser().getFullName() + "\n\n";
                            }
                        }
                        message += "\nRETRIEVED MEDIA: " + mediaFeeds2user.size();
                        
                        if ((location2mediaFeeds.size() > 0) && (mediaFeeds2user.size() > 0)) {
                            InstagramData.setLocation2MediaFeeds(location2mediaFeeds);
                            InstagramData.setMediaFeeds2User(mediaFeeds2user);
                        }
                        
                    } catch (Exception ex) {
                        message = "AN ERROR HAS OCCURRED RETRIEVING POINTS OF INTEREST...";
                    }
                    
                }
                else
                    message += "DISTANCE CANT BE NULL\n";
            }
            else {
                message += "LONGITUDE CANT BE NULL\n";
                if (distance==null && distance.equals(""))
                    message += "DISTANCE CANT BE NULL\n";
            }
        }
        else {
            message += "LATITUDE CANT BE NULL\n";
            if (longitude==null || longitude.equals(""))
                message += "LONGITUDE CANT BE NULL\n";
            if (distance==null || distance.equals(""))
                message += "DISTANCE CANT BE NULL\n";
        }
        
        Main.getApp().getJSONOutputTextArea().setText(message);
        
        return null;
    }
    
}
