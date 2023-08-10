package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.biz.user.UserDAO;
import com.ssamz.biz.user.UserVO;
import com.ssamz.web.controller.Controller;

public class InsertUserController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("회원가입 처리");
		
		// 1. 사용자 입력 정보 추출
		String id = (String) request.getParameter("id");
		String pw = (String) request.getParameter("pw");
		String name = (String) request.getParameter("name");
		String role = (String) request.getParameter("role");
		
		// 2. DB 연동 처리
		UserVO vo = new UserVO();
		
		vo.setId(id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setRole(role);
		
		UserDAO dao = new UserDAO();
		
		dao.insertUser(vo);
		return "index";
	}

}
