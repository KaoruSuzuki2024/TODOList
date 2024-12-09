<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<style type="text/css">
main {
	text-align: center;
}
</style>
<meta charset="UTF-8">
<title>エラー発生</title>
</head>
<body>
	<main>
		<p>
			<c:out value="${requestScope.errormessage }" />
		</p>
		<form action="${requestScope.returnjsp }" method="post">
			<input type="hidden" name="btn" value="error"> 
			<input type="submit" value="戻る">
		</form>
	</main>
</body>
</html>