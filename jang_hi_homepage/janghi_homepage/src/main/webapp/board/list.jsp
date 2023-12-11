<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Page</title>
</head>
<body>
<h1>게시판 리스트</h1>

<table>

	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	
	<!-- DB에서 가져온 리스트를 c:foreach를 통해 반복 -->
	<!-- itmes=list BoardController -->
	<!-- bvo의 list생성자를 참조하여 쓴다 -->
	<c:forEach items="${list}" var="bvo">
		<tr>
			<td> <a>${bvo.bno}</a> </td>
			<td> <a href="/brd/detail?bno=${bvo.bno}">${bvo.title}</a> </td>
			<td> <a>${bvo.writer}</a> </td>
			<td> <a>${bvo.regdate}</a> </td>
			<td> <a>${bvo.readcount}</a> </td>
		</tr>
	</c:forEach>
</table>

<a href="/brd/regster"> <button> 글쓰기 </button> </a>
<a href="index.jsp"> <button> 홈으로 </button> </a>

</body>
</html>