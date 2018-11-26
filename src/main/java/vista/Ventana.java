package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ControladorProducto;

public class Ventana extends JFrame implements ActionListener {

    private PanelInferior panelInferior;
    private PanelProductos panelProductos;
    private int cantRegistros;

    public Ventana() {
        inicializar();
    }

    //Set up
    private void inicializar() {
        this.panelInferior = new PanelInferior();
        this.panelProductos = new PanelProductos();
        this.add(this.panelProductos, BorderLayout.CENTER);
        this.add(this.panelInferior, BorderLayout.SOUTH);

        this.setTitle("Gestor de productos");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(420, 100);

        this.cantRegistros = this.panelProductos.getNombre().size();
        
        this.panelInferior.getActualizar().addActionListener(this);

        for (int i = 0; i < cantRegistros; i++) {
            this.panelProductos.getEditar().get(i).addActionListener(this);
            this.panelProductos.getEliminar().get(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //Botón actualizar
        if (this.panelInferior.getActualizar() == ae.getSource()) {
            refresh();
        }
        
        File fichero = new File("src/ficheros/productos.txt");
        ControladorProducto controlador = new ControladorProducto(fichero);

        //Botones eliminar y editar
        for (int i = 0; i < this.cantRegistros; i++) { 
            if (this.panelProductos.getEditar().get(i) == ae.getSource()) {
                String nombrePrevio = this.panelProductos.getNombre().get(i).getText();

                String opcion = opcionEditar(fichero, controlador, nombrePrevio);

                controlador.editarProducto(i, opcion);
                refresh();
            } else if (this.panelProductos.getEliminar().get(i) == ae.getSource()) {
                controlador.eliminarProducto(i);
                refresh();
            }
        }
    }

    //Re-inicializa la ventana para ver los cambios
    public void refresh() {
        this.dispose();
        Ventana v = new Ventana();
    }

    //Mensajes de diálogo para el usuario. Perfectamente puede hacerse en el controlador
    public String opcionEditar(File fichero, ControladorProducto controlador, String nombrePrevio) {
        String seleccion = JOptionPane.showInputDialog(
                "Escriba el nuevo nombre del producto");

        if (seleccion == null || seleccion.equals("")) {
            JOptionPane.showMessageDialog(null, "Input no válido", "Error", JOptionPane.ERROR_MESSAGE);
            seleccion = nombrePrevio;
        } else {
            controlador.agregarProducto(seleccion);
            JOptionPane.showMessageDialog(null, "Producto editado correctamente");
        }
        return seleccion;
    }
}
