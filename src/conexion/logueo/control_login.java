
package conexion.logueo;

import controlador.control_registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.usuario_login;
import vista.login;
import vista.registro_usuario;

public class control_login implements ActionListener, KeyListener{

    login vista = new login();
    daoLoginAdmin dao = new daoLoginAdmin();
    usuario_login modelo = new usuario_login();

    public control_login(login vista) {
        this.vista = vista;
        this.vista.getBtnIniciarSesion().addActionListener(this);
        this.vista.getBtnRegistrateAqui().addActionListener(this);
        this.vista.getComboTipoUsuario().addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(vista.getBtnIniciarSesion())) {
            modelo.setUsuario(vista.getTxtUsuario().getText());
            modelo.setContraseña(vista.getTxtContraseña().getText());
            modelo.setTipoUsuario(vista.getComboTipoUsuario().getSelectedItem().toString());
            try {
                dao.iniciarSesion(modelo);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(control_login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (ae.getSource().equals(vista.getBtnRegistrateAqui())) {
            iniciarVistaRegistro();
        }
    }

    public void iniciarVistaRegistro() {
        registro_usuario viewRegistro = new registro_usuario();
        control_registro control = new control_registro(viewRegistro);
        viewRegistro.setVisible(true);
        viewRegistro.setLocationRelativeTo(null);

    }

    public void limpiar() {
        vista.getTxtUsuario().setText("");
        vista.getTxtContraseña().setText("");

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getSource().equals(vista.getComboTipoUsuario())){
            modelo.setUsuario(vista.getTxtUsuario().getText());
            modelo.setContraseña(vista.getTxtContraseña().getText());
            modelo.setTipoUsuario(vista.getComboTipoUsuario().getSelectedItem().toString());
            try {
                dao.iniciarSesion(modelo);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(control_login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
           iniciarVistaRegistro(); 
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
