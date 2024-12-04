<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>TaskMaster/DL</title>
</head>
<body>
	<header>
		<h1>TaskMaster/DL</h1>
	</header>
	<main>
		<form action="controller" method="post">
			<p>IDとPWを入力してください</p>
			<% String message = "test";%>	<!-- 本来はサーブレット側でメッセージの内容を記載 -->
			<p><%=message %></p>			<!-- Login.javaから変数を受け取る -->
			<p>ID：<input type="text" name="id"></p>
			<p>PW：<input type="text" name="pw"></p>
			<input type="button" name="login" value="ログイン">
		</form>

	</main>
</body>
</html>