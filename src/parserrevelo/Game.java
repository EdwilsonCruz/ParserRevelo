package parserrevelo;

import java.util.List;

public class Game {
	private String nome;
	private List<Jogador> jogadores;
	
	public Game (String nome, List<Jogador> jogadores) {
		this.nome = nome;
		this.jogadores = jogadores;
	}
	
	public String getNome () {
		return nome;
	}
	
	public List<Jogador> getJogadores () {
		return jogadores;
	}
}
