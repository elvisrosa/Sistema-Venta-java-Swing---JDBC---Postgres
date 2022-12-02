/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import conexion.coneccionBD;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class peoductoDeVenta extends javax.swing.JPanel {

    public peoductoDeVenta() {
        initComponents();
        mostrarDatos();
    }

    PreparedStatement ps;
    ResultSet rs;

    public void mostrarDatos() {
        coneccionBD con = new coneccionBD();
        con.obtenerConeccion();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Imagen");
        tablaProducto.setModel(modeloTabla);
        tablaProducto.setDefaultRenderer(Object.class, new fotoProducto());
        try {
            Object[] datos = new Object[3];
            String sql = "select nombre, precio, foto from producto";
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getString("nombre");
                datos[1] = rs.getString("precio");
                Blob blob = rs.getBlob("foto");
                if (blob != null) {
                    try {
                        byte[] data = blob.getBytes(1, (int) blob.length());
                        BufferedImage img = null;
                        try {
                            img = ImageIO.read(new ByteArrayInputStream(data));
                        } catch (Exception e) {
                            System.out.println("" + e.getMessage());
                        }
                        ImageIcon icono = new ImageIcon(img);
                        Image imag = icono.getImage().getScaledInstance(150, 90, 90);
                        Icon iconoEscalado = new ImageIcon(imag);
                        datos[2] = new JLabel(iconoEscalado);

                    } catch (Exception e) {
                        datos[3] = "No hay Imagen";
                    }
                } else {
                    datos[3] = "No hay Imagen";
                }
                modeloTabla.addRow(datos);
                tablaProducto.setRowHeight(80);
                tablaProducto.setRowMargin(0);
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();

        tablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaProducto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProducto;
    // End of variables declaration//GEN-END:variables
}
