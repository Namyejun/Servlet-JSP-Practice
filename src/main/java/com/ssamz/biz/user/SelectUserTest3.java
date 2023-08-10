package com.ssamz.biz.user;

import java.util.List;

public class SelectUserTest3 {

	public static void main(String[] args) {
		//  UserDAO 객체 생성
		UserDAO userDAO = new UserDAO();
		
		// 목록 조회
		List<UserVO> userList = userDAO.getUserList();
		
		// Case 1
		System.out.println("전체 회원의 수 : " + userList.size());
		
		// Case 2
		System.out.println("[ 회원의 권한 ]");
		for(UserVO user : userList) {
			String name = user.getName();
			String role = user.getRole();
			System.out.println(name + "의 권한 : " + role);
		}
	}

}
