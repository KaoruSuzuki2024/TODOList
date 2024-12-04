package service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

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
				ud = new UsersDao();
				UsersBean ub = ud.searchUser(id);
				if (password == ub.getPassword()) {
					/*ログイン処理*/
					jsp = "/taskhome.jsp";
				} //else {request.setAttribute("message", "IDとPWが一致しません");}
			} else {
				request.setAttribute("message", "IDとPWが一致しません");
			}
		} catch (Exception ex) {
			//例外処理
		} finally {
			if (ud != null) {
				ud.close();
			}
		}
		return jsp;
	}
}
