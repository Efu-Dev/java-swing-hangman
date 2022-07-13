/*
 * @author Diego Faria
 * @author Winderson Villarreal
 * 
 * Computación Gráfica N813
 */
package com.urbe.ahorcado;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JOptionPane;

// Clase del JPanel donde se juega al ahorcado
public class JugarPanel extends JPanel implements ActionListener{

	// Propiedades de la clase
	private JButton[] teclas;
	private String palabra, actual;
	private char[] letrasDescubiertas;
	private Integer fallos;
	
	public JugarPanel(String palabra) { // Constructor de la clase
		final String TECLADO = "QWERTYUIOPASDFGHJKLÑZXCVBNM";
		this.teclas = new JButton[27];
		letrasDescubiertas = new char[0];
		this.palabra = palabra;
		this.actual = "";
		this.fallos = 0;

		setLayout(null); //Para colocar JComponents en coordenadas y tamaños libres.

		for(int i = 0; i < teclas.length; i++){ //Ciclo para la creacion de las teclas
			teclas[i] = new JButton();
			teclas[i].setText(TECLADO.substring(i, i+1));
			
			if(i < 10)
				teclas[i].setBounds(40*(i+1), 300, 45, 40);
			else if(i < 20)
				teclas[i].setBounds(40*(i-10 + 1), 350, 45, 40);
			else
				teclas[i].setBounds(40*(i-20 + 1) + 60, 400, 45, 40);

			teclas[i].addActionListener(this);

			add(teclas[i]);
		}

		setBackground(Color.WHITE); // Se coloca fondo blanco
	}

	@Override
	public void paint(Graphics g) { // Metodo sobreescrito para dibujar y actualizar los graficos.
		super.paint(g); // Se llama al metodo de la superclase
		int x = 10; // Desplazamiento en x de la figura.
		
		//Validaciones para dibujas 
		if(fallos >= 1)
			g.fillOval(120 + x, 60, 50, 50); // CABEZA
		
		if(fallos >= 2)
			g.drawLine(145 + x, 85, 145 + x, 160);    // CUERPO
		
		if(fallos >= 3){
			g.drawLine(145 + x, 160, 100 + x, 200);   // PIERNA IZQUIERDA
			g.drawLine(145 + x, 160, 190 + x, 200);   // PIERNA DERECHA
		}
		
		if(fallos >= 4)
			g.drawLine(95 + x, 120, 200 + x, 120);    // BRAZOS

		if(fallos >= 5){
			g.drawLine(230 + x, 200, 260 + x, 200);   // PALO INFERIOR
			g.drawLine(245 + x, 200, 245 + x, 50);	  // PALO VERTICAL
		}
		
		if(fallos >= 6){ // GAME OVER
			g.drawLine(145 + x, 50, 145 + x, 60);	  // CUERDA
			g.drawLine(145 + x, 50, 245 + x, 50);	  // PALO HORIZONTAL
		}

		g.setFont(new Font("DIALOG", Font.ITALIC, 40)); // Fuente de los String

		// Operaciones del margen agregado dependiendo de la longitud de la palabra
		int agg = palabra.length() <= 7 ? (palabra.length() < 6 ? 120 : 80)
		: (palabra.length() <= 10 ? 30 : 0);

		// Operaciones de la separacion de cada letra
		int mult = palabra.length() <= 11 ? 40 : (palabra.length() > 12 ? 33 : 35);

		// Determinacion del String actual para mostrar en pantalla y almacenar.
		actual = "";
		for(int i = 0; i < palabra.length(); i++){
			if(estaDescubierta(palabra.charAt(i))){
				g.drawString(palabra.substring(i, i+1), mult*(i+1) + agg, 250);
				actual += palabra.substring(i, i+1);
			}
			else{
				g.drawString("_", mult*(i+1) + agg, 250);
				actual += "_";
			}
		}

		// Grafico de los fallos
		g.drawString("FALLOS:", 290, 100);
		g.drawString(fallos + " / 6", 330, 150);
	}

	private boolean estaDescubierta(Character letra) { // Metodo para verificar letras descubiertas
		for(int i = 0; i < letrasDescubiertas.length; i++){
			if(letra.equals(letrasDescubiertas[i]))
				return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) { // Metodo implementado de ActionListener
		Object pulso = e.getSource();  // Se obtiene la fuente del evento
		
		for(int i = 0; i < teclas.length; i++){ // Ciclo para verificar cual letra fue presionada
			if(pulso.equals(teclas[i])){
				teclas[i].setEnabled(false); // Se desactiva la tecla
				if(chequearCorrecto(teclas[i].getText())) //Se chequea si esta en la palabra
					chequearVictoria(); // Si si, se chequea si gano.
				else // Si no
					chequearGameOver(); // Se chequea si perdio
			}
		}
	}

	private void chequearGameOver() { //Metodo para el fin de juego
		if(fallos >= 6){ // Se verifica que los fallos sean mayor o igual a 6
			// Si lo son, se muestra el mensaje de que perdió.
			JOptionPane.showMessageDialog(null, "Has perdido el juego.La palabra era: " + palabra);
			cerrarJuego(); // Y se cierra el juego.
		}		
	}

	private void chequearVictoria() { // Metodo para la victoria
		
		// Se crea el String de la palabra actual mostrada en pantalla
		actual = "";
		for(int i = 0; i < palabra.length(); i++){
			if(estaDescubierta(palabra.charAt(i)))
				actual += palabra.substring(i, i+1);
			else
				actual += "_";
		}

		// Se verifica si la palabra es igual a la actual
		if(palabra.equals(actual)){
			// Si lo es, se informa al usuario y se cierra el juego.
			JOptionPane.showMessageDialog(null, "¡Has ganado el juego!");
			cerrarJuego();
		}
	}

	private boolean chequearCorrecto(String text) { //Metodo boolean que sirve para validar si el caracter es correcto
		for(Character c : palabra.toCharArray()) //Ciclo para verificar que el caracter este en la palabra
			if(c.equals(text.toCharArray()[0])){
				char[] aux = letrasDescubiertas;
				letrasDescubiertas = new char[letrasDescubiertas.length + 1];
				for(int i = 0; i < aux.length; i++)
					letrasDescubiertas[i] = aux[i];
				
				letrasDescubiertas[letrasDescubiertas.length - 1] = c;
				repaint(); // Se vuelve a pintar la figura actualizada.
				return true;
			}
			
		fallos++; // Se incrementan los fallos
		repaint(); // Se vuelve a pintar la figura actualizada.
		return false;
	}

	private void cerrarJuego(){ // Metodo para cerrar juego
		// Se crea el marco de la ventana principal
		Marco marco = new Marco(new VentanaPrincipal());
		marco.setVisible(true);
		marco.setTitle("Inicio");

		// Se cierra esta ventana
		Window w = SwingUtilities.getWindowAncestor(this);
		w.dispose();
	}
}
