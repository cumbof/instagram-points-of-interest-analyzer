package instagram.point.util;

import instagram.point.db.C3P0DataSource;
import instagram.point.db.DataBaseRepository;
import java.util.ArrayList;
import java.util.HashMap;

public class Facade {
	
	private static C3P0DataSource datasource;

	public void connect(String addr, String usr, String pwd) {
            datasource = C3P0DataSource.getInstance(addr, usr, pwd);
            // getConnection() to test connection - to refactor
            datasource.getConnection();
	}
	
	public boolean insert(HashMap<String, LocationData> location2tagFrequency) {
            
            System.out.println(location2tagFrequency.size());
            
            for (String loc: location2tagFrequency.keySet()) {
                ArrayList<String> queries = new ArrayList<>();
                for (String tag: location2tagFrequency.get(loc).getTag2Freq().keySet()) {
                    String query = "INSERT INTO location2tagfreq (location, tag, freq, locid, loclatitude, loclongitude) VALUES('"+loc+"', '"+tag+"', "+location2tagFrequency.get(loc).getTag2Freq().get(tag)+", '"+location2tagFrequency.get(loc).getLocID()+"', '"+location2tagFrequency.get(loc).getLocLatitude()+"', '"+location2tagFrequency.get(loc).getLocLongitude()+"')";
                    System.out.println(query);
                    queries.add(query);
                }
                for (String q: queries)
                    DataBaseRepository.executeQuery(datasource.getConnection(), q);
            }
            return true;
	}
	
	public static C3P0DataSource getDatasource() {
            return datasource;
	}
        
        
        /*private String hashMD5(String message) {
            String digest = null;
            try { 
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] hash = md.digest(message.getBytes("UTF-8")); //converting byte array to Hexadecimal String 
                StringBuilder sb = new StringBuilder(2*hash.length); 
                for(byte b : hash){ 
                    sb.append(String.format("%02x", b&0xff)); 
                } digest = sb.toString(); 
            } catch (UnsupportedEncodingException ex) { 
                ex.printStackTrace(); 
            } catch (NoSuchAlgorithmException ex) { 
                ex.printStackTrace();
            }
            return digest;
        }*/

}
