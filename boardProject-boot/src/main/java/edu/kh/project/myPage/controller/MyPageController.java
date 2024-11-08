package edu.kh.project.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("myPage")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

	private final MyPageService service;
	
	/**
	 * @param loginMember : 세션에 조재하는 loginMember를 얻어와 매개변수에 대입
	 * @return
	 */
	@GetMapping("info") // /myPage/info GET 방식 요청
	public String info(
				@SessionAttribute("loginMember") Member loginMember,
				Model model
			) {
		
		// 현재 로그인한 회원의 주소를 꺼내옴
		// 현재 로그인한 회원 정보 -> Session에 등록된 상태(loginMember)
		// log.debug("loginMember : "+loginMember);
		/*loginMember : Member(memberNo=2, memberEmail=memory1261@naver.com, 
		 * memberPw=null, memberNickname=우왁굳, memberTel=01000000000, 
		 * memberAddress=null, profileImg=21999^^^인천 연수구 송도동 7-8^^^왁물원, 
		 * enrollDate=2024년 11월 08일 11시 23분 08초, memberDelFl=null, authority=1)
		 * */
		String memberAddress = loginMember.getMemberAddress();
		
		// 주소가 있을 경우에만 동작
		if(memberAddress != null) {
			// 구분자 "^^^"를 기준으로
			// memberAddress 값을 쪼개어 String[]로 반환
			String[] arr = memberAddress.split("\\^\\^\\^");
			
			// 21999^^^인천 연수구 송도동 7-8^^^왁물원
			// -> ["21999", "인천 연수구 송도동 7-8", "왁물원"]
			//		  0					1				 2
			model.addAttribute("postcode", arr[0]);
			model.addAttribute("address", arr[1]);
			model.addAttribute("detailAddress", arr[2]);
			
		}
		
		
		// /templates/myPage/mypage-info.html로 forward
		return "myPage/myPage-info";
	}
	
	// 프로필 이미지 변경 화면 이동
	@GetMapping("profile") 
	public String profile() {
		return "myPage/myPage-profile";
	}
	
	// 비밀번호 변경 화면 이동
	@GetMapping("changePw")
	public String changePw() {
		return "myPage/myPage-changePw";
	}
	
	// 회원 탈퇴 화면 이동
	@GetMapping("secession")
	public String secession() {
		return "myPage/myPage-secession";
	}
	
	// 파일 업로드 테스트 화면 이동
	@GetMapping("fileTest") 
	public String fileTest() {
		return "myPage/myPage-fileTest";
	}
	
	// 파일 목록 조회 화면 이동
	@GetMapping("fileList") 
	public String fileList() {
		return "myPage/myPage-fileList";
	}
	
	
	/** 회원 정보 수정
	 * @param inputMember  : 커맨드 객체(@ModelAttribue 생략된 강태)
	 * 						 제출된 회원 닉네임, 전화번호, 주소
	 * @param loginMember  : 로그인한 회원 정보 (회원 번호 사용할 예정)
	 * @param memberAdress : 주소만 따로 받은 String[]
	 * @param ra		   : 리다이렉트 시 request scope로 message같은 데이터 전달
	 * @return
	 */
	@PostMapping("info")
	public String updateInfo(Member inputMember,
							 @SessionAttribute("loginMember") Member loginMember,
							 @RequestParam("memberAddress") String[] memberAdress,
							 RedirectAttributes ra) {
		
		// inputMember에 로그인한 회원 번호 추가
		inputMember.setMemberNo(loginMember.getMemberNo());
		// 회원 닉네임, 전화번호, 주소, 회원번호
		
		// 회원 정보 수정 서비스 호출
		int result = service.updateInfo(inputMember, memberAdress);
		
		// log.debug("inputMember : "+inputMember);
		
		String message = null;
		
		if(result>0) {
			message = "회원 정보 수정 성공!!!";
			
			// loginMember 새로 세팅
			// 우리가 방금 바꾼 값으로 세팅
			
			// loginMember는 세션에 저장된 로그인한 회원 정보가 저장된 객체를 참조하고 있다
			
			// -> loginMember를 수정하면
			//	  세션에 저장된 로그인한 회원 정보가 수정된다
			
			// == 세션 데이터와 DB 데이터를 맞춤
			
			loginMember.setMemberNickname(inputMember.getMemberNickname());
			loginMember.setMemberTel(inputMember.getMemberTel());
			loginMember.setMemberAddress(inputMember.getMemberAddress());
			
		} else {
			message = "회원 정보 수정 실패...";
		}
		
		ra.addFlashAttribute("message",message);
		
		return "redirect:info";
	}
	
	
	
}
