package edu.kh.project.myPage.model.service;

import edu.kh.project.member.model.dto.Member;

public interface MyPageService {

	/** 회원 정보 수정 서비스
	 * @param inputMember
	 * @param memberAdress
	 * @return int result
	 */
	int updateInfo(Member inputMember, String[] memberAdress);

	

}
