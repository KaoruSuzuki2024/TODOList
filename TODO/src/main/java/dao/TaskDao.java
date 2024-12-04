package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		PreparedStatement pstatement = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "INSERT INTO tasks(use_id,task_title,task_deadline,task_priority,task_content,task_check) values(?,?,?,?,?,?);";
			pstatement = connection.prepareStatement(sql);
			
			pstatement.setInt(1, tBean.getUser_id());
			pstatement.setString(2,tBean.getTitle());
			pstatement.setDate(3,(java.sql.Date) tBean.getDeadline());
			pstatement.setInt(4, tBean.getPriority());
			pstatement.setString(5,tBean.getContent());
			pstatement.setBoolean(6, tBean.isCheck());
			
			//INSERTを実行
			pstatement.executeUpdate();

		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}	
	}
	
	//受け取ったTaskBeanを更新するメソッド
	public void updateTask(TaskBean tBean)throws SQLException {
		PreparedStatement pstatement = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "UPDATE tasks SET task_title = ?,task_deadline = ?,task_priority = ?,task_content = ?,task_check = ? WHERE task_id = ?;";
			pstatement = connection.prepareStatement(sql);
			
			pstatement.setString(1,tBean.getTitle());
			pstatement.setDate(2,(java.sql.Date) tBean.getDeadline());
			pstatement.setInt(3, tBean.getPriority());
			pstatement.setString(4,tBean.getContent());
			pstatement.setBoolean(5, tBean.isCheck());
			pstatement.setInt(6, tBean.getTask_id());
			
			//UPDATEを実行
			pstatement.executeUpdate();
			
		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}	
	}
	
	//受け取ったTaskBeanを削除するメソッド
	public void deleteTask(String task_id)throws SQLException {
		PreparedStatement pstatement = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "DELETE FROM tasks WHERE task_id = ?;";
			pstatement = connection.prepareStatement(sql);
			
			pstatement.setInt(1, Integer.parseInt(task_id));
			
			//DELETEを実行
			pstatement.executeUpdate();
			
		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
	}
	
	//受け取ったtask_idをもとにタスクデータを取得するメソッド
	public TaskBean searchTask(String task_id)throws SQLException {
		TaskBean tb =new TaskBean();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM tasks WHERE task_id = ?;";
			pstatement = connection.prepareStatement(sql);
			
			//INパラメータの設定
			pstatement.setInt(1,Integer.parseInt(task_id));
			
			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			if(rs.next()) {
				//列名を指定してResultSetオブジェクトから値を取得
				tb.setUser_id(rs.getInt("user_id"));
				tb.setTask_id(rs.getInt("task_id"));
				tb.setTitle(rs.getString("title"));
				tb.setDeadline(rs.getDate("deadline"));
				tb.setPriority(rs.getInt("priority"));
				tb.setContent(rs.getString("content"));
				tb.setCheck(rs.getBoolean("check"));
			}
			//ResultSetオブジェクトの解放
			rs.close();
		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return tb;
	}
	
	//達成済みがTRUEのタスクを締め切りが近い順に取得するメソッド
	public ArrayList<TaskBean> searchAchiveDead() throws SQLException{
		ArrayList<TaskBean> arrayTb = new ArrayList<>();
		TaskBean tb =new TaskBean();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM tasks WHERE task_check = TRUE ORDER BY task_deadline ASC;";
			pstatement = connection.prepareStatement(sql);
			
			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			
			while(rs.next()) {
				//列名を指定してResultSetオブジェクトから値を取得
				tb.setUser_id(rs.getInt("user_id"));
				tb.setTask_id(rs.getInt("task_id"));
				tb.setTitle(rs.getString("title"));
				tb.setDeadline(rs.getDate("deadline"));
				tb.setPriority(rs.getInt("priority"));
				tb.setContent(rs.getString("content"));
				tb.setCheck(rs.getBoolean("check"));
				arrayTb.add(tb);
			}
			
			//ResultSetオブジェクトの解放
			rs.close();
		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayTb;
	}
	
	//達成済みがTRUEのタスクを優先度が高い順に取得するメソッド
	public ArrayList<TaskBean> searchAchivePriority() throws SQLException{
		ArrayList<TaskBean> arrayTb = new ArrayList<>();
		TaskBean tb =new TaskBean();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM tasks WHERE task_check = TRUE ORDER BY task_priority DESC;";
			pstatement = connection.prepareStatement(sql);
			
			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			
			while(rs.next()) {
				//列名を指定してResultSetオブジェクトから値を取得
				tb.setUser_id(rs.getInt("user_id"));
				tb.setTask_id(rs.getInt("task_id"));
				tb.setTitle(rs.getString("title"));
				tb.setDeadline(rs.getDate("deadline"));
				tb.setPriority(rs.getInt("priority"));
				tb.setContent(rs.getString("content"));
				tb.setCheck(rs.getBoolean("check"));
				arrayTb.add(tb);
			}
			//ResultSetオブジェクトの解放
			rs.close();
		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayTb;
	}
	
	//達成済みがFALSEのタスクを締め切りが近い順に取得するメソッド
	public ArrayList<TaskBean> searchUnachiveDead() throws SQLException{
		ArrayList<TaskBean> arrayTb = new ArrayList<>();
		TaskBean tb =new TaskBean();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM tasks WHERE task_check = FALSE ORDER BY CASE task_deadline ASC;";
			pstatement = connection.prepareStatement(sql);
			
			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			
			while(rs.next()) {
				//列名を指定してResultSetオブジェクトから値を取得
				tb.setUser_id(rs.getInt("user_id"));
				tb.setTask_id(rs.getInt("task_id"));
				tb.setTitle(rs.getString("title"));
				tb.setDeadline(rs.getDate("deadline"));
				tb.setPriority(rs.getInt("priority"));
				tb.setContent(rs.getString("content"));
				tb.setCheck(rs.getBoolean("check"));
				arrayTb.add(tb);
			}
			//ResultSetオブジェクトの解放
			rs.close();
		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayTb;
	}
	
	//達成済みがFALSEのタスクを優先度が高い順に取得するメソッド
	public ArrayList<TaskBean> searchUnachivePriority() throws SQLException{
		ArrayList<TaskBean> arrayTb = new ArrayList<>();
		TaskBean tb =new TaskBean();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM tasks WHERE task_check = FALSE ORDER BY task_priority DESC;";
			pstatement = connection.prepareStatement(sql);
			
			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			
			while(rs.next()) {
				//列名を指定してResultSetオブジェクトから値を取得
				tb.setUser_id(rs.getInt("user_id"));
				tb.setTask_id(rs.getInt("task_id"));
				tb.setTitle(rs.getString("title"));
				tb.setDeadline(rs.getDate("deadline"));
				tb.setPriority(rs.getInt("priority"));
				tb.setContent(rs.getString("content"));
				tb.setCheck(rs.getBoolean("check"));
				arrayTb.add(tb);
			}
			//ResultSetオブジェクトの解放
			rs.close();
		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayTb;
	}
}