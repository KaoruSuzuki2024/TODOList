package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UsersBean;

public class UsersDao {
	private Connection connection;
	
	public UsersDao() throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/java_web_system?useSSL=false";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url,user,password);
	}
	
	public void close() {
		try {
			if(connection != null) {
				connection.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ユーザーデータを取得するメソッド
	public UsersBean searchUser(String user_id) throws SQLException{
		UsersBean bean = new UsersBean();
		ResultSet rs = null;
		PreparedStatement pstatement = null;
		try {
			String sql = "select * from users where user_id=?";
			pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1, Integer.parseInt(user_id));
			rs = pstatement.executeQuery();
			if(rs.next()) {
				bean.setId(rs.getInt("user_id"));
				bean.setName(rs.getString("user_name"));
				bean.setPassword(rs.getString("user_pass"));
			}
		}finally {
			pstatement.close();
		}
		return bean;
	}
}
