package dto;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Juego extends JFrame {
	Random rnd = new Random();

	private JPanel juego, nivel;
	private JColorChooser selector;
	private Color color;
	private JButton boton, aceptar, cancelar, comprobar;
	private JRadioButton opcion1, opcion2, opcion3;
	private ButtonGroup grupo;
	private JTextArea texArea, cuadrado1, cuadrado2, cuadrado3,cuadrado4;
	private JLabel texto;
	private JTextArea[] respuesta = new JTextArea[4];
	private Color[] colores = new Color[4]; // Colores de la solucion
	private Color[] coloresDisponibles;
	private Color[] coloresUsuario = new Color[4];
	private Color[] coloresAux;
	private int num = 0, x, y = 10, intentos;

	public Color nextColor(int index) {
		if (index >= coloresDisponibles.length) {
			num = 0;
			return coloresDisponibles[num];
		}
		else {
			return coloresDisponibles[num];
		}
	}

	public Color prevColor(int index) {
		if (index == -1) {
			num = coloresDisponibles.length - 1;
			return coloresDisponibles[num];
		}
		else {
			return coloresDisponibles[num];
		}
	}

	public Juego() {
		setTitle("Selector de nivel");
		setBounds(100, 100, 450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		nivel = new JPanel();
		nivel.setLayout(null);
		setContentPane(nivel);

		opcion1 = new JRadioButton("Principiante", true);
		opcion1.setBounds(38, 63, 109, 23);
		nivel.add(opcion1);
		opcion2 = new JRadioButton("Medio", false);
		opcion2.setBounds(38, 89, 109, 23);
		nivel.add(opcion2);
		opcion3 = new JRadioButton("Avanzado", false);
		opcion3.setBounds(38, 115, 109, 23);
		nivel.add(opcion3);

		grupo = new ButtonGroup();
		grupo.add(opcion1);
		grupo.add(opcion2);
		grupo.add(opcion3);

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setTitle("Juego");
				setBounds(100, 100, 850, 500);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);
				juego = new JPanel();
				juego.setLayout(null);
				setContentPane(juego);

				// nivel Medio
				if (opcion2.isSelected()) {
					coloresDisponibles = new Color[5];
					coloresAux = new Color[5];
					coloresDisponibles[4] = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
					coloresAux[4] = coloresDisponibles[4];
					intentos = 8;
					// Nivel avanzado
				} else if (opcion3.isSelected()) {
					coloresDisponibles = new Color[6];
					coloresAux = new Color[6];
					coloresDisponibles[4] = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
					coloresAux[4] = coloresDisponibles[4];
					coloresDisponibles[5] = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
					coloresAux[5] = coloresDisponibles[5];
					intentos = 6;
					// Nivel principiante (por defecto)
				} else {
					coloresDisponibles = new Color[4];
					coloresAux = new Color[4];
					intentos = 10;
				}

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
						JOptionPane.showMessageDialog(null,
								"La forma de puntuación es bastante sencilla: se puede convenir antes de\r\n"
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
						JOptionPane.showMessageDialog(null,
								"Mastermind es un juego de habilidad y lógica que consiste en descubrir\r\n"
										+ "una secuencia de colores oculta. Es un clásico que lleva muchos años en el\r\n"
										+ "mercado.");
					}
				});

				JMenuItem programadores = new JMenuItem("Programadores");
				acercaDe.add(programadores);
				programadores.addActionListener(new ActionListener() { // Cuando clique mostrara el sistema seleccionado
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Adrián Pobo\nAnna Marín\nSergio Aragón");
					}
				});

				boton = new JButton("Seleccionar");
				boton.setBounds(209, 59, 115, 30);
				juego.add(boton);
				boton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int i = 0, x = 615; i < colores.length; i++, x += 30) {
							color = selector.showDialog(null, "Seleccione un color", null);
							colores[i] = (color);
							coloresAux[i] = color;
							coloresDisponibles[i] = (color);
							if (colores[i] == null) {
								i--;
								x -= 30;
							} else {
								respuesta[i] = new JTextArea();
								respuesta[i].setBounds(x, 110, 20, 20);
								respuesta[i].setEditable(false); // Para que no se pueda escribir en él
								respuesta[i].setBackground(colores[i]);
								respuesta[i].setVisible(false);
								juego.add(respuesta[i]);
							}
						}

						// Para randomizar el array
						for (int j = 0; j < coloresDisponibles.length; j++) {
							int index = rnd.nextInt(coloresDisponibles.length);
							Color temp = coloresDisponibles[j];
							coloresDisponibles[j] = coloresDisponibles[index];
							coloresDisponibles[index] = temp;
						}

						// Para pintar colores disponibles
						for (int i = 0, x = 615; i < coloresDisponibles.length; i++, x += 30) {
							texArea = new JTextArea();
							texArea.setBounds(x, 50, 20, 20);
							texArea.setEditable(false); // Para que no se pueda escribir en él
							texArea.setBackground(coloresDisponibles[i]);
							juego.add(texArea);
						}
						texto = new JLabel("");
						texto.setBounds(600, 25, 200, 55);
						juego.add(texto);
						texto.setBorder(BorderFactory.createTitledBorder("Colores Disponibles"));

						texto = new JLabel("");
						texto.setBounds(600, 85, 200, 55);
						juego.add(texto);
						texto.setBorder(BorderFactory.createTitledBorder("Solución"));
						juego.remove(boton);

						cuadrado1 = new JTextArea();
						cuadrado1.setBounds(10, 10, 24, 24);
						cuadrado1.setBackground(Color.white);
						cuadrado1.setBorder(new LineBorder(Color.BLACK, 2));
						cuadrado1.setEditable(false);
						cuadrado1.addMouseListener(new MouseListener() {
							public void mouseClicked(MouseEvent e) {

								if (e.getButton() == MouseEvent.BUTTON1) {
									num++;
									cuadrado1.setBackground(nextColor(num));
								}

								if (e.getButton() == MouseEvent.BUTTON3) {
									num--;
									cuadrado1.setBackground(prevColor(num));
								}

							}

							@Override
							public void mousePressed(MouseEvent e) {
							}

							@Override
							public void mouseReleased(MouseEvent e) {
							}

							@Override
							public void mouseEntered(MouseEvent e) {
							}

							@Override
							public void mouseExited(MouseEvent e) {
							}
						});

						cuadrado2 = new JTextArea();
						cuadrado2.setBounds(40, 10, 24, 24);
						cuadrado2.setBackground(Color.white);
						cuadrado2.setBorder(new LineBorder(Color.BLACK, 2));
						cuadrado2.setEditable(false);
						cuadrado2.addMouseListener(new MouseListener() {
							public void mouseClicked(MouseEvent e) {

								if (e.getButton() == MouseEvent.BUTTON1) {
									num++;
									cuadrado2.setBackground(nextColor(num));
								}

								if (e.getButton() == MouseEvent.BUTTON3) {
									num--;
									cuadrado2.setBackground(prevColor(num));
								}
							}

							@Override
							public void mousePressed(MouseEvent e) {
							}

							@Override
							public void mouseReleased(MouseEvent e) {
							}

							@Override
							public void mouseEntered(MouseEvent e) {
							}

							@Override
							public void mouseExited(MouseEvent e) {
							}
						});

						cuadrado3 = new JTextArea();
						cuadrado3.setBounds(70, 10, 24, 24);
						cuadrado3.setBackground(Color.white);
						cuadrado3.setBorder(new LineBorder(Color.BLACK, 2));
						cuadrado3.setEditable(false);
						cuadrado3.addMouseListener(new MouseListener() {
							public void mouseClicked(MouseEvent e) {

								if (e.getButton() == MouseEvent.BUTTON1) {
									num++;
									cuadrado3.setBackground(nextColor(num));
								}

								if (e.getButton() == MouseEvent.BUTTON3) {
									num--;
									cuadrado3.setBackground(prevColor(num));
								}

							}

							@Override
							public void mousePressed(MouseEvent e) {
							}

							@Override
							public void mouseReleased(MouseEvent e) {
							}

							@Override
							public void mouseEntered(MouseEvent e) {
							}

							@Override
							public void mouseExited(MouseEvent e) {
							}
						});

						cuadrado4 = new JTextArea();
						cuadrado4.setBounds(100, 10, 24, 24);
						cuadrado4.setBackground(Color.white);
						cuadrado4.setBorder(new LineBorder(Color.BLACK, 2));
						cuadrado4.setEditable(false);
						cuadrado4.addMouseListener(new MouseListener() {
							public void mouseClicked(MouseEvent e) {

								if (e.getButton() == MouseEvent.BUTTON1) {
									num++;
									cuadrado4.setBackground(nextColor(num));
								}

								if (e.getButton() == MouseEvent.BUTTON3) {
									num--;
									cuadrado4.setBackground(prevColor(num));
								}

							}

							@Override
							public void mousePressed(MouseEvent e) {
							}

							@Override
							public void mouseReleased(MouseEvent e) {
							}

							@Override
							public void mouseEntered(MouseEvent e) {
							}

							@Override
							public void mouseExited(MouseEvent e) {
							}
						});

						juego.add(cuadrado1);
						juego.add(cuadrado2);
						juego.add(cuadrado3);
						juego.add(cuadrado4);

						comprobar = new JButton("Comprobar");
						comprobar.setBounds(140, 10, 100, 24);
						juego.add(comprobar);
						comprobar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								int negra = 0, blancas = 0, i;
								coloresUsuario[0] = cuadrado1.getBackground();
								coloresUsuario[1] = cuadrado2.getBackground();
								coloresUsuario[2] = cuadrado3.getBackground();
								coloresUsuario[3] = cuadrado4.getBackground();

								// Para ver cuantas negras hay
								for (i = 0; i < coloresUsuario.length; i++) {
									if (coloresUsuario[i] == colores[i]) {
										coloresAux[i] = Color.white;
										negra++;
									}
								}
								// Comprobamos cuantas blancas hay
								for (i = 0; i < colores.length; i++) {
									for (int j = 0; j < colores.length; j++) {
										if (coloresUsuario[i] == coloresAux[j]) {
											blancas++;
										}
									}
								}

								if (negra == colores.length) {
									JOptionPane.showMessageDialog(null, "Has ganado");
									System.exit(0);
								} else {
									intentos--;
									y += 50;
									for (i = 0, x = 260; i < (negra); i++, x += 44) {
										JTextArea holi = new JTextArea();
										holi.setBounds(x, y, 24, 24);
										holi.setBackground(Color.black);
										holi.setBorder(new LineBorder(Color.BLACK, 2));
										holi.setEditable(false);
										juego.add(holi);
									}
									for (i = 0, x = 260; i < (blancas); i++, x += 44) {
										final JTextArea holi = new JTextArea();
										holi.setBounds(x, y, 24, 24);
										holi.setBackground(Color.white);
										holi.setBorder(new LineBorder(Color.BLACK, 2));
										holi.setEditable(false);
										juego.add(holi);
									}
									for (i = 0, x = 10; i < coloresUsuario.length; i++, x += 30) {
										final JTextArea holi = new JTextArea();
										holi.setBounds(x, y, 24, 24);
										holi.setBackground(coloresUsuario[i]);
										holi.setBorder(new LineBorder(Color.BLACK, 2));
										holi.setEditable(false);
										juego.add(holi);
									}
									if (intentos == 0) {
										for (i = 0; i < coloresUsuario.length; i++) {
											respuesta[i].setVisible(true);
                                        }
										JOptionPane.showMessageDialog(null, "Has perdido");
										
//										System.exit(0);
									}
								}
							}
						});

					}

				});

			}
		};

		aceptar = new JButton("Aceptar");
		aceptar.setBounds(209, 59, 95, 30);
		nivel.add(aceptar);
		aceptar.addActionListener(al);

		cancelar = new JButton("Cancelar");
		cancelar.setBounds(209, 111, 95, 30);
		nivel.add(cancelar);
		cancelar.addActionListener(al);
	}
}