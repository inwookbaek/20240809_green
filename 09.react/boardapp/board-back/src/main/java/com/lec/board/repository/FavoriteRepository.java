package com.lec.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lec.board.entity.FavoriteEntity;
import com.lec.board.entity.primaryKey.FavoritePK;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePK> {

}
