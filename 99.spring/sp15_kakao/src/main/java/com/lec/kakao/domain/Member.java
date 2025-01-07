package com.lec.kakao.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{

    @Id
    private String mid;

    private String mpw;
    private String email;
    private boolean del;

    private boolean social;
    
    /*
		값 타입 컬렉션을 매핑할 때 사용(값 타입을 컬렉션에 담아서 사용할 때, 해당 컬렉션을 값 타입 컬렉션이라 한다)
		 
		RDB에는 컬렉션과 같은 형태의 데이터를 컬럼에 저장할 수 없기 때문에, 별도의 테이블을 생성하여 컬렉션을 관리해야 합니다.
		이때 해당 필드가 컬렉션 객체임을 JPA에게 알려주는 어노테이션이 @ElementCollection 입니다.
		 
		@Entity가 아닌 Basic Type이나 Embeddable Class로 정의된 컬렉션을 테이블로 생성하며 One-To-Many 관계로 다룹니다
    */
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String mpw ){
        this.mpw = mpw;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public void changeDel(boolean del){
        this.del = del;
    }

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void clearRoles() {
        this.roleSet.clear();
    }

    public void changeSocial(boolean social){this.social = social;}
}