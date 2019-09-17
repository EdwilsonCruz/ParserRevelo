package parserrevelo;

import java.util.ArrayList;
import java.util.List;

public class LinhasGame {
	private String nome;
	private List<String> linhas;
	
	public LinhasGame () {
		this.linhas = new ArrayList<> ();
	}
	
	public String getNome () {
		return nome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public List<String> getLinhas () {
		return linhas;
	}
	
	public void setLinhas (List<String> linhas) {
		this.linhas = linhas;
	}
	
	public void adicionaLinha (String linha) {
		linhas.add(linha);
	}
}
