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
			<p><% String message = "test";%></p>	<!-- 本来はサーブレット側でメッセージの内容を記載 -->
			<p><%=message %></p>					<!-- Login.javaから変数を受け取る -->
			<p>ID：<input type="text" name="Iid" placeholder="01234567"></p>		<!-- nameを後に修正 -->
			<p>PW：<input type="password" name="Ipw" placeholder="********"></p>	<!-- cssで*表示が可能 -->
			<input type="button" name="login" value="ログイン">
		</form>

	</main>
</body>
</html>