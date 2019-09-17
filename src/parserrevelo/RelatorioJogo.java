/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserrevelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author Edwilson
 */
public class RelatorioJogo {    

    private static int numJogadores;
    private static int marcador;
    public static void RelatorioJogo (List <Game> games, int opcao) {	
        //Ranking de todas partidas do servidor
        System.out.println("");

        Map<String, Integer> totalKillsPlayer = new HashMap<>();
        List<Jogador> players = new ArrayList<>();

        for (Game game : games) {
                for (Jogador p : game.getJogadores()) {
                        players.add(p);
                }

        }
        //Mostrar Informacoes das partidas
        games.stream().map((Game game) -> {            
            numJogadores = game.getJogadores().size();
            System.out.println(game.getNome() + ": {");
            return game;
        }).map((game) -> {
            int totalKills = 0;            
            for (Jogador player : game.getJogadores()) {
                totalKills = totalKills + player.getPlayerKD().getTotalDeaths();
            }
            System.out.println("\t total_kills: " + totalKills+";");
            System.out.print("\t players: [");
            return game;
        }).map((game) -> {
            marcador=0;
            for (Jogador player : game.getJogadores()) {
                marcador+=1;
                if (numJogadores>marcador) {
                    System.out.print("\"" + player.getNome() + "\", ");    
                }else{
                    System.out.print("\"" + player.getNome()+"\"");
                }                
            }
            return game;
        }).map((game) -> {
            System.out.print("]");
            return game;
        }).map((game) -> {
            System.out.println("");
            return game;
        }).map((game) -> {
            System.out.println("\t Kills: {");
            return game;
        }).map((game) -> {
            marcador=0;
            game.getJogadores().forEach((player) -> {
                marcador+=1;
                if( numJogadores > marcador){
                    System.out.println("\t \t \"" +player.getNome() + "\" : " + player.getPlayerKD().getKillsValidas()+",");
                }else{
                    System.out.println("\t \t \"" +player.getNome() + "\" : " + player.getPlayerKD().getKillsValidas());
                }
            });
            return game;
        }).map((game) -> {
            System.out.println("\t }");
            return game;
        }).map( new Function<Game, Game>() {
                    @Override
                    public Game apply(Game game) {
                        // Se a opcao escolhida no menu for sim, printa tipos de morte de cada partida                        
                        if (opcao == 1) {
                            Map<TiposDeMorte, Integer> tiposDeMorte = new HashMap<>();
                            
                            for (Jogador player : game.getJogadores()) {
                                Map<TiposDeMorte, Integer> tiposDeMortePlayer = player.getPlayerKD().getTiposDeMorte();
                                
                                tiposDeMortePlayer.entrySet().forEach((entry) -> {
                                    TiposDeMorte tipoDeMorte = entry.getKey();
                                    Integer Total = entry.getValue();
                                    
                                    if (!tiposDeMorte.containsKey(tipoDeMorte)) {
                                        tiposDeMorte.put(tipoDeMorte, 0);
                                    }
                                    tiposDeMorte.put(tipoDeMorte, tiposDeMorte.get(tipoDeMorte) + Total);
                                });
                            }
                            Iterator<Map.Entry<TiposDeMorte, Integer>> iteratorTiposDeMorte = tiposDeMorte.entrySet().iterator();
                            System.out.println("\t kills_by_means: {");
                            marcador=0;
                            while (iteratorTiposDeMorte.hasNext()) {
                                Map.Entry<TiposDeMorte, Integer> totalTipoDeMorte = iteratorTiposDeMorte.next();
                                TiposDeMorte tipoDeMorte = totalTipoDeMorte.getKey();
                                Integer total = totalTipoDeMorte.getValue();
                                marcador+=1;                                
                                if( tiposDeMorte.size() > marcador){
                                    System.out.println("\t \t"+tipoDeMorte + ": " + total+",");
                                }else{System.out.println("\t \t"+tipoDeMorte + ": " + total);}
                            }
                            System.out.println("\t  }");
                        }
                        return game;
                    }
                }).forEachOrdered((_item) -> {
            System.out.println("}");
        });
       
        System.out.println("Ranking: {");
        
        marcador=0;
        for (Jogador p : players) {
           
            String nome = p.getNome();
            Integer killsGame = p.getPlayerKD().getKillsValidas();
            if (!totalKillsPlayer.containsKey(nome)) {
                    totalKillsPlayer.put(nome, 0);
            }
            totalKillsPlayer.put(nome, totalKillsPlayer.get(nome) + killsGame);            
            
        }
	
        for (Map.Entry<String, Integer> Player : totalKillsPlayer.entrySet()) {
            marcador+=1;
            if (totalKillsPlayer.size() > marcador) {
                System.out.println("\t\"" + Player.getKey() + "\": " + Player.getValue()+",");
            }else{
                System.out.println("\t\"" + Player.getKey() + "\": " + Player.getValue());
            }
        }
        System.out.println("}");
        System.out.println("");
    }
}