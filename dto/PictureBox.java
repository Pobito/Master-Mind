package DTO;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.Color;


public class PictureBox extends JFrame {
	
	public int idColor;
	private static Color color1 = Color.BLUE;
	private static Color color2 = Color.RED;
	private static Color color3 = Color.yellow;
	private static Color color4 = Color.green;
	private int margen = 40;
	private JPanel mPanel;
	final Color[] colores = {color1, color2, color3, color4};
	
	public Color nextColor(int index) {
		
		if (index >= 3) {
			return colores[0];
		}
		
		else {
			return colores[index+1];
		}
		
	}
	
	public Color prevColor(int index) {
		
		if (index == 0) {
			return colores[colores.length-1];
		}
		
		else {
			return colores[index-1];
		}
		
	}

	
	public void crearVentana() {
		
		setTitle("Ventana");
		setBounds(0, 0, 250, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		mPanel = new JPanel();
		mPanel.setLayout(null);
		setContentPane(mPanel);
		
		for (int i= 0; i < 4; i++) {
			
			final JTextArea txtarea = new JTextArea();
			final int ind = i;
			txtarea.setBounds((100 + margen * i), 100, 24, 24);
			txtarea.setBackground(colores[i]);
			txtarea.setBorder(new LineBorder(Color.BLACK, 2));
			txtarea.setEditable(false);
			
			txtarea.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					
					if (e.getButton() == MouseEvent.BUTTON1) {

						txtarea.setBackground(nextColor(ind));
					}
					
					if (e.getButton() == MouseEvent.BUTTON3) {
						
					
						txtarea.setBackground(prevColor(ind));
					}

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				});
			mPanel.add(txtarea);
			
		}

		//CrearTodo();
		
	}

	
	public PictureBox() {
		crearVentana();
	}


}
