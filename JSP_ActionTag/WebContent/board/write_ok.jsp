<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%> 
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%
	request.setCharacterEncoding("UTF-8");
	if(session.getAttribute("userid") == null){
%>
	<script>
		alert('로그인 후 이용하세요');
		location.href='../login.jsp';
	</script>
<%

	}else{

		
		int size = 1024*1024*10;
		   String uploadPath = request.getRealPath("upload");
		   System.out.println(uploadPath);
		   MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());

	
%>
		<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
	<!--  	<jsp:setProperty property="title" param="b_title" name="board"/>
		<jsp:setProperty property="content" param="b_content" name="board"/>
		<jsp:setProperty property="file" param="b_file" name="board"/> -->
		<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<%
      board.setUserid((String) session.getAttribute("userid"));
	  board.setTitle(multi.getParameter("b_title"));
	  board.setFile(multi.getFilesystemName("b_file"));
	  board.setContent(multi.getParameter("b_content"));
      if (dao.write(board) == 1) {
%>


<script>
	alert('등록되었습니다');
	location.href='list.jsp';
</script>

<%
   } else {
%>
<script>
   alert('등록실패');
   history.back();
</script>


<%
}
   }
%>