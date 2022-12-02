package conexion.logueo;

import conexion.coneccionBD;
import controlador.controlVentanaInicio;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.usuario_login;
import vista.barraProgreso;
import vista.login;
import vista.ventanaInicio;

public class daoLoginAdmin {

    barraProgreso vistaP = new barraProgreso();
    coneccionBD con = new coneccionBD();
    login vista = new login();
    ventanaInicio inicio = new ventanaInicio();
    PreparedStatement ps;
    ResultSet rs;

    public void iniciarSesion(usuario_login user) throws IOException, InterruptedException {
        String consulta = "Select * From persona Where usuario=? and contrasenna=? and tipousuario=?";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(consulta);
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getContraseña());
            ps.setString(3, user.getTipoUsuario());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("tipousuario").equals("Administrador")) {
                    JOptionPane.showConfirmDialog(vista, "Bienvenido " + user.getUsuario(), "mensaje", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    inicio.getLblNnombreDeUsuario().setText(user.getUsuario());
                    inicio.getMenuProductos().setVisible(false);

                    abrirVentanaInicioAdministrador();
                    traerImagen();

                } else if (rs.getString("tipousuario").equals("Cliente")) {
                    inicio.getLblNnombreDeUsuario().setText(user.getUsuario());
                    inicio.getMenuIMantenimiento().setVisible(false);
                    inicio.getMenuConfiguracion().setVisible(false);
                    inicio.getMenuVenta().setVisible(false);
                    traerImagen();
                    abrirVentanaInicioUsuarario();
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Datos erroneos", "mensaje", JOptionPane.INFORMATION_MESSAGE);

            }

        } catch (SQLException e) {
            System.out.println("error " + e);

        }

    }

    public void CambiarContraseña(usuario_login u) {
        String sql = "Update persona Set CONTRASENNA=? where USUARIO=?";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, u.getContraseña());
            ps.setString(2, u.getUsuario());
            rs = ps.executeQuery();

            JOptionPane.showMessageDialog(vista, "Contraseña actualizada", "Sistema", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al actaulizar contraseña", "Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void traerImagen() throws IOException {
        String sql = "Select foto from persona where usuario=?";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, inicio.getLblNnombreDeUsuario().getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getBlob("foto") != null) {
                    InputStream imgString = rs.getBinaryStream("foto");
                    Image i = ImageIO.read(imgString);
                    i = i.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                    inicio.getLblImagen().setIcon(new ImageIcon(i));
                }
            }
            //Image foto = getToolkit().getImage(txtRutaImg.getText());
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());

        }
    }

    public void abrirVentanaInicioAdministrador() {
        usuario_login usuario = new usuario_login();
        controlVentanaInicio control = new controlVentanaInicio(inicio, usuario);
        inicio.setVisible(true);

    }

    void abrirVentanaInicioUsuarario() {
        usuario_login usuario = new usuario_login();
        controlVentanaInicio control = new controlVentanaInicio(inicio, usuario);
        inicio.setVisible(true);

    }

}
//https://www.youtube.com/watch?v=TG2Q-Bpt80g
