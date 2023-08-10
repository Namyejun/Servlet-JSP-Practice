package com.ssamz.web.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.controller.Controller;

public class InsertBoardViewController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 등록 화면으로 이동");
		return "insertBoard";
	}

}
