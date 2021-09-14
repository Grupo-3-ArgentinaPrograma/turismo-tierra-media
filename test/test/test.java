package test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import tierramedia.AdministradorDeArchivos;
import tierramedia.Atraccion;
import tierramedia.ComparadorDeProductos;
import tierramedia.Producto;
import tierramedia.Promo;
import tierramedia.PromoAbsoluta;
import tierramedia.PromoAxB;
import tierramedia.PromoPorcentual;
import tierramedia.TipoAtraccion;
import tierramedia.Usuario;

public class test {

	@Test
	public void obtenerCostoAtraccion() {
		Atraccion atraccion = new Atraccion("Arenas", 6, 7d, 9, TipoAtraccion.AVENTURA);
		assertEquals(6,atraccion.getPrecio(),0);
	}
	
	@Test
	public void obtenerCostoPromocion() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		Atraccion atraccion = new Atraccion("Playa", 20, 6d, 11, TipoAtraccion.PAISAJE);
		Atraccion atraccion2 = new Atraccion("Cielo", 4, 5d, 8, TipoAtraccion.PAISAJE);
		Atraccion atraccion3 = new Atraccion("Arenas", 5, 7d, 9, TipoAtraccion.AVENTURA);
		
		atracciones.add(atraccion);
		atracciones.add(atraccion2);
		atracciones.add(atraccion3);
	
		String[] nombres = {"Playa","Cielo"};
		PromoPorcentual promo = new PromoPorcentual(TipoAtraccion.PAISAJE, "Porcentual", nombres,20);
		promo.establecerPrecioPromo(promo, atracciones);
		
		assertEquals(20,promo.getPrecio(),0);
	}
	
	@Test
	public void obtenerTiempoAtraccion() {
		Atraccion atraccion = new Atraccion("Arenas", 9, 7d, 9, TipoAtraccion.AVENTURA);
		assertEquals(7,atraccion.getTiempo(),0);
	}
	
	@Test
	public void obtenerTiempoPromocion() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		Atraccion atraccion = new Atraccion("Playa", 10, 6d, 11, TipoAtraccion.PAISAJE);
		Atraccion atraccion2 = new Atraccion("Cielo", 4, 5d, 8, TipoAtraccion.PAISAJE);
		Atraccion atraccion3 = new Atraccion("Arenas", 9, 7d, 9, TipoAtraccion.AVENTURA);
		
		atracciones.add(atraccion);
		atracciones.add(atraccion2);
		atracciones.add(atraccion3);
	
		String[] nombres = {"Playa","Cielo"};
		PromoPorcentual promo = new PromoPorcentual(TipoAtraccion.PAISAJE, "Porcentual", nombres,20);
		promo.establecerHsPromo(promo, atracciones);
		
		assertEquals(11,promo.getTiempo(),0);
		
	}
	
	@Test
	public void obtenerTipoAtraccion() {
		Atraccion atraccion1 = new Atraccion("Playa", 10, 6d, 11, TipoAtraccion.PAISAJE);
		
		assertEquals(TipoAtraccion.PAISAJE,atraccion1.getTipo());
		
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		Atraccion atraccion = new Atraccion("Playa", 10, 6d, 11, TipoAtraccion.PAISAJE);
		Atraccion atraccion2 = new Atraccion("Cielo", 4, 5d, 8, TipoAtraccion.PAISAJE);
		Atraccion atraccion3 = new Atraccion("Arenas", 9, 7d, 9, TipoAtraccion.AVENTURA);
		
		atracciones.add(atraccion);
		atracciones.add(atraccion2);
		atracciones.add(atraccion3);
	
		String[] nombres = {"Playa","Cielo"};
		PromoPorcentual promo = new PromoPorcentual(TipoAtraccion.PAISAJE, "Porcentual", nombres,20);
		
		promo.establecerHsPromo(promo, atracciones);
		promo.establecerPrecioPromo(promo, atracciones);
		
		assertEquals(TipoAtraccion.PAISAJE,promo.getTipo());
		
	}
	
	@Test
	public void usuarioPuedeComprar() {
		Usuario usuario = new Usuario("Pepito",TipoAtraccion.AVENTURA,100,100d);
		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		
		Atraccion atraccion1 = new Atraccion("Playa", 10, 3d, 11, TipoAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Cielo", 4, 2.5d, 8, TipoAtraccion.AVENTURA);
		Atraccion atraccion3 = new Atraccion("Arenas", 2, 1d, 9, TipoAtraccion.AVENTURA);
		Atraccion atraccion4 = new Atraccion("BLABLA", 8, 4d, 6, TipoAtraccion.AVENTURA);
		
		atracciones.add(atraccion1);
		atracciones.add(atraccion2);
		atracciones.add(atraccion3);
		atracciones.add(atraccion4);
		
		PromoAbsoluta p1 = new PromoAbsoluta(TipoAtraccion.AVENTURA,"Absoluta",new String[] {"Playa","Cielo"});
		p1.establecerHsPromo(p1, atracciones);
		p1.establecerPrecioPromo(p1, atracciones);
		
		PromoPorcentual p2 = new PromoPorcentual(TipoAtraccion.AVENTURA, "Porcentual", new String[] {"Playa","Arenas"},30);
		p2.establecerHsPromo(p2, atracciones);
		p2.establecerPrecioPromo(p2, atracciones);
		
		PromoAxB p3 = new PromoAxB(TipoAtraccion.AVENTURA,"AxB",new String[] {"Playa","Arenas","Cielo"});
		p3.establecerHsPromo(p3, atracciones);
		p3.establecerPrecioPromo(p3, atracciones);
		
		PromoAbsoluta p4 = new PromoAbsoluta(TipoAtraccion.AVENTURA,"Absoluta",new String[] {"Cielo","BLABLA"});
		p4.establecerHsPromo(p4, atracciones);
		p4.establecerPrecioPromo(p4, atracciones);
		
		/*usuario.comprar(atraccion1);
		assertFalse(usuario.puedeComprar(p1)); */
		
		/*usuario.comprar(atracciones,p1);
		assertFalse(usuario.puedeComprar(atraccion2));*/
		
		/*usuario.comprar(atracciones,p1);
		assertFalse(usuario.puedeComprar(p2));*/
		     
		usuario.comprar(atracciones,p3);
		assertFalse(usuario.puedeComprar(p4));
	}
	
	@Test
	public void ordenarPorPreferencia() {
		Usuario usuario = new Usuario("Martin",TipoAtraccion.AVENTURA,20,15d);
		List<Atraccion> atracciones = new LinkedList<Atraccion>(AdministradorDeArchivos.leerAtracciones());
		List<Promo> promociones = new LinkedList<Promo>(AdministradorDeArchivos.leerPromociones());
		for(Promo promo : promociones) {
			//Establece las horas totales de una promo (suma tiempos de cada atraccion)
			 promo.establecerHsPromo(promo, atracciones);
			//Establece el precio total de una promo (axb,absoluta o porcentual) (suma precios de cada atraccion)
			 promo.establecerPrecioPromo(promo, atracciones);
		}
		
		Collections.sort(promociones,new ComparadorDeProductos(usuario.getAtraccionPreferida()));
		Collections.sort(atracciones,new ComparadorDeProductos(usuario.getAtraccionPreferida()));
		
		List<Producto> productos = new LinkedList<Producto>(promociones); 
		productos.addAll(atracciones);
		
		usuario.sugerencia(productos);
	}
	@Test
	public void resumenItinerarioUser() {
		Usuario usuario = new Usuario("Julio",TipoAtraccion.PAISAJE,50,30d);
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		Atraccion atraccion1 = new Atraccion("Playa", 10, 6d, 11, TipoAtraccion.PAISAJE);
		Atraccion atraccion2 = new Atraccion("Cielo", 4, 5d, 8, TipoAtraccion.PAISAJE);
		Atraccion atraccion3 = new Atraccion("Arenas", 9, 7d, 9, TipoAtraccion.AVENTURA);
		
		atracciones.add(atraccion1);
		atracciones.add(atraccion2);
		atracciones.add(atraccion3);
	
		String[] nombres = {"Playa","Cielo"};
		PromoPorcentual promo = new PromoPorcentual(TipoAtraccion.PAISAJE, "Porcentual", nombres,20);
		
		promo.establecerHsPromo(promo, atracciones);
		promo.establecerPrecioPromo(promo, atracciones);
		
		usuario.comprar(atracciones,promo);//comprando la promo se le aplica el 20% desc
		assertEquals(11d,promo.getTiempo(),0);
		assertEquals(12,promo.getPrecio(),0); 
		
		usuario.comprar(atraccion3);
		assertEquals(7d,atraccion3.getTiempo(),0);
		assertEquals(9,atraccion3.getPrecio(),0); 
		
		System.out.println("############ PRUEBA TEST resumenItinerarioUser #################");
		usuario.datosUsuario();
		System.out.println("#################################################################\n\n");
	}

}
