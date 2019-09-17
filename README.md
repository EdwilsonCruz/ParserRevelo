# ParserRevelo
 Parser Quake 3

1- Este aplicativo escrito em Java que ler as informaçoes do Log gerado por um servidor de Quake III.
   Ele agrupa as informações em um ranking geral, e de todas as partidas disputadas e de todas as mortes e seus tipos.
  

2- Requisitos para rodar o aplicativo:
 * Java 8
 Com o Java configurado na maquina, abra o prompt de Comando e entre no diretorio onde se encontra o arquivo
ParserRevelo.jar.
2.1 - Execute o comando 'java -jar ParserRevelo.jar
O programa ira pedir um arquivo, entre com o arquivo teste.txt que se encontra dentro do diritorio do projeto.
Se diretorio estiver correto o aplicativo ira apresentar na tela opçoes de relatorios, ou sair(fnalizar o programa).

3- Primeiro foi analizado o Log gerado pelo servidor e as palavras de cada evento em foco:
	A partida inicia com "InitGame":
		0:00 InitGame: \sv_floodProtect\1\sv_maxPing...
	Cada jogador que entra na partida gera um comando "ClientUserinfoChanged" e um numero Identificador "id"
que é unico para cada Jogador:
		Ex: 21:51 ClientUserinfoChanged: 2 n\Dono da Bola\t\0\...
	O evento de morte de algum Jogador é reconhecido pela palavra "Kill", neste evento obtemos os numeros 
identificadores do player que realizou a kill, do player que foi morto e também do tipo de morte ocorrido;
		22:06 Kill: 2 2 4: Isgalamido killed Mocinha by MOD_SPLASH

4- No final do programa sempre gera um Ranking geral.
