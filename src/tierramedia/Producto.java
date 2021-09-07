package tierramedia;

public abstract class Producto{
	protected TipoAtraccion tipo;
	protected String nombre;
	protected Integer precio;
	protected Double tiempo;
	
	public Producto(TipoAtraccion tipo, String nombre, Integer precio, Double tiempo) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.precio = precio;
		this.tiempo = tiempo;
	}
	
	public TipoAtraccion getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Double getTiempo() {
		return tiempo;
	}

	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}	
}
