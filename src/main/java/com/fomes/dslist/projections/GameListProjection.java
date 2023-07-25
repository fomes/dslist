package com.fomes.dslist.projections;

import com.fomes.dslist.entities.GameList;

public interface GameListProjection {
    GameList createList(Long id, String name);
}
