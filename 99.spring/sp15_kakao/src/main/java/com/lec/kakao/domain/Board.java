package com.lec.kakao.domain;

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
import lombok.ToString;

@Entity
@Getter
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
	
	// p614~
	// @OneToMany는 기본엔티티와 중간에 매핑하는 테이블이 생성된다.
	// board_image와 board_image_set 테이블이 생성
	// 매핑테이블을 생성하지 않는 방법은
	// 1. @OneToMany를 이용하는 경우 @JoinColumn을 이용
	// 2. mappedBy 속성을 이용하는 방법 : Board와 BoardImage가 양방향 참조하는 상황에서 사용
	//    mappedBy는 '어떤 엔티티의 속성으로 매핑되는지를 의미"
    @OneToMany(mappedBy = "board",         // BoardImage의 board변수 
            cascade = {CascadeType.ALL},   // 영속성의 전이 p616
            fetch = FetchType.LAZY,        // @OneToMany는 기본적으로 Lazy로딩 즉, 기본적으로 board와 boardimage 
    									   // 2번의 select가 필요, 이런 경우 2번째 select는 DB세션이 종료된 상태
    									   // 이기 때문에 에러 발생
    									   // 이 문제를 해결하기 위새 @Transaction을 추가(메소드내에서 여러번 쿼리수행가능)
										   // 하위엔티티를 로딩하는 가장 간단한 방법은 즉시(Eager)로딩을 적용하는 것이지만
										   // 지연로딩이 기본방식이므로 @EntityGraph를 이용 -> BoardRepository의 findByIdWithImages()
            orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 200)
    private Set<BoardImage> imageSet = new HashSet<>();
    
    public void addImage(String uuid, String fileName){
        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .board(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(boardImage);
    }    
	
    public void clearImages() {
        imageSet.forEach(boardImage -> boardImage.changeBoard(null));
        this.imageSet.clear();
    }    
}
