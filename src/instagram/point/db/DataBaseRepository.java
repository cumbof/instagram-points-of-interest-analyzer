package instagram.point.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class DataBaseRepository {
	
	public static boolean executeQuery(Connection connection, String query) {
            boolean result = false;
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                if (statement.executeUpdate()==1) {
                    result = true;
                }
            }
            catch (SQLException ecc){
                ecc.printStackTrace();
                return result;
            }
            /*finally {
            try{ 
                    connection.close(); 
            }
            catch (SQLException e) {
                    return false;
            }
            }*/
            return result;
	}
	
	public static boolean createSimpleTable(Connection connection, String table) {
            boolean result = false;
            String query = "CREATE TABLE " + table + " ( id VARCHAR(64), PRIMARY KEY(id) );";
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                if (statement.executeUpdate()==0) {
                    result = true;
                }
            }
            catch (SQLException ecc){
                ecc.printStackTrace();
                return result;
            }
            /*finally {
            try{ 
                    connection.close(); 
            }
            catch (SQLException e) {
                    return false;
            }
            }*/
            return result;
	}
	
	public static boolean dropTable(Connection connection, String table) {
            boolean result = false;
            String query = "DROP TABLE " + table;
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                if (statement.executeUpdate()==1) {
                    result = true;
                }
            }
            catch (SQLException ecc){
                ecc.printStackTrace();
                return result;
            }
            /*finally {
            try{ 
                    connection.close(); 
            }
            catch (SQLException e) {
                    return false;
            }
            }*/
            return result;
	}
	
	public static boolean insertValues(Connection connection, HashMap<String, String> data, String table) {
            boolean result = false;
            try {
                String insert_query = "INSERT INTO " + table + " (col) VALUES (val);";
                for (String col: data.keySet()) {
                        insert_query = insert_query.replaceAll("col", col + ", col");
                        insert_query = insert_query.replaceAll("val", "'"+data.get(col)+"', val");
                }
                insert_query = insert_query.replaceAll(", col", "");
                insert_query = insert_query.replaceAll(", val", "");

                PreparedStatement insert_statement;
                insert_statement = connection.prepareStatement(insert_query);
                if (insert_statement.executeUpdate()==1)
                        result = true;
            }
            catch (SQLException ecc) { 
                    return result; 
            }

            /*finally {
                try{ connection.close(); }
                catch (SQLException e) {
                    return false;
                }
            }*/

            return result;
	}
	
}
