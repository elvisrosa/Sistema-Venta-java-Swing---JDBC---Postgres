
package modelo;


public class clientes {
    private int id;
    private String nombres;
    private String telefono;
    private String cedula;
    private String estado;

    public clientes() {
    }

    public clientes(int id, String cedula, String nombres, String telefono, String estado) {
        this.id = id;
        this.nombres = nombres;
        this.telefono = telefono;
        this.cedula = cedula;
        this.estado=estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

   

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    
    
    
    
}
