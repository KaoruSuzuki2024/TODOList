package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import bean.TaskBean;

public class TaskDao {
	private Connection connection;
	
	//コンストラクター
	//todo_listデータベースとの接続を行う
	public TaskDao() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/java_web_system?useSSL=false";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url,user,password);
	}
	
	
	//java_web_systemデータベースと切断するメソッド
	public void close() {
		try {
			if(connection != null) {
				connection.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}


	//受け取ったTaskBeanを新規登録するメソッド
	public void insertTask(TaskBean tBean) throws SQLException{
		TaskBean tb =null;
		PreparedStatement pstatement = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "INSERT INTO todo_list.tasks(use_id,task_title,task_deadline,task_priority,task_content,task_check) values(?,?,?,?,?,?);";
			pstatement = connection.prepareStatement(sql);
			
			int userid = tb.getUser_id();
			String title = tb.getTitle();
			Date dead = tb.getDeadline();
			int priority = tb.getPriority();
			String content = tb.getContent();
			boolean check = tb.isCheck();
			
			pstatement.setInt(1, userid);
			pstatement.setString(2,title);
			pstatement.setDate(3,(java.sql.Date) dead);
			pstatement.setInt(4, priority);
			pstatement.setString(5,content);
			pstatement.setBoolean(6, check);
			
			//INSERTを実行
			pstatement.executeUpdate();

		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}	
	}
	
	
	public void updateTask(TaskBean tBean)throws SQLException {
		TaskBean tb =null;
		PreparedStatement pstatement = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "UPDATE todo_list.tasks SET use_id = ?,task_title = ?,task_deadline = ?,task_priority = ?,task_content = ?,task_check = ?;";
			pstatement = connection.prepareStatement(sql);
			
			int userid = tb.getUser_id();
			String title = tb.getTitle();
			Date dead = tb.getDeadline();
			int priority = tb.getPriority();
			String content = tb.getContent();
			boolean check = tb.isCheck();
			
			pstatement.setInt(1, userid);
			pstatement.setString(2,title);
			pstatement.setDate(3,(java.sql.Date) dead);
			pstatement.setInt(4, priority);
			pstatement.setString(5,content);
			pstatement.setBoolean(6, check);
			
			//UPDATEを実行
			pstatement.executeUpdate();
			
		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}	
	}
	
	
	public void deleteTask(TaskBean tBean)throws SQLException {
		TaskBean tb =null;
		PreparedStatement pstatement = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "DELETE FROM todo_list.tasks WHERE use_id = ?,task_id = ?;";
			pstatement = connection.prepareStatement(sql);
			
			int userid = tb.getUser_id();
			int taskid = tb.getTask_id();
			
			pstatement.setInt(1, userid);
			pstatement.setInt(2, taskid);
			
			//DELETEを実行
			pstatement.executeUpdate();
			
		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
	}
	
	
	public TaskBean searchTask(String id) {
		TaskBean tb = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM tasks WHERE user_id = ?,task_id = ?;";
			pstatement = connection.prepareStatement(sql);
			
			//INパラメータの設定
			int userid = tb.getUser_id();
			int taskid = tb.getTask_id();
			
			pstatement.setInt(1, userid);
			pstatement.setInt(2, taskid);
			
			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			if(rs.next()) {
				//列名を指定してResultSetオブジェクトから値を取得
				tb = new TaskBean();
				//tb.setUser_id();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


