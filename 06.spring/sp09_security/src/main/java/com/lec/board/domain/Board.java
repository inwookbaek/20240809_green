package com.lec.board.domain;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageSet")
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(length = 2000, nullable = false)
	private String content;

	@Column(length = 50, nullable = false)
	private String writer;
	
	public void change(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	/*
		@OneToMany는 기본엔티티와 중간에 매핑하는 테이블이 생성된다.
		board_image와 board_image_set 테이블이 생성된다.
		매핑테이블 즉, board_image_set을 생성하지 않는 방법은
		1. @OneToMany를 사용할 경우에는 @JoinColumn을 이용
		2. mappedBy속성을 이용하는 방법 : Board와 BoardImage가 양방향참조하는 상황에 사용
		   mappedBy는 '어떤 엔티티의 속성으로 매핑되는지를 의미'	
	*/
	@OneToMany(mappedBy = "board", // BoardImage에서 Board 변수 
			   cascade = {CascadeType.ALL}, // 영속성 -> board이 변경이 되면 boardImage로 같이 변경
			   // @OneToMany는 기본적으로 Lazy로딩, board와 boardImage를 2번 select가 필용
			   // 이런 경우에 JPA는 첫 번째(board)select후 DB세션을 끊고 2번째(boardImge)
			   // select할 경우 DB세션이 종료가 된 상태가 된다. 이런 경우 에러가 발생된다.
			   // 이 문제를 해결하기 위해서 @Transaction을 추가(메서드내에서는 여러번 쿼리를
			   // 수행할 수 있다) 하위엔티티를 로딩하는 가장 간단한 방법은 EAGER(즉시로딩)를
			   // 적용하는 것이지만 @OneToMany는 지연로딩이 기본방식이므로 @EntityGraph를 이용
			   fetch = FetchType.LAZY, 
			   orphanRemoval = true)	
	@Builder.Default
	@BatchSize(size = 20)
	// size속성값은 지정된 수만큼 BoardImage를 조회할 때 한 번에 in 조건으로 사용된다.
	private Set<BoardImage> imageSet = new HashSet<>();
	
	public void addImage(String uuid, String fileName) {
		BoardImage boardImage = BoardImage.builder()
				.uuid(uuid)
				.fileName(fileName)
				.board(this)
				.ord(imageSet.size())
				.build();
		imageSet.add(boardImage);
	}
	
	public void clearImage() {
		imageSet.forEach(boardImage -> boardImage.changeBoard(null));
		this.imageSet.clear();
	}
}

