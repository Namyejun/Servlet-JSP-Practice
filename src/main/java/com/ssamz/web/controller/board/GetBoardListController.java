package com.ssamz.web.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssamz.biz.board.BoardDAO;
import com.ssamz.biz.board.BoardVO;
import com.ssamz.web.controller.Controller;

public class GetBoardListController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
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

		return "getBoardList";
	}

}
