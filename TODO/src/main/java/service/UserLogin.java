package service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.UsersBean;
import dao.UsersDao;

public class UserLogin {
	public String execute(HttpServletRequest request) throws SQLException {
		UsersDao ud = null;
		String id = request.getParameter("id");
		String password = request.getParameter("pw");
		String jsp = "/login.jsp";

		try {
			if (id != null && !id.isEmpty()) {
				int numcheck = Integer.parseInt(id);	//idがint型かの確認(異なる場合は例外判定)
				ud = new UsersDao();
				UsersBean ub = ud.searchUser(id);
				if (password.equals(ub.getPassword())) {
					jsp = "/taskhome.jsp";				//ログイン処理で遷移するページのURLを格納
					HttpSession session = request.getSession(true);
					session.setAttribute("user",ub);
					request.setAttribute("sort", "dead");
				} else {request.setAttribute("message", "IDとPWが一致しません");}
			} else {request.setAttribute("message", "IDとPWが一致しません");}
		} catch (NumberFormatException ex) {
			System.out.println("IDには数値を入力してください");
		}catch(Exception ex) {
			//例外処理
			System.out.println("例外エラーIserLogin.java");
		}
		finally {
			if (ud != null) {
				ud.close();
			}
		}
		return jsp;
	}
}
