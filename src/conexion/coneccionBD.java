package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class coneccionBD {

    Connection con;

    public coneccionBD() {
    }
    public coneccionBD(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void obtenerConeccion() {
        String usuario = "prueba";
        String contrseña = "12345";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", usuario, contrseña);   
        } catch (Exception e) {
        }

    }
    
    public void cerrarConeccion() throws SQLException{
        if(con!=null){
            con.close();
        }
        
     }
   
    }

