package co.edu.uniquindio.grid.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Permite manejar las operaciones generales de la capa de presentacion
 * @author EinerZG
 * @version 1.0
 */
public final class Utilidades {

	/**
	 * permite mostrar un texto informativo en pantalla
	 * @param titulo subtitulo de la alerta
	 * @param mensaje mensaje principal
	 */
	public static void mostrarMensaje( String titulo, String mensaje ) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Balotera");
		alert.setHeaderText(titulo);
		alert.setContentText(mensaje);
		alert.showAndWait();	
	}
	
	/**
	 * permite hacer un casting de localDate a Date
	 * @param localDate que se quiere cambiar
	 * @return una fecha en formato date
	 */
	public static Date pasarADate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * permite hacer un casting de date a localdate
	 * @param date que se desea cambiar de formato
	 * @return una fecha en formato local date
	 */
	public static LocalDate pasarALocalDate(Date date) {
		 return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	
}
