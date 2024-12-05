package service;

import javax.servlet.http.HttpServletRequest;

import bean.TaskBean;
import dao.TaskDao;

public class InsertTask {
	public void execute(HttpServletRequest request) throws Exception{
		TaskDao dao = null;
		TaskBean task=new TaskBean();
		// 情報をリクエストパラメーターから取得
		String title = request.getParameter("title");
		String deadline = request.getParameter("deadline");		
		String priority = request.getParameter("priority");
		String content = request.getParameter("content");
		String check = request.getParameter("check");
		String task_id = request.getParameter("task_id");

}
}