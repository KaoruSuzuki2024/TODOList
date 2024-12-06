package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import bean.TaskBean;
import dao.TaskDao;

public class SelectAchieved {
	
	//選択したタスクを全て未達成に戻すメソッド
	public void execute(HttpServletRequest request)throws Exception{
		TaskDao tdao = null;
		TaskBean tbean = null;
		ArrayList<String> taskid = new ArrayList<>();
		String count = request.getParameter("count");
		for(int i = 1;i <= Integer.parseInt(count);++i) {
			String id = request.getParameter("task" + i);
			taskid.add(id);
		}
		try {
			if(taskid != null) {
				tdao = new TaskDao();
				tbean = new TaskBean();
				for(String id : taskid) {
					tbean = tdao.searchTask(id);
					tbean.setCheck(false);
					tdao.updateTask(tbean);
				}
			}
		}finally {
			if(tdao != null) {
				tdao.close();
			}
		}
	}
}
