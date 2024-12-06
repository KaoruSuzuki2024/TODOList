package service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import bean.TaskBean;
import dao.TaskDao;

public class SearchTask {
	public String execute(HttpServletRequest request){
		TaskBean bean = null;
		String taskid = request.getParameter("taskid");
		String jsp;
		TaskDao dao = null;
		try {
			dao = new TaskDao();
			bean = dao.searchTask(taskid);
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	        Date date = bean.getDeadline();
	        String datestr = formatter.format(date);
	        request.setAttribute("taskdate", datestr);
	        request.setAttribute("title", bean.getTitle());
	        request.setAttribute("content", bean.getContent());
	        request.setAttribute("check", bean.isCheck());
	        request.setAttribute("taskid", bean.getTask_id());
			//request.setAttribute("taskdata", bean);
			jsp = "/TaskEdit.jsp";
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			request.setAttribute("message", "サーバーの取得ができませんでした。");
			jsp = "/error.jsp";
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			request.setAttribute("message", "サーバーと接続できませんでした。");
			jsp = "/error.jsp";
		}finally {
			if(dao != null) {
				dao.close();
			}
		}
		return jsp;
	}
}
