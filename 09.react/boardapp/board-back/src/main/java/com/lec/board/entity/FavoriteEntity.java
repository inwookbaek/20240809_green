package com.lec.board.entity;

import com.lec.board.entity.primaryKey.FavoritePK;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "favorite")
@Table(name = "favorite")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FavoritePK.class)
public class FavoriteEntity {

	@Id 
	private String userEmail;
	
	@Id 
	private int boardNumber;
}
