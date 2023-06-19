package com.example.board2.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board2.dto.ItemDto;

// @Controller : controller역할을 하는 클래스 , bean객체가 된다.
@Controller // RestController와 달리 html화면이 있기 때문에 그 화면으로 이동한다.
@RequestMapping(value="/thymeleaf") // 어노테이션 자체에 변수를 줄 수 있음 GetMapping과 비슷한데 경로를 만드는 것 
public class thymeleafExController {
	
	@GetMapping(value="/ex01")
	public String thymeleafExample01 (Model model) {
		model.addAttribute("data", "타임리프 예제입니다.");	// 앞단에 값을 넘겨주기 위함 (키,값) 형태
		return "tymeleafEx/thymeleafEx01";
	}
	
	@GetMapping(value="/ex02")
	public String thymeleafExample02(Model model) {
		ItemDto itemDto = new ItemDto();
		itemDto.setItemDetail("상품 상세설명");
		itemDto.setItemNm("테스트 상품1");
		itemDto.setPrice(10000);
		itemDto.setRegTime(LocalDateTime.now());
		
		model.addAttribute("itemDto", itemDto);
		
		return "tymeleafEx/thymeleafEx02";
	}
	
	@GetMapping(value="/ex03")
	public String thymeleafExample03(Model model) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		
		for(int i=0; i<=10; i++) {
			ItemDto itemDto = new ItemDto();
			itemDto.setItemDetail("상품 상세설명" + i);
			itemDto.setItemNm("테스트 상품1" + i);
			itemDto.setPrice(10000 * i);
			itemDto.setRegTime(LocalDateTime.now());
			
			itemDtoList.add(itemDto);
		}
		
		model.addAttribute("itemDtoList", itemDtoList);
		
		return "tymeleafEx/thymeleafEx03";
	}
	
	@GetMapping(value="/ex04")
	public String thymeleafExample04(Model model) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		
		for(int i=0; i<=10; i++) {
			ItemDto itemDto = new ItemDto();
			itemDto.setItemDetail("상품 상세설명" + i);
			itemDto.setItemNm("테스트 상품1" + i);
			itemDto.setPrice(10000 * i);
			itemDto.setRegTime(LocalDateTime.now());
			
			itemDtoList.add(itemDto);
		}
		
		model.addAttribute("itemDtoList", itemDtoList);
		
		return "tymeleafEx/thymeleafEx04";
	}
	
	@GetMapping(value="/ex05")
	public String thymeleafExample05() {
		
		return "tymeleafEx/thymeleafEx05";
	}
	
	@GetMapping(value="/ex06")
	public String thymeleafExample06(Model model,String param1, String param2 ) {
		System.out.println("param1" + param1 );
		System.out.println("param2" + param2 );
		
		//파라미터에 저장된 값을 그대로 model을 통해 넘겨준다.
		model.addAttribute("param1",param1);
		model.addAttribute("param2",param2);
		
		return "tymeleafEx/thymeleafEx06";
	}
	
	@GetMapping(value="/ex07")
	public String thymeleafExample07() {
		
		return "tymeleafEx/thymeleafEx07";
	}
}
