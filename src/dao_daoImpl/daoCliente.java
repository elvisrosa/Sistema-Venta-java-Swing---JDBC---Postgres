package dao_daoImpl;

import conexion.coneccionBD;
import dao.daoC;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.clientes;

public class daoCliente implements daoC {

    coneccionBD con = new coneccionBD();
    PreparedStatement ps;
    ResultSet rs;

    public clientes listarID(String cedula) {
        clientes cliente = new clientes();
        String sql = "select * from cliente where cedula=?";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            while (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEstado(rs.getString("estado"));

            }
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        }

        return cliente;

    }

    @Override
    public ArrayList listar() {
        ArrayList<clientes> list = new ArrayList<>();

        String consulta = "Select * from cliente";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                clientes cliente = new clientes(rs.getInt("id"),
                        rs.getString("cedula"),
                        rs.getString("nombres"),
                        rs.getString("telefono"),
                        rs.getString("estado"));
                list.add(cliente);

            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return list;
    }

    @Override
    public void eliminar(int id) {
        String sql = "Delete from cliente where id=?";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
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
    public int actualizar(Object[] c) {
        int r = 0;
        String sql = "UPDATE cliente SET cedula=?, nombres=?, telefono=?, estado=? WHERE id = ?";
        try {

            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setObject(1, c[0]);
            ps.setObject(2, c[1]);
            ps.setObject(3, c[2]);
            ps.setObject(4, c[3]);
            ps.setObject(5, c[4]);

            r = ps.executeUpdate();
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizado");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Actualizar" + e);
        }
        return r;
    }

    @Override
    public int agregar(Object[] o) {
        int r = 0;
        String sql = "Insert Into cliente (cedula, nombres, telefono, estado) values (?, ?, ?, ?)";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            r = ps.executeUpdate();
            try {
                JOptionPane.showMessageDialog(null, "Creado");
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "No creado");
            }
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
        return r;
    }
}
