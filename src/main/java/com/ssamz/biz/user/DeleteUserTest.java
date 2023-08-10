package com.ssamz.biz.user;

public class DeleteUserTest {

	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		userDAO.deleteUser("ssamz3");
		userDAO.getUserList();
	}
}
