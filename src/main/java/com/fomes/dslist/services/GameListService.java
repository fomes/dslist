package com.fomes.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fomes.dslist.dto.GameListDTO;
import com.fomes.dslist.entities.GameList;
import com.fomes.dslist.projections.GameListProjection;
import com.fomes.dslist.projections.GameMinProjection;
import com.fomes.dslist.repositories.GameListRepository;
import com.fomes.dslist.repositories.GameRepository;

@Service
public class GameListService implements GameListProjection {

  @Autowired
  private GameListRepository gameListRepository;

  @Autowired
  private GameRepository gameRepository;

  @Transactional
  public List<GameListDTO> findAll() {
    List<GameList> result = gameListRepository.findAll();
    return result.stream().map(x -> new GameListDTO(x)).toList();
  }

  @Transactional
  public void move(Long listId, int sourceIndex, int destinationIndex) {
    List<GameMinProjection> list = gameRepository.searchByList(listId);

    GameMinProjection obj = list.remove(sourceIndex);
    list.add(destinationIndex, obj);

    int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
    int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

    for (int i = min; i <= max; i++) {
      gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
    }
  }

  @Override
  public GameList createList(Long id, String name) {
      GameList list = new GameList();
      list.setId(id);
      list.setName(name);

      return gameListRepository.save(list);
  }
}
