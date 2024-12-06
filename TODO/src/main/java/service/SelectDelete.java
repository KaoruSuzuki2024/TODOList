package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.TaskDao;

public class SelectDelete {
	
	//選択したタスクを全て削除するメソッド
	public void execute(HttpServletRequest request)throws Exception{
		TaskDao td = null;
		ArrayList<String> taskid = new ArrayList<>();
		String count = request.getParameter("count");
		for(int i = 1;i <= Integer.parseInt(count);++i) {
			String id = request.getParameter("task" + i);
			taskid.add(id);
		}
		try {
			if(taskid != null) {
				td = new TaskDao();
				for(String id : taskid) {
					td.deleteTask(id);
				}
			}
		}finally {
			if(td != null) {
				td.close();
			}
		}
	}
}
