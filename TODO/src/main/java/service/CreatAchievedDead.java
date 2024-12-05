package service;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import bean.TaskBean;

public class CreatAchievedDead {
	public void execute(HttpServletRequest request) throws Exception{
		//リストを作成しrequestに入れる
		ArrayList<TaskBean> list = new ArrayList<TaskBean>();
		
		//
		for(int i = 0;i < 5;++i) {
			TaskBean bean = new TaskBean();
			Date date = new Date(2024/2/5);
			bean.setDeadline(date);
			bean.setTitle("自己学習");
			bean.setPriority(1);
			list.add(bean);
		}
		request.setAttribute("tasks", list);
	}
}
