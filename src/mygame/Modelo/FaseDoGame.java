package mygame.Modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FaseDoGame extends JPanel implements ActionListener{
	private Image fundo;
	private Player player;
	private Timer timer;
	
	
	
	public FaseDoGame() {
		setFocusable(true);
		setDoubleBuffered(true);
		ImageIcon referencia = new ImageIcon("res/fundo.png");   //A IMAGEM DO FUNDO
		fundo = referencia.getImage();
		
		player = new Player();
		player.load();
		addKeyListener(new TecladoAdapter());
		timer = new Timer(5, this);
		timer.start();
	}
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), null);   //PARA A IMAGEM DO CHAT (900, 650) FICAR DO JEITO CERTO NA TELA (1024, 728)
		graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		repaint();
		
	}
	private class TecladoAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
	}
}

