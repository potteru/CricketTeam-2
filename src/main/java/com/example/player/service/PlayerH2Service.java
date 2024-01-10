package com.example.player.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.player.model.Player;
import com.example.player.model.PlayerRowMapper;
import com.example.player.repository.PlayerRepository;

@Service
public class PlayerH2Service implements PlayerRepository {

	// Do not modify the above code

	// Write your code here
	@Autowired
	private JdbcTemplate db;

	@Override
	public ArrayList<Player> getPlayerList() {
		Collection<Player> playerlist = db.query("select * from team", new PlayerRowMapper());
		ArrayList<Player> arrList = new ArrayList<>(playerlist);
		return arrList;
	}

	@Override
	public Player getPlayerById(int playerId) {
		try {
			Player player = db.queryForObject("select * from team where playerId = ?", new PlayerRowMapper(), playerId);
			return player;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Player addPlayer(Player player) {

		db.update("insert into team( playerName, jerseyNumber, role) values (?, ?, ?)", player.getPlayerName(),
				player.getJerseyNumber(), player.getRole());

		Player savedPlayer = db.queryForObject(
				"select * from team where playerName = ? and  jerseyNumber = ? and role = ?",
				new PlayerRowMapper(), player.getPlayerName(), player.getJerseyNumber(), player.getRole());

		return savedPlayer;
	}

	@Override
	public Player updatePlayer(int playerId, Player player) {

		try {
			if (player.getPlayerName() != null) {
				db.update("update team set playerName = ? and jerseyNumber = ? where playerId = ?",
						player.getPlayerName(), player.getJerseyNumber());
			}
			return getPlayerById(playerId);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deletePlayer(int playerId) {

		db.update("delete from team where playerId = ?", playerId);

	}

}