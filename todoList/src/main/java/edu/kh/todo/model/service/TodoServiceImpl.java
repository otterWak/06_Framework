package edu.kh.todo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.todo.model.dao.TodoDAO;

@Service // 비즈니스 로직(데이터 가공, 트랜잭션 처리) 역할 명시 + Bean 등록
public class TodoServiceImpl implements TodoService{

	@Autowired // TodoDAO와 같은 타입 Bean 의존성 주입(DI)
	private TodoDAO dao;
	
	// (TEST) todoNo가 1인 할 일 제목 조회
	@Override
	public String testTitle() {
		return dao.testTitle();
	}

}
