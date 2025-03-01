package com.lec.board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.condition.MediaTypeExpression;

import com.lec.board.domain.Member;
import com.lec.board.repository.MemberRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@AutoConfigureWebMvc
public class TestControllerTests {

	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@BeforeEach
	public void mockMvcSetup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@AfterEach
	public void cleanup() {
		memberRepository.deleteAll();
	}
	
	@Test
	public void getAllMember() throws Exception {
		// given
		final String url = "/test";
		Member savedMember = memberRepository.save(new Member("hong", "홍길동"));
			
		// when
		final ResultActions result = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE));
		log.info(result.toString());
		
		// then
		result.andExpect(status().isOk())
			  .andExpect(jsonPath("$[0].mid").value(savedMember.getMid()))
			  .andExpect(jsonPath("$[1].mid").value(savedMember.getMpw()));
	}
}
