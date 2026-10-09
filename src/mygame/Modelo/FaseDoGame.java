package mygame.Modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FaseDoGame extends JPanel implements ActionListener{
	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Inimigo1> enemy1;
	
	private int inimigosCriados = 0;
	private long ultimoInimigo = 0;
	
	

private final int INIMIGOS_POR_ONDA = 5;
private final long INTERVALO_INIMIGOS = 1000; // 1 SEGUNDO
private final long PAUSA_ENTRE_ONDAS = 3000; // 3 SEGUNDOS
	
	
	
	public FaseDoGame() {
		setFocusable(true);
		setDoubleBuffered(true);
		ImageIcon referencia = new ImageIcon("res/fundo.png");   //A IMAGEM DO FUNDO
		fundo = referencia.getImage();
		
		player = new Player();
		player.load();
		addKeyListener(new TecladoAdapter());
		timer = new Timer(5, this);
		enemy1 = new ArrayList<Inimigo1>();
		
		timer.start();
		
		gerarInimigos();
	}
	public void gerarInimigos() {
		long agora = System.currentTimeMillis();
		
		
		 if (inimigosCriados  < INIMIGOS_POR_ONDA) {
			 if(agora - ultimoInimigo >= INTERVALO_INIMIGOS) {
				 int x = Math.max(getWidth(), 1024) + 50;
				 int y = (int) (Math.random() *( Math.max(getHeight(), 728) - 100 )+ 30);
				 
				 enemy1.add(new Inimigo1(x, y));
				 
				 inimigosCriados++;
				 ultimoInimigo = agora;
			 }
			
	 }	  else if(agora - ultimoInimigo >= PAUSA_ENTRE_ONDAS) {
				 inimigosCriados = 0;
				 ultimoInimigo = agora;
			 
		 }
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), null);   //PARA A IMAGEM DO CHAT (900, 650) FICAR DO JEITO CERTO NA TELA (1024, 728)
		graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
		
		List<Tiro> tiros = player.getTiros();
		for(int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			m.load();
			graficos.drawImage(m.getImagem(),  m.getX(),  m.getY(),  this);
				
		}
		for(int o = 0; o < enemy1.size(); o++) {
			
			Inimigo1 in = enemy1.get(o);
			in.load();
			graficos.drawImage(in.getImagem(), in.getX(), in.getY(),  this);
		}
		
		g.dispose();
	}
	@Override  //ESSE MÉTODO AQUI ESTÁ SOBRESCREVENDO UM MÉTODO QUE JÁ EXISTE NA CLASSE PAI.
	public void actionPerformed(ActionEvent e) {
		gerarInimigos();
		player.update();
		List<Tiro> tiros = player.getTiros();
		for(int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
				if(m.isVisivel()) {
					m.update();
				}else {
					tiros.remove(i);
				}
		}
		for(int o = 0; o < enemy1.size(); o++) {
			Inimigo1 in = enemy1.get(o);
			if(in.isVisivel()) {
				in.update();
			}
			else {
				enemy1.remove(o);
			}
		}
		
		repaint();
		
	}
	private class TecladoAdapter extends KeyAdapter{
		@Override  //ESSE MÉTODO AQUI ESTÁ SOBRESCREVENDO UM MÉTODO QUE JÁ EXISTE NA CLASSE PAI.
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
		@Override //ESSE MÉTODO AQUI ESTÁ SOBRESCREVENDO UM MÉTODO QUE JÁ EXISTE NA CLASSE PAI.
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
	}
}

