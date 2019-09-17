/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserrevelo;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import static parserrevelo.RelatorioJogo.RelatorioJogo;

/**
 *
 * @author Edwilson
 */
public class ParserRevelo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {        
        // TODO code application logic here
        //variaveis e objetos
        String diretorio;
        List<String> linhas;
        int opcao=0 ;
        
        //Inicio do programa
        System.out.println("--------------------");
        System.out.println("Parser Revelo");
        System.out.println("Edwilson Cruz");
        System.out.println("--------------------");
        //Entrada da informação do diretorio onde se encontra o arquivo de Log
        System.out.println("Digite o caminho do arquivo:");
        
        Scanner entrada = new Scanner (System.in);
        diretorio = entrada.next();        
        Path caminho = Paths.get(diretorio);
        
        boolean arquivoExiste = Files.exists(Paths.get(diretorio));
       //rotina menu 
        do{
             //se o arquivo existir execute se não (avise e retorne)
            if (arquivoExiste) {
                System.out.println("Apresentar relatorio com motivos de Morte ?");
                System.out.println("1: Sim");
                System.out.println("2: Nao");
                System.out.println("0: Sair");
                opcao = new Scanner (System.in).nextInt();
                if(opcao ==1 || opcao ==2){
                    try {
                        linhas = Files.readAllLines(caminho);
                        ParserJogo game = new ParserJogo(linhas);
                        List<Game> games = game.parserJogo();
                        RelatorioJogo(games, opcao);
                    } catch (IOException ex) {
                        System.out.println("Error - "+ ex.getMessage());
                        throw new Exception (ex.getMessage());
                    }    
                }
            }
            else{
                //Se o arquivo nao existe, informar Error arquivo não encontrado
                System.out.println ("Error - Arquivo não encontrado.");
            }            
        }
        while(opcao!=0);
    }
}
