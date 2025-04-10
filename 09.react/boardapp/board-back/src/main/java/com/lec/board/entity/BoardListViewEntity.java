package com.lec.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/*
1. 기본적인 View 엔티티 매핑
   가장 기본적인 방법은 @Entity와 @Table 어노테이션을 사용하는 것

2. Native Query를 사용한 View 매핑

@Immutable // 뷰는 불변(읽기 전용)으로 설정
@Subselect("SELECT e.id as employee_id, e.name as employee_name, d.name as department_name " +
           "FROM employees e JOIN departments d ON e.department_id = d.id")

3. @SecondaryTable 사용하기 : 여러 테이블이나 뷰를 조인한 결과를 매핑할 때 사용할 수 있다.

@Entity
@Table(name = "employees")
@SecondaryTable(name = "employee_details_view", pkJoinColumns = @PrimaryKeyJoinColumn(name = "emp_id"))

4. View에 대한 주의사항
   1) 읽기 전용: 대부분의 뷰는 업데이트가 불가능하므로 @Immutable 사용을 권장
   2) ID 필드: 뷰도 @Id를 지정해야 함 (뷰에 적절한 PK 컬럼이 없으면 모든 컬럼을 결합한 복합 키 사용)
   3) 성능: 복잡한 뷰는 쿼리 성능에 영향을 줄 수 있음
   4) 스키마 생성: spring.jpa.hibernate.ddl-auto가 설정되어 있으면 뷰가 자동 생성되지 않음 (수동으로 생성 필요)
*/
@Getter
@Entity(name = "board_list_view")
@Table(name = "board_list_view")
@NoArgsConstructor
@AllArgsConstructor
public class BoardListViewEntity {

	@Id
	private int boardNumber;
	private String title;
	private String content;
	private String titleImage;
	private int favoriteCount;
	private int commentCount;
	private int viewCount;
	private String writeDatetime;
	private String writerEmail;
	private String writerNickname;
	private String writerProfileImage;
}
