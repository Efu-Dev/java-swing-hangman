/*
 * @author Diego Faria
 * @author Winderson Villarreal
 * 
 * Computación Gráfica N813
 */
package com.urbe.ahorcado;

 // Clase principal del programa, contenedora del metodo main.
public class Principal {
	
	public static void main(String[] args) { // Metodo Main del programa
	    Marco marco1 = new Marco(new VentanaPrincipal()); // Se crea la ventana principal
	    marco1.setTitle("Inicio"); // Se le coloca un titulo
	    marco1.setVisible(true); // Se pone visible
	}

}
