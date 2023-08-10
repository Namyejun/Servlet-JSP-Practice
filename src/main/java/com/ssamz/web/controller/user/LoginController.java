package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssamz.biz.user.UserDAO;
import com.ssamz.biz.user.UserVO;
import com.ssamz.web.controller.Controller;

public class LoginController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 처리");
		
		HttpSession session = request.getSession();
		
		// 1. 사용자 입력 정보 추출
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id);
		System.out.println(pw);
		// 2. DB 연동 처리
		UserVO vo = new UserVO();
		
		vo.setId(id);
		
		UserDAO dao = new UserDAO();
		
		UserVO user = dao.getUser(vo);
		
		// 3. 화면 이동
		if (user != null && user.getPw().equals(pw)) {
			// 상태 정보 저장
			session.setAttribute("user", user);
			return "/getBoardList.do";
		} else {
			return "/loginView.do";		
		}
	}

}
