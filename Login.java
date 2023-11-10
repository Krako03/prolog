package interfaces;
import funciones.funciones;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jpl7.*;

public class Login implements ActionListener{
    
    //variables
    JFrame ventana;
    JButton botonIngresar,botonCrear;
    JTextField campoNombre;
    JPasswordField campoContra;
    public Login() {
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
        //Inicialización del campo Nombre
        campoNombre = new JTextField();
        campoNombre.setBounds(400, 300, 250, 40);
        ventana.add(campoNombre);
        
        //Inicialización del campo Contraseña
        campoContra = new JPasswordField();
        campoContra.setBounds(400, 350, 250, 40);
        ventana.add(campoContra);
        
        //Inicialización de botones
        botonIngresar = new JButton("Ingresar");
        botonIngresar.setBounds(400, 400, 120, 30);
        botonIngresar.addActionListener(this);
        ventana.add(botonIngresar);
        
        //Inicialización de botones
        botonCrear = new JButton("Nuevo usuario");
        botonCrear.setBounds(530, 400, 120, 30);
        botonCrear.addActionListener(this);
        ventana.add(botonCrear);   
    }

    public static void main(String[] args) {
        Login ventana = new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonIngresar){
            
            String nombre = campoNombre.getText();
            String contra = campoContra.getText();
            if(nombre.equals("") || contra.equals("")){
                JOptionPane.showMessageDialog(null, "Los campos están vacíos!","Aviso!",2);
            }else{
                if(funciones.verificarUsuario(nombre, contra)){
                    System.out.println(funciones.verificarUsuario(nombre, contra));
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos","Aviso!",2);
                }
            }
            
        }else if(e.getSource() == botonCrear){
            CrearUsuario crear = new CrearUsuario();
            ventana.dispose();
        }
    }
}
