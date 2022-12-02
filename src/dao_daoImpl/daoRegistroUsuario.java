package dao_daoImpl;

import conexion.coneccionBD;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.usuario_login;
import vista.registro_usuario;

public class daoRegistroUsuario {

    coneccionBD con = new coneccionBD();
    registro_usuario vista = new registro_usuario();

    public void registroUsuario(usuario_login usuario) {
        PreparedStatement ps;
        ResultSet rs;
        String consulta = "Insert Into persona (usuario, contrasenna, tipousuario) values (?,?,?)";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(consulta);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContraseña());
            ps.setString(3, usuario.getTipoUsuario());
            rs = ps.executeQuery();
            try {
                JOptionPane.showMessageDialog(vista, "Usuario creado", "", JOptionPane.INFORMATION_MESSAGE);
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(vista, "Error al crear usuario", "", JOptionPane.QUESTION_MESSAGE);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
