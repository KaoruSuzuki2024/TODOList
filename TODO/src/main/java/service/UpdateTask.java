
package service;

import javax.servlet.http.HttpServletRequest;

import bean.TaskBean;
import dao.TaskDao;

public class UpdateTask {
	public void execute(HttpServletRequest request) throws Exception {
		TaskDao dao = null;
		TaskBean bean = null;

		String task_id = request.getParameter("task_id");

		try {
			if (task_id != null) {
				dao = new TaskDao();
				bean = dao.searchTask(task_id);

				int numRow = dao.updateTask(bean);
				if (numRow > 0) {
					System.out.println("更新成功");
				}
			} else {
				System.out.println("更新失敗");
			}
		} finally

		{
			if (dao != null) {
				dao.close();
			}
		}
	}
}
