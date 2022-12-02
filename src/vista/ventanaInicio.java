package vista;

import conexion.coneccionBD;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ventanaInicio extends javax.swing.JFrame {

    PreparedStatement ps;
    ResultSet rs;

    public ventanaInicio() {
        initComponents();
        setLocationRelativeTo(null);
        //dimensionPantalla();
        CerrarVentana();
    }

    public JMenuItem getMenuItemVentasEfectuadas() {
        return menuItemVentasEfectuadas;
    } 
    public JButton getBtnGuardarImg() {
        return btnGuardarImg;
    }

    public void setBtnGuardarImg(JButton btnGuardarImg) {
        this.btnGuardarImg = btnGuardarImg;
    }

    public JLabel getLblImagen() {
        return lblImagen;
    }

    public void setLblImagen(JLabel lblImagen) {
        this.lblImagen = lblImagen;
    }

    public JTextField getTxtRutaImg() {
        return txtRutaImg;
    }

    public void setTxtRutaImg(JTextField txtRutaImg) {
        this.txtRutaImg = txtRutaImg;
    }

    public JLabel getLblNnombreDeUsuario() {
        return lblNnombreDeUsuario;
    }

    public JMenu getMenuIMantenimiento() {
        return menuIMantenimiento;
    }

    
    public void setLblNnombreDeUsuario(JLabel lblNnombreDeUsuario) {
        this.lblNnombreDeUsuario = lblNnombreDeUsuario;
    }

    public JMenuItem getMenuItemColor() {
        return menuItemColor;
    }

    public void setMenuItemColor(JMenuItem menuItemColor) {
        this.menuItemColor = menuItemColor;
    }

    public JMenu getMenuProductos() {
        return menuProductos;
    }

    public void setMenuProductos(JMenu menuProductos) {
        this.menuProductos = menuProductos;
    }

    public JMenu getMenuVenta() {
        return menuVenta;
    }

    public void setMenuVenta(JMenu menuVenta) {
        this.menuVenta = menuVenta;
    }

    public JMenu getMenuConfiguracion() {
        return menuConfiguracion;
    }

    public void setMenuConfiguracion(JMenu menuConfiguracion) {
        this.menuConfiguracion = menuConfiguracion;
    }

    public JMenu getMenuIClientes() {
        return menuIMantenimiento;
    }

    public void setMenuIClientes(JMenu menuIClientes) {
        this.menuIMantenimiento = menuIClientes;
    }

    public JMenuItem getMenuItemAddProducto() {
        return menuItemAddProducto;
    }

    public void setMenuItemAddProducto(JMenuItem menuItemAddProducto) {
        this.menuItemAddProducto = menuItemAddProducto;
    }

    public JMenuItem getMenuItemCliente() {
        return menuItemCliente;
    }

    public void setMenuItemCliente(JMenuItem menuItemCliente) {
        this.menuItemCliente = menuItemCliente;
    }

    public JMenuItem getMenuItemEmpleados() {
        return menuItemUsuarios;
    }

    public void setMenuItemEmpleados(JMenuItem menuItemEmpleados) {
        this.menuItemUsuarios = menuItemEmpleados;
    }

    public JMenuItem getMenuItemGenerarVentas() {
        return menuItemGenerarVentas;
    }

    public void setMenuItemGenerarVentas(JMenuItem menuItemGenerarVentas) {
        this.menuItemGenerarVentas = menuItemGenerarVentas;
    }

    public JMenuItem getMenuItemProducto() {
        return menuItemProducto;
    }

    public void setMenuItemProducto(JMenuItem menuItemProducto) {
        this.menuItemProducto = menuItemProducto;
    }

 

    public JMenuItem getMenuItempantalla() {
        return menuItempantalla;
    }

    public JMenuItem getMenuItemUsuarios() {
        return menuItemUsuarios;
    }

    
    public void setMenuItempantalla(JMenuItem menuItempantalla) {
        this.menuItempantalla = menuItempantalla;
    }

    public JTabbedPane getTablePanelPricipal() {
        return tablePanelPricipal;
    }

    public void setTablePanelPricipal(JTabbedPane tablePanelPricipal) {
        this.tablePanelPricipal = tablePanelPricipal;
    }

    public JButton getCambiarContrseña() {
        return CambiarContrseña;
    }

    public JButton getBtnCAmbiarADministrador() {
        return btnCAmbiarADministrador;
    }

    public JButton getBtnCambiarFoto() {
        return btnCambiarFoto;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public void CerrarVentana() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    cerrar();
                }
            });

        } catch (Exception e) {

        }
    }

    public void cerrar() {
        int r = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            this.setVisible(true);
        }
    }

    public void dimensionPantalla() {
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((ancho / 2) - (this.getWidth() / 2), (alto / 2) - (this.getHeight() / 2), 500, 500);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablePanelPricipal = new javax.swing.JTabbedPane();
        panelBajo = new javax.swing.JPanel();
        panelbajo = new javax.swing.JPanel();
        panelEncima = new javax.swing.JPanel();
        lblNnombreDeUsuario = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        CambiarContrseña = new javax.swing.JButton();
        btnCAmbiarADministrador = new javax.swing.JButton();
        btnCambiarFoto = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();
        txtRutaImg = new javax.swing.JTextField();
        btnGuardarImg = new javax.swing.JButton();
        btn = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        menuIMantenimiento = new javax.swing.JMenu();
        menuItemCliente = new javax.swing.JMenuItem();
        menuItemProducto = new javax.swing.JMenuItem();
        menuItemVentasEfectuadas = new javax.swing.JMenuItem();
        menuItemUsuarios = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JMenu();
        menuItemAddProducto = new javax.swing.JMenuItem();
        menuConfiguracion = new javax.swing.JMenu();
        menuItempantalla = new javax.swing.JMenuItem();
        menuItemColor = new javax.swing.JMenuItem();
        menuVenta = new javax.swing.JMenu();
        menuItemGenerarVentas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablePanelPricipal.setBackground(new java.awt.Color(102, 102, 102));
        tablePanelPricipal.setBorder(new javax.swing.border.MatteBorder(null));
        tablePanelPricipal.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(tablePanelPricipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 750, 520));

        javax.swing.GroupLayout panelBajoLayout = new javax.swing.GroupLayout(panelBajo);
        panelBajo.setLayout(panelBajoLayout);
        panelBajoLayout.setHorizontalGroup(
            panelBajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBajoLayout.setVerticalGroup(
            panelBajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );

        getContentPane().add(panelBajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1003, 0, -1, 517));

        panelbajo.setBackground(new java.awt.Color(0, 0, 0));
        panelbajo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelEncima.setBackground(new java.awt.Color(102, 102, 102));
        panelEncima.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        panelEncima.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNnombreDeUsuario.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblNnombreDeUsuario.setForeground(new java.awt.Color(0, 0, 204));
        panelEncima.add(lblNnombreDeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 120, 21));

        btnCerrarSesion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(51, 51, 255));
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        panelEncima.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 133, -1));

        CambiarContrseña.setBackground(new java.awt.Color(102, 102, 102));
        CambiarContrseña.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        CambiarContrseña.setForeground(new java.awt.Color(0, 51, 255));
        CambiarContrseña.setText("Cambiar Contraseña");
        CambiarContrseña.setBorder(null);
        CambiarContrseña.setOpaque(false);
        panelEncima.add(CambiarContrseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 130, 30));

        btnCAmbiarADministrador.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        btnCAmbiarADministrador.setForeground(new java.awt.Color(0, 51, 255));
        btnCAmbiarADministrador.setText("Cambiar de Admin");
        btnCAmbiarADministrador.setBorder(null);
        panelEncima.add(btnCAmbiarADministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 370, 160, -1));

        btnCambiarFoto.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnCambiarFoto.setForeground(new java.awt.Color(0, 51, 255));
        btnCambiarFoto.setText("Buscar imagen");
        btnCambiarFoto.setBorder(null);
        btnCambiarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarFotoActionPerformed(evt);
            }
        });
        panelEncima.add(btnCambiarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 130, 20));

        lblImagen.setAlignmentX(1.0F);
        panelEncima.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, 142, 130));
        panelEncima.add(txtRutaImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 130, 20));

        btnGuardarImg.setText("Guardar cambios");
        btnGuardarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarImgActionPerformed(evt);
            }
        });
        panelEncima.add(btnGuardarImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 130, -1));

        panelbajo.add(panelEncima, new org.netbeans.lib.awtextra.AbsoluteConstraints(-170, 0, 170, 520));

        btn.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfil.png"))); // NOI18N
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });
        panelbajo.add(btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 50, -1));

        getContentPane().add(panelbajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 520));

        menuIMantenimiento.setBorder(null);
        menuIMantenimiento.setText("Mantenimiento");
        menuIMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIMantenimientoActionPerformed(evt);
            }
        });

        menuItemCliente.setText("Clientes");
        menuIMantenimiento.add(menuItemCliente);

        menuItemProducto.setText("Productos");
        menuIMantenimiento.add(menuItemProducto);

        menuItemVentasEfectuadas.setText("Ventas efectuadas");
        menuIMantenimiento.add(menuItemVentasEfectuadas);

        menuItemUsuarios.setText("Usuarios");
        menuItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemUsuariosActionPerformed(evt);
            }
        });
        menuIMantenimiento.add(menuItemUsuarios);

        jMenuBar2.add(menuIMantenimiento);

        menuProductos.setText("Productos");

        menuItemAddProducto.setText("Comprar productos");
        menuItemAddProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAddProductoActionPerformed(evt);
            }
        });
        menuProductos.add(menuItemAddProducto);

        jMenuBar2.add(menuProductos);

        menuConfiguracion.setText("Configuracion ");

        menuItempantalla.setText("pantalla");
        menuConfiguracion.add(menuItempantalla);

        menuItemColor.setText("Color");
        menuConfiguracion.add(menuItemColor);

        jMenuBar2.add(menuConfiguracion);

        menuVenta.setText("Ventas");

        menuItemGenerarVentas.setText("generar venta");
        menuVenta.add(menuItemGenerarVentas);

        jMenuBar2.add(menuVenta);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuIMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIMantenimientoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_menuIMantenimientoActionPerformed

    private void menuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemUsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemUsuariosActionPerformed

    private void menuItemAddProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAddProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemAddProductoActionPerformed

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
        // TODO add your handling code here:
        int posicion = btn.getX();
        if (posicion > 2) {
            Animacion.Animacion.mover_izquierda(200, 2, 2, 2, btn);
            Animacion.Animacion.mover_izquierda(2, -200, 2, 2, panelEncima);

        } else {
            Animacion.Animacion.mover_derecha(2, 200, 2, 2, btn);
            Animacion.Animacion.mover_derecha(-200, 2, 2, 2, panelEncima);

        }
    }//GEN-LAST:event_btnActionPerformed

    private void btnCambiarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarFotoActionPerformed
        // TODO add your handling code here:

        FileNameExtensionFilter ex = new FileNameExtensionFilter("formato de archivo JPEG(*.jpg;*.jpeg)", "jpg", "jpeg");
        JFileChooser archivoFile = new JFileChooser();
        archivoFile.addChoosableFileFilter(ex);
        archivoFile.setDialogTitle("Abrir archivo");

        int ventana;
        ventana = archivoFile.showOpenDialog(null);
        if (ventana == JFileChooser.APPROVE_OPTION) {
            File file = archivoFile.getSelectedFile();
            txtRutaImg.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtRutaImg.getText());

            foto = foto.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            lblImagen.setIcon(new ImageIcon(foto));

        }

    }//GEN-LAST:event_btnCambiarFotoActionPerformed

    private void btnGuardarImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarImgActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarImgActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed

    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    public void guardar() {
        coneccionBD con = new coneccionBD();
        String sql = "Update persona set foto=? where usuario=?";
        FileInputStream archivoFoto;
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            archivoFoto = new FileInputStream(txtRutaImg.getText());
            ps.setBlob(1, archivoFoto);
            ps.setString(2, lblNnombreDeUsuario.getText());
            rs = ps.executeQuery();
            JOptionPane.showMessageDialog(this, "Listo!");
            txtRutaImg.setText("");

        } catch (HeadlessException | FileNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Elige una imagen! "+e.getMessage());
        }
    }

    public void traerImagen() throws IOException {
        coneccionBD con = new coneccionBD();
        String sql = "Select foto from persona where usuario=?";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, lblNnombreDeUsuario.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                InputStream imgString = rs.getBinaryStream("foto");
                Image i = ImageIO.read(imgString);
                i = i.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                lblImagen.setIcon(new ImageIcon(i));
                System.out.println("exito!");
            }

            //Image foto = getToolkit().getImage(txtRutaImg.getText());
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CambiarContrseña;
    private javax.swing.JButton btn;
    private javax.swing.JButton btnCAmbiarADministrador;
    private javax.swing.JButton btnCambiarFoto;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnGuardarImg;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNnombreDeUsuario;
    private javax.swing.JMenu menuConfiguracion;
    private javax.swing.JMenu menuIMantenimiento;
    private javax.swing.JMenuItem menuItemAddProducto;
    private javax.swing.JMenuItem menuItemCliente;
    private javax.swing.JMenuItem menuItemColor;
    private javax.swing.JMenuItem menuItemGenerarVentas;
    private javax.swing.JMenuItem menuItemProducto;
    private javax.swing.JMenuItem menuItemUsuarios;
    private javax.swing.JMenuItem menuItemVentasEfectuadas;
    private javax.swing.JMenuItem menuItempantalla;
    private javax.swing.JMenu menuProductos;
    private javax.swing.JMenu menuVenta;
    private javax.swing.JPanel panelBajo;
    private javax.swing.JPanel panelEncima;
    private javax.swing.JPanel panelbajo;
    private javax.swing.JTabbedPane tablePanelPricipal;
    private javax.swing.JTextField txtRutaImg;
    // End of variables declaration//GEN-END:variables
}
