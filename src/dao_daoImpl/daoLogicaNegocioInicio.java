
package dao_daoImpl;

import conexion.coneccionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.usuario_login;

public class daoLogicaNegocioInicio {

    coneccionBD con = new coneccionBD();

    public ArrayList<usuario_login> listarUsuarios() {
        ArrayList<usuario_login> list = new ArrayList();
        con.obtenerConeccion();
        PreparedStatement ps;
        ResultSet rs;
        String consulta = "select * from persona";
        try {
            ps = con.getCon().prepareCall(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario_login usuario = new usuario_login();
                usuario.setCodigo(rs.getInt("id"));
                usuario.setUsuario(rs.getString("usuario"));
                //usuario.setContraseña(rs.getString("contrasenna"));
                usuario.setTipoUsuario(rs.getString("tipousuario"));
                list.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("error "+e);

        }
        return list;

    }

}
