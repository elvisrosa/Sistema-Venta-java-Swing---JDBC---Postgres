package modelo;

public class usuario_login {

    private String usuario;
    private String contraseña;
    private int codigo;
    private String tipoUsuario="Cliente";

    public usuario_login() {
    }

    public usuario_login(String tipoUsuario, int codigo, String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.codigo = codigo;
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
