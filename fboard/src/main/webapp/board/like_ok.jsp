<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<%
	if(session.getAttribute("userid") == null){
%>
	<script>
		alert('로그인 후 이용하세요');
		location.href='../login.jsp';
	</script>
<%
	}else{
	
%>
	<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
	<jsp:useBean class="com.koreait.board.BoardDAO" id="dao" />
<% 		
	board.setIdx(Integer.parseInt(String.valueOf(request.getParameter("b_idx"))));
		
		if(dao.like(board) !=null ){
		out.print(board.getLike());
		}
	}

%>		
		