package edu.kh.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Spring EL 같은 경우 DTO 객체 출력할 때 getter가 필수로 작성되어있어야
// -> ${Student.getName()} == ${Student.name}
// getter 대신 필드명 호출하는 형식으로 EL에 작성을 하게 되는데
// 자동으로 getter 메서드를 호출하기 때문!

@Getter
@Setter
@ToString
@NoArgsConstructor	// 기본 생성자
@AllArgsConstructor // 모든필드 초기화용 매개변수생성자
public class Student {

	
	private String studentNo; // 학생 번호
	private String name;	  // 이름
	private int age;		  // 나이
}
