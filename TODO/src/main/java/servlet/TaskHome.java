package servlet;

import java.io.IOException;
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

import bean.UsersBean;
import service.CreatUnachievedDead;
import service.CreatUnachievedPriority;
import service.SearchTask;


/**
 * Servlet implementation class TaskHome
 */
@WebServlet("/home")
public class TaskHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		UsersBean user = (UsersBean) session.getAttribute("user");
		String jsp;
		//ログインされてなければログインページに飛ぶ
		
			//タスク一覧を作成
			CreatUnachievedDead search = new CreatUnachievedDead();
			try {
				search.execute(request);
				jsp = "/taskhome.jsp";
				request.setAttribute("sort", "dead");
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				System.out.println("リストの作成に失敗しました。");
				e.printStackTrace();
				jsp = "/error.jsp";
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
		try {
			if(btn != null && !btn.isEmpty()) {
				if(btn.equals("logout")) {
					request.setAttribute("message", "ログアウトしますか？");
					jsp = "/check.jsp";
				}else if(btn.equals("regist")) {
					Date date = new Date(); // ここでDate型の変数を取得
		        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		        	String formattedDate = formatter.format(date);
		        	request.setAttribute("date", formattedDate);
		        	jsp = "/TaskEdit.jsp";
				}else if(btn.equals("sort")) {
					String nowsort = request.getParameter("nowsort");
					if(nowsort.equals("dead")) {
						CreatUnachievedPriority creatlist = new CreatUnachievedPriority();
						creatlist.execute(request);
						sort = "priority";
					}else if(nowsort.equals("priority")) {
						CreatUnachievedDead search = new CreatUnachievedDead();
						search.execute(request);
						sort = "dead";
					}
					jsp = "/taskhome.jsp";
				}else if(btn.equals("delete")) {
					String taskid = request.getParameter("taskid");
					request.setAttribute("message","削除してもよいですか");
					request.setAttribute("deleteid", taskid);
					jsp = "/check.jsp";
				}else if(btn.equals("update")) {
					SearchTask search = new SearchTask();
					search.execute(request);
		        	jsp = "/TaskEdit.jsp";
				}else {
					jsp = "taskhome.jsp";
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

}
