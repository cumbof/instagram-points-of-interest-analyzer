/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jinstagram.entity.common.User;
import org.jinstagram.entity.users.feed.MediaFeedData;

/**
 *
 * @author Fabio Cumbo
 */
public class PrintData {

    public static void printInterest2UserMatrix(File outDir, long filePrefix, HashMap<MediaFeedData, User> mediaFeeds2User) {
        List<String> interests = new ArrayList<>();
        List<String> users = new ArrayList<>();
        HashMap<String, List<String>> userid2interests = new HashMap<>();
        for (MediaFeedData media: mediaFeeds2User.keySet()) {
            for (String interest: media.getTags()) {
                if (!interests.contains(interest))
                    interests.add(interest);
            }
            if (!users.contains(String.valueOf(media.getUser().getId())))
                users.add(String.valueOf(media.getUser().getId()));
            if (userid2interests.containsKey(String.valueOf(media.getUser().getId()))) {
                List<String> userInterests = userid2interests.get(String.valueOf(media.getUser().getId()));
                userInterests.addAll(media.getTags());
                userid2interests.put(String.valueOf(media.getUser().getId()), userInterests);
            }
            else {
                userid2interests.put(String.valueOf(media.getUser().getId()), media.getTags());
            }
        }
        try {
            File outFile = new File(outDir.getAbsoluteFile()+"/"+filePrefix+"_interest2user.csv");
            outFile.createNewFile();
            
            FileOutputStream fos = new FileOutputStream(outFile.getAbsoluteFile()); 
            PrintStream out = new PrintStream(fos);
            
            out.print("#;");
            for (String u: users)
                out.print(u+";");
            out.println();
            for (String i: interests) {
                out.print(i+";");
                for (String u: users) {
                    if (userid2interests.get(u).contains(i))
                        out.print("1;");
                    else
                        out.print("0;");
                }
                out.println();
            }
            
            out.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printUser2UserMatrix(File outDir, long filePrefix, HashMap<String, List<String>> userCouple2Interests, HashMap<MediaFeedData, User> mediaFeeds2user) {
        ArrayList<String> users = new ArrayList<>();
        for (MediaFeedData media: mediaFeeds2user.keySet()) {
            if (!users.contains(String.valueOf(media.getUser().getId())))
                users.add(String.valueOf(media.getUser().getId()));
        }
        try {
            File outFile = new File(outDir.getAbsoluteFile()+"/"+filePrefix+"_usersAdjMatrix.csv");
            outFile.createNewFile();
            
            FileOutputStream fos = new FileOutputStream(outFile.getAbsoluteFile()); 
            PrintStream out = new PrintStream(fos);
        
            out.print("#;");
            for (String u: users)
                out.print(u+";");
            out.println();
            
            for (String u: users) {
                out.print(u+";");
                for (String u_: users) {
                    String userCouple_0 = u+"|"+u_;
                    String userCouple_1 = u_+"|"+u;
                    if (userCouple2Interests.containsKey(userCouple_0))
                        out.print(userCouple2Interests.get(userCouple_0).size()+";");
                    else if (userCouple2Interests.containsKey(userCouple_1))
                        out.print(userCouple2Interests.get(userCouple_1).size()+";");
                    else
                        out.print("0;");
                }
                out.println();
            }
            
            out.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printUsersInteractions(File outDir, long filePrefix, HashMap<String, List<String>> userCouple2Interests) {
        try {
            File outFile = new File(outDir.getAbsoluteFile()+"/"+filePrefix+"_usersInteractions.dat");
            outFile.createNewFile();
            
            FileOutputStream fos = new FileOutputStream(outFile.getAbsoluteFile()); 
            PrintStream out = new PrintStream(fos);
        
            out.println("SOURCE\tTARGET\tWEIGHT");
            for (String couple: userCouple2Interests.keySet()) {
                String[] couple_split = couple.split("\\|");
                if (!couple_split[0].equals(couple_split[1]) && userCouple2Interests.get(couple).size()>0)
                    out.println(couple_split[0]+"\t"+couple_split[1]+"\t"+userCouple2Interests.get(couple).size());
            }
            
            out.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
