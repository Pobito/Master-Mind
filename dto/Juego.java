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