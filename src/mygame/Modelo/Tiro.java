package mygame.Modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tiro {

	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	
	private static final int LARGURA = 1925;  //ATÉ ONDE VAI O TIRO
	private static int VELOCIDADE = 3;       //	VELOCIDADE DO TIRO
	
	public Tiro(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}
	
	public void load() {
		ImageIcon referencia =  new ImageIcon("res\\laser.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
	} 
	
	public void update() {
		this.x += VELOCIDADE;
			if (this.x > LARGURA) {
				isVisivel = false;  // TIRO VAI SUMIR
			}
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
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
	
	
	
	
}
