package com.example.player.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.player.model.Player;
import com.example.player.service.PlayerH2Service;

@RestController
public class PlayerController {
	
	@Autowired
	PlayerH2Service playerService ;
	
	@GetMapping("/players")
    public ArrayList<Player> get_Player_List(){

        return playerService.getPlayerList();
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player){

        return playerService.addPlayer(player);
    }

     @GetMapping("/players/{playerId}")
    public Player getPlayerById(@PathVariable("playerId") int playerId){

        return playerService.getPlayerById(playerId);
    } 
    
    @PutMapping("/players/{playerId}")
    public Player updatePlayer(@PathVariable("playerId") int playerId, @RequestBody Player player) {

        return playerService.updatePlayer(playerId, player);
    }

    @DeleteMapping("/players/{playerId}")
    public ArrayList<Player> deletePlayer(@PathVariable("playerId") int playerId) {
    
    	playerService.deletePlayer(playerId);
        return playerService.getPlayerList();
    }

}
