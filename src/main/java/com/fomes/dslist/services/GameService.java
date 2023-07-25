package com.fomes.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fomes.dslist.dto.GameDTO;
import com.fomes.dslist.dto.GameMinDTO;
import com.fomes.dslist.entities.Game;
import com.fomes.dslist.projections.GameMinProjection;
import com.fomes.dslist.projections.GameProjection;
import com.fomes.dslist.repositories.GameRepository;

@Service
public class GameService implements GameProjection {

  @Autowired
  private GameRepository gameRepository;

  @Transactional
  public List<GameMinDTO> findAll() {
    List<Game> result = gameRepository.findAll();
    List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();
    return dto;
  }

  @Transactional(readOnly = true)
  public GameDTO finById(Long id) {
    Game result = gameRepository.findById(id).get();
    return new GameDTO(result);
  }

  @Transactional
  public List<GameMinDTO> findByList(Long listId) {
    List<GameMinProjection> result = gameRepository.searchByList(listId);
    return result.stream().map(x -> new GameMinDTO(x)).toList();
  }

  @Override
  public Game createGame(Long id, String title, Integer year, String genre, String platforms, Double score,
      String imgUrl,
      String shortDescription, String longDescription) {
    Game game = new Game();
    game.setId(id);
    game.setTitle(title);
    game.setYear(year);
    game.setGenre(genre);
    game.setPlatforms(platforms);
    game.setScore(score);
    game.setImgUrl(imgUrl);
    game.setShortDescription(shortDescription);
    game.setLongDescription(longDescription);

    return gameRepository.save(game);
  }
}
