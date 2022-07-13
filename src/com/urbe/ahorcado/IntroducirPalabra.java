/*
 * @author Diego Faria
 * @author Winderson Villarreal
 * 
 * Computación Gráfica N813
 */
package com.urbe.ahorcado;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// Clase para introducir una palabra secreta.
public class IntroducirPalabra extends JPanel implements ActionListener {

    // Propiedades de la clase.
    private JPasswordField palabra;
    private JButton confirmar;
    private JLabel texto;

    public IntroducirPalabra(){ // Constructor de la clase.
        setLayout(null);
        setBackground(Color.WHITE); // Fondo blanco.

        palabra = new JPasswordField(); //Se usa el JPasswordField para ingresar las letras en forma de contraseña
        palabra.setBounds(40, 80, 220, 30);
        palabra.setBackground(Color.LIGHT_GRAY); // Fondo del JPasswordField
        palabra.setForeground(Color.BLACK); // Color de las letras

        texto = new JLabel("Ingrese la palabra secreta:"); // JLabel con informacion
        texto.setBounds(20, 20, 220, 30);

        confirmar = new JButton("Confirmar"); // Boton para confirmar
        confirmar.setBounds(100, 150, 100, 30);
        confirmar.addActionListener(this); // Se le da el ActionListener de la clase.
        
        // Se usa el add para que los botones y JLabel se agreguen al JPanel
        add(palabra);
        add(texto);
        add(confirmar);
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Este metodo sobreescrito indica las acciones por evento 

        if(palabra.getPassword().length < 4 || palabra.getPassword().length > 14){ //Validaciones para que la palabra tenga mas de 4 caracteres y un maximo de 14 caracteres
            JOptionPane.showMessageDialog(null, "¡La palabra debe tener al menos 4 caracteres y menos de 15!");
            return;
        }

        // Se utiliza la libreria StringBuilder para crear un String de un array de caracteres.
        StringBuilder sb = new StringBuilder();
        
        for(char c : palabra.getPassword()) // Se crea el String
            sb.append(c);

        String palabraSecreta = sb.toString().toUpperCase(); // Se lleva la palabra a UPPERCASE

        if(palabraSecreta.equals(null)) //Validacion para que se ingrese una palabra de forma obligatoria
            JOptionPane.showMessageDialog(null, "¡Debe introducir una palabra!");
        else{
            if(palabraSecreta.matches(".*[0-9].*")) //Validacion por si el usuario coloca numeros
                JOptionPane.showMessageDialog(null, "¡La palabra no debe contener numeros!");
            else if(palabraSecreta.matches("[A-Z|ÁÍÚÉÓÑ]*")){ //Si la palabra tiene solo texto en UPPERCASE
                // Se reemplazan los acentos por letras no acentuadas
                palabraSecreta = palabraSecreta.replaceAll("Á", "A");
                palabraSecreta = palabraSecreta.replaceAll("É", "E");
                palabraSecreta = palabraSecreta.replaceAll("Í", "I");
                palabraSecreta = palabraSecreta.replaceAll("Ó", "O");
                palabraSecreta = palabraSecreta.replaceAll("Ú", "U");

                // Empieza el juego en otro marco
                Marco marco = new Marco(new JugarPanel(palabraSecreta));
                marco.setSize(500,500);
                marco.setLocationRelativeTo(null);
                marco.setTitle("Jugar");
                marco.setVisible(true);

                // Se cierra esta ventana.
                Window w = SwingUtilities.getWindowAncestor(this);
                w.dispose();
            }
            else // Cuando la palabra tiene caracteres especiales se mostrara un error.
                JOptionPane.showMessageDialog(null, "¡La palabra no debe contener caracteres especiales!");
        }
    }
    
}