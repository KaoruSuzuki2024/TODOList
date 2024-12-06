package service;

import bean.TaskBean;
import dao.TaskDao;

public class InsertTask {
	public void execute(TaskBean bean) throws Exception {
		TaskDao dao = null;
		try {
				dao = new TaskDao();
				int numRow = dao.insertTask(bean);
				if (numRow > 0) {
					System.out.println("登録成功");
				}
			else {
				System.out.println("登録失敗");
			}
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}
