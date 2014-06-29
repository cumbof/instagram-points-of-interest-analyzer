/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.action;

import instagram.point.Main;
import instagram.point.util.InstagramData;
import instagram.point.util.LocationData;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;

/**
 *
 * @author Fabio Cumbo
 */
public class StartRecommendationSystemAction extends Action {

    public HashMap<String, Integer> location2rank = new HashMap<>();
    
    @Override
    public ArrayList<Object> execute(String command) {
        return null;
    }

    @Override
    public ArrayList<Object> execute() {
        if (InstagramData.getLocation2TagFrequency().isEmpty()) {
            Main.getApp().getSystemRecommendationTextArea().setText("YOU HAVE TO SEARCH FOR SOME LOCATION FIRST...");
            return null;
        }
        Main.getApp().getSystemRecommendationTextArea().setText("");
        
        try {
            Main.getApp().getSystemRecommendationTextArea().append("RETRIEVING MEDIA FEEDS FOR CURRENT USER...\n");
            MediaFeed currentUserMediaFeed = Main.getInstagram().getUserFeeds();
            List<MediaFeedData> mediaFeeds = currentUserMediaFeed.getData();
            System.err.println("Current User Media Feeds: " + mediaFeeds.size());
            Main.getApp().getSystemRecommendationTextArea().append("...DONE!\n"+mediaFeeds.size()+" MEDIA FEEDS RETRIEVED\n\n");
            
            HashMap<String, Integer> tagFreq = new HashMap<>();
            Main.getApp().getSystemRecommendationTextArea().append("EXTRACTING TAGS...\n");
            for (MediaFeedData m: mediaFeeds) {
                for (String t: m.getTags()) {
                    if (!InstagramData.getPopularTags().contains(t)) {
                        if (tagFreq.containsKey(t))
                            tagFreq.put(t, tagFreq.get(t)+1);
                        else
                            tagFreq.put(t, 1);
                        System.out.print(t+":"+tagFreq.get(t)+" | ");
                    }
                }
            }
            Main.getApp().getSystemRecommendationTextArea().append("...DONE!\n"+tagFreq.size()+" TAGS EXTRACTED\n\n");
            
            Main.getApp().getSystemRecommendationTextArea().append("SEARCHING FOR TOP 10 LOCATIONS TO RECOMMEND...\n");
            location2rank = new HashMap<>();
            for (String loc: InstagramData.getLocation2TagFrequency().keySet()) {
                HashMap<String, Integer> locDataTag2Freq = InstagramData.getLocation2TagFrequency().get(loc).getTag2Freq();
                int locRank = 0;
                for (String tag: tagFreq.keySet()) {
                    if (locDataTag2Freq.containsKey(tag))
                        locRank += (tagFreq.get(tag)+locDataTag2Freq.get(tag));
                }
                location2rank.put(loc, locRank);
            }
            
            List<String> locations = new ArrayList<String>(location2rank.keySet());
            Collections.sort(locations, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    Integer rank1 = location2rank.get(s1);
                    Integer rank2 = location2rank.get(s2);
                    return rank1.compareTo(rank2);
                }
            });
            
            Main.getApp().getSystemRecommendationTextArea().append("...DONE\n\n");
            int count = 1;
            for (String location: locations) {
                if (count<11) {
                    Main.getApp().getSystemRecommendationTextArea().append(count+": "+location+" [Latitude: "+InstagramData.getLocation2TagFrequency().get(location).getLocLatitude()+" | Longitude: "+InstagramData.getLocation2TagFrequency().get(location).getLocLongitude()+"]\n");
                    count++;
                }
                else
                    break;
            }
            
        } catch (InstagramException ex) {
            Logger.getLogger(StartRecommendationSystemAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
