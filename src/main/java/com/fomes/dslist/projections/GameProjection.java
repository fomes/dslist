package com.fomes.dslist.projections;

import com.fomes.dslist.entities.Game;

public interface GameProjection {
  Game createGame(Long id, String title, Integer year, String genre, String platforms, Double score, String imgUrl,
  String shortDescription, String longDescription);
}
