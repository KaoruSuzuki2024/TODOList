package service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import bean.TaskBean;
import bean.UsersBean;
import dao.TaskDao;

public class CreatAchievedPriority {
	public void execute(HttpServletRequest request){
		//リストを作成しrequestに入れる
		ArrayList<TaskBean> list = new ArrayList<TaskBean>();
		TaskDao dao = null;
		UsersBean ub = new UsersBean();
		String user_id = String.valueOf(ub.getId());
		
		try {
			dao = new TaskDao();
			list = dao.searchUnachivePriority(user_id);
			request.setAttribute("tasks", list);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			if(dao != null) {
				dao.close();
			}
		}
	}
}
