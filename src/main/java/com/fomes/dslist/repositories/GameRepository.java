package com.fomes.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fomes.dslist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
  
}
