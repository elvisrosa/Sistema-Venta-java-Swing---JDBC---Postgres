package controlador;

import dao_daoImpl.daoProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.producto;
import vista.panelProducto;

public class controlProducto implements ActionListener, MouseListener {

    private panelProducto vista = new panelProducto();
    daoProducto dao;
    producto modeloProduto;
    DefaultTableModel tabla = new DefaultTableModel();

    public controlProducto(panelProducto vista) {
        this.vista = vista;
        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnNuevo().addActionListener(this);
        this.vista.getTablaProductos().addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        /*
        dao = new daoProducto();
        if (ae.getSource().equals(vista.getBtnAgregar())) {
            if (camposVacios() == true) {
                try {               
                    modeloProduto = new producto();
                    modeloProduto.setN
                    dao.agregar();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(controlProducto.class.getName()).log(Level.SEVERE, null, ex);
                }

                limpiarTabla();
                //listar();
ombres(vista.getTxtNombre().getText());
                    modeloProduto.setPrecio(new Double(vista.getTxtPrecio().getText()));
                    modeloProduto.setStock(vista.getTxtStock().getText());
                    modeloProduto.setEstado(vista.getTxtEstado().getText());

                    dao.agregar();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(controlProducto.class.getName()).log(Level.SEVERE, null, ex);
                }

                limpiarTabla();
                //listar();

            }
        }
*/
        if (ae.getSource().equals(vista.getBtnActualizar())) {
            if (camposVacios() == true) {
                modeloProduto = new producto();
                modeloProduto.setNombres(vista.getTxtNombre().getText());
                modeloProduto.setPrecio(new Double(vista.getTxtPrecio().getText()));
                modeloProduto.setStock(vista.getTxtStock().getText());
                modeloProduto.setEstado(vista.getTxtEstado().getText());
                modeloProduto.setId(Integer.parseInt(vista.getTxtIdProducto().getText()));
                dao.Actualizar(modeloProduto);
                limpiar();
                limpiarTabla();
                listar();
            }
        }

        if (ae.getSource().equals(vista.getBtnNuevo())) {
            limpiar();
        }
        if (ae.getSource().equals(vista.getBtnEliminar())) {
            if (vista.getTxtIdProducto().getText().equals("")) {
                JOptionPane.showMessageDialog(vista, "Selecciona el producto a eliminar", "MSJ sistema", JOptionPane.ERROR_MESSAGE);
            } else {
                modeloProduto = new producto();
                int id = new Integer(vista.getTxtIdProducto().getText());
                dao.eliminar(id);
                limpiar();
                limpiarTabla();
                listar();
            }

        }
    }

    public void listar() {
        dao = new daoProducto();
        ArrayList<producto> lista = dao.listar();
        Object[] datos = new Object[5];
        tabla = (DefaultTableModel) vista.getTablaProductos().getModel();
        try {
            for (int i = 0; i < lista.size(); i++) {
                datos[0] = lista.get(i).getId();
                datos[1] = lista.get(i).getNombres();
                datos[2] = lista.get(i).getPrecio();
                datos[3] = lista.get(i).getStock();
                datos[4] = lista.get(i).getEstado();
                tabla.addRow(datos);
            }
            vista.getTablaProductos().setModel(tabla);
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void limpiar() {
        vista.getTxtNombre().setText("");
        vista.getTxtPrecio().setText("");
        vista.getTxtStock().setText("");
        vista.getTxtEstado().setText("");
        vista.getTxtIdProducto().setText("");

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int fila = vista.getTablaProductos().getSelectedRow();
        if (me.getSource().equals(vista.getTablaProductos())) {
            if (me.getClickCount() == 1) {
                int id = new Integer(tabla.getValueAt(fila, 0).toString());
                String nombre = tabla.getValueAt(fila, 1).toString();
                String precio = tabla.getValueAt(fila, 2).toString();
                String estock = tabla.getValueAt(fila, 3).toString();
                String estado = tabla.getValueAt(fila, 4).toString();

                String idP = tabla.getValueAt(fila, 0).toString();

                vista.getTxtEstado().setText(estado);
                vista.getTxtPrecio().setText(precio);
                vista.getTxtStock().setText(estock);
                vista.getTxtNombre().setText(nombre);
                vista.getTxtIdProducto().setText(idP);

            } else {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }
        }
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

    public void limpiarTabla() {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tabla.removeRow(i);
            i = i - 1;
        }

    }

    public boolean camposVacios() {
        if (vista.getTxtEstado().getText().equals("") || vista.getTxtPrecio().getText().equals("") || vista.getTxtStock().getText().equals("") || vista.getTxtNombre().getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Campos vacios", "MSJ sistema", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
