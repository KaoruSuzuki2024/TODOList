<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>達成済み画面</title>
</head>
<body>
	<header>
		<h1>${sessionScope.userbean.name }様、達成済みタスク一覧</h1>
	</header>
	<main>
		<form action="achieve" method="post">
			<input type="hidden" name="btn" value="delete">
			<p><input type="submit" value="選択削除"></p>
		</form>
		
		<form action="achieve" method="post">
			<input type="hidden" name="btn" value="unachieve">
			<input type="submit" value="未達成に戻す">
		</form>
		
		<form action="achieve" method="post">
			<input type="hidden" name="nowsort" value="${requestScope.sort }">
			<input type="hidden" name="btn" value="sort">
			<%
				String sort = (String)request.getAttribute("sort");
				if(sort.equals("dead")){
			%>
					<p><input type="submit" value="締切順"></p>
			<%
				}else if(sort.equals("priority")){
			%>
					<p><input type="submit" value="優先度順"></p>
			<%	} //if%>
		</form

		<form action = "achieve" method = "post">
			<input type="hidden" name="btn" value="home">
			<input type="submit" value="ホーム">
		</form>
		
		<table border="1">
			<tr>
				<th></th>
				<th>締切日</th>
				<th>タイトル</th>
				<th>優先度</th>
				<th></th>
			<tr>
			<c:forEach var="task" items="${requestScope.achievedtasks }">
				<tr>
					<form action = "achieve" method = "post">
						<input type = "hidden" name = "taskid" value = "${task.task_id }">
						<input type = "checkbox" name = "achievetask">
					</form>
					<td><fmt:formatDate value="${task.deadline }" type="DATE" pattern="yyyy/MM/dd"/></td>
					<td><c:out value="${task.title }"/></td>
					<td><c:out value="${task.priority }"/></td>
					<td>Congratulations!!</td>
				</tr>
			</c:forEach>
		</table>
		<form action="achieve" method="post">
			<input type="hidden" name="btn" value="delete">
			<p><input type="submit" value="選択削除"></p>
		</form>
		
		<form action="achieve" method="post">
			<input type="hidden" name="btn" value="unachieve">
			<input type="submit" value="未達成に戻す">
		</form>
	</main>
</body>
</html>