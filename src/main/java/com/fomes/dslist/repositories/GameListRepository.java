package com.fomes.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fomes.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{
  
}
