package controlador;

import conexion.logueo.daoLoginAdmin;
import dao_daoImpl.daoProducto;
import dao_daoImpl.daoUsuarios;
import dao_daoImpl.daoVEntas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.usuario_login;
import modelo.venta;
import vista.panelProducto;
import vista.panelRegistroCliente;
import vista.panelUsuarios;
import vista.panelVentasEfectuadas;
import vista.panelventas;
import vista.peoductoDeVenta;
import vista.ventanaInicio;

public class controlVentanaInicio implements ActionListener {

    ventanaInicio vista = new ventanaInicio();
    usuario_login usuario;
    panelRegistroCliente pCliente;
    panelProducto pProducto;
    panelventas pVentas;
    daoLoginAdmin daoL;
    panelVentasEfectuadas pVEfectuadas;
    panelUsuarios pUsuarios;
    venta v;
    daoVEntas daoV;
    DefaultTableModel tabla;
    peoductoDeVenta pVentasProducto;

    int p1 = 0;
    int p2 = 0;
    int p3 = 0;
    int p4 = 0;
    int p5 = 0;
    int p6 = 0;

    controlProducto control;
    daoProducto dao = new daoProducto();
    usuario_login mview = new usuario_login();

    public controlVentanaInicio(ventanaInicio vista, usuario_login usuario) {
        this.vista = vista;
        this.usuario = usuario;
        this.vista.getMenuItemCliente().addActionListener(this);
        this.vista.getMenuItemProducto().addActionListener(this);
        this.vista.getMenuItemAddProducto().addActionListener(this);
        this.vista.getMenuItemGenerarVentas().addActionListener(this);
        this.vista.getCambiarContrseña().addActionListener(this);
        this.vista.getBtnCerrarSesion().addActionListener(this);
        this.vista.getBtnGuardarImg().addActionListener(this);
        this.vista.getBtnCambiarFoto().addActionListener(this);
        this.vista.getMenuItemVentasEfectuadas().addActionListener(this);
        this.vista.getMenuItemUsuarios().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(vista.getMenuItemCliente())) {
            AbrirVentanaRegistreoCliente();

        }
        if (ae.getSource().equals(vista.getMenuItemProducto())) {
            abrirPanelProducto();
        }

        if (ae.getSource().equals(vista.getMenuItemAddProducto())) {
            abrirVentanaProductoDeVenta();

        }

        if (ae.getSource().equals(vista.getMenuItemGenerarVentas())) {
            abrirPanelVentas();
        }

        if (ae.getSource().equals(vista.getCambiarContrseña())) {
            try {
                cambioPass();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Se cancelo");
            }
        }
        if (ae.getSource().equals(vista.getBtnCerrarSesion())) {
            //:_________________________________    
        }
        if (ae.getSource().equals(vista.getMenuItemVentasEfectuadas())) {
            abrirVentanaVentasEfectuiadas();
        }
        if (ae.getSource().equals(vista.getMenuItemUsuarios())) {
            limpiarTabla();
            abrirVentanaUSuario();
        }

    }

    public void cambioPass() {
        usuario = new usuario_login();
        daoL = new daoLoginAdmin();
        String us = JOptionPane.showInputDialog("Ingresa tu usuario");
        if (us.equals(vista.getLblNnombreDeUsuario().getText())) {
            String contraseñaNueva = JOptionPane.showInputDialog("Ingresa la contraseña nueva");
            usuario.setUsuario(us);
            usuario.setContraseña(contraseñaNueva);
            daoL.CambiarContraseña(usuario);
        } else {
            JOptionPane.showMessageDialog(vista, "Usuario invalido, no puede continuar", "Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirPanelProducto() {
        if (p2 == 0) {
            pProducto = new panelProducto();
            control = new controlProducto(pProducto);
            vista.getTablePanelPricipal().addTab("Ventana de productos", pProducto);
            vista.getTablePanelPricipal().setSelectedComponent(pProducto);
            pProducto.show();
            control.listar();

        } else {
            //vista.getTablePanelPricipal().setSelectedComponent(pProducto);
            JOptionPane.showMessageDialog(null, "La ventana ya esta abierta");
        }
        p2 = 1;
    }

    public void abrirPanelVentas() {
        if (p3 == 0) {
            pVentas = new panelventas();
            controlVentas contr = new controlVentas(pVentas);
            vista.getTablePanelPricipal().addTab("Ventana de ventas", pVentas);
            vista.getTablePanelPricipal().setSelectedComponent(pVentas);
            contr.generarSerieVenta();
            pVentas.show();

        } else {
            //vista.getTablePanelPricipal().setSelectedComponent(pVentas);
            JOptionPane.showMessageDialog(null, "La ventana ya esta abierta");
        }
        p3 = 1;
    }

    public void AbrirVentanaRegistreoCliente() {

        if (p1 == 0) {
            pCliente = new panelRegistroCliente();
            controlCliente contro = new controlCliente(pCliente);
            vista.getTablePanelPricipal().addTab("Registro de clientes", pCliente);
            vista.getTablePanelPricipal().setSelectedComponent(pCliente);
            contro.listar();
            pCliente.show();

        } else {
            //vista.getTablePanelPricipal().setSelectedComponent(pCliente);
            JOptionPane.showMessageDialog(null, "La ventana ya esta abierta");
        }
        p1 = 1;
    }

    public void abrirVentanaVentasEfectuiadas() {
        pVEfectuadas = new panelVentasEfectuadas();
        controlVentasEfectuadas cve = new controlVentasEfectuadas(pVEfectuadas);
        if (p4 == 0) {
            vista.getTablePanelPricipal().addTab("Ventas realizadas", pVEfectuadas);
            vista.getTablePanelPricipal().setSelectedComponent(pVEfectuadas);
            pVEfectuadas.show();
            cve.listarVentas();
        } else {
            //vista.getTablePanelPricipal().setSelectedComponent(pVEfectuadas);
            JOptionPane.showMessageDialog(null, "La ventana ya esta abierta");
        }
        p4 = 1;
    }

    public void abrirVentanaUSuario() {
        pUsuarios = new panelUsuarios();
        if (p5 == 0) {
            vista.getTablePanelPricipal().addTab("Usuarios del sistema", pUsuarios);
            vista.getTablePanelPricipal().setSelectedComponent(pUsuarios);
            pUsuarios.show();
            mostrarUsuarios();
        } else {
            JOptionPane.showMessageDialog(null, "La ventana ya esta abierta");
            //vista.getTablePanelPricipal().setSelectedComponent(pVEfectuadas);
        }
        p5 = 1;
    }

    public void abrirVentanaProductoDeVenta() {
        pVentasProducto = new peoductoDeVenta();
        if (p6 == 0) {
            vista.getTablePanelPricipal().addTab("Productos de venta", pVentasProducto);
            vista.getTablePanelPricipal().setSelectedComponent(pVentasProducto);
            pVentasProducto.show();
        } else {
            //vista.getTablePanelPricipal().setSelectedComponent(pVentasProducto);
            JOptionPane.showMessageDialog(null, "La ventana ya esta abierta");
        }
        p6 = 1;
    }

    public void mostrarUsuarios() {
        daoUsuarios daoU = new daoUsuarios();
        ArrayList<usuario_login> lista = daoU.listarUsuarios();
        tabla = (DefaultTableModel) pUsuarios.getTablaUsuarios().getModel();
        try {
            Object[] obj = new Object[3];
            for (int i = 0; i < lista.size(); i++) {
                obj[0] = lista.get(i).getCodigo();
                obj[1] = lista.get(i).getUsuario();
                //obj[2] = lista.get(i).getContraseña();
                obj[2] = lista.get(i).getTipoUsuario();

                tabla.addRow(obj);
            }
            pUsuarios.getTablaUsuarios().setModel(tabla);
        } catch (Exception e) {
            System.out.println("error " + e);
        }

    }

    public void limpiarTabla() {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tabla.removeRow(i);
            i = i - 1;
        }
    }

}
