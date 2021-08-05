package dto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class Juego extends JFrame {

	private JPanel juego;
	private JPanel nivel;
	private JColorChooser selector;
	private Color color;
	private JButton boton;
	private JLabel etiqueta;
	private JButton aceptar;
	private JButton cancelar;
	private JRadioButton opcion1;
	private JRadioButton opcion2;
	private JRadioButton opcion3;
	private ButtonGroup grupo;
	
	public Juego() {		
		setTitle("Selector de nivel");
		setBounds(100, 100, 450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		nivel = new JPanel();
		nivel.setLayout(null);
		setContentPane(nivel);
		
		opcion1 = new JRadioButton("Principiante", true);
		opcion1.setBounds(38,63,109,23);
		nivel.add(opcion1);
		opcion2 = new JRadioButton("Medio", false);
		opcion2.setBounds(38,89,109,23);
		nivel.add(opcion2);
		opcion3 = new JRadioButton("Avanzado", false);
		opcion3.setBounds(38,115,109,23);
		nivel.add(opcion3);
		
		grupo = new ButtonGroup();
		grupo.add(opcion1);
		grupo.add(opcion2);
		grupo.add(opcion3);
		
		ActionListener al = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				setTitle("Juego");
				setBounds(100, 100, 550, 500);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);
				juego = new JPanel();
				juego.setLayout(null);
				setContentPane(juego);
				
				// Barra menu
				JMenuBar menu = new JMenuBar();
				setJMenuBar(menu);

				// Apartado archivo
				JMenu menuArchivo = new JMenu("Archivo");
				menu.add(menuArchivo);

				// items
				JMenuItem nuevoJuego = new JMenuItem("Nuevo juego");
				menuArchivo.add(nuevoJuego);

				JMenuItem salir = new JMenuItem("Salir");
				menuArchivo.add(salir);
				salir.addActionListener(new ActionListener() { // Cuando clique mostrara el sistema seleccionado
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});

				// menu ayuda
				JMenu ayuda = new JMenu("Ayuda");
				menu.add(ayuda);

				JMenu comoJugar = new JMenu("Como jugar");
				ayuda.add(comoJugar);

				JMenuItem elementos = new JMenuItem("Elementos del juego");
				comoJugar.add(elementos);
				elementos.addActionListener(new ActionListener() { // Cuando clique mostrara el sistema seleccionado
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null,
								"Tablero compuesto por:\r\n"
										+ "· 12 filas de agujeros grandes para las clavijas de colores.\r\n"
										+ "· 12 filas de agujeros pequeños para las espigas blancas y negras.\r\n"
										+ "· 5 agujeros para ocultar con el escudo el código secreto.\r\n"
										+ "· 2 filas para las clavijas rojas y azules, para anotar los tanteos.\r\n"
										+ "· 1 Compartimento grande para las clavijas de colores.\r\n"
										+ "· 1 Compartimento para las espigas blancas y negras.\r\n" + "Piezas:\r\n"
										+ "· 1 Escudo para ocultar el código secreto.\r\n"
										+ "· 192 clavijas grandes de diferentes colores (Rojas, azules, verdes,\r\n"
										+ "amarillas, rosas, blancas, negras y marrones).\r\n"
										+ "· 60 espigas blancas y negras para la clave.\r\n"
										+ "· 4 espigas de tanteo, 2 rojas y 2 azules.");
					}
				});

				JMenuItem comoSeJuega = new JMenuItem("Como se juega");
				comoJugar.add(comoSeJuega);
				comoSeJuega.addActionListener(new ActionListener() { // Cuando clique mostrara el sistema seleccionado
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null,
								"En Mastermind compiten 2 jugadores, uno de ellos creará un código\r\n"
										+ "oculto con 5 clavijas de colores, pudiendo hacer las combinaciones con los 8\r\n"
										+ "colores disponibles e incluso repitiendo color si lo desea.\r\n"
										+ "El código de colores debe de ocultarse con el escudo para que no\r\n"
										+ "pueda verlo el oponente, que deberá acertar en el menor número posible de\r\n"
										+ "jugadas la clave para obtener una buena puntuación. Para descifrar el código\r\n"
										+ "secreto de colores el jugador deberá ir probando combinaciones aleatorias de\r\n"
										+ "colores, y en cada combinación, el jugador contrario debe darle pistas mediante\r\n"
										+ "las espigas blancas y negras.\r\n"
										+ "Por cada clavija acertada en color y posición, colocará una espiga negra,\r\n"
										+ "y por cada color acertado pero en un lugar equivocado colocara una espiga\r\n"
										+ "blanca.");
					}
				});

				JMenuItem puntuacion = new JMenuItem("Puntuación");
				comoJugar.add(puntuacion);
				puntuacion.addActionListener(new ActionListener() { // Cuando clique mostrara el sistema seleccionado
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "La forma de puntuación es bastante sencilla: se puede convenir antes de\r\n"
								+ "empezar de cuantos juegos va a ser la partida, cada jugador tiene un color\r\n"
								+ "distinto de espiga de tanteo, rojo o azul, que colocará en el número 1 de la fila\r\n"
								+ "vertical de tanteo.\r\n"
								+ "Al final de cada juego, el jugador que ha creado el código ganará tantos\r\n"
								+ "puntos como filas haya cubierto el oponente hasta que ha conseguido descifrar\r\n"
								+ "el código. Es decir, si ha necesitado probar 7 combinaciones distintas antes de\r\n"
								+ "acertar, estos son los puntos que gana el jugador contrario, que se anotarán\r\n"
								+ "cambiando la posición de la clavija de tanteo correspondiente.\r\n"
								+ "En el caso de que el jugador que ha creado el código se equivoque al\r\n"
								+ "dar las pistas con las espigas blancas y negras, deberá repetir la serie y\r\n"
								+ "además tendrá que dar 3 puntos al jugador contrario. Los jugadores se\r\n"
								+ "alternarán para crear el código secreto e irán anotando sus puntuaciones con\r\n"
								+ "las espigas de tanteo hasta terminar la partida.");
					}
				});

				// menu acerca de
				JMenu acercaDe = new JMenu("Acerca de");
				ayuda.add(acercaDe);

				JMenuItem consisteJuego = new JMenuItem("En que consiste el juego?");
				acercaDe.add(consisteJuego);
				consisteJuego.addActionListener(new ActionListener() { // Cuando clique mostrara el sistema seleccionado
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Mastermind es un juego de habilidad y lógica que consiste en descubrir\r\n"
								+ "una secuencia de colores oculta. Es un clásico que lleva muchos años en el\r\n"
								+ "mercado.");
					}
				});


				JMenuItem programadores = new JMenuItem("Programadores");
				acercaDe.add(programadores);
				programadores.addActionListener(new ActionListener() { // Cuando clique mostrara el sistema seleccionado
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Adrián Pobo \nAnna Marín \nSergio Aragón");
					}
				});
				
				boton = new JButton("Seleccionar");
				boton.setBounds(10,52,215,47);
				juego.add(boton);
				boton.addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent e) {
						color = selector.showDialog(null, "Seleccione un color", color);
						juego.setBackground(color);
						System.out.println(color);
					}
				});
			}
		};
		
		aceptar = new JButton("Aceptar");
		aceptar.setBounds(209,59,95,30);
		nivel.add(aceptar);
		aceptar.addActionListener(al);
		
		cancelar = new JButton("Cancelar");
		cancelar.setBounds(209,111,95,30);
		nivel.add(cancelar);
		cancelar.addActionListener(al);
	}
}
