
/**
 *
 * @author RafgiMepo
 */
package myconnections;

import java.sql.*;
import java.util.*;

public class DBconnect {

     private static Connection dbConnect = null;

  private DBconnect(){}   
     
     
     
     public static Connection getConnection() {
        if (dbConnect!=null)return dbConnect;
        PropertyResourceBundle properties = 
                (PropertyResourceBundle) PropertyResourceBundle.getBundle("Resources.application");
            
        String serverName = properties.getString("cours.DB.server");
        String dbName = properties.getString("cours.DB.database");
        String username = properties.getString("cours.DB.login");
        String password = properties.getString("cours.DB.password");
        String dbPort = properties.getString("cours.DB.port");
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@//" + serverName + ":" + dbPort + "/" + dbName;
            dbConnect = DriverManager.getConnection(url, username, password);
            return dbConnect;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("erreur de connexion " + e);
            return null;
        }
    }
    
    public static void closeConnection(){
       
        try{
            
            dbConnect.close();
           
        }
        catch(SQLException e){
            System.out.println("erreur de fermeture de connexion "+e);
                    
        }
         dbConnect=null;
     }

}
