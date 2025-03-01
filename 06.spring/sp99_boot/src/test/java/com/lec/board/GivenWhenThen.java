package com.lec.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lec.board.domain.Menu;
import com.lec.board.repository.MenuRepository;
import com.lec.board.service.MenuService;
/*
	Given-When_Then 패턴
	
	이 테스트패턴은 테스트코드를 3단계로 구분해서 작성하는 패턴을 의미한다.
	
	① given은 테스트 실행을 준비하는 단계
	② when은 테스트를 진행하는 단계
	③ then은 테스트 결과를 검증하는 단계
*/
@SpringBootTest
public class GivenWhenThen {

	@Autowired
	private MenuService menuService;
	
	@Test
	@DisplayName("새로운 메뉴를 정한다.")
	public void saveMenuTest() {
		// 1) given
		final String name = "아메리카노";
		final int price = 2000;
		final Menu americano = new Menu(1L, name, price);
		
		// 2) when
		final long menuId = menuService.save(americano);
		
		String query = """ 
				  select * from member
				   where status = "aaa"
				""";
		
		String query1 = """
					{
						
					}
				""";
		record ItemDTO(String name, int price) {
			String getName() { 
				return name; 
			}
			
			int getPrice() {
				return price;
			}
		}
		
		ItemDTO dto = new ItemDTO("hong", 10);
		dto.getName();

	}
}
