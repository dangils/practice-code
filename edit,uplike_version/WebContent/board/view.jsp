<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.koreait.db.Dbconn"%>

<%
	String b_idx = request.getParameter("b_idx");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql ="";

	
	String b_regdate = "";
	String b_userid = "";
	String b_title = "";
	String b_content = "";
	int b_like = 0;
	int b_hit = 0;

	

	 try {
	      conn = Dbconn.getConnection();
	      if (conn != null) {
	    	  sql = "update tb_board set b_hit = b_hit + 1 where b_idx=?";
	    	  pstmt = conn.prepareStatement(sql);  
	    	  pstmt.setString(1, b_idx);
	    	  pstmt.executeUpdate();
	    	  

	    	  sql="select b_idx,b_userid,b_title,b_content,b_regdate,b_like,b_hit from tb_board where b_idx=?";
	    	  pstmt = conn.prepareStatement(sql);
	    	  pstmt.setString(1, b_idx);
	    	  rs = pstmt.executeQuery();
	    	  
	    	  if(rs.next()){
	    		  b_userid = rs.getString("b_userid");
	    		  b_title = rs.getString("b_title");
	    		  b_content = rs.getString("b_content");
	    		  b_regdate = rs.getString("b_regdate");
	    		  b_like = rs.getInt("b_like");
	    		  b_hit = rs.getInt("b_hit");
	    	  }
	    		  
	    	  }
	    	  
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보기</title>
</head>
<body>
	<h2>글보기</h2>
	<table border="1" width="800">
		<tr>
			<td>제목</td><td><%=b_title%></td>
		</tr>
		<tr>
			<td>날짜</td><td><%=b_regdate%></td>
		</tr>
		<tr>
			<td>작성자</td><td><%=b_userid%></td>
		</tr>
		<tr>
			<td>조회수</td><td><%=b_hit%></td>
		</tr>
		<tr>
			<td>좋아요</td><td id='uplike'><%=b_like%></td>
		</tr>
		<tr>
			<td>내용</td><td><%=b_content%></td>
		</tr>
		<tr>
			<td colspan="2">
			
	<%
		if(b_userid.equals((String)session.getAttribute("userid"))){
			%>
			<input type="button" value="수정" onclick="location.href='./edit2.jsp?b_idx=<%=b_idx%>'">
			<input type="button" value = "삭제" onclick="location.href='./delete.jsp?b_idx=<%=b_idx%>'">
<% 
		}
	
	%>		
			<input type="button" value = "좋아요" onclick="sendlike()">
			<input type="button" value = "리스트" onclick="location.href='./list.jsp'">
			</td>
		</tr>
	</table>
	
	
	
	
	<script>
	
		'user strict'; 
		function sendlike(){
			const xhr = new XMLHttpRequest();
			xhr.open('GET', 'uplike.jsp?b_idx=<%=b_idx%>',true);
			xhr.send();
			xhr.onreadystatechange = function(){ //readyState 값이 변동될때 수행하는 함수를 설정할수있는 프로퍼티
			if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){ //페이지가 연결된 상태				
					document.getElementById('uplike').innerHTML = xhr.responseText;
					}
				}
			}
		
		
	</script>
</body>
</html>






