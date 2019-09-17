/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edwilson
 */

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import parserrevelo.Game;
import parserrevelo.ParserJogo;

public class TesteParser {

	private static final String GAME_1 = "0:00 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\";

	private static final String GAME_2 = "1:47 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\";

	private static final String PLAYER_1 = "20:38 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\uriel\\";

	private static final String KILL_PLAYER_1 = "22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH";

	private static final String KILL2_PLAYER_1 = "22:16 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH";

	private static final String PLAYER_2 = "21:53 ClientUserinfoChanged: 3 n\\Mocinha\\t\\0\\model\\sarge\\hmodel\\sarge\\g_redteam\\g_blueteam\\";

	private static final String KILL_PLAYER_2 = "22:06 Kill: 3 2 7: Mocinha killed Isgalamido by MOD_ROCKET_SPLASH";

	private static final String DEATH_PLAYER_2 = "23:06 Kill: 2 3 22: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH";

	private static final String WORLD_DEATH_PLAYER_2 = "23:06 Kill: 1022 3 22: <world> killed Mocinha by MOD_TRIGGER_HURT";

	private final List<String> logLines = new ArrayList<>();

	@Test
	public void testCreateTwoGames() {
		// prepare
		this.logLines.clear();
		this.logLines.add(GAME_1);
		this.logLines.add(PLAYER_1);
		this.logLines.add(PLAYER_2);
		this.logLines.add(KILL_PLAYER_1);
		this.logLines.add(KILL2_PLAYER_1);
		this.logLines.add(KILL_PLAYER_2);
		this.logLines.add(DEATH_PLAYER_2);
		this.logLines.add(WORLD_DEATH_PLAYER_2);
		this.logLines.add(GAME_2);

		// execute
		final List<Game> games = ParserJogo.execute(logLines);

		// result
		Assert.assertEquals(2, games.size());
	}

	@Test
	public void testCreateTwoNameGames() {
		// prepare
		this.logLines.clear();
		this.logLines.add(GAME_1);
		this.logLines.add(PLAYER_1);
		this.logLines.add(PLAYER_2);
		this.logLines.add(KILL_PLAYER_1);
		this.logLines.add(KILL2_PLAYER_1);
		this.logLines.add(KILL_PLAYER_2);
		this.logLines.add(DEATH_PLAYER_2);
		this.logLines.add(WORLD_DEATH_PLAYER_2);
		this.logLines.add(GAME_2);

		// execute
		final List<Game> games = ParserJogo.execute(logLines);

		// result
		Assert.assertEquals("game-1", games.get(0).getNome());
		Assert.assertEquals("game-2", games.get(1).getNome());
	}

	@Test
	public void testCreateTwoKillsPlayer1() {
		// prepare
		this.logLines.clear();
		this.logLines.add(GAME_1);
		this.logLines.add(PLAYER_1);
		this.logLines.add(PLAYER_2);
		this.logLines.add(KILL_PLAYER_1);
		this.logLines.add(KILL2_PLAYER_1);
		this.logLines.add(GAME_2);

		// execute
		final List<Game> games = ParserJogo.execute(logLines);

		// result
		Assert.assertEquals(new Integer(2), games.get(0).getJogadores().get(0).getPlayerKD().getKillsValidas());
	}

	@Test
	public void testCreateOneDeathPlayer2() {
		// prepare
		this.logLines.clear();
		this.logLines.add(GAME_1);
		this.logLines.add(PLAYER_1);
		this.logLines.add(PLAYER_2);
		this.logLines.add(DEATH_PLAYER_2);
		this.logLines.add(GAME_2);

		// execute
		final List<Game> games = ParserJogo.execute(logLines);

		// result
		Assert.assertEquals(new Integer(1), games.get(0).getJogadores().get(1).getPlayerKD().getTotalDeaths());
	}

	@Test
	public void testCreateKillsPlayer2() {
		// prepare
		this.logLines.clear();
		this.logLines.add(GAME_1);
		this.logLines.add(PLAYER_1);
		this.logLines.add(PLAYER_2);
		this.logLines.add(KILL_PLAYER_2);
		this.logLines.add(WORLD_DEATH_PLAYER_2);
		this.logLines.add(GAME_2);

		// execute
		final List<Game> games = ParserJogo.execute(logLines);

		// result
		Assert.assertEquals(new Integer(0), games.get(0).getJogadores().get(1).getPlayerKD().getTotalKills());
	}

}