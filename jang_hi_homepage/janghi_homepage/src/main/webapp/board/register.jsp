<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 페이지</title>
</head>
<body>

<form action="/brd/insert" method="post">

제목 : <input type="text" name="title">
<br>

작성자 : <input type="text" name="writer">
<br>

내용 : <textarea rows="10" cols="30" name="content"></textarea>
<br>

<button type="submit">올리기</button>
<br>

</form>

<button type="button">리스트로</button>

</body>
</html>