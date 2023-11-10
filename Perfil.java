package interfaces;
import funciones.funciones;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jpl7.*;

public class Perfil implements ActionListener{
    
    //variables
    JFrame ventana;
    JTextField campoNombre;
    JPasswordField campoContra;
    JTable amigos,amigosEnComun;
    JLabel MiImagen,MiNombre,MiDescrip,imagenAmigo,nombreAmigo,descripAmigo,amigosTit,amigosEnComunTit;
    
    public Perfil() {
        ventana();
        inicializarElementos();
        ventana.setVisible(true);
    }
    private void ventana(){
        //Inicialización de la ventana
        ventana = new JFrame("Login");
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        
    }
    private void inicializarElementos() {
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
