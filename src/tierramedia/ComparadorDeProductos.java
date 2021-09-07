package tierramedia;

import java.util.Comparator;

public class ComparadorDeProductos implements Comparator<Producto>{
	private TipoAtraccion preferencia;//prioridad en el tipo de preferencia de cada User
	
	public ComparadorDeProductos(TipoAtraccion preferencia) {
		this.preferencia = preferencia;
	}
	
	@Override
	public int compare(Producto p1, Producto p2) {	
		//prioriso la preferencia del usuario
		if(this.preferencia == p1.getTipo()
				&& this.preferencia != p2.getTipo()) 
			return -1;
		else if(this.preferencia != p1.getTipo() 
				&& this.preferencia == p2.getTipo()) 
			return 1;
		else { 
			//ordeno por atracciones mas caras
			if (p1.getPrecio() != p2.getPrecio())
				return -1*Integer.compare(p1.getPrecio(), p2.getPrecio());
			else//si el precio de p1 y p2 son iguales, ordeno por tiempo mas elevado
				return -1*Double.compare(p1.getTiempo(), p2.getTiempo());
		} 
	}	
}
