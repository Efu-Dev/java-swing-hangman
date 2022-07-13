/*
 * @author Diego Faria
 * @author Winderson Villarreal
 * 
 * Computación Gráfica N813
 */
package com.urbe.ahorcado;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// Clase del Panel de la ventana principal
public class VentanaPrincipal extends JPanel implements ActionListener {
	
	// Propiedades de la clase
	private JButton jugar, salida, autor, lista, pass;
	private JLabel titulo;
	
	public VentanaPrincipal() { // Constructor de la clase

		setBackground(Color.WHITE);
		setLayout(null);
		
		//Creacion de los botones
		jugar = new JButton("Jugar");
		salida = new JButton ("Salida");
		autor = new JButton ("Autor");
		pass = new JButton("Ingresar Palabra");
		lista = new JButton("Utilizar Lista");

		// Creacion del JLabel con el titulo del juego
		titulo = new JLabel("El Ahorcado");
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setFont(new Font("DIALOG", Font.BOLD, 20));
		titulo.setBounds(20, 10, 250, 30);

		// Propiedades de los botones
		jugar.setBounds(10, 215, 70, 25);
		salida.setBounds(105, 215, 70, 25);
		autor.setBounds(200, 215, 70, 25);

		// Propiedades de los botones auxiliares
		pass.setBounds(10, 215, 130, 40);
		pass.setVisible(false);
		lista.setBounds(150, 215, 125, 40);
		lista.setVisible(false);

		// Se añade el ActionListener de esta clase a los botones
		jugar.addActionListener(this);
		salida.addActionListener(this);
		autor.addActionListener(this);
		pass.addActionListener(this);
		lista.addActionListener(this);

		// Se añaden los botones y el JLabel a la clase
		add(jugar);
		add(salida);
		add(autor);
		add(titulo);
		add(pass);
		add(lista);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) { // Metodo sobreescrito de los eventos de accion

		Object pulso = e.getSource(); //Object sirve para cuando el usuario pulse una opcion el programa lo acepte 
		
		if (pulso == autor) { //Pulso del boton Autor para ir a la ventana Autor
			Marco marco = new Marco(new AutorPanel());
		    marco.setTitle("Autor");
		    marco.setVisible(true);
			marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		
		else if(pulso == salida) {  //Pulso Salida para cerrar la aplicacion 
			Window w = SwingUtilities.getWindowAncestor(VentanaPrincipal.this);
			w.dispatchEvent(new WindowEvent(w, WindowEvent.WINDOW_CLOSING));
		}
		
		else if (pulso == jugar){ // Pulso jugar para ir a la ventana de juego y ir a las opciones de juego
			jugar.setVisible(false);
			autor.setVisible(false);
			salida.setVisible(false);

			lista.setVisible(true);
			pass.setVisible(true);
		}

		else if(pulso == lista){ //Este boton estara en la clase jugar donde el usuario podra escoger

			String[] palabras = { "CIUDAD", "VENEZUELA", "UNIVERSIDAD", "ESQUELETO", "COMPUTADORA", "INFORMATICA",
            "COMPUTACION", "TECLADO", "MARACAIBO", "MONITOR", "VIDEOJUEGO", "DINERO", "HELADO", "ANCIANO", "CAMARA",
            "TEXTO", "JAVA", "PROGRAMACION", "DIEGO", "WINDERSON", "ELECTRICIDAD", "INGENIERIA", "CIENCIA", "PIXEL"};
			int random = (int)(Math.random()*(palabras.length) - 1);

			Marco marco = new Marco(new JugarPanel(palabras[random]));		
			marco.setSize(500,500);
            marco.setLocationRelativeTo(null);
            marco.setTitle("Jugar");
            marco.setVisible(true);

			Window w = SwingUtilities.getWindowAncestor(this);
			w.dispose();
		}
		
		else if(pulso == pass){ //Este boton estara en la clase jugar donde el usuario podra escribir su palabra con un limite de 14 caracteres
			Marco marco = new Marco(new IntroducirPalabra());
			marco.setSize(300, 250);
			marco.setTitle("Introducir palabra");
			marco.setLocationRelativeTo(null);	
			marco.setVisible(true);

			Window w = SwingUtilities.getWindowAncestor(this);
			w.dispose();
		}
	}

	@Override
	public void paintComponent(Graphics g){ // Metodo sobreescrito para la creacion estática de graficos
		super.paintComponent(g); // Se llama al metodo de la superclase

		g.setColor(Color.BLACK); // Color de los graficos negro
		g.fillOval(120, 60, 50, 50); // CABEZA
		g.drawLine(145, 85, 145, 160);    // CUERPO
		g.drawLine(145, 160, 100, 200);   // PIERNA IZQUIERDA
		g.drawLine(145, 160, 190, 200);   // PIERNA DERECHA
		g.drawLine(95, 120, 200, 120);    // BRAZOS
		g.drawLine(230, 200, 260, 200);   // PALO INFERIOR
		g.drawLine(245, 200, 245, 50);	  // PALO VERTICAL
		g.drawLine(145, 50, 245, 50);	  // PALO HORIZONTAL
		g.drawLine(145, 50, 145, 60);	  // CUERDA
	}

}
