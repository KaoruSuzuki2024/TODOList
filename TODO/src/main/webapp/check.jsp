<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>入力の確認</title>
</head>
<body>
<main>
<p><c:out value="${requestScope.message }"/></p>
<form action="${requestScope.returnjsp }" method="post">
<c:if test="${not empty requestScope.task_id }">
<input type="hidden" name="task_id" value="${requestScope.task_id }">
</c:if>
<c:if test="${not empty requestScope.logout }">
<input type="hidden" name="logout" value="${requestScope.logout }">
</c:if>
<c:if test="${not empty requestScope.tasksid }">
<% int count = 0; %>
<c:forEach var="id" items="${requestScope.tasksid }">
<%count++; %>
<input type="hidden" name="task<%=count %>" value="${id }">
</c:forEach>
<input type="hidden" name="count" value="<%=count %>">
<input type="hidden" name="select" value="${requestScope.select }">
</c:if>
<input type="hidden" name="btn" value="yes">
<input type="submit" value="はい">
</form>
<form action="${request.Scope.returnjsp }" method="post">
<input type="hidden" name="btn" value="no">
<input type="submit" value="いいえ">
</form>
</main>
</body>
</html>