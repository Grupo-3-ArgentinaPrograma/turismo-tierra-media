package tierramedia;

import java.util.List;

public class PromoAxB extends Promo{

	public PromoAxB(TipoAtraccion nombrePack, String nombrePromo, String[] nombres_atracciones) {
		super(nombrePack, nombrePromo, nombres_atracciones, 0, 0d);
	}
	//devuelve la suma de precios de las atracciones compradas, menos la ult que es gratis
	@Override
	public Integer precio(List<Atraccion> atracciones) {
		Integer resultado = 0;
		for ( int i = 0; i<nombres_atracciones.length -1 ; i++ ) {
			for (Atraccion atraccion : atracciones) {
				if (atraccion.getNombre().equals(nombres_atracciones[i])) {
					 resultado += atraccion.getPrecio();
					 break;
				}
			}
		}
		return resultado;
	}
}
