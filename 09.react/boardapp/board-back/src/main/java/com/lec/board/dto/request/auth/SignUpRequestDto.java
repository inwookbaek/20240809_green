package com.lec.board.dto.request.auth;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
	
	@NotBlank @Email
	private String email;
	
	@NotBlank @Size(min = 8, max = 20)
	private String password;
	
	@NotBlank
	private String nickname;
		
	@NotBlank @Pattern(regexp = "^[0-9]{11,13}$") // "^01[016789]-?\\d{3,4}-?\\d{4}$") 
	private String telNumber;
	
	@NotBlank
	private String address;
	
	private String addressDetail;
	
	@NotNull @AssertTrue
	private boolean agreedPersonal;

}
