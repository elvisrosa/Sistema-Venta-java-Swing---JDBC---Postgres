package dao_daoImpl;

import conexion.coneccionBD;
import dao.dao;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.producto;
import vista.panelProducto;

public class daoProducto implements dao {

    coneccionBD con = new coneccionBD();
    panelProducto vistaproducto;
    PreparedStatement ps;
    ResultSet rs;
    producto product;
    int r;

    public int actualizarStock(int cantidad, int idP) {
        String sql = "Update producto set stock=? where idp=?";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setInt(2, idP);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("" + e.getMessage());

        }
        return r;

    }

    public producto listarId(int id) { //String cambio
        con.obtenerConeccion();
        producto p = new producto();
        try {
            String sql = "Select * from producto where idp=?";
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, id); //Cambio
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("idp"));
                p.setNombres(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getString("stock"));
                p.setEstado(rs.getString("estado"));
                //p.setFoto(rs.getBlob("foto"));
            }

        } catch (SQLException e) {
        }
        return p;
    }

    public void agregar() throws FileNotFoundException {
        FileInputStream archivoFoto;
        String sql = "Insert Into producto(nombre, precio, stock, estado, foto, rutafoto) values (?, ?, ?, ?, ?, ?)";
        vistaproducto = new panelProducto();

        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, vistaproducto.getTxtNombre().getText());
            ps.setString(2,(vistaproducto.getTxtPrecio().getText()));
            ps.setString(3, vistaproducto.getTxtStock().getText());
            ps.setString(4, vistaproducto.getTxtEstado().getText());
            ps.setString(5, vistaproducto.getTxtfoto().getText());
            archivoFoto = new FileInputStream(vistaproducto.getTxtfoto().getText());
            ps.setBinaryStream(6, archivoFoto);

            rs = ps.executeQuery();
            try {
                JOptionPane.showMessageDialog(null, "creado");
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "No se pudo crear");
            }
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }

    }

    public void Actualizar(producto producto) {
        String sql = "UPDATE producto SET nombre=?, precio=?, stock=?, estado=? WHERE idp = ?";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, producto.getNombres());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getStock());
            ps.setString(4, producto.getEstado());
            ps.setInt(5, producto.getId());
            rs = ps.executeQuery();
            try {
                JOptionPane.showMessageDialog(null, "Actualizado");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar");
            }
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "delete from producto where idP=?";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            try {
                JOptionPane.showMessageDialog(null, "Eliminado");
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "error al eliminar");
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println("" + e.getMessage());
        }

    }

    @Override
    public ArrayList listar() {
        ArrayList lista = new ArrayList<>();
        try {
            con.obtenerConeccion();
            String sql = "select * from producto";
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                product = new producto();
                product.setId(rs.getInt("idp"));
                product.setNombres(rs.getString("nombre"));
                product.setPrecio(rs.getDouble("precio"));
                product.setStock(rs.getString("stock"));
                product.setEstado(rs.getString("estado"));
                lista.add(product);
            }

        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
        }
        return lista;
    }
}
