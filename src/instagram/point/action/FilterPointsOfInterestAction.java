/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.action;

import instagram.point.Main;
import instagram.point.util.Facade;
import instagram.point.util.InstagramData;
import instagram.point.util.LocationData;
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
public class FilterPointsOfInterestAction extends Action {

    @Override
    public ArrayList<Object> execute(String command) {
        return null;
    }

    @Override
    public ArrayList<Object> execute() {
        
        Main.getApp().getInterestsOutputTextArea().setText("");
        if (InstagramData.getLocation2MediaFeeds().isEmpty() && InstagramData.getMediaFeeds2User().isEmpty())
            Main.getApp().getInterestsOutputTextArea().setText("YOU HAVE TO SEARCH FOR MEDIA FIRST...");
        else {
            Main.getApp().getInterestsOutputTextArea().setText("FILTERING LOCATIONS:\nTHIS OPERATION MAY TAKE A FEW MINUTES...\n");
            
            /*HashMap<User, List<String>> user2tags = new HashMap<User, List<String>>();
            for (MediaFeedData m: InstagramData.getMediaFeeds2User().keySet()) {
                if (user2tags.containsKey(InstagramData.getMediaFeeds2User().get(m))) {
                    List<String> tags = user2tags.get(InstagramData.getMediaFeeds2User().get(m));
                    tags.addAll(m.getTags());
                    user2tags.put(InstagramData.getMediaFeeds2User().get(m), tags);
                }
                else {
                    user2tags.put(m.getUser(), m.getTags());
                }
            }
            
            HashMap<String, List<String>> userCouple2interests = new HashMap<String, List<String>>();
            for (User u1: user2tags.keySet()) {
                for (User u2: user2tags.keySet()) {
                    if (!userCouple2interests.containsKey(String.valueOf(u1.getId())+"|"+String.valueOf(u2.getId())) && !userCouple2interests.containsKey(String.valueOf(u2.getId())+"|"+String.valueOf(u1.getId())))
                        userCouple2interests.put(String.valueOf(u1.getId())+"|"+String.valueOf(u2.getId()), intersect(user2tags.get(u1), user2tags.get(u2)));
                }
            }
            
            if (userCouple2interests.size() > 0)
                InstagramData.setUserCouple2Interests(userCouple2interests);
            int count = 0;
            for (String couple: userCouple2interests.keySet()) {
                String[] couple_split = couple.split("\\|");
                if (!couple_split[0].equals(couple_split[1]) && userCouple2interests.get(couple).size()>0)
                    count++;
            }
            Main.getApp().getInterestsOutputTextArea().append(count+" COUPLE OF USERS WITH COMMON INTERESTS FOUNDED!");
            */
            
            //////////////////////////////////////////////////////////////////////////
            Main.getApp().getInterestsOutputTextArea().append("FILTERING LOCATIONS...\n");
            HashMap<String, LocationData> location2tagFrequency = new HashMap<>();
            for (Location l: InstagramData.getLocation2MediaFeeds().keySet()) {
                if (l.getName()!=null) {
                    System.out.println("LOCATION: "+l.getName());
                    LocationData locData = new LocationData();
                    if (location2tagFrequency.containsKey(l.getName()))
                        locData = location2tagFrequency.get(l.getName());
                    for (MediaFeedData m: InstagramData.getLocation2MediaFeeds().get(l)) {
                        for (String t: m.getTags()) {
                            if (!InstagramData.getPopularTags().contains(t)) {
                                if (locData.getTag2Freq().containsKey(t))
                                    locData.addTag(t, locData.getTag2Freq().get(t)+1);
                                else
                                    locData.addTag(t, 1);
                                System.out.println(t+":"+locData.getTag2Freq().get(t));
                            }
                        }
                    }
                    locData.setLocID(l.getId());
                    locData.setLocLatitude(l.getLatitude());
                    locData.setLocLongitude(l.getLongitude());
                    location2tagFrequency.put(l.getName(), locData);
                }
            }
            
            InstagramData.setLocation2tagFrequency(location2tagFrequency);
            Main.getApp().getInterestsOutputTextArea().append("...DONE!\n\n");
            Main.getApp().getInterestsOutputTextArea().append(location2tagFrequency.size()+" TOTAL LOCATIONS");
            //Main.getApp().getInterestsOutputTextArea().append("...DONE!\nRETRIEVED "+location2tagFrequency.size()+" LOCATIONS\n");
            //Main.getApp().getInterestsOutputTextArea().append("INSERTING LOCATIONS AND TAGS DATA IN DB...");
            System.out.println("location2tagfreq: "+location2tagFrequency.size());
            //Facade facade = new Facade();
            //facade.insert(location2tagFrequency);
            //Main.getApp().getInterestsOutputTextArea().append("...DONE!");
            //////////////////////////////////////////////////////////////////////////
            
        }
        
        return null;
    }
    
    public java.util.List intersect(List<String> a, List<String> b){
        java.util.List lstIntersectAB=new ArrayList(a);
        lstIntersectAB.retainAll(b);
        return lstIntersectAB;
    }
    
}
