
package service;

import bean.TaskBean;
import dao.TaskDao;

public class UpdateTask {
	public void execute(TaskBean bean) throws Exception {
	TaskDao dao=null;
	

		try {
			
				dao = new TaskDao();


				int numRow = dao.updateTask(bean);
				if (numRow > 0) 
					System.out.println("更新成功");
				
			
				else {System.out.println("更新失敗");}
				
		} finally

		{
			if (dao != null) {
				dao.close();
			}
		}
	}
}
