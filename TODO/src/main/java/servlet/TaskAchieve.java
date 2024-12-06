package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UsersBean;
import service.CreatAchievedDead;
import service.CreatAchievedPriority;
import service.DeleteTask;

/**
 * Servlet implementation class TaskAchive
 */
@WebServlet("/achieve")
public class TaskAchieve extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		UsersBean user = (UsersBean) session.getAttribute("user");
		String jsp;
		//ログイン画面と接続するまで
		user = new UsersBean();
		user.setId(1);
		//ログインされてなければログインページに飛ぶ
		if(user == null) {
			jsp = "/login.jsp";
		}else {
			//タスク一覧を作成
			try {
				jsp = CreatList(request);
			}catch (Exception e){
				e.printStackTrace();
				request.setAttribute("message", "エラーが発生しました");
				jsp = "/error.jsp";
			}
		}
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("btn");
		String sort = "dead";
		String jsp;
		HttpSession session = request.getSession(false);
		UsersBean user = (UsersBean)session.getAttribute("user");
		user = new UsersBean();
		user.setId(1);
		CreatAchievedDead search = new CreatAchievedDead();
		try {
			if(btn != null && !btn.isEmpty()) {
				if(btn.equals("sort")) {
					String nowsort = request.getParameter("nowsort");
					if(nowsort.equals("dead")) {
						CreatAchievedPriority creatlist = new CreatAchievedPriority();
						creatlist.execute(request,Integer.toString(user.getId()));
						sort = "priority";
					}else if(nowsort.equals("priority")) {
						search.execute(request,Integer.toString(user.getId()));
						sort = "dead";
					}
					jsp = "/taskachieve.jsp";
				}else if(btn.equals("delete")) {
					String[] tasksid = request.getParameterValues("tasksid");
					request.setAttribute("message","選択したタスクを削除してもよいですか");
					request.setAttribute("task_id", tasksid);
					request.setAttribute("returnjsp", "achieve");
					jsp = "/check.jsp";
				}else if(btn.equals("yes")) {//check.jspからyesで戻ってきたときの処理（deleteの処理）
					String[] tasksid = request.getParameterValues("tasksid");
					DeleteTask delete = new DeleteTask();
					for(String s : tasksid) {
						request.setAttribute("delete", s);
						delete.execute(request);
					}
					//ページを再表示
					jsp = CreatList(request);
				}else if(btn.equals("no")) {//check.jspからnoで戻ってきたときの処理（deleteの処理）
					//ページを再表示
					jsp = CreatList(request);
				}else if(btn.equals("unachieve")) {
					String[] tasksid = request.getParameterValues("tasksid");
					request.setAttribute("message","選択したタスクを未達成に戻してもよいですか");
					request.setAttribute("task_id", tasksid);
					request.setAttribute("returnjsp", "achieve");
					jsp = "/check.jsp";
				}else if(btn.equals("yes")) {//check.jspからyesで戻ってきたときの処理（unachieveの処理）
					String[] tasksid = request.getParameterValues("tasksid");
					//UpdateTask update = new UpdateTask();
					for(String s : tasksid) {
						request.setAttribute("update", s);
						//update.execute(request);
					}
					//ページを再表示
					jsp = CreatList(request);
				}else if(btn.equals("no")) {//check.jspからnoで戻ってきたときの処理（unachieveの処理）
					//ページを再表示
					jsp = CreatList(request);
				}else {
					jsp = "/taskhome.jsp";
				}
			}else {
				request.setAttribute("message", "ボタンが押されませんでした");
				jsp = "/error.jsp";
			}
		}catch (Exception e){
			e.printStackTrace();
			request.setAttribute("message", "エラーが発生しました");
			jsp = "/error.jsp";
		}
		
		request.setAttribute("sort", sort);
		
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}
	
	
	//ページにタスク一覧を表示/再表示するメソッド
	public String CreatList(HttpServletRequest request)throws Exception{
		HttpSession session = request.getSession(false);
		CreatAchievedDead search = new CreatAchievedDead();
		String jsp;
		UsersBean user = (UsersBean)session.getAttribute("user");
		user = new UsersBean();
		user.setId(1);
		try {
			search.execute(request,Integer.toString(user.getId()));
			jsp = "/taskachieve.jsp";
			request.setAttribute("sort", "dead");
		} catch (Exception e) {
			System.out.println("リストの作成に失敗しました。");
			e.printStackTrace();
			jsp = "/error.jsp";
		}
		return jsp;
	}
}