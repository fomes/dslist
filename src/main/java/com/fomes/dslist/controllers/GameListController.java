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

import com.fomes.dslist.dto.GameListDTO;
import com.fomes.dslist.dto.GameMinDTO;
import com.fomes.dslist.dto.ReplacementDTO;
import com.fomes.dslist.entities.GameList;
import com.fomes.dslist.services.GameListService;
import com.fomes.dslist.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

  @Autowired
  private GameListService gameListService;

  @Autowired
  private GameService gameService;

  @GetMapping
  public List<GameListDTO> findAll() {
    List<GameListDTO> result = gameListService.findAll();
    return result;
  }

  @GetMapping(value = "/{listId}/games")
  public List<GameMinDTO> findByList(@PathVariable Long listId) {
    List<GameMinDTO> result = gameService.findByList(listId);
    return result;
  }

  @PostMapping(value = "/{listId}/replacement")
  public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
    gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
  }

  @PostMapping(value = "/new")
  public ResponseEntity<GameList> createList(@RequestBody GameList body) {
    List<GameListDTO> result = gameListService.findAll();
    Long newId = (long)result.size()+1;
    GameList list = gameListService.createList(newId, body.getName());
    return new ResponseEntity<>(list, HttpStatus.CREATED);
  }
}
