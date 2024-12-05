package daotest;

import java.sql.SQLException;

import bean.UsersBean;
import dao.UsersDao;

public class UsersDaoTest {
	public static void main(String[] args) {
		UsersDao dao = null;
		String id = args[0];
		try {
			if(id != null &&!id.isEmpty()) {
				UsersBean user = new UsersBean();
				dao = new UsersDao();
				user = dao.searchUser(id);
				if(user != null) {
					System.out.println("user_id：" + user.getId());
					System.out.println("user_pass：" + user.getPassword());
					System.out.println("user_name：" + user.getName());
				}
			}else {
				System.out.println("idをコマンドライン引数に入れて実行");
			}
		}catch(SQLException e) {
			System.out.println("SQLでエラーが発生しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("クラスが見つかりませんでした");
		}catch(NumberFormatException e) {
			System.out.println("数値以外が入力されました。");
		}
		finally {
			if(dao != null) {
				dao.close();
			}
		}
	}
}
