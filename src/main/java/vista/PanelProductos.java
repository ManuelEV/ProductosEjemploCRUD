package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.io.*;
import java.util.*;
import javax.swing.BorderFactory;
import modelo.ControladorProducto;

public class PanelProductos extends JPanel{

    private int cantidadRegistros;
    private File fichero;
    private ArrayList<JButton> editar;
    private ArrayList<JButton> eliminar;
    private ArrayList<JTextField> nombre;
    private ArrayList<JTextField> ultimaModificacion;

    public PanelProductos() {
        inicializar();
    }

    //Set up
    private void inicializar() {
        
        this.setBorder(BorderFactory.createTitledBorder("Productos"));
        
        this.fichero = new File("src/ficheros/productos.txt");
        ControladorProducto controlador = new ControladorProducto(fichero);
        
        this.eliminar = new ArrayList<>();
        this.editar = new ArrayList<>();
        this.nombre = controlador.obtenerNombreProductos();
        this.ultimaModificacion = controlador.obtenerFechaProductos();
        
        this.cantidadRegistros = controlador.getCantidadRegistros();
        int ancho = 4;
        int alto = cantidadRegistros;
        this.setLayout(new GridLayout(alto, ancho));
        
        //Se agregan los elementos al panel
        for (int i = 0; i < cantidadRegistros; i++) {
            JButton elim = new JButton("Eliminar");
            this.eliminar.add(elim);
            JButton edit = new JButton("Editar");
            this.editar.add(edit);
            
            this.add(this.nombre.get(i));
            this.add(this.ultimaModificacion.get(i));
            this.add(this.eliminar.get(i));
            this.add(this.editar.get(i));
            
        }
    }

    /*
    Métodos para manejar estos eventos en la clase Ventana (para poder hacer un "refresh" automático)
    */
    
    public ArrayList<JButton> getEditar() {
        return editar;
    }

    public ArrayList<JButton> getEliminar() {
        return eliminar;
    }

    public ArrayList<JTextField> getNombre() {
        return nombre;
    }

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public ArrayList<JTextField> getUltimaModificacion() {
        return ultimaModificacion;
    }

    
}
