package tierramedia;

import java.util.List;

public class PromoAbsoluta extends Promo{
	private static final Integer REBAJA = 8;
	
	public PromoAbsoluta(TipoAtraccion nombrePack, String nombrePromo, String[] nombres_atracciones) {
		super(nombrePack, nombrePromo, nombres_atracciones, 0, 0d);
	}
	//devuelve la suma de las atracciones compradas, menos la rebaja
	@Override
	public Integer precio(List<Atraccion> atracciones) {
		Integer resultado = 0;
		for(String nombre_atraccion : nombres_atracciones) {
			for (Atraccion atraccion : atracciones) {
				if (atraccion.getNombre().equals(nombre_atraccion)) {
					 resultado += atraccion.getPrecio();
					 break;
				}
			}
		}
		return resultado - REBAJA;	
	}
}
