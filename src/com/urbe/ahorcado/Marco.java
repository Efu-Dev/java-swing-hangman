/*
 * @author Diego Faria
 * @author Winderson Villarreal
 * 
 * Computación Gráfica N813
 */
package com.urbe.ahorcado;

import javax.swing.JFrame;
import javax.swing.JPanel;

// Clase del Marco que utilizaran los Panel
public class Marco extends JFrame{
	
	public Marco(JPanel panel){ // Constructor de la clase.
        setSize(300,300); // Tamaño.
        setLocationRelativeTo(null); // Centra el frame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Operacion al cerar.
        setResizable(false); // No se pueden ajustar las ventanas.
        add(panel); // Se le dañade el panel pasado como parametro.
    }
}
