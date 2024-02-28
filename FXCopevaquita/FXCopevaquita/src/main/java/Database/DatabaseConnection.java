/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author alber
 * @author kim03
 */
public class DatabaseConnection {
    
    private static Connection con = null;
    // conecion, host, puerto, basedatos
    private static String url = "jdbc:mysql://127.0.0.1:3306/planillaDB";
    
    public static Connection getConnection(){
        if(con != null) return con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, "root", "");
        }
        catch(Exception ex){
            System.out.println("Conexión fallida");
        }
        return con;
    }

    
}
