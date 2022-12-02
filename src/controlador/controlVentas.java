package controlador;

import dao_daoImpl.daoCliente;
import dao_daoImpl.daoProducto;
import dao_daoImpl.daoVEntas;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.clientes;
import modelo.detalleVenta;
import modelo.producto;
import modelo.venta;
import vista.panelRegistroCliente;
import vista.panelventas;
import vista.ventanaInicio;

public class controlVentas implements ActionListener {

    panelventas vista = new panelventas();
    producto modeloP = new producto();
    daoCliente dao;
    daoProducto daop;
    panelRegistroCliente pCliente;
    int p1 = 0;
    int idP;
    double totalPagar;
    double precio;
    int cantidadDeProducto;
    venta v = new venta();
    daoVEntas daoventas = new daoVEntas();
    detalleVenta dv = new detalleVenta();
    clientes cliente = new clientes();

    ventanaInicio vistaI = new ventanaInicio();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public controlVentas(panelventas vista) {
        this.vista = vista;
        vista.getBtnBuscarCliente().addActionListener(this);
        vista.getBtnBuscarProducto().addActionListener(this);
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtngenerarventa().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(vista.getBtnBuscarCliente())) {
            buscarCliente();
        } else if (ae.getSource().equals(vista.getBtnBuscarProducto())) {
            buscarProducto();
        } else if (ae.getSource().equals(vista.getBtnAgregar())) {
            agregarProducto();
            calcularTotalPagar();
        } else if (ae.getSource().equals(vista.getBtngenerarventa())) {
            if (vista.getTxtTotalpagar().getText().equals("")) {
                JOptionPane.showMessageDialog(vista, "Para generar una venta se necesita al menos un producto, (Usuario)");
            } else {
                //actualizarStock();
                GuardarVenta();
                guardarDetalleVenta(); //?               
                JOptionPane.showMessageDialog(vista, "Se realizo con exito!");
                limpiarCajas();
            }
        }

    }

    void limpiarTabla() {
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }

    public void actualizarStock() {
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            try {
                modeloP = new producto();
                idP = Integer.parseInt(vista.getTablaGeneralOrden().getValueAt(i, 1).toString());
                cantidadDeProducto = Integer.parseInt(vista.getTablaGeneralOrden().getValueAt(i, 3).toString());
                modeloP = daop.listarId(idP);
                int stockAct = Integer.parseInt(modeloP.getStock()) - cantidadDeProducto;
                System.out.println("" + stockAct);
                daop.actualizarStock(stockAct, idP);
            } catch (HeadlessException | NumberFormatException e) {
                System.out.println("" + e.getMessage());
            }
        }
    }

    public void generarSerieVenta() {
        int serie = daoventas.Obtenerserie();
        try {
            if (serie == 0) {
                vista.getTxtSerie().setText("1");
            } else {
                int incremen = serie;
                incremen = incremen + 1;
                vista.getTxtSerie().setText("incremento" + incremen);
            }
        } catch (NumberFormatException e) {
            System.out.println("error" + e);
        }
    }

    public void GuardarVenta() {
        int idV = 2;
        int idC = cliente.getId();
        String serie = vista.getTxtSerie().getText();
        String fecha = vista.getTxtFecha().getText();
        double monto = totalPagar;
        String estado = "1";

        v.setIdCliente(idC);
        v.setIdVendedor(idV);
        v.setSerie(serie);
        v.setFecha(fecha);
        v.setMonto(monto);
        v.setEstado(estado);
        actualizarStock();
        daoventas.GuardarVenta(v);

    }

    public void guardarDetalleVenta() {
        int idV = daoventas.IdVentas();
        int idventa = idV;
        for (int i = 0; i < vista.getTablaGeneralOrden().getRowCount(); i++) {
            int idp = Integer.parseInt(vista.getTablaGeneralOrden().getValueAt(i, 1).toString());
            int cantidad = Integer.parseInt(vista.getTablaGeneralOrden().getValueAt(i, 3).toString());
            double prec = Double.parseDouble(vista.getTablaGeneralOrden().getValueAt(i, 4).toString());

            dv.setIdVenta(idventa);
            dv.setIdProducto(idp);
            dv.setCantidad(cantidad);
            dv.setPrecioVenta(prec);
            daoventas.guardarDetalleVenta(dv);

        }

    }

    public void agregarProducto() {
        int contador = 0;
        double total;
        modeloTabla = (DefaultTableModel) vista.getTablaGeneralOrden().getModel();
        contador += 1;
        idP = modeloP.getId();
        String nombreP = vista.getTxtproducto().getText();
        precio = parseDouble(vista.getTxtPrecio().getText());
        cantidadDeProducto = Integer.parseInt(vista.getSpinerCAntidad().getValue().toString());
        int stock = parseInt(vista.getTxtStock().getText());
        total = cantidadDeProducto * precio;
        ArrayList lista = new ArrayList();

        if (stock > 0) {
            lista.add(contador);
            lista.add(idP);
            lista.add(nombreP);
            lista.add(cantidadDeProducto);
            lista.add(precio);
            lista.add(total);
            Object[] ob = new Object[6];
            ob[0] = lista.get(0);
            ob[1] = lista.get(1);
            ob[2] = lista.get(2);
            ob[3] = lista.get(3);
            ob[4] = lista.get(4);
            ob[5] = lista.get(5);

            modeloTabla.addRow(ob);
            vista.getTablaGeneralOrden().setModel(modeloTabla);

        } else {
            JOptionPane.showMessageDialog(vista, "No se ha encontrado stock de " + modeloP.getNombres());
        }
    }

    public void calcularTotalPagar() {
        totalPagar = 0;
        for (int i = 0; i < vista.getTablaGeneralOrden().getRowCount(); i++) {
            cantidadDeProducto = Integer.parseInt(vista.getTablaGeneralOrden().getValueAt(i, 3).toString());
            precio = Double.parseDouble(vista.getTablaGeneralOrden().getValueAt(i, 4).toString());
            totalPagar = totalPagar + (cantidadDeProducto * precio);

        }
        vista.getTxtTotalpagar().setText("" + totalPagar);

    }

    public void buscarProducto() {
        daop = new daoProducto();
        int codigo = Integer.parseInt(vista.getTxtCodigoProducto().getText());
        if (vista.getTxtCodigoProducto().getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Se necesita un codigo");
        } else {
            modeloP = daop.listarId(codigo);
            if (modeloP.getId() != 0) {
                vista.getTxtproducto().setText(modeloP.getNombres());
                vista.getTxtStock().setText(modeloP.getStock());
                vista.getTxtPrecio().setText(modeloP.getPrecio().toString());
            } 

        }
    }

    

    public void buscarCliente() {
        int respuesta;
        dao = new daoCliente();
        String codCliente = vista.getTxtCodigoCliente().getText();
        if (vista.getTxtCodigoCliente().getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Se necesita un codigo");
        } else {
            cliente = dao.listarID(codCliente);
            if (cliente.getCedula() != null) {
                vista.getTxtCliente().setText(cliente.getNombres());
                vista.getTxtCodigoProducto().requestFocus();

                //----------------------
            } else {
                respuesta = JOptionPane.showConfirmDialog(vista, "Cliente no registrado, desea registrar ?");

                //Corregir codigo a partir de esta linea en adelante
                if (respuesta == 0) {
                    if (p1 == 0) {
                        pCliente = new panelRegistroCliente();
                        controlCliente contro = new controlCliente(pCliente);
                        vistaI.getTablePanelPricipal().addTab("Registro de clientes", pCliente);
                        vistaI.getTablePanelPricipal().setSelectedComponent(pCliente);
                        contro.listar();
                        pCliente.show();
                    } else {
                        vistaI.getTablePanelPricipal().setSelectedComponent(pCliente);
                    }
                    p1 = 1;

                }

            }

        }

    }

    void limpiarCajas() {
        limpiarTabla();
        vista.getTxtPrecio().setText("");
        vista.getTxtCodigoProducto().setText("");
        vista.getTxtCodigoCliente().setText("");
        vista.getTxtCantidadTotal().setText("");
        vista.getTxtStock().setText("");
        vista.getTxtTotalpagar().setText("");
        vista.getTxtproducto().setText("");
        vista.getTxtCliente().setText("");
        vista.getTxtCodigoCliente().requestFocus();

    }

}
