package edu.kh.project.myPage.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class) // 모든 예외 발생 시 롤백 / 아니면 커밋
@RequiredArgsConstructor
@Slf4j
public class MyPageServiceImpl implements MyPageService{

	private final MyPageMapper mapper;
	
	// 회원 정보 수정
	@Override
	public int updateInfo(Member inputMember, String[] memberAdress) {

		// 입력된 주소가 있을 경우
		// memberAddress를 A^^^B^^^C 형태로 가공
		
		// 주소 입력 X -> inputMember.getMemberAddress(0 -> ",,"
		if(inputMember.getMemberAddress().equals(",,")) {
			
			// 주소에 null 대입
			inputMember.setMemberAddress(null);
			
		} else {
			String address = String.join("^^^", memberAdress);
			inputMember.setMemberAddress(address);
		}
		
		return mapper.updateInfo(inputMember);
	}
	
	
	
	
	
	
	
	
}
