package com.ssamz.biz.user;

public class UpdateUserTest {

	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		UserVO vo = new UserVO();
		vo.setName("수정");
		vo.setRole("USER");
		vo.setId("ssamz4");
		userDAO.updateUser(vo);
		userDAO.getUserList();
	}

}
