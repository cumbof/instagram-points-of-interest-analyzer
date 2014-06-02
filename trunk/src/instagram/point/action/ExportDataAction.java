/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.action;

import instagram.point.Main;
import instagram.point.util.InstagramData;
import instagram.point.util.PrintData;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author Fabio Cumbo
 */
public class ExportDataAction extends Action {

    @Override
    public ArrayList<Object> execute(String command) {
        return null;
    }

    @Override
    public ArrayList<Object> execute() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            //System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            //System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            
            if (chooser.getSelectedFile().isDirectory()) {
                if ((InstagramData.getMediaFeeds2User().size()>0) && (InstagramData.getUserCouple2Interests().size()>0)) {
                    long filePrefix = System.currentTimeMillis();
                    PrintData.printInterest2UserMatrix(chooser.getSelectedFile(), filePrefix, InstagramData.getMediaFeeds2User());
                    PrintData.printUser2UserMatrix(chooser.getSelectedFile(), filePrefix, InstagramData.getUserCouple2Interests(), InstagramData.getMediaFeeds2User());
                    Main.getApp().getInterestsOutputTextArea().setText("DATA EXPORTED SUCCESSFULLY!");
                }
                else {
                    Main.getApp().getInterestsOutputTextArea().setText("YOU HAVE TO SEARCH FOR MEDIA FIRST...");
                }
            }
            else {
                Main.getApp().getInterestsOutputTextArea().setText("YOU HAVE TO SELECT A VALID DIRECTORY!");
            }
            
        } 
        //else {
            //System.out.println("No Selection ");
        //}
        return null;
    }
    
}
