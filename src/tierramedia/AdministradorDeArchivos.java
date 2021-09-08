package tierramedia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdministradorDeArchivos {

	public static List<Usuario> leerUsuarios() {
		Scanner sc = null;
		String[] registro;
		List<Usuario> usuarios = null;

		try {
			sc = new Scanner(new File("archivos_sistema/Usuarios.txt"));
			usuarios = new LinkedList<Usuario>();

			while (sc.hasNextLine()) {
				registro = sc.nextLine().split("-");
				usuarios.add(new Usuario(
							registro[0], 
							TipoAtraccion.valueOf(registro[1]), 
							Integer.parseInt(registro[2]),
							Double.parseDouble(registro[3]))
							);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (InputMismatchException e) { // el elemento recibido no corresponde al tipo de dato esperado
			System.err.println(e.getMessage());
		} finally {
			sc.close();
		}
		return usuarios;
	}

	public static void escribirUsuario(Usuario usuario) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("archivos_usuarios/" + usuario.getNombre() + ".txt")));
		bw.write(usuario.toString());
		bw.close();
	}

	public static List<Atraccion> leerAtracciones() {
		Scanner sc = null;
		String[] registro;
		List<Atraccion> atracciones = null;

		try {
			sc = new Scanner(new File("archivos_sistema/Atracciones.txt"));
			atracciones = new LinkedList<Atraccion>();

			while (sc.hasNextLine()) {
				registro = sc.nextLine().split("-");
				atracciones.add(new Atraccion(
								registro[0], 
								Integer.parseInt(registro[1]),
								Double.parseDouble(registro[2]),
								Integer.parseInt(registro[3]), 
								TipoAtraccion.valueOf(registro[4]))
								);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (InputMismatchException e) { // el elemento recibido no corresponde al tipo de dato esperado
			System.err.println(e.getMessage());
		} finally {
			sc.close();
		}
		return atracciones;
	}

	public static List<Promo> leerPromociones() {
		Scanner sc = null;
		String[] registro;
		List<Promo> promociones = null;
		String[] atracciones;
		try {
			sc = new Scanner(new File("archivos_sistema/Promociones.txt"));
			promociones = new LinkedList<Promo>();

			while (sc.hasNextLine()) {
				registro = sc.nextLine().split("-");
				atracciones = registro[2].split("%");

				switch (registro[1]) {
					case "AxB": promociones.add(new PromoAxB(
												TipoAtraccion.valueOf(registro[0]), 
												registro[1], 
												atracciones)
												);
					break;
					case "Porcentual": promociones.add(new PromoPorcentual(
													   TipoAtraccion.valueOf(registro[0]), 
													   registro[1], 
													   atracciones,
													   Integer.parseInt(registro[3]))
													   );
					break;
					case "Absoluta": promociones.add(new PromoAbsoluta(
													 TipoAtraccion.valueOf(registro[0]), 
													 registro[1], 
													 atracciones)
													 );
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (InputMismatchException e) { // el elemento recibido no corresponde al tipo de dato esperado
			System.err.println(e.getMessage());
		} finally {
			sc.close();
		}
		return promociones;
	}
}
