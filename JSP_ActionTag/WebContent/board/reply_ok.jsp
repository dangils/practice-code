<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<% 
   request.setCharacterEncoding("UTF-8");
   if(session.getAttribute("userid") == null){
      
%>
<script>
   alert('로그인 후 이용하세요.');
   location.href = '../login.jsp';
</script>
<%
   } else { 
     

%>

		<jsp:useBean id="reply" class="com.koreait.board.ReplyDTO"/>
		<jsp:setProperty property="content" param="re_content" name="reply"/>
		<jsp:useBean id="dao2" class="com.koreait.board.ReplyDAO"/>
<%
	reply.setBoardidx(Integer.parseInt(String.valueOf(request.getParameter("b_idx"))));
	reply.setContent(String.valueOf(request.getParameter("re_content")));
    reply.setUserid((String) session.getAttribute("userid"));
    
    if (dao2.repl(reply) == 1) {
%>

	<script>
	   alert('댓글이 등록되었습니다.');
	   location.href='view.jsp?b_idx=<%=reply.getBoardidx()%>';
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