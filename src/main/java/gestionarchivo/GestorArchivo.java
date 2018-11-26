package gestionarchivo;

import java.io.*;
import java.util.*;
import modelo.Producto;

public class GestorArchivo {

    public ArrayList<Producto> leerArchivo(File fichero) {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            //Si existe el fichero
            if (fichero.exists()) {
                //Abre un flujo de lectura a el fichero
                BufferedReader bReader = new BufferedReader(new FileReader(fichero));
                String linea;

                //Lee el fichero linea a linea hasta llegar a la ultima
                while ((linea = bReader.readLine()) != null) {
                    if (!linea.equals("")) {
                        String[] separado = linea.split(";");
                        Producto prod = new Producto();
                        prod.setNombre(separado[0]);
                        prod.setFechaModificacion(separado[1]);
                        productos.add(prod);
                    }
                }
                
                /*Cierra el flujo*/
                bReader.close();
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            /*Captura un posible error y le imprime en pantalla*/
            System.out.println(ex.getMessage());
        }
        return productos;
    }

    public void escribirFichero(File fichero, ArrayList<Producto> productos) {
        try {
            fichero.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));

            for (Producto producto : productos) {
                String nombre = producto.getNombre();
                String fecha = producto.getFechaModificacion().toString();
                
                bw.write(nombre + ";" + fecha + "\n");
            }

            bw.close();

        } catch (Exception ex) {
            //Captura un posible error le imprime en pantalla 
            System.out.println(ex.getMessage());
        }
    }
}
