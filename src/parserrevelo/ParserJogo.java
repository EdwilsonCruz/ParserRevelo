/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserrevelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Edwilson
 */
public class ParserJogo {
    private List<String> linhas;
	
    public ParserJogo (List<String> linhas) {
            this.linhas = linhas; 
    }
public static List<Game> execute(final List<String> lines) {
		return new ParserJogo().parserJogo();
	}	

    private ParserJogo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<Game> parserJogo() {
            List<LinhasGame> linhasGame = parserLinhas();
            List<Game> games = new ArrayList<>();

            for (LinhasGame linhaGame : linhasGame) {
                    games.add(parserLinhasGame(linhaGame));
            }
            return games;
    }

    private List<LinhasGame> parserLinhas() {
            List<LinhasGame> games = new ArrayList<>();
            int TotaldePartidas = 0;
            String UltimaLinha = linhas.get(linhas.size() - 1);
            LinhasGame game = new LinhasGame();

            for (String linha : linhas) {
                    boolean InitGame = Regex.EstaPresente(linha.trim(), "InitGame");
                    if (InitGame) {
                            if (TotaldePartidas > 0) {
                                    games.add(game);
                                    game = new LinhasGame();
                            }
                            TotaldePartidas++;
                            game.setNome("game_"+ TotaldePartidas);
                    }
                    else {
                            game.adicionaLinha(linha);
                    }

                    if (linha.equals(UltimaLinha)) {
                            games.add(game);
                    }
            }
            return games;
    }

    private Game parserLinhasGame (LinhasGame game) {
            List<String> linhas = game.getLinhas();
            String nome = game.getNome();
            Map<String, Jogador> mapPlayers = new HashMap<>();
            Analyser.analyser(linhas, mapPlayers); 
            List<Jogador> players = new ArrayList<>(mapPlayers.values());

            return new Game(nome, players);
    }	
}