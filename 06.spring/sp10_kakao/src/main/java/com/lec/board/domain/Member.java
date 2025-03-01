package com.lec.board.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{

	@Id
	private String mid;
	
	private String mpw;
	private String email;
	private boolean del;
	
	private boolean social;
	
	/*
		@ElementCollection은 값타입컬렉션을 매핑할 때 사용(값의 타입을 컬렉션에
		담아서 사용할 때, 해당 컬렉션을 값타입컬렉션이라고 한다.)
		
		RDB에는 컬렉션과 같은 형태의 데이터를 저장할 수 없기 때문에, 별동의 테이블을
		생성하여 컬렉션을 관리해야 한다. 이때 해당 필드가 컬렉션객체임을 JPA에게 알려
		주는 어노테이션이 @ElementCollection이다.
		
		@Entity가 아닌 Basic Type이나 Embeded Class로 정의된 컬렉션을 테이블로 생성
		하며 One-To-Many관계를 다룬다.
	*/	

	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private Set<MemberRole> roleSet = new HashSet<>();
	
	public void changePassword(String mpw) {
		this.mpw = mpw;
	}
	
	public void changeEmail(String email) {
		this.email = email;
	}
	
	public void changeDel(boolean del) {
		this.del = del;
	}
	
	public void addRole(MemberRole memberRole) {
		this.roleSet.add(memberRole);
	}
	
	public void clearRole() {
		this.roleSet.clear();
	}
	
	public void changeSocial(boolean social) {
		this.social = social;
	}
}
