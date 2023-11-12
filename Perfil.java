package interfaces;

import funciones.funciones;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.jpl7.*;

public class Perfil implements ActionListener {

    //variables
    JFrame ventana;
    JTable amigos, amigosEnComun, Tabladesconocidos;
    JLabel MiImagen, MiNombre, MiDescrip, imagenAmigo, nombreAmigo, descripAmigo, amigosTit, amigosEnComunTit, desconocidostit;
    JButton opciones, eliminarAmigo,agregarAmigo;
    DefaultTableModel model, model2, model3;
    JScrollPane scrollAmigos, scrollAmigosEnComun, Scrolldesconocidos;
    Boolean bandera;

    public Perfil(String nombre) {
        bandera = true;
        ventana();
        inicializarElementos(nombre);
        cargarTablaAmigos(nombre);
        tablaPersonas(nombre);
        ventana.setVisible(true);

        amigos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = amigos.getSelectedRow();
                if (selectedRow != -1) {
                    mostrarUsuario(amigos.getValueAt(selectedRow, 0).toString());
                    eliminarAmigo.setVisible(true);
                    agregarAmigo.setVisible(false);
                    if (bandera) {
                        crearTablaAmigosEnComun();
                        bandera = false;
                    }
                    cargarTablaAmigosEnComun(nombre, amigos.getValueAt(selectedRow, 0).toString());
                }
            }
        });
        
        Tabladesconocidos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = Tabladesconocidos.getSelectedRow();
                if (selectedRow != -1) {
                    mostrarUsuario(Tabladesconocidos.getValueAt(selectedRow, 0).toString());
                    eliminarAmigo.setVisible(false);
                    agregarAmigo.setVisible(true);
                }
            }
        });
    }

    private void mostrarUsuario(String usuario) {
        java.util.Map<String, Term> amigo = funciones.obtenerUsuario(usuario.replace(' ', '_'));
        Term foto = amigo.get("Foto");
        Term des = amigo.get("Des");
        String desc = des.toString().replace('_', ' ');
        nombreAmigo.setText(usuario);
        descripAmigo.setText(desc);

        ImageIcon imagenIcon = new ImageIcon("FotosPerfil/" + foto + ".jpg");
        Image imagen = imagenIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagen);
        imagenAmigo.setIcon(iconoEscalado);

    }

    private void ventana() {
        //Inicialización de la ventana
        ventana = new JFrame("Login");
        ventana.setSize(650, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);

    }

    private void inicializarElementos(String nombre) {

        //Tu perfil
        java.util.Map<String, Term> usuario = funciones.obtenerUsuario(nombre);
        Term foto = usuario.get("Foto");

        ImageIcon imagenIcon = new ImageIcon("FotosPerfil/" + foto + ".jpg");
        Image imagen = imagenIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagen);

        MiImagen = new JLabel(iconoEscalado);
        MiImagen.setBounds(20, 20, 75, 75);
        ventana.add(MiImagen);

        //Nombre
        MiNombre = new JLabel(nombre);
        MiNombre.setBounds(140, 20, 50, 50);
        ventana.add(MiNombre);

        //Descripcion
        Term des = usuario.get("Des");
        MiDescrip = new JLabel(des.toString());
        MiDescrip.setBounds(200, 20, 300, 50);
        ventana.add(MiDescrip);

        //Perfil amigo
        imagenAmigo = new JLabel();
        imagenAmigo.setBounds(250, 170, 75, 75);
        ventana.add(imagenAmigo);

        nombreAmigo = new JLabel();
        nombreAmigo.setBounds(250, 250, 150, 50);
        ventana.add(nombreAmigo);

        descripAmigo = new JLabel();
        descripAmigo.setBounds(250, 300, 150, 50);
        ventana.add(descripAmigo);

        //Listas
        amigosTit = new JLabel("Amigos");
        amigosTit.setBounds(480, 120, 150, 50);
        ventana.add(amigosTit);

        //boton opciones
        opciones = new JButton("Opciones");
        opciones.setBounds(500, 20, 100, 40);
        opciones.addActionListener(this);
        //ventana.add(opciones);

        eliminarAmigo = new JButton("Eliminar ");
        eliminarAmigo.setBounds(250, 420, 100, 40);
        eliminarAmigo.addActionListener(this);
        ventana.add(eliminarAmigo);
        eliminarAmigo.setVisible(false);
        
        agregarAmigo = new JButton("Agregar ");
        agregarAmigo.setBounds(250, 420, 100, 40);
        agregarAmigo.addActionListener(this);
        ventana.add(agregarAmigo);
        agregarAmigo.setVisible(false);

        //tabla amigos
        amigos = new JTable();
        scrollAmigos = new JScrollPane(amigos);
        scrollAmigos.setBounds(400, 160, 200, 300);
        ventana.add(scrollAmigos);

        model = new DefaultTableModel();
        model.addColumn("Nombre");
        amigos.setModel(model);

    }

    private void crearTablaAmigosEnComun() {
        //tabla amigosEnComun
        amigosEnComunTit = new JLabel("Amigos en Común ");
        amigosEnComunTit.setBounds(20, 120, 150, 50);
        ventana.add(amigosEnComunTit);

        amigosEnComun = new JTable();
        scrollAmigosEnComun = new JScrollPane(amigosEnComun);
        scrollAmigosEnComun.setBounds(20, 160, 200, 150);
        ventana.add(scrollAmigosEnComun);

        model2 = new DefaultTableModel();
        model2.addColumn("Nombre");
        amigosEnComun.setModel(model2);

        

    }
    private void tablaPersonas(String nombre){
        //tabla amigosEnComun
        desconocidostit = new JLabel("Personas que quiza conozcas");
        desconocidostit.setBounds(20, 310, 180, 50);
        ventana.add(desconocidostit);

        Tabladesconocidos = new JTable();
        Scrolldesconocidos = new JScrollPane(Tabladesconocidos);
        Scrolldesconocidos.setBounds(20, 360, 200, 150);
        ventana.add(Scrolldesconocidos);

        model3 = new DefaultTableModel();
        model3.addColumn("Nombre");
        Tabladesconocidos.setModel(model3);
        cargarTablaDesconocidos(nombre);
    }
    private void cargarTablaAmigos(String nombre) {
        //Tabla amigos
        model.setRowCount(0);

        //consulta
        nombre = nombre.replace(' ', '_');
        Query archivo = new Query("consult('Nombres.pl')");

        if (archivo.hasSolution()) {
            Query consulta = new Query("persona(" + nombre + ",_,Amigos,_,_)");
            // Obtener la lista de amigos como un array de términos
            Term[] amigosTerms = consulta.oneSolution().get("Amigos").toTermArray();

            // Convertir cada término a una cadena y almacenarlo en el array
            for (int i = 0; i < amigosTerms.length; i++) {
                Vector fila = new Vector();
                fila.add(amigosTerms[i].toString().replace('_', ' '));
                model.addRow(fila);
            }
            consulta.close();
        }
        archivo.close();
    }

    private void cargarTablaAmigosEnComun(String nombre, String amigo) {
        //Tabla amigos
        model2.setRowCount(0);

        //consulta
        nombre = nombre.replace(' ', '_');
        amigo = amigo.replace(' ', '_');
        
        Query archivo = new Query("consult('Nombres.pl')");

        if (archivo.hasSolution()) {
            Query consulta = new Query("amigoEnComun(" + nombre + "," + amigo + ",AmigosComun)");
            // Obtener la lista de amigos como un array de términos
            Term[] amigosTerms = consulta.oneSolution().get("AmigosComun").toTermArray();

            // Convertir cada término a una cadena y almacenarlo en el array
            for (int i = 0; i < amigosTerms.length; i++) {
                Vector fila = new Vector();
                fila.add(amigosTerms[i].toString().replace('_', ' '));
                model2.addRow(fila);
            }

            consulta.close();
        }
        archivo.close();
    }
    private void cargarTablaDesconocidos(String nombre) {
        //Tabla amigos
        model3.setRowCount(0);

        //consulta
        nombre = nombre.replace(' ', '_');
        
        
        Query archivo = new Query("consult('Nombres.pl')");

        if (archivo.hasSolution()) {
            Query consulta = new Query("personasNoAmigas("+nombre+",Personas)");
            // Obtener la lista de amigos como un array de términos
            Term[] amigosTerms = consulta.oneSolution().get("Personas").toTermArray();

            // Convertir cada término a una cadena y almacenarlo en el array
            for (int i = 0; i < amigosTerms.length; i++) {
                Vector fila = new Vector();
                fila.add(amigosTerms[i].toString().replace('_', ' '));
                model3.addRow(fila);
            }

            consulta.close();
        }
        archivo.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (opciones == e.getSource()) {
            Object[] options = {"Cambiar Foto", "Cambiar Descripción", "Cerrar Sesión"};
            int selection = JOptionPane.showOptionDialog(null, "Selecciona una opción", "Configuración",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[2]);
            switch (selection) {
                case 0:

                    Fotos marco = new Fotos();
                    ventana.dispose();
                    break;
                case 1:

                    String descripcion = JOptionPane.showInputDialog(null, "Escriba su descripción:");
                    String cambio = descripcion.replace(' ', '_').toLowerCase();
                    //tuvariablededescripcion.setText(descripcion);
                    //remplazar la persona desde (],) hasta (,)
                    break;
                case 2:
                    Login ven = new Login();
                    //ventana.dispose();
                    break;
                default:
                    break;
            }
        }
        if (e.getSource() == eliminarAmigo) {
            funciones.eliminarAmigo(MiNombre.getText(), nombreAmigo.getText());
            cargarTablaAmigos(MiNombre.getText());
            cargarTablaDesconocidos(MiNombre.getText());
            nombreAmigo.setText("");
            descripAmigo.setText("");
            imagenAmigo.setIcon(null);
            eliminarAmigo.setVisible(false);
            ventana.repaint();
        }
        if (e.getSource() == agregarAmigo) {
            funciones.agregarAmigo(MiNombre.getText(), nombreAmigo.getText());
            cargarTablaAmigos(MiNombre.getText());
            cargarTablaDesconocidos(MiNombre.getText());
            
            eliminarAmigo.setVisible(true);
            agregarAmigo.setVisible(false);
            ventana.repaint();
        }
        
    }
}
