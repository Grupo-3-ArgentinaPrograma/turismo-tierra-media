package tierramedia;

import java.util.List;

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
	
	public Boolean contieneAtraccion(List<Producto> compras) {
		if (this instanceof Promo) {
			for (Producto compra : compras) {
				if (compra instanceof Promo) { // Promo - Promo
					for (String nombreP : ((Promo) this).getNombres_atracciones()) {
						for (String nombreC : ((Promo) compra).getNombres_atracciones()) {
							if ( nombreP.equals(nombreC) ) {
								return true;	
							}
						}
						
					}					
				} else { // Promo - Atraccion
					for (String nombreP : ((Promo) this).getNombres_atracciones()) {
						if ( nombreP.equals(((Atraccion) compra).getNombre()) ) {
							return true;	
						}
					}
				}
			}
		} else {
			for (Producto compra : compras) {
				if (compra instanceof Promo) { // Atraccion - Promo
					for (String nombreC : ((Promo) compra).getNombres_atracciones()) {
						if ( nombreC.equals( ((Atraccion) this).getNombre() ) ) {
							return true;	
						}
					}					
				} else { // Atraccion - Atraccion
					if ( ((Atraccion) compra).getNombre().equals( ((Atraccion) this).getNombre() ) ) {
							return true;	
						}
					
				}
			}
		}
		return false; //no contiene la atraccion
	}
}
