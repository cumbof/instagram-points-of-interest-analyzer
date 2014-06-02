/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.action;

import instagram.point.Main;
import instagram.point.util.InstagramData;
import java.util.ArrayList;
import org.jinstagram.Instagram;
import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;

/**
 *
 * @author Fabio Cumbo
 */
public class ConnectInstagramAction extends Action {
    
    private static final Token EMPTY_TOKEN = null;
    private final String apiKey = "0fbcf8f88e6d45b89ae445fd961a752f";
    private final String apiSecret = "1351472489994299a4c7e3c1ca83f04d";
    private final String callback = "http://localhost/instagram-api/instagammy.php";
    
    @Override
    public ArrayList<Object> execute(String command) {
        return null;
    }

    @Override
    public ArrayList<Object> execute() {
        
        String accessToken = Main.getApp().getAccessTokenTextField().getText();
        System.err.println("TOKEN: " + accessToken);
        if (accessToken!=null || !accessToken.equals("")) {
            
            InstagramService service = new InstagramAuthService()
                                .apiKey(apiKey)
                                .apiSecret(apiSecret)
                                .callback(callback) 
                                .build();
            
            Verifier verifier = new Verifier(accessToken);
            Token aToken = service.getAccessToken(EMPTY_TOKEN, verifier);
            
            Instagram instagram = new Instagram(aToken);
            if (instagram.getAccessToken()!=null) {
                Main.setInstagram(instagram);
                Main.getApp().getConnectionStatusLabel().setText("CONNECTION ESTABLISHED!");
                
                InstagramData.setAccessToken(accessToken);
            }
            else
                Main.getApp().getConnectionStatusLabel().setText("CONNECTION ERROR!");
        }
        else
            Main.getApp().getConnectionStatusLabel().setText("EMPTY ACCESS TOKEN!");
        
        return null;
    }
    
}
