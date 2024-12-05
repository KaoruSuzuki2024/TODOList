<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<style>
ul li {
	list-style: none;
}

ul li label {
	margin-right: 0px;
	width: 100px;
	float: left;
}

.td {
	text-align: center;
}

.example2 {
	　　diaplay: inline-block;
	position: relative;
	top: 45px;
	left: 500px;
	position: relative;
}

.button-container input:nth-of-type(1) {
	margin: 10px; /* ボタン間のスペースを調整 */
}

.button-container input:nth-of-type(2) {
	margin-left: 220px; /* 特定のボタンのスペースを調整 */
}

.button-container input:nth-of-type(3) {
	margin-left: 20px; /* 特定のボタンのスペースを調整 */
}

.size_test {
	font-size: 8px; /* 文字サイズ指定 */
}

form {
	border: 2px solid #000; /* 枠線の太さ、スタイル、色を設定 */
	padding: 20px; /* 枠線と内容の間に余白を設定 */
}

.test {
	position: relative;
	top: 20px;
	left: 400px;
}
</style>
<html>

<head>
<meta charset="UTF-8">
<title>タスク編集</title>
</head>
<body>

	<h1>タスク編集</h1>
	<p>*の表示されている文字は入力必須です</p>

	<form>
		<ul>
			<li class="task_deadline"><label for="task_dealine">*締切日:</label>
				<input type="date" name="task_deadline"  min="2024-1-1"
				max="2035-12-31" value ="${requestScope.deadline}"></li>


			<li class="task_title"><label for="task_title">*タイトル:</label> <input
				type="text" name="task_title" value="${requestScope.title}"></li>
			<div class="size_test">上限15文字</div>



			<li class="task_content"><label for="task_content">*タスク内容:</label>
				<textarea name="task_content" rows="5" cols="40" maxlength="100">${requestScope.cotent }</textarea>
			</li>
			<div class="size_test">上限100文字</div>

			<li class="task_priority"><label for="task_priority">*優先度:</label>
				<select name="task_priority">
					<option value="one">1</option>
					<option value="two">2</option>
					<option value="three">3</option>
					<option value="four">4</option>
					<option value="five">5</option>
			</select></li>


			<li class="task_check"><label for="task_check">達成済み:</label> <input
				type="checkbox" name="task_check" value="${requestScope.check}" ></li>
		</ul>
	</form>

	<div class="button-container">
		<input type="button" value="戻る"> <input type="button"
			value="クリア"> <input type="button" value="登録">
	</div>

</body>
</html>