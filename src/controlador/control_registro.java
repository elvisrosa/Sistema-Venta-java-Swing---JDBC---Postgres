package controlador;

import dao_daoImpl.daoRegistroUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.usuario_login;
import vista.registro_usuario;

public class control_registro implements ActionListener {

    usuario_login modelo;
    daoRegistroUsuario dao = new daoRegistroUsuario();
    registro_usuario vista = new registro_usuario();

    public control_registro(registro_usuario vista) {
        this.vista = vista;
        this.vista.getBtnRegistrar().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(vista.getBtnRegistrar())){           
            try {
                modelo = new usuario_login();
                modelo.setUsuario(vista.getTxtUsuario().getText());
                modelo.setContraseña(vista.getTxtContraseña().getText());
                dao.registroUsuario(modelo);               
            } catch (Exception e) {
                throw e;
            }
        }

    }
    
}
