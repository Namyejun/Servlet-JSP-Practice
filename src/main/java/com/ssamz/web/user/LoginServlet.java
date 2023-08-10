package com.ssamz.web.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssamz.biz.user.UserDAO;
import com.ssamz.biz.user.UserVO;

//@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자 입력정보 추출
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		// 2. DB 연동 처리
		UserVO vo = new UserVO();
		vo.setId(id);

		UserDAO dao = new UserDAO();
		UserVO user = dao.getUser(vo);

		// 3. 응답 화면 구성
		// 응답 메시지에 대한 인코딩 설정
		response.setContentType("text/html;charset=UTF-8");
		// HTTP 응답 프로토콜 message-body와 연결된 출력 스트림 획득
		PrintWriter out = response.getWriter();

		// 메시지 출력
		if (user != null) {
			if (user.getPw().equals(pw)) {
//				// 상태 정보를 쿠키에 저장하여 전송한다. 
//				Cookie userId = new Cookie("userId", user.getId());
//				response.addCookie(userId);

				// 상태 정보를 세션에 저장한다. 
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10);
				session.setAttribute("user", user);	
				
				// 글 목록 화면으로 포워딩한다.
				// 글 목록 화면에서 사용할 데이터를 ServletContext에 등록한다.
				ServletContext context = getServletContext();
				context.setAttribute("welcomeMessage", " 환영합니다.");
				response.sendRedirect("getBoardList.do");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("getBoardList.do");
//				dispatcher.forward(request, response);
			} else {
				out.println("비밀번호 오류입니다.<br>");
				out.println("<a href='/'>다시 로그인</a>");
			}
		} else {
			out.println("아이디 오류입니다.<br>");
			out.println("<a href='/'>다시 로그인</a>");
		}

	}

}
