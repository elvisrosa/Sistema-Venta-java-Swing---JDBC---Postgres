
package dao_daoImpl;

import conexion.coneccionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.usuario_login;

public class daoUsuarios {
    
    coneccionBD con= new coneccionBD();
    ResultSet rs;
    PreparedStatement ps;
    
    
    public ArrayList<usuario_login> listarUsuarios(){
        ArrayList<usuario_login> list = new ArrayList<>();       
        String sql ="Select id, usuario, tipousuario from persona";
        try {
            usuario_login u = new usuario_login();
            con.obtenerConeccion();
            ps=con.getCon().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                u.setCodigo(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setTipoUsuario(rs.getString("tipousuario"));
                //Blob foto = rs.getBlob("foto");                               
                list.add(u);
            }
            
        } catch (SQLException e) {
            System.out.println("error usuario"+e.getMessage());
        }              
        return list;      
    }
    
}
