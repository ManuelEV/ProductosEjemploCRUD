package modelo;


//Esta clase representa el modelo de lo que se guardará en el archivo txt
public class Producto {
	private String nombre;
	private String fechaModificacion;

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
}