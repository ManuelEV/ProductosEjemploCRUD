package modelo;

import java.io.File;
import java.util.*;
import gestionarchivo.GestorArchivo;
import javax.swing.*;

public class ControladorProducto {

    private final File fichero;
    private final ArrayList<Producto> productos;

    public ControladorProducto(File fichero) {
        this.fichero = fichero;
        this.productos = iniciarProductos();
    }
    
    public int getCantidadRegistros(){
        return productos.size();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    //Obtiene la lista de todos los productos desde el fichero
    private ArrayList<Producto> iniciarProductos(){
        GestorArchivo gestor = new GestorArchivo();
        return gestor.leerArchivo(this.fichero);
    }

    //Obtiene lista de nombres en el archivo txt para utilizarlos en el panel
    public ArrayList<JTextField> obtenerNombreProductos() {
        ArrayList<JTextField> nombres = new ArrayList<>();
        for (Producto producto : productos) {
            JTextField nombre = new JTextField(producto.getNombre());
            nombre.setEditable(false);
            nombres.add(nombre);
        }
        return nombres;
    }
    
    //Obtiene lista de fechas en el archivo txt para utilizarlos en el panel
    public ArrayList<JTextField> obtenerFechaProductos() {
        ArrayList<JTextField> fechas = new ArrayList<>();
        for (Producto fecha : productos) {
            JTextField nombre = new JTextField(fecha.getFechaModificacion());
            nombre.setEditable(false);
            fechas.add(nombre);
        }
        return fechas;
    }

    //Agrega un producto al fichero
    public void agregarProducto(String nombreProducto) {
        Producto p = new Producto();
        p.setNombre(nombreProducto);
        Date now = new Date();
        p.setFechaModificacion(now.toString());
        this.productos.add(p);
        GestorArchivo gestor = new GestorArchivo();
        gestor.escribirFichero(this.fichero, this.productos);
    }

    //Edita un producto según su índice en el ArrayList
    public void editarProducto(int indice, String nuevoNombre) {
        Producto newProducto = new Producto();
        newProducto.setNombre(nuevoNombre);
        Date now = new Date();
        newProducto.setFechaModificacion(now.toString());
        
        this.productos.set(indice, newProducto);
        //ojo, se elimina el ultimo porque se agrega uno duplicado al final
        this.productos.remove(productos.size()-1);
        
        GestorArchivo gestor = new GestorArchivo();
        gestor.escribirFichero(this.fichero, this.productos); 
    }

    //Elimina un producto según su índice en el ArrayList
    public void eliminarProducto(int indice) {
        this.productos.remove(indice);
        
        GestorArchivo gestor = new GestorArchivo();
        gestor.escribirFichero(this.fichero, this.productos); 
    }
}
