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
import org.jinstagram.entity.common.User;
import org.jinstagram.entity.users.feed.MediaFeedData;

/**
 *
 * @author Fabio Cumbo
 */
public class FindCommonInterestsAction extends Action {

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
            Main.getApp().getInterestsOutputTextArea().setText("FINDING COMMON INTERESTS:\nTHIS OPERATION MAY TAKE A FEW MINUTES...\n");
            
            HashMap<User, List<String>> user2tags = new HashMap<User, List<String>>();
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
            Main.getApp().getInterestsOutputTextArea().append(userCouple2interests.size()+" COUPLE OF USERS WITH COMMON INTERESTS FOUNDED!");
            
        }
        
        return null;
    }
    
    public java.util.List intersect(List<String> a, List<String> b){
        java.util.List lstIntersectAB=new ArrayList(a);
        lstIntersectAB.retainAll(b);
        return lstIntersectAB;
    }
    
}
