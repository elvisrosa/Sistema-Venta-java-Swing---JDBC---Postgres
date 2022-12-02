package dao_daoImpl;
import conexion.coneccionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.detalleVenta;
import modelo.venta;

public class daoVEntas {
    coneccionBD con = new coneccionBD();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public int Obtenerserie() {
        int serie=0;
        String sql = "select max(numeroventa) from ventas";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareCall(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                //serie = rs.getInt(4); 
                System.out.println("numero "+rs.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println("error numeroventas " + e.getMessage());
        }
        
        
        return serie;
    }

    public int IdVentas() {
        int idV = 0;
        String sql = "Select max(IDVENTAS) from ventas";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idV = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error idventas" + e.getMessage());
        }
        return idV;
    }

    public int GuardarVenta(venta v) {
        venta ven = new venta();
        String sql = "Insert into ventas (idcliente, idvendedor, numeroventa, fechaventas, monto, estado) values(?,?,?,?,?,?)";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, v.getIdCliente());
            ps.setInt(2, v.getIdVendedor());
            ps.setString(3, v.getSerie());
            ps.setString(4, v.getFecha());
            ps.setDouble(5, v.getMonto());
            ps.setString(6, v.getEstado());
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
        }

        return r;

    }

    public int guardarDetalleVenta(detalleVenta dv) {
        String sql = "Insert into detalleventas (idventa, idproducto, cantidad, precioventa)  values (?,?,?,?)";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, dv.getIdVenta());
            ps.setInt(2, dv.getIdProducto());
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getPrecioVenta());
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
        }

        return r;

    }

    public ArrayList<venta> ventasEfectadas() {
        ArrayList<venta> lista = new ArrayList<>();
        String sql = "select * from ventas";
        try {
            con.obtenerConeccion();
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                venta v = new venta();
                v.setId(rs.getInt("idventas"));
                v.setIdCliente(rs.getInt("idcliente"));
                v.setIdVendedor(rs.getInt("idvendedor"));
                v.setSerie(rs.getString("numeroventa"));
                v.setFecha(rs.getString("fechaventas"));
                v.setMonto(rs.getDouble("monto"));
                v.setEstado(rs.getString("estado"));
                lista.add(v);                          
            }
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return lista;

    }

}
