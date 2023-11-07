package conexionprologjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.jpl7.*;

public class funciones {

    public static void consultarProlog() {
        Query consulta = new Query("consult('Nombres.pl')");
        if (consulta.hasSolution()) {
            Query q = new Query("persona(Nombre, Sexo, Amigos)");
            while (q.hasMoreSolutions()) {
                java.util.Map<String, Term> solution = q.nextSolution();
                Term nombre = solution.get("Nombre");
                Term sexo = solution.get("Sexo");
                Term amigos = solution.get("Amigos");
                System.out.println("Nombre: " + nombre + ", Sexo: " + sexo + ", Amigos: " + amigos);
            }
        }
    }

    public static String verAmigos(String persona) {
        Query consulta = new Query("consult('Nombres.pl')");
        if (consulta.hasSolution()) {
            Query q = new Query("persona(" + persona + ",_,Amigos)");
            java.util.Map<String, Term> solution = q.nextSolution();
            Term amigos = solution.get("Amigos");
            return amigos.toString();
        }
        return "";
    }

    public static void agregarAmigo(String nombrePersona, String nuevoAmigo) {
        if (nombrePersona.equals(nuevoAmigo)) {
            System.out.println("No te puedes añadir a ti mismo");
        } else {
            String nombreArchivo = "Nombres.pl"; // Reemplaza con el nombre de tu archivo

            // Leer el archivo Prolog y obtener el hecho existente
            StringBuilder contenido = new StringBuilder();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
                String linea;
                while ((linea = reader.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                reader.close();

                // Buscar la posición donde se encuentra la lista de amigos de la persona especificada
                int index = contenido.indexOf("persona(" + nombrePersona);
                if (index != -1) {

                    index = contenido.indexOf("[", index) + 1;

                    if (contenido.charAt(index) != ']') {
                        contenido.insert(index, nuevoAmigo + ",");
                    } else {
                        contenido.insert(index, nuevoAmigo);
                    }

                } else {
                    System.out.println("No se ha encontrado la persona especificada en el archivo Prolog.");
                    return;
                }

                // Escribir el contenido actualizado de vuelta al archivo
                FileWriter writer = new FileWriter(nombreArchivo);
                writer.write(contenido.toString());
                writer.close();

                System.out.println("Se ha actualizado el archivo Prolog correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al agregar el amigo.");
            }
        }
    }

    public static void eliminarAmigo(String nombrePersona, String AmigoAeliminar) {

        String nombreArchivo = "Nombres.pl"; // Reemplaza con el nombre de tu archivo

        // Leer el archivo Prolog y obtener el hecho existente
        StringBuilder contenido = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            reader.close();

            // Buscar la posición donde se encuentra la lista de amigos de la persona especificada
            int index = contenido.indexOf("persona(" + nombrePersona);
            int end;
            if (index != -1) {

                index = contenido.indexOf(AmigoAeliminar, index);
                end = index + AmigoAeliminar.length();

                if (index != -1) {
                    if (contenido.charAt(end + 1) != ',') {
                        if (contenido.charAt(index - 1) != '[') {
                            contenido.delete(index - 1, end);
                        } else {
                            contenido.delete(index, end);
                        }
                    } else {
                        contenido.delete(index, end + 1);
                    }

                } else {
                    System.out.println("No se ha encontrado el amigo a eliminar.");
                }

            } else {
                System.out.println("No se ha encontrado la persona especificada en el archivo Prolog.");
                return;
            }

            // Escribir el contenido actualizado de vuelta al archivo
            FileWriter writer = new FileWriter(nombreArchivo);
            writer.write(contenido.toString());
            writer.close();

            System.out.println("Se ha actualizado el archivo Prolog correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al agregar el amigo.");
        }
    }

    public static void agregarPersona(String nuevaPersona, String sexo) {

        //persona(nombre,sexo ,[amigos,null]).
        // Leer el contenido original del archivo
        File archivoOriginal = new File("Nombres.pl");
        StringBuilder contenido = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivoOriginal));
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Agregar el nuevo hecho al contenido existente
        String facto = "persona(" + nuevaPersona + ", " + sexo + ", []).";
        contenido.append(facto).append("\n");

        // Escribir el contenido actualizado de vuelta al archivo
        File archivoNuevo = new File("Nombres.pl");
        try {
            FileWriter writer = new FileWriter(archivoNuevo);
            writer.write(contenido.toString());
            writer.close();
            System.out.println("Se ha actualizado el archivo Prolog correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarPersona(String nombrePersona) {
        // Leer el contenido original del archivo
        File archivoOriginal = new File("Nombres.pl");
        StringBuilder contenido = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivoOriginal));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.contains("persona(" + nombrePersona)) {
                    contenido.append(linea).append("\n");
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Escribir el contenido actualizado de vuelta al archivo
        File archivoNuevo = new File("Nombres.pl");
        try {
            FileWriter writer = new FileWriter(archivoNuevo);
            writer.write(contenido.toString());
            writer.close();
            System.out.println("Se ha eliminado la persona del archivo Prolog correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
