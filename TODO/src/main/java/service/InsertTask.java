package service;

import javax.servlet.http.HttpServletRequest;

import bean.TaskBean;
import dao.TaskDao;

public class InsertTask {
	public void execute(HttpServletRequest request) throws Exception {
		TaskDao dao = null;
		TaskBean bean = null;

		String task_id = request.getParameter("task_id");

		try {
			if (task_id != null) {
				dao = new TaskDao();
				bean = dao.searchTask(task_id);
				//bean.setUser_id(1);
				//bean.setTitle("title");
				//bean.setPriority(1);
				//bean.setContent("content");
				//bean.setCheck(false);
				int numRow = dao.insertTask(bean);
				if (numRow > 0) {
					System.out.println("登録成功");
				}
			} else {
				System.out.println("登録失敗");
			}
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}
