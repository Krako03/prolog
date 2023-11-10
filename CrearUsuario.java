package interfaces;
import funciones.funciones;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CrearUsuario implements ActionListener{
    
    //variables
    JFrame ventana;
    JButton botonRegresar,botonCrear;
    JTextField campoNombre;
    JPasswordField campoContra;
    public CrearUsuario() {
        ventana();
        inicializarElementos();
        ventana.setVisible(true);
    }
    private void ventana(){
        //Inicialización de la ventana
        ventana = new JFrame("Crear usuario");
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
        botonRegresar = new JButton("Regresar");
        botonRegresar.setBounds(400, 400, 120, 30);
        botonRegresar.addActionListener(this);
        ventana.add(botonRegresar);
        
        //Inicialización de botones
        botonCrear = new JButton("Crear usuario");
        botonCrear.setBounds(530, 400, 120, 30);
        botonCrear.addActionListener(this);
        ventana.add(botonCrear);   
    }
  

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonRegresar){
            Login reg = new Login();
            ventana.dispose();
        }else if(e.getSource() == botonCrear){
            String nombre = campoNombre.getText();
            String contra = campoContra.getText();
            if(nombre.equals("") || contra.equals("")){
                JOptionPane.showMessageDialog(null, "Los campos están vacíos!","Aviso!",2);
            }else{
                funciones.agregarPersona(nombre, contra);
                JOptionPane.showMessageDialog(null, "Usuario creado","Completado!",3);
                Login ven = new Login();
                ventana.dispose();
            }
        }
    }
}
