
import conexion.logueo.control_login;
import vista.login;
public class main {
    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
        java.awt.EventQueue.invokeLater(() -> {
            login vista = new login();
            control_login login = new control_login(vista);           
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);

        });
    }

}

//generar pdf
//https://www.youtube.com/watch?v=TAJoVkaVv-8
//https://www.youtube.com/watch?v=btLhIuM4QWc
//https://www.youtube.com/watch?v=5Moa7F_hMg8
//https://www.incanatoit.com/2015/04/sistema-de-ventas-en-java-mysql-netbeans-codigo-barras.html
//https://www.youtube.com/watch?v=AFV4JJtJwxE
//https://www.youtube.com/watch?v=k-q2OCnwnrA
