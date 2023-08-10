package com.ssamz.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String handlerRequest(HttpServletRequest request, HttpServletResponse response);
}
