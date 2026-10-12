package mygame.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {

	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;
	private List <Tiro> tiros;  //POR NAO SABER QUANTOS TIROS O JOGADOR VAI ATIRAR
	private long ultimoTiro = 0;
	private final long INTERVALO_TIRO = 200; //MILESSEGUNDOS
	private boolean isVisivel;
	
	public Player() {
		this.x = 100;
		this.y = 100;
		isVisivel = true;
		tiros = new ArrayList<Tiro>();
	}
	public void load() {  //DEFINIR A IMAGEM DO PLAYER
		ImageIcon referencia = new ImageIcon("res\\heroi.png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		
	}
	public void update(int larguraTela, int alturaTela) {    //METODO PARA A IMAGEM DO HEROI SE MOVA NO EIXO X E Y
		x += dx;
		y += dy;
		
		 if (x < 0) {
		        x = 0;
		    }

		    if (y < 0) {
		        y = 0;
		    }

		    if (x > larguraTela - largura) {
		        x = larguraTela - largura;
		    }

		    if (y > alturaTela - altura) {
		        y = alturaTela - altura;
		    }
		
		
	}
	public void tiroSimples() {
		long agora = System.currentTimeMillis();
		if(agora - ultimoTiro >= INTERVALO_TIRO) {
		this.tiros.add(new Tiro (x + largura, y + (altura /2)));
		ultimoTiro = agora;
		}
		
	}
	public Rectangle getBounds() {
		return new Rectangle (x, y, largura, altura); //CRIA UM RETANGULO EM VOLTA DA NAVE 
	}
	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		if (codigo == KeyEvent.VK_SPACE) {
			tiroSimples();
		}  
		if (codigo == KeyEvent.VK_W) {
			dy= -5;
		}
		if (codigo == KeyEvent.VK_S) {
			dy= 5;
		}
		if (codigo == KeyEvent.VK_A) {
			dx= -5;
		}
		if (codigo == KeyEvent.VK_D) {
			dx= 5;
		}
	}
	public void keyRelease(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		if (codigo == KeyEvent.VK_W) {
			dy= 0;
		}
		if (codigo == KeyEvent.VK_S) {
			dy= 0;
		}
		if (codigo == KeyEvent.VK_A) {
			dx= 0;
		}
		if (codigo == KeyEvent.VK_D) {
			dx= 0;
		}
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImagem() {
		return imagem;
	}
	public List<Tiro> getTiros() {
		return tiros;
	}
	
	
}

