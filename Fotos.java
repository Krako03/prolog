package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Fotos implements ActionListener{
    JFrame ventana;
    JButton cambiarFoto;
    JComboBox opciones;
    JLabel etiqueta;
    
    public Fotos() {
        ventana = new JFrame("Fotos");
        ventana.setSize(300,220);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        
        etiqueta = new JLabel("Eliga una opción");
        etiqueta.setBounds(95, 10, 120, 30);
        ventana.add(etiqueta);

        //Inicialización de botones
        cambiarFoto = new JButton("Seleccionar");
        cambiarFoto.setBounds(90, 135, 120, 30);
        cambiarFoto.addActionListener(this);
        ventana.add(cambiarFoto);

        //Inicialización de botones
        opciones = new JComboBox();
        opciones.setBounds(10, 70, 120, 30);
        opciones.addItem("Default");
        opciones.addItem("Joao");
        opciones.addActionListener(this);
        ventana.add(opciones);
        
        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
