package mygame;

import javax.swing.JFrame;

import mygame.Modelo.FaseDoGame;

public class Container extends JFrame {
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 728;
	 
	public Container() {
		add(new FaseDoGame());
		setTitle("PHANTOM 7");
		setSize(WIDTH, HEIGHT);     //LARGURA E ALTURA DA TELA // ARRUMADO COM A IMAGEM DO CHAT (900, 650)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //QUANDO CLICAR NO (x) VAI FECHAR A ABA
		setLocationRelativeTo(null);  //PONTO QUE A TELA QUE VAI INICIAR NO NOTEBOOK O (NULL) SERVE PARA FICAR NO MEIO
		this.setResizable(true);  // SERVE PRA CONTROLA QUANDO A PESSOA QUISER MAXIMIZAR A TELA7
		setVisible(true);  //PARA QUE OS COMANDOS FIQUEM VISIVEL
	}
	public static void main (String [] args) {
		new Container();   //O TERRENO
	}
}

