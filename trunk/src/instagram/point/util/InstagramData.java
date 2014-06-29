/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.util;

import java.io.*;
import java.util.ArrayList;
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
    public static ArrayList<String> popularTags = new ArrayList<>();
    public static HashMap<String, LocationData> location2tagFrequency = new HashMap<String, LocationData>();
    
    public static void setAccessToken(String token) {
        accessToken = token;
    }
    
    public static String getAccessToken() {
        return accessToken;
    }
    
    public static void setMediaFeeds2User(HashMap<MediaFeedData, User> data, boolean toReset) {
        if (toReset)
            mediaFeeds2user = data;
        else {
            for (MediaFeedData m: data.keySet()) {
                mediaFeeds2user.put(m, data.get(m));
            }
        }
    }
    
    public static void setLocation2MediaFeeds(HashMap<Location, List<MediaFeedData>> data, boolean toReset) {
        location2mediaFeeds = data;
        if (toReset)
            location2mediaFeeds = data;
        else {
            for (Location l: data.keySet()) {
                location2mediaFeeds.put(l, data.get(l));
            }
        }
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
    
    public static ArrayList<String> getPopularTags() {
        if (popularTags.isEmpty()) {
            initPopularTags();
        }
        return popularTags;
    }
    
    private static void initPopularTags() {
        try {
            File inputFile = new File("popularTags.txt");
            FileInputStream fos = new FileInputStream(inputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fos));
            String line;
            while ((line = br.readLine()) != null) {
                popularTags.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLocation2tagFrequency(HashMap<String, LocationData> data) {
        location2tagFrequency = data;
    }
    
    public static HashMap<String, LocationData> getLocation2TagFrequency() {
        return location2tagFrequency;
    }
    
}
