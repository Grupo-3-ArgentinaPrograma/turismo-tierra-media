package tierramedia;

import java.util.Arrays;
import java.util.List;

public abstract class Promo extends Producto{
	protected String[] nombres_atracciones;
	//valor : si es gratis, porcentual o absoluto
	public Promo(TipoAtraccion nombrePack, String nombrePromo, String[] nombres_atracciones,Integer valor,Double duracionTotal) {
		super(nombrePack,nombrePromo,valor,duracionTotal);
		this.nombres_atracciones = nombres_atracciones;
	}
	
	public abstract Integer precio(List<Atraccion> atracciones);
		
	public void setCupos(List<Atraccion> atracciones, String[] elementos) {
		for (Atraccion atraccion : atracciones){
			for (String elemento : elementos) {
				if (elemento.equals(atraccion.getNombre())) {
					atraccion.setCupo(atraccion.getCupo() - 1);
					break;
				}
			}
		}
	}
	
	public Boolean hayCupo(List<Atraccion> atracciones, String[] elementos) {
		for (Atraccion atraccion : atracciones){
			for (String elemento : elementos) {
				if (elemento.equals(atraccion.getNombre())) {
					if (!atraccion.hayCupo()) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void establecerHsPromo(Promo promo,List<Atraccion> atracciones) {
		Double sumaTiempos = 0d;
		 for(String nombre_atraccion : promo.getNombres_atracciones()) {
			 //filtro 
			 for (Atraccion atraccion : atracciones) {
				 if (atraccion.getNombre().equals(nombre_atraccion)) {
				 	sumaTiempos += atraccion.getTiempo();
				 	break;
				 }
			 }
		 }
		 promo.setTiempo(sumaTiempos);
	}
	
	public void establecerPrecioPromo(Promo promo,List<Atraccion> atracciones) {
		promo.setPrecio(promo.precio(atracciones));
	}
	
	public String[] getNombres_atracciones() {
		return nombres_atracciones;
	}

	@Override
	public String toString() {
		return "Promo ( Pack: "+ nombre + " Tipo de Promo : " + tipo +  ", Atracciones incluidas : " + Arrays.toString(nombres_atracciones) + ", Precio : " + precio + ", Tiempo total : " + tiempo + ")";
	}
	
	public String nombresAtracciones() {
		return Arrays.toString(nombres_atracciones);
	}
}
