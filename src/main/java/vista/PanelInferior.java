package vista;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import modelo.ControladorProducto;

public class PanelInferior extends JPanel implements ActionListener {

    private JButton agregar;
    private JButton actualizar;
    private File fichero;

    public PanelInferior() {
        inicializar();
    }

    //Set up
    private void inicializar() {
        this.agregar = new JButton("Agregar");
        this.add(this.agregar);
        this.agregar.addActionListener(this);
        this.actualizar = new JButton("Actualizar productos");
        this.add(this.actualizar);
        this.actualizar.addActionListener(this);

        this.fichero = new File("src/ficheros/productos.txt");
    }

    //Manejo de eventos
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.agregar == ae.getSource()) {
            
            String seleccion = inputProducto();

            //Error en el input
            if (seleccion == null || seleccion.equals("")) {
                mostrarMensajeError();
            //No hay error -> guardar producto en el txt
            } else {
                agregarProducto(seleccion);
            }
        }
    }

    //Input para el nombre del producto
    public String inputProducto() {
        String seleccion;
        seleccion = JOptionPane.showInputDialog("Escriba el nombre del producto a agregar"
                + "\nPara ver el producto en el panel, actualice la página");
        return seleccion;
    }

    //Dialogo de error
    public void mostrarMensajeError() {
        JOptionPane.showMessageDialog(null, "Input no válido", "Error", JOptionPane.ERROR_MESSAGE);
    }

    //Agrega el producto al archivo de texto a través del controlador
    public void agregarProducto(String seleccion) {
        ControladorProducto controlador = new ControladorProducto(fichero);

        controlador.agregarProducto(seleccion);
        JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
    }

    //Para poder usarlo en la clase Ventana y así manejar el evento
    public JButton getActualizar() {
        return actualizar;
    }

}
