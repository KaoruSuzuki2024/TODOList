package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TaskBean;

/**
 * Servlet implementation class TaskEdit
 */
@WebServlet("/TaskEdit")
public class TaskEdit extends HttpServlet {
	public static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// JSP への転送
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/TaskEdit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		// リクエストパラメーターの値
		String button = request.getParameter("button");
		String deadline = request.getParameter("task_deadline");
		String title = request.getParameter("task_title");
		String content = request.getParameter("task_content");
		String jsp = null;
		String message = null;

		TaskBean task;// = (TaskBean) session.getAttribute("task");
		task = new TaskBean();
		task.setTask_id(1);

		try {
			if (button != null && !button.isEmpty()) {
				if (button.equals("clear")) {
					request.setAttribute(title, "");
					jsp="/TaskEdit";
				}

				if (deadline != null && title != null && content != null && !deadline.isEmpty() && !title.isEmpty()
						&& content.isEmpty()) {
					if (title.length() < 15 && content.length() < 100) {
					} else

					{
						request.setAttribute("errormessage", "文字数が超過しています");
						jsp = "/error.jsp";
					}

				} else {
					request.setAttribute("errormessage", "必須項目が入力されていません");
					jsp = "/error.jsp";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "エラーが発生しました");
			jsp = "/error.jsp";
		}

		// JSP への転送
		ServletContext context = request.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
		request.setAttribute("sort", "dead");
	}
}
