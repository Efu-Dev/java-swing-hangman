/*
 * @author Diego Faria
 * @author Winderson Villarreal
 * 
 * Computación Gráfica N813
 */
package com.urbe.ahorcado;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

// Clase del Panel de autores.
public class AutorPanel extends JPanel {
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); //Se llama al metodo de la superclase. 
		setBackground(Color.cyan); // Se coloca fondo cyan.

		Font mi11 = new Font("DIALOG", Font.ITALIC ,20); //Tipo de letra a usar y su tamaño
		g.setFont(mi11);

		g.setColor(Color.black); // Color de las figuras

		//Creacion de los strings de los autores
		g.drawString("Autores", 100, 50);
		g.drawString("Diego, Faria", 90, 100);
		g.drawString("Winderson Villarreal", 60, 150);
		g.drawString("N813 Computacion Grafica", 30, 200);
		
	}
}
