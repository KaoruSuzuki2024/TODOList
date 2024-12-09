<%@ page language = "java" contentType = "text/html; charset=UTF-8"
    pageEncoding = "UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html lang = "ja">
<head>
	<meta charset = "UTF-8">
	<title>達成済み画面</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/TaskAchieve.css">
</head>
<body>
	<header>
		<h1>${sessionScope.user.name }様、達成済みタスク一覧</h1>
	</header>
	<main>
		<table >
			<tr>
				<td>
					<form action = "achieve" method = "post">
						<input type = "hidden" name = "nowsort" value = "${requestScope.sort }">
						<input type = "hidden" name = "btn" value = "sort">
						<%
						String sort = (String)request.getAttribute("sort");
							if(sort.equals("dead")){
						%>
								<p><input type = "submit" value = "締切順"></p>
						<%
							}else if(sort.equals("priority")){
						%>
								<p><input type = "submit" value = "優先度順"></p>
						<%	} //if%>
					</form>
				</td>
				<td class = "home">
					<form action = "home" method = "get">
						<input type = "hidden" name = "btn" value = "home">
						<input type = "submit" value = "ホーム" class = "home">
					</form>
				</td>
			</tr>
		</table>
		
		<form action = "achieve" method = "post">
			<table>
				<tr>
					<td>
						<label><input type="radio" name="btn" value="delete" checked="checked">選択削除</label>
						<label><input type="radio" name="btn" value="unachieve">未達成に戻す</label>
						<input type = "submit" value = "実行">
					</td>
				<tr>
			<table>
			<table border = "1">
				<tr>
					<th class = "check"></th>
					<th class = "deadline">締切日</th>
					<th>タイトル</th>
					<th class = "priority">優先度</th>
					<th></th>
				<tr>
				<c:forEach var = "task" items = "${requestScope.tasks }">
					<tr>
						<td class = "chk">
							<input type = "checkbox" name = "tasksid" value = "${task.task_id }">
						</td>
						<td><fmt:formatDate value = "${task.deadline }" type = "DATE" pattern = "yyyy/MM/dd"/></td>
						<td><c:out value = "${task.title }"/></td>
						<td class = "pri"><c:out value = "${task.priority }"/></td>
						<td>Congratulations!!</td>
					</tr>
				</c:forEach>
			</table>
			
		</form>
	</main>
</body>
</html>