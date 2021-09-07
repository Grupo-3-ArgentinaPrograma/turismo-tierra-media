package tierramedia;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
	
	public static void establecerHsPromo() {
		 for(Promo promo : promociones) {
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
	}
	
	public static void establecerPrecioPromo() {
		for(Promo promo : promociones) {
			 promo.setPrecio(promo.precio(atracciones));
		 }
	}
	
	public static void leerArchivos() {
		usuarios = AdministradorDeArchivos.leerUsuarios();
		atracciones = AdministradorDeArchivos.leerAtracciones();
		promociones = AdministradorDeArchivos.leerPromociones();
	}
	
	
	private static List<Usuario> usuarios;
	private static List<Atraccion> atracciones;
	private static List<Promo> promociones;
	private static List<Producto> productos;

	private static int dato;
	
	public static void main(String[] args) throws IOException{
		 Scanner sc = new Scanner(System.in);
		 
		 usuarios = new LinkedList<Usuario>();
		 atracciones = new LinkedList<Atraccion>();
		 promociones = new LinkedList<Promo>();
		 leerArchivos();
		 //Establece las horas totales de una promo (suma tiempos de cada atraccion)
		 establecerHsPromo();
		//Establece el precio total de una promo (axb,absoluta o porcentual) (suma precios de cada atraccion)
		 establecerPrecioPromo();
		 
		 //Interaccion con cada usuario
		 for (Usuario usuario : usuarios) {
			 //ordeno segun la preferencia del user
			 Collections.sort(promociones,new ComparadorDeProductos(usuario.getAtraccionPreferida()));
			 Collections.sort(atracciones,new ComparadorDeProductos(usuario.getAtraccionPreferida()));
			 
			 productos = new LinkedList<Producto>(promociones);//lista de promos
			 productos.addAll(atracciones);//lista de promos + lista de atracciones --> lista de productos(ordenada)
			 
			 usuario.datosUsuario(); //muestro al user con sus datos iniciales
			 usuario.sugerencia(productos); //genero sugeencia para usuario (itinerario)
			 
			 for (Producto producto : productos) {
				 //si el user tiene monedas y tiempo y si hay cupos discponibles
				 if ( usuario.tieneMonedas() && usuario.tieneTiempo() && ((producto instanceof Promo)? ((Promo)producto).hayCupo(atracciones, ((Promo)producto).getNombres_atracciones()) : ((Atraccion) producto).hayCupo())) {
					 //si las monedas y el tiempo que tiene el user alcanza para comprar y/o realizar la propuesta
					 if ( (usuario.getMonedas() >= producto.getPrecio()) && (usuario.getTiempoDisponible() >= producto.getTiempo()) ) {
						 System.out.println( (producto instanceof Promo) ? usuario.getNombre() + " Desea comprar el Pack " + producto.getTipo() + (((Promo) producto).nombresAtracciones()) + " de promo " + producto.getNombre() + "?": 
							 											   usuario.getNombre() + " Desea comprar la atraccion " + producto.getNombre() + " de tipo " + producto.getTipo() + "?");
						 System.out.println("1 - SI              2 - NO");
						 dato = sc.nextInt();
						 if ( dato == 1) {
							 if (producto instanceof Promo) {
								 usuario.comprar(atracciones, (Promo) producto); 
							 } else {
								 usuario.comprar((Atraccion) producto);
							 }
							 
						 }
						 usuario.datosUsuario();
					 }
				 }
			 }
			 System.out.println("###############################################################################");
			 System.out.println(usuario.getNombre() + " total de hs a consumir " + usuario.getHsAConsumir() + " total de dinero a gastar " + usuario.getGasto());
			 System.out.println("###############################################################################\n");
			 System.out.println("\n");
			 AdministradorDeArchivos.escribirUsuario(usuario); //escribo el itinerario final del user en su archivo correspondiente
		 }	 
	}
}
