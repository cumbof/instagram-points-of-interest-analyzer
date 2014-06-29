/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point;

import instagram.point.frame.POINT;
import instagram.point.util.Facade;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jinstagram.Instagram;

/**
 *
 * @author Fabio Cumbo
 */
public class Main {
    
    private static Application app;
    private static final String appName = "POINT - Instagram Points Of Interest Analyzer";
    private static final String appVersion = "v1.0";
    private static final String appAuthor = "Fabio Cumbo";
    
    private static Instagram instagram = null;
    
    public Main() { }
    
    public static void main(String args[]) {
        init();
    }
    
    private static void init() {
        final String lafWindows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	final String lafGTK     = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
	final String lafMetal   = "com.sun.java.swing.plaf.metal.MetalLookAndFeel";
	final String lafMotif   = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                
                try {
                    String operatingSystem;
                    operatingSystem = System.getProperty("os.name");

                    if (operatingSystem.equalsIgnoreCase("linux")) {
                        UIManager.setLookAndFeel(lafGTK);
                    } else if (operatingSystem.startsWith("Windows")) {
			UIManager.setLookAndFeel(lafWindows);
                    } else if (operatingSystem.startsWith("solaris")) {
			UIManager.setLookAndFeel(lafMotif);
                    } else {
			UIManager.setLookAndFeel(lafMetal);
                    }
                } 
                catch (ClassNotFoundException e) { e.printStackTrace(); }
                catch (IllegalAccessException e) { e.printStackTrace(); }
                catch (InstantiationException e) { e.printStackTrace(); }
                catch (UnsupportedLookAndFeelException e) { e.printStackTrace(); }
                
                //Facade facade = new Facade();
                //facade.connect("localhost:5432/point", "postgres", "postgres");
                app = new POINT();
                app.setVisibleOverride(true);
                app.setDefaultCloseOperationOverride(JFrame.EXIT_ON_CLOSE);
            }
        });
        
    };
    
    public static Application getApp() {
        return app;
    }

    public static String getAppAuthor() {
        return appAuthor;
    }

    public static String getAppName() {
        return appName;
    }

    public static String getAppVersion() {
        return appVersion;
    }
    
    public static Instagram getInstagram() {
        return instagram;
    }
    
    public static void setInstagram(Instagram inst) {
        instagram = inst;
    }
    
    public boolean connectionEstablished() {
        return instagram!=null;
    }
    
}
