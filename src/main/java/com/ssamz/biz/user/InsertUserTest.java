package com.ssamz.biz.user;

public class InsertUserTest {

	public static void main(String[] args) {
		// UserDAO 객체 생성
		UserDAO userDAO = new UserDAO();
		
		// VO 객체 생성
		UserVO vo = new UserVO();
		vo.setId("ssamz4");
		vo.setPw("ssamz123");
		vo.setName("쌤즈");
		vo.setRole("ADMIN");
		
		// 회원 정보 등록
		userDAO.insertUser(vo);
		
		// 회원 목록 출력
		userDAO.getUserList();
	}
}
