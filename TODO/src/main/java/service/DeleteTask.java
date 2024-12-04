package service;

//import bean.TaskBean;
import javax.servlet.http.HttpServletRequest;

import dao.TaskDao;

public class DeleteTask {
	public void execute(HttpServletRequest request) throws Exception{
	TaskDao dao = null;
	// 従業員情報をリクエストパラメーターから取得
	String task_id = request.getParameter("task_id");
	try {
	if (task_id != null) {
	dao = new TaskDao();
	//int id = Integer.parseInt(task_id);
	//String numRow = dao.deleteTask(task_id);
	if(dao==null){
	request.setAttribute("completeMessage", "従業員情報を削除しました");
	}else{
	request.setAttribute("completeMessage", "従業員情報を削除できませんでした");
	}
	} else {
	request.setAttribute("confirmMessage", "不正アクセスです");
	}
	} finally {
	if (dao != null) {
	dao.close();
	}
	}}}
	


