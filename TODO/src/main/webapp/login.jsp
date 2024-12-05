<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<header>
		<h1>TaskMaster/DL</h1>
	</header>
	<main>
		<form action="login" method="post">
			<p>IDとPWを入力してください</p>
			<c:if test="${not empty requestScope.message}">
				<p class="message">${requestScope.message}</p>
			</c:if>
			<p>
				ID：<input type="text" name="id" placeholder="01234567">
			</p>
			<p>
				PW：<input type="password" name="pw" placeholder="********">		<!-- cssで*表示が可能 -->
			</p>
			<input type="submit" value="ログイン">
		</form>

	</main>
</body>
</html>