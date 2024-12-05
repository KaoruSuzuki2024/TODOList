package service;

import javax.servlet.http.HttpServletRequest;

import dao.TaskDao;

public class InsertTask {
	public void execute(HttpServletRequest request) throws Exception{
		TaskDao dao = null;
		// 情報をリクエストパラメーターから取得
		String[] task_id = request.getParameterValues("task_id");

}
}