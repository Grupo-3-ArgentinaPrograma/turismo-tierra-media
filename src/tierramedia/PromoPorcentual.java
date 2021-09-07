package tierramedia;

import java.util.List;

public class PromoPorcentual extends Promo{
	private Integer porcentajeDescuento;

	public PromoPorcentual(TipoAtraccion nombrePack, String nombrePromo, String[] nombres_atracciones, Integer porcentajeDescuento) {
		super(nombrePack, nombrePromo, nombres_atracciones, 0, 0d);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	//comprando 2 o mas sacar un porcentaje
	@Override
	public Integer precio(List<Atraccion> atracciones) {
		Integer resultado = 0;
		for(String nombre_atraccion : nombres_atracciones) {
			 //buscar atraccion en atracciones, si esta lo sumo su tiempo
			for (Atraccion atraccion : atracciones) {
				if (atraccion.getNombre().equals(nombre_atraccion)) {
					 resultado += atraccion.getPrecio();
					 break;
				}
			}
		}
		return resultado - ((resultado * porcentajeDescuento) / 100);
	}
}
