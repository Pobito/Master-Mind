package DTO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.util.Random;

import javax.swing.*;

public class Test extends JFrame {
	
	private JPanel mPanel;
	private JLabel etiqueta;
	private JColorChooser color;
	
	static Color color1 = Color.blue;
	static Color color2 = Color.red;
	static Color color3 = Color.yellow;
	static Color color4 = Color.BLACK;
	
	static Color[] colores = {color1, color2, color3, color4};
	
	public static final Color DEFAULT_COLOR = Color.blue;
	
	int margen = 10;
	
	public Test() {
		
//		setTitle("Ventana");
//		setBounds(20, 20, 550, 500);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setVisible(true);
//		mPanel = new JPanel();
//		mPanel.setLayout(null);
//		setContentPane(mPanel);
//		PictureBox pb = new PictureBox(24, colores);
//        mPanel.add(pb.crearCuadrado());
//		//CrearTodo();
	}
	
	public JPanel getPanel() {
		return this.mPanel;
	}
	

}
