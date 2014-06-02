/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.util;

import java.util.HashMap;
import java.util.List;
import org.jinstagram.entity.common.Location;
import org.jinstagram.entity.common.User;
import org.jinstagram.entity.users.feed.MediaFeedData;

/**
 *
 * @author Fabio Cumbo
 */
public class InstagramData {
    
    public static String accessToken = "";
    public static HashMap<MediaFeedData, User> mediaFeeds2user = new HashMap<MediaFeedData, User>();
    public static HashMap<Location, List<MediaFeedData>> location2mediaFeeds = new HashMap<Location, List<MediaFeedData>>();
    public static HashMap<String, List<String>> userCouple2interests = new HashMap<String, List<String>>();
    
    public static void setAccessToken(String token) {
        accessToken = token;
    }
    
    public static String getAccessToken() {
        return accessToken;
    }
    
    public static void setMediaFeeds2User(HashMap<MediaFeedData, User> data) {
        mediaFeeds2user = data;
    }
    
    public static void setLocation2MediaFeeds(HashMap<Location, List<MediaFeedData>> data) {
        location2mediaFeeds = data;
    }
    
    public static void setUserCouple2Interests(HashMap<String, List<String>> data) {
        userCouple2interests = data;
    }
    
    public static HashMap<MediaFeedData, User> getMediaFeeds2User() {
        return mediaFeeds2user;
    }
    
    public static HashMap<Location, List<MediaFeedData>> getLocation2MediaFeeds() {
        return location2mediaFeeds;
    }
    
    public static HashMap<String, List<String>> getUserCouple2Interests() {
        return userCouple2interests;
    }
    
}
