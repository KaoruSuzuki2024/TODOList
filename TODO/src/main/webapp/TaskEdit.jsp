<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
.example2{
    display: flex;
    justify-content:space-around;
</style>
<html>

<head>
<meta charset="UTF-8">
<title>タスク編集</title>
</head>
<body>
<h1>タスク編集</h1>
締切日:<input type="date" name="task_deadline">
<br>
タイトル:<input type="text" name="task_title">

<br>
タスク内容:
<textarea name="task_content" rows="5" cols="40" maxlength="100"></textarea>
<br>
優先度:
<select name="task_priority">
<option value="one">1</option>
<option value="two">2</option>
<option value="three">3</option>
<option value="four">4</option>
<option value="five">5</option>
</select>
<br>
達成済み:<input type="checkbox" name="task_check">
<br>
<div class="example2">
<input type="hidden" name="btn" value="IdSearch">
<input type="submit" value="戻る">
<input type="hidden" name="btn" value="IdSearch">
<input type="submit" value="クリア">
<input type="hidden" name="btn" value="IdSearch">
<input type="submit" value="登録">
</div>
</body>
</html>