package com.lec.file.domain;

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
@Table(name = "Reply", indexes = {@Index(name = "idx_reply_board_bno", columnList = "board_bno")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
// @ToString(exclude = "board")
// p539 설명참조 : @Transaction과 연관
// exclude가 없다면 board도 쿼리를 실행해야 하는 상황이기 때문에 DB를 다시 한번 더 
// 연결해야 하는 상황이다. 테스트코드는 한 번만 실행할 수 있기 때문에 에러가 발생한다.
// 이를 해결하기 위해 강제로 실행하려면 테스트코드에 @Transactional을 추가하면 가능하다.
// 테스트시만 @ToString / @Transaction을 추가하고 live에서는 @ToString(exclude = "board")
// 만 추가할 것(테스트 코드에서 @Transaction은 삭제)
@ToString 
public class Reply extends BaseEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    // @ManyToOne과 같이 연관관계를 나타낼 때는 반드시 FetchType.LAZY로 지정
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String replyText;

    private String replyer;

    public void changeText(String text){
        this.replyText = text;
    }
}
