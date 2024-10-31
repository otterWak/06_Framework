package edu.kh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.todo.model.service.TodoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그 객체 자동 생성
@Controller // 요청/응답 제어 역할 명시 + Bean 등록
public class MainController {

	@Autowired // 등록된 Bean 중 같은 타입이거나 상속관계 DI(의존성 주입)
	private TodoService service;
	
	@RequestMapping("/")
	public String mainPage(Model model) {
		
		log.debug("service : " + service);
		// edu.kh.todo.model.service.TodoServiceImpl@61fd30d5
		
		// todoNo가 1인 todo의 제목 조회하여 request scope에 추가
		String testTitle = service.testTitle();
		model.addAttribute("testTitle", testTitle);
		
		
		
		// 접두사 : classpath:/templates/
		// 접미사 : .html
		return "common/main"; // forward
	}
	
	
}
