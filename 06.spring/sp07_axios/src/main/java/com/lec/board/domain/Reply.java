package com.lec.board.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reply", indexes = {
		@Index(name = "idx_reply_board_bno", columnList = "board_bno")
})
@ToString
// @ToString(exclude = "board")
// exclude가 없다면 board도 쿼리를 실행하야 하는 상황이기 때문에 DB를 한번 연결해야하는 상황이 된다.
// 테스트코드는 한번만 실행할 수 있기 때문에 에러가 발생한다. 이를 해결하기 위해 강제로 실행하려면
// 테스트코드에 @Transaction을 추가 하면 가능하다.
// 테스트할 때만 @ToString / @Transaction을 추가하고 실제환경에서는 @ToString(exclude = "board")만
// 추가하면된다(테스트코드에서는 @Transaction을 삭제)
public class Reply extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	
	// @ManyToOne과 같이 연관관계를 나타낼 때는 반드시 FetchType.LAZY
	// FetchType LAZY(지연로딩) EAGER(즉시로딩)
	// 지연로딩은 기본적으로 필요한 순간까지 데이터베이스와 연결하지 않는 방식으로 동작한다.
	// 즉시로딩은 해당 엔티티를 로딩할 때 같이 로딩하는 방식이다. EAGER는 성능에 영향을 줄
	// 수 있기 때문에 LAZY를 사용하고 필요에 따라 EAGER를 사용하면 된다.
	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;
	
	private String replyText;
	private String replyer;
	
	public void changeText(String replyText) {
		this.replyText = replyText;
	}
}
