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
	//si el user ya compro una atraccion o promo
	@Test
	public void usuarioContieneAtraccion() {
		Usuario usuario = new Usuario("Julio",TipoAtraccion.PAISAJE,50,30d);
		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		
		Atraccion atraccion1 = new Atraccion("Playa", 10, 6d, 11, TipoAtraccion.PAISAJE);
		Atraccion atraccion4 = new Atraccion("Cielo", 4, 5d, 8, TipoAtraccion.PAISAJE);
		Atraccion atraccion2 = new Atraccion("Arenas", 9, 7d, 9, TipoAtraccion.AVENTURA);
		Atraccion atraccion3 = new Atraccion("BLABLA", 4, 5d, 6, TipoAtraccion.AVENTURA);
		
		atracciones.add(atraccion1);
		atracciones.add(atraccion2);
		atracciones.add(atraccion3);
		atracciones.add(atraccion4);
		
		String[] nombres = {"Playa","Cielo"};
		PromoPorcentual promo = new PromoPorcentual(TipoAtraccion.PAISAJE, "Porcentual", nombres,20);	
		promo.establecerHsPromo(promo, atracciones);
		promo.establecerPrecioPromo(promo, atracciones);
		
		assertFalse(promo.contieneAtraccion(usuario.getCompras()));
		
		usuario.comprar(atracciones,promo);
		
		assertTrue(promo.contieneAtraccion(usuario.getCompras()));
		
		//el user ya compro estas 2 atracciones en la promo
		assertTrue(atraccion1.contieneAtraccion(usuario.getCompras()));
		assertTrue(atraccion4.contieneAtraccion(usuario.getCompras()));
		//pero estas 2 no las compro aun
		assertFalse(atraccion2.contieneAtraccion(usuario.getCompras()));
		assertFalse(atraccion3.contieneAtraccion(usuario.getCompras()));
		
		usuario.comprar(atraccion2);
		
		assertTrue(atraccion2.contieneAtraccion(usuario.getCompras()));
		//da true xq el user no compro la atraccion3
		assertFalse(atraccion3.contieneAtraccion(usuario.getCompras()));
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
