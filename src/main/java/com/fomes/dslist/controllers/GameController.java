package com.fomes.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fomes.dslist.dto.GameDTO;
import com.fomes.dslist.dto.GameMinDTO;
import com.fomes.dslist.entities.Game;
import com.fomes.dslist.services.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {

  @Autowired
  private GameService gameService;

  @GetMapping(value = "/{id}")
  public GameDTO findById(@PathVariable Long id) {
    GameDTO result = gameService.finById(id);
    return result;
  }

  @GetMapping
  public List<GameMinDTO> findAll() {
    List<GameMinDTO> result = gameService.findAll();
    return result;
  }

  @PostMapping(value = "/new")
  public ResponseEntity<Game> createGame(@RequestBody Game body) {
    List<GameMinDTO> result = gameService.findAll();
    Long newId = (long) result.size() + 1;
    Game game = gameService.createGame(newId, body.getTitle(), body.getYear(), body.getGenre(), body.getPlatforms(),
        body.getScore(), body.getImgUrl(), body.getShortDescription(), body.getLongDescription());
    return new ResponseEntity<>(game, HttpStatus.CREATED);
  }
}
