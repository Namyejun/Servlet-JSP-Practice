package com.ssamz.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssamz.biz.board.BoardDAO;
import com.ssamz.biz.board.BoardVO;
import com.ssamz.biz.user.UserDAO;
import com.ssamz.biz.user.UserVO;

/**
 * Servlet implementation class DispatcherServlet
 */
//@WebServlet("*.do")
public class DispatcherServletBackup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServletBackup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 요청 path를 추출한다.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);
		// 2. 추출된 path 정보에 따라 요청을 분기 처리한다.
		if (path.equals("/login.do")) {
			System.out.println("로그인 처리");
			
			HttpSession session = request.getSession();
			
			// 1. 사용자 입력 정보 추출
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");

			// 2. DB 연동 처리
			UserVO vo = new UserVO();
			
			vo.setId(id);
			
			UserDAO dao = new UserDAO();
			
			UserVO user = dao.getUser(vo);
			
			// 3. 화면 이동
			if (user != null && user.getPw().equals(pw)) {
				// 상태 정보 저장
				session.setAttribute("user", user);
				
				// 글 목록 화면 이동
				RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
				dispatcher.forward(request, response);
			} else {
				// 글 목록 화면 이동
				RequestDispatcher dispatcher = request.getRequestDispatcher("/loginView.do");
				dispatcher.forward(request, response);				
			}
		} else if (path.equals("/insertUser.do")) {
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
			
			// 3. 화면 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else if (path.equals("/logout.do")) {
			System.out.println("로그아웃 처리");
			HttpSession session = request.getSession();
			
			session.invalidate();
			response.sendRedirect("/");
		} else if (path.equals("/insertBoard.do")) {
			System.out.println("글 등록 처리");
			
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			
			BoardDAO dao = new BoardDAO();
			dao.insertBoard(vo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
		} else if (path.equals("/updateBoard.do")) {
			System.out.println("글 수정 처리");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setSeq(seq);
			
			BoardDAO dao = new BoardDAO();
			dao.updateBoard(vo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
		} else if (path.equals("/deleteBoard.do")) {
			System.out.println("글 삭제 처리");
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			BoardVO vo = new BoardVO();
			vo.setSeq(seq);
			
			BoardDAO dao = new BoardDAO();
			dao.deleteBoard(vo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
		} else if (path.equals("/getBoard.do")) {
			System.out.println("글 상세 조회 처리");
			int seq = Integer.parseInt(request.getParameter("seq"));
			BoardVO vo = new BoardVO();
			vo.setSeq(seq);
			
			BoardDAO dao = new BoardDAO();
			BoardVO board = dao.getBoard(vo);
			
			request.setAttribute("board", board);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/getBoard.jsp");
			dispatcher.forward(request, response);
		} else if (path.equals("/getBoardList.do")) {
			System.out.println("글 목록 조회 처리");
			HttpSession session = request.getSession();
			
			// 0. 사용자 입력 정보 추출
			String searchCondition = request.getParameter("searchCondition");
			String searchKeyword = request.getParameter("searchKeyword");
			
			if (searchCondition == null) searchCondition = "TITLE";
			if (searchKeyword == null) searchKeyword = "";
			
			// 세션 저장
			session.setAttribute("condition", searchCondition);
			session.setAttribute("keyword", searchKeyword);
			
			// 1. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSearchCondition("TITLE");
			vo.setSearchKeyword("");
			
			BoardDAO dao = new BoardDAO();
			List<BoardVO> boardList = dao.getBoardList(vo);
			// 3. 화면 이동
			request.setAttribute("boardList", boardList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/getBoardList.jsp");
			dispatcher.forward(request, response);
		} else if (path.equals("/insertUserView.do")) {
			System.out.println("회원가입 화면으로 이동");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/insertUser.jsp");
			dispatcher.forward(request, response);
		} else if (path.equals("/loginView.do")) {
			System.out.println("로그인 화면으로 이동");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/login.jsp");
			dispatcher.forward(request, response);
		} else if (path.equals("/insertBoardView.do")) {
			System.out.println("글 등록 화면으로 이동");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/insertBoard.jsp");
			dispatcher.forward(request, response);
		}
	}

}
