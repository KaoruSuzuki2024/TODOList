<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "ja">
<head>
<meta charset="UTF-8">
<title>達成済み画面</title>
	<header><h1>様、達成済みタスク一覧</h1>
</head>
<body>
	<tr>
		<th>締切日</th>
		<th>タイトル</th>
		<th>優先度</th>
	</tr>
	<c:forEach >
		<tr>
			<td><input type = "checkbox" name = "chk"></td>
			<td>${requestScope.}</td><!-締切日->
			<td>${requestScope.}</td><!-タイトル->
			<td>${requestScope.}</td><!-優先度->
			<p>Congratulations!!</p>
		</tr>
		<form action ="" method ="post">
			<input type = submit name  ="btn" value = "選択削除">
			<input type = submit name = "btn" value = "未達成に戻す">
			<imput type = submit name = "btn" value = "締切順">
			<imput type = submit name = "btn" value = "ホーム">
		</form>
	</c:forEach>

</body>
</html>