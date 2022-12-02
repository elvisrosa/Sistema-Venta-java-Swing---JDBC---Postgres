package controlador;

import dao_daoImpl.daoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.clientes;
import vista.panelRegistroCliente;

public class controlCliente implements MouseListener, ActionListener {

    panelRegistroCliente vista = new panelRegistroCliente();
    daoCliente dao;
    clientes modelo;
    int id;
    DefaultTableModel tabla;

    public controlCliente(panelRegistroCliente vista) {
        this.vista = vista;
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnActualizar().addActionListener(this);
        vista.getTablaTodosClientes().addMouseListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getBtnNuevo().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        dao = new daoCliente();
        if (ae.getSource().equals(vista.getBtnAgregar())) {
            if (camposNull() == true) {
                agregar();
            }

        } else if (ae.getSource().equals(vista.getBtnActualizar())) {
            if (camposNull() == true) {
                actaulizar();
            }

        }
        if (ae.getSource().equals(vista.getBtnEliminar())) {
            if(vista.getTxtIdCliente().getText().equals("")){
            JOptionPane.showMessageDialog(vista, "Selecciona el cliente a eliminar!", "msj Sistema", JOptionPane.ERROR_MESSAGE);    
            }else{
            eliminar();
            }
        }
        if (ae.getSource().equals(vista.getBtnNuevo())) {
            limpiar();
        }

    }

    void agregar() {
        String cedula = (vista.getTxtCEdula().getText());
        String nombres = (vista.getTxtNomres().getText());
        String telefono = (vista.getTxtDireccion().getText());
        String estado = (vista.getTxtEstado().getText());
        Object[] ob = new Object[5];
        ob[0] = cedula;
        ob[1] = nombres;
        ob[2] = telefono;
        ob[3] = estado;
        dao.agregar(ob);
        limpiarTabla();
        listar();
        limpiar();
    }

    void actaulizar() {
        try {
            String cedula = (vista.getTxtCEdula().getText());
            String nombres = (vista.getTxtNomres().getText());
            String telefono = (vista.getTxtDireccion().getText());
            String estado = (vista.getTxtEstado().getText());
            Object[] ob = new Object[5];
            ob[0] = cedula;
            ob[1] = nombres;
            ob[2] = telefono;
            ob[3] = estado;
            ob[4] = id;

            dao.actualizar(ob);
            limpiarTabla();
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    void eliminar() {
        dao = new daoCliente();
        String idc = vista.getTxtIdCliente().getText();
        int i = Integer.parseInt(idc);
        dao.eliminar(i);
        limpiarTabla();
        limpiar();
        listar();
    }

    public void listar() {
        dao = new daoCliente();
        tabla = (DefaultTableModel) vista.getTablaTodosClientes().getModel();
        ArrayList<clientes> lis = dao.listar();
        Object[] obj = new Object[5];
        try {
            for (int i = 0; i < lis.size(); i++) {
                obj[0] = lis.get(i).getId();
                obj[1] = lis.get(i).getCedula();
                obj[2] = lis.get(i).getNombres();
                obj[3] = lis.get(i).getTelefono();
                obj[4] = lis.get(i).getEstado();
                tabla.addRow(obj);
            }

            vista.getTablaTodosClientes().setModel(tabla);

        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource().equals(vista.getTablaTodosClientes())) {
            JTable tabla = vista.getTablaTodosClientes();
            int fila = tabla.getSelectedRow();
            if (me.getClickCount() == 1) {
                id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                String cedula = tabla.getValueAt(fila, 1).toString();
                String nombres = tabla.getValueAt(fila, 2).toString();
                String telefono = tabla.getValueAt(fila, 3).toString();
                String estado = tabla.getValueAt(fila, 4).toString();

                String idCliente = tabla.getValueAt(fila, 0).toString(); //no es muy necesario, solo muestra el id del cliente

                vista.getTxtCEdula().setText(cedula);
                vista.getTxtNomres().setText(nombres);
                vista.getTxtDireccion().setText(telefono);
                vista.getTxtEstado().setText(estado);
                vista.getTxtIdCliente().setText(idCliente);
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar una fila");
            }
        }

    }

    public boolean camposNull() {
        if (vista.getTxtCEdula().getText().equals("") || vista.getTxtNomres().getText().equals("") || vista.getTxtDireccion().getText().equals("")
                || vista.getTxtEstado().getText().equals("")){
            JOptionPane.showMessageDialog(vista, "Hay campos vacios", "msj Sistema", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        return true;
    }



@Override
        public void mousePressed(MouseEvent me) {
    }

    @Override
        public void mouseReleased(MouseEvent me) {
    }

    @Override
        public void mouseEntered(MouseEvent me) {
    }

    @Override
        public void mouseExited(MouseEvent me) {
    }

    public void limpiar() {
        vista.getTxtCEdula().setText("");
        vista.getTxtNomres().setText("");
        vista.getTxtDireccion().setText("");
        vista.getTxtEstado().setText("");
        vista.getTxtIdCliente().setText("");
    }

    public void limpiarTabla() {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tabla.removeRow(i);
            i = i - 1;
        }

    }

}
