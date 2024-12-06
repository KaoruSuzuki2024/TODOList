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
import service.SelectAchieved;
import service.SelectDelete;

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
					if(tasksid != null) {
						request.setAttribute("message","本当に削除しますか");
						request.setAttribute("tasksid", tasksid);
						request.setAttribute("returnjsp", "achieve");
						request.setAttribute("select", "del");
						jsp = "/check.jsp";
					}else {
						request.setAttribute("errormessage","タスクを選択してください");
						request.setAttribute("returnjsp", "achieve");
						jsp = "/error.jsp";
					}
				}else if(btn.equals("unachieve")) {
					String[] tasksid = request.getParameterValues("tasksid");
					if(tasksid != null) {
						request.setAttribute("message","本当に変更しますか");
						request.setAttribute("tasksid", tasksid);
						request.setAttribute("returnjsp", "achieve");
						request.setAttribute("select", "un");
						jsp = "/check.jsp";
					}else {
						request.setAttribute("errormessage","タスクを選択してください");
						request.setAttribute("returnjsp", "achieve");
						jsp = "/error.jsp";
					}
				}else if(btn.equals("yes")) {//check.jspからyesで戻ってきたときの処理（unachieveの処理）
					String select = request.getParameter("select");
					if(select.equals("un")) {
						SelectAchieved achieved = new SelectAchieved();
						achieved.execute(request);
					}else if(select.equals("del")){
						SelectDelete delete = new SelectDelete();
						delete.execute(request);
					}
					//ページを再表示
					jsp = CreatList(request);
				}else if(btn.equals("no")) {//check.jspからnoで戻ってきたときの処理（unachieveの処理）
					//ページを再表示
					jsp = CreatList(request);
				}else if(btn.equals("error")) {
					//ページを再表示
					jsp = CreatList(request);
				}else {
					jsp = "/taskhome.jsp";
				}
			}else {
				request.setAttribute("errormessage", "ボタンが押されませんでした");
				request.setAttribute("returnjsp", "achieve");
				jsp = "/error.jsp";
			}
		}catch (Exception e){
			e.printStackTrace();
			request.setAttribute("errormessage", "エラーが発生しました");
			request.setAttribute("returnjsp", "achieve");
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
			request.setAttribute("errormessage", "エラーが発生しました");
			e.printStackTrace();
			request.setAttribute("returnjsp", "achieve");
			jsp = "/error.jsp";
		}
		return jsp;
	}
}