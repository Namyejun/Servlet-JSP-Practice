<%@page import="java.util.List"%>
<%@page import="com.ssamz.biz.board.BoardDAO"%>
<%@page import="com.ssamz.biz.board.BoardVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<%
	// 1. 컨트롤러(Servlet)가 모델(DAO)을 이용하여 request에 등록한 글 목록을 꺼낸다.
	List<BoardVO> boardList = (List) request.getAttribute("boardList");
	
	// 2. 응답 화면 구성
%>

<center>
<!-- 검색 시작 -->
<form action="getBoardList.jsp" method="post">
	<table border="1" cellpadding="0" cellspacing="0" width="700">
	<tr>
		<td align="right">
			<select name="searchCondition">
			<option value="TITLE">제목
			<option value="CONTENT">내용
			</select>
			<input name="searchKeyword" type="text"/>
			<input type="submit" value="검색"/>
		</td>
	</tr>
	</table>
</form>
<!-- 검색 종료 -->

<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<th bgcolor="orange" width="100">번호</th>
	<th bgcolor="orange" width="200">제목</th>
	<th bgcolor="orange" width="150">작성자</th>
	<th bgcolor="orange" width="150">등록일</th>
	<th bgcolor="orange" width="100">조회수</th>
</tr>

<% for(BoardVO board : boardList) { %>
<tr>
	<td><%= board.getSeq() %></td>
		<td align="left"><a href="getBoard.do?seq=<%= board.getSeq() %>"><%= board.getTitle() %></a></td>
	<td><%= board.getWriter() %></td>
	<td><%= board.getRegDate() %></td>
	<td><%= board.getCnt() %></td>
</tr>
<% } %>

</table>
</center>

<%@ include file="../layout/footer.jsp" %>
