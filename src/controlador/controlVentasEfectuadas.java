package controlador;

import dao_daoImpl.daoVEntas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.venta;
import vista.panelVentasEfectuadas;

public class controlVentasEfectuadas implements ActionListener, KeyListener {

    panelVentasEfectuadas vist = new panelVentasEfectuadas();
    venta venta = new venta();
    DefaultTableModel tabla;
    daoVEntas daoV;
    TableRowSorter tbr; //clasificacion de filtrado de una JTable

    public controlVentasEfectuadas(panelVentasEfectuadas vist) {
        this.vist = vist;
        vist.getTxtBuscar().addKeyListener((KeyListener)this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }

    public void listarVentas() {
        daoV = new daoVEntas();
        tabla = (DefaultTableModel) vist.getTablaVentasEfectuadas().getModel();

        ArrayList<venta> lista = daoV.ventasEfectadas();
        Object[] obj = new Object[7];
        try {
            for (int i = 0; i < lista.size(); i++) {
                obj[0] = lista.get(i).getId();
                obj[1] = lista.get(i).getIdCliente();
                obj[2] = lista.get(i).getIdVendedor();
                obj[3] = lista.get(i).getSerie();
                obj[4] = lista.get(i).getFecha();
                obj[5] = lista.get(i).getMonto();
                obj[6] = lista.get(i).getEstado();
                tabla.addRow(obj);
            }

            vist.getTablaVentasEfectuadas().setModel(tabla);

        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (ke.getSource().equals(vist.getTxtBuscar())) {
            tabla = (DefaultTableModel) vist.getTablaVentasEfectuadas().getModel();
            vist.getTxtBuscar().addKeyListener(new KeyAdapter() { // add evento KeyListener de tipo adapter(evento al soltar y presionar claves en el teclado)             
                @Override
                public void keyReleased(KeyEvent ke) {
                    tbr.setRowFilter(RowFilter.regexFilter("(?i)" + vist.getTxtBuscar().getText(), 0, 1, 3));   //filtro en el txt     
               }
            });
            tbr=new TableRowSorter(tabla);
            vist.getTablaVentasEfectuadas().setRowSorter(tbr);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
