<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ホーム画面</title>
</head>
<body>
<header>
<h1>${sessionScope.userbean.name }様、未達成タスク一覧</h1>
<form action="home" method="post">
<input type="hidden" name="btn" value="logout">
<input type="submit" value=ログアウト>
</form>
</header>
<main>
<form action="home" method="post">
<input type="hidden" name="btn" value="regist">
<p><input type="submit" value="新規登録"></p>
</form>
<form action="home" method="post">
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
<%} %>
</form>
<form action="achieve" method="get">
<input type="submit" value="達成済み">
</form>
<table border="1">
<tr>
<th>締切日</th>
<th>タイトル</th>
<td>優先度</td>
<th></th>
<th></th>
<tr>
<c:forEach var="task" items="${requestScope.tasks }">
<tr>
<td><fmt:formatDate value="${task.deadline }" type="DATE" pattern="yyyy/MM/dd"/></td>
<td><c:out value="${task.title }"/></td>
<td><c:out value="${task.priority }"/></td>
<td>
<form action="home" method="post">
<input type="hidden" name="taskid" value="${task.task_id }">
<input type="hidden" name="btn" value="delete">
<input type="submit" value="削除">
</form>
</td>
<td>
<form action="home" method="post">
<input type="hidden" name="taskid" value="${task.task_id }">
<input type="hidden" name="btn" value="update">
<input type="submit" value="編集">
</form>
</td>
</tr>
</c:forEach>
</table>
<form action="home" method="post">
<input type="hidden" name="btn" value="regist">
<p><input type="submit" value="新規登録"></p>
</form>
${requestScope.title }
</main>
</body>
</html>