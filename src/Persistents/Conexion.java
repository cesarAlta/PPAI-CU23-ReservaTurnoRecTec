package Persistents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection conexion;
    String url = "jdbc:mysql://localhost:3306/cu23-ppai-dsi";
    String user = "root";
    String pass = "donesystems";

    public Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url,user,pass);

        }
        catch (SQLException e){
            System.out.println("ERROR" + e);
        }
        catch(Exception e){
            System.out.println("ERROR"+e);
        }
        return conexion;
    }

    public void deconectar(){
        try{
            conexion.close();
        }
        catch(Exception e){

        }
    }
}
