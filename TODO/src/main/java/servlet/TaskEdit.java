package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TaskBean;
import bean.UsersBean;
import service.InsertTask;
import service.UpdateTask;

/**
 * Servlet implementation class TaskEdit
 */
@WebServlet("/TaskEdit")
public class TaskEdit extends HttpServlet {
	public static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		UsersBean user = (UsersBean) session.getAttribute("user");
		String jsp;
		// TODO Auto-generated method stub
		if (user == null) {
			jsp = "/login.jsp";
		} else {
			jsp = "/taskedit.jsp";
		}
		// JSP への転送
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		// リクエストパラメーターの値;
		String jsp = null;
		String task_deadline = request.getParameter("task_deadline");

		String button = request.getParameter("button");

		String title = request.getParameter("task_title");
		String content = request.getParameter("task_content");
		String btn = request.getParameter("btn");

		try {
			if (button != null && !button.isEmpty()) {
//				if (button.equals("clear")) {
//					request.setAttribute("title", "");
//					jsp = "/taskedit";
//				} else 
				if (button.equals("登録")) {
					if (task_deadline != null && title != null && content != null&& !task_deadline.isEmpty() && !title.isEmpty()
							&& !content.isEmpty()) {
						if (title.length() <= 15 && content.length() <= 100) {
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							Date date = null;
							try {
								date = formatter.parse(task_deadline);
								HttpSession se = request.getSession(false);
								if (btn.equals("編集")) {
									UsersBean user = (UsersBean) se.getAttribute("user");
									TaskBean bean = new TaskBean();
									String id = request.getParameter("task_id");
									bean.setTask_id(Integer.parseInt(id));
									bean.setUser_id(user.getId());
									bean.setDeadline(date);
									bean.setTitle(title);
									bean.setContent(content);
									String priority = request.getParameter("task_priority");
									bean.setPriority(Integer.parseInt(priority));
									String check = request.getParameter("task_check");
									boolean taskcheck;
									if (check != null && !check.isEmpty()) {
										taskcheck = true;
									} else {
										taskcheck = false;
									}
									bean.setCheck(taskcheck);
									UpdateTask updatetask = new UpdateTask();
									updatetask.execute(bean);
								} else {
									UsersBean user = (UsersBean) se.getAttribute("user");

									TaskBean bean = new TaskBean();
									bean.setUser_id(user.getId());
									bean.setDeadline(date);
									bean.setTitle(title);
									bean.setContent(content);
									String priority = request.getParameter("task_priority");
									bean.setPriority(Integer.parseInt(priority));
									String check = request.getParameter("task_check");
									boolean taskcheck;
									if (check != null && !check.isEmpty()) {
										taskcheck = true;
									} else {
										taskcheck = false;
									}
									bean.setCheck(taskcheck);
									InsertTask updatetask = new InsertTask();
									updatetask.execute(bean);
								}
								jsp = "/taskhome.jsp";
							} catch (ParseException e) {
								request.setAttribute("errormessage", "日付に正常な値が入力されていません。");
								request.setAttribute("returnjsp", "TaskEdit");
								jsp = "/error.jsp";
							}
						}
					} else{
						request.setAttribute("errormessage", "必須項目が入力されていません。");
						jsp = "/error.jsp";
						request.setAttribute("returnjsp", "TaskEdit");
					}

				} else {
					request.setAttribute("errormessage", "ボタンが押されませんでした。");
					jsp = "/error.jsp";
					request.setAttribute("returnjsp", "TaskEdit");
				}

			} else if (btn.equals("error")) {
				jsp = "/taskedit.jsp";

			} else {
				request.setAttribute("errormessage", "エラーが発生しました。ボタンの入力が確認できませんでした。");
				jsp = "/error.jsp";
				request.setAttribute("returnjsp", "TaskEdit");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "エラーが発生しました。編集画面に戻ります。");
			jsp = "/error.jsp";
			request.setAttribute("returnjsp", "TaskEdit");
		}
		// JSP への転送

		if (jsp.equals("/taskhome.jsp")) {
			response.sendRedirect("http://localhost:8080/TODO/home");
		} else {
			ServletContext context = request.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		}
	}
}
