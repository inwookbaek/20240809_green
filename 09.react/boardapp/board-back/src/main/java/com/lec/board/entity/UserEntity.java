package com.lec.board.entity;

import com.lec.board.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Entity(name="user")
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

	@Id 
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.IDENTITY)는 
	// 기본 키가 자동 증가하는 정수(Long or Integer) 타입에서만 사용 가능
	private String email;
	private String password;
	private String nickname;
	private String telNumber;
	private String address;
	private String addressDetail;
	private boolean agreedPersonal;
	private String profileImage;
	
	public UserEntity(SignUpRequestDto dto) {
		this.email = dto.getEmail();
		this.password = dto.getPassword();
		this.nickname = dto.getNickname();
		this.telNumber = dto.getTelNumber();
		this.address = dto.getAddress();
		this.addressDetail = dto.getAddressDetail();
		this.agreedPersonal = dto.isAgreedPersonal();	
		this.profileImage = null;
	}
}