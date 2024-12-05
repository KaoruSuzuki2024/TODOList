package daotest;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.TaskBean;
import dao.TaskDao;

public class TaskDaoTest {
	public static void main(String[] args) {
		TaskDao dao = null;
		String id = args[0];
		int flg = Integer.parseInt(args[1]);
		TaskBean bean = null;
		ArrayList<TaskBean> list = null;
		try {
			dao = new TaskDao();
			if(flg == 0) {//タスクidを指定し検索
				bean = dao.searchTask(id);
				if(bean != null) {
					System.out.println("task_id：" + bean.getTask_id());
					System.out.println("user_id：" + bean.getUser_id());
					System.out.println("task_title：" + bean.getTitle());
					System.out.println("task_deadline：" + bean.getDeadline());
					System.out.println("task_priority：" + bean.getPriority());
					System.out.println("task_content：" + bean.getContent());
					System.out.println("task_check：" + bean.isCheck());
				}else {
					System.out.println("取得に失敗");
				}
			}else if(flg == 1) {//ユーザーidの達成済みタスクを締切日順に表示
				list = dao.searchAchiveDead(id);
				if(!list.isEmpty()) {
					for(TaskBean be : list) {
						System.out.println("task_id：" + be.getTask_id());
						System.out.println("user_id：" + be.getUser_id());
						System.out.println("task_title：" + be.getTitle());
						System.out.println("task_deadline：" + be.getDeadline());
						System.out.println("task_priority：" + be.getPriority());
						System.out.println("task_content：" + be.getContent());
						System.out.println("task_check：" + be.isCheck());
						System.out.println("------------------------------------------");
					}
				}else if(list.size() <= 0) {
					System.out.println("タスクがありません");
				}
				else {
					System.out.println("リストの取得に失敗");
				}
			}else if(flg == 2) {//ユーザーidの達成済みタスクを優先度順に表示
				list = dao.searchAchivePriority(id);
				if(!list.isEmpty()) {
					for(TaskBean be : list) {
						System.out.println("task_id：" + be.getTask_id());
						System.out.println("user_id：" + be.getUser_id());
						System.out.println("task_title：" + be.getTitle());
						System.out.println("task_deadline：" + be.getDeadline());
						System.out.println("task_priority：" + be.getPriority());
						System.out.println("task_content：" + be.getContent());
						System.out.println("task_check：" + be.isCheck());
						System.out.println("------------------------------------------");
					}
				}else if(list.size() <= 0) {
					System.out.println("タスクがありません");
				}
				else {
					System.out.println("リストの取得に失敗");
				}
			}else if(flg == 3) {//ユーザーidの未達成タスクを締切日順に表示
				list = dao.searchUnachiveDead(id);
				if(!list.isEmpty()) {
					for(TaskBean be : list) {
						System.out.println("task_id：" + be.getTask_id());
						System.out.println("user_id：" + be.getUser_id());
						System.out.println("task_title：" + be.getTitle());
						System.out.println("task_deadline：" + be.getDeadline());
						System.out.println("task_priority：" + be.getPriority());
						System.out.println("task_content：" + be.getContent());
						System.out.println("task_check：" + be.isCheck());
						System.out.println("------------------------------------------");					}
				}else if(list.size() <= 0) {
					System.out.println("タスクがありません");
				}
				else {
					System.out.println("リストの取得に失敗");
				}
			}else if(flg == 4) {//ユーザーidの未達成タスクを優先度順に表示
				list = dao.searchUnachivePriority(id);
				if(!list.isEmpty()) {
					for(TaskBean be : list) {
						System.out.println("task_id：" + be.getTask_id());
						System.out.println("user_id：" + be.getUser_id());
						System.out.println("task_title：" + be.getTitle());
						System.out.println("task_deadline：" + be.getDeadline());
						System.out.println("task_priority：" + be.getPriority());
						System.out.println("task_content：" + be.getContent());
						System.out.println("task_check：" + be.isCheck());
						System.out.println("------------------------------------------");
					}
				}else if(list.size() <= 0) {
					System.out.println("タスクがありません");
				}
				else {
					System.out.println("リストの取得に失敗");
				}
			}else if(flg == 5) {//更新
				bean = dao.searchTask(id);
				bean.setTitle("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
				//bean.setTitle(null);//テスト項目No.14実行時にコメントを外してください
				int numRow = dao.updateTask(bean);
				if(numRow > 0) {
					System.out.println("更新に成功");
				}else {
					System.out.println("更新に失敗");
				}
			}else if(flg == 6) {//新規追加
				bean = dao.searchTask(id);
				bean.setUser_id(1);
				bean.setTitle(null);
				bean.setPriority(1);
				bean.setContent("スキルアップのための勉強");
				bean.setCheck(false);
				int numRow = dao.insertTask(bean);
				if(numRow > 0) {
					System.out.println("登録成功");
				}else {
					System.out.println("登録失敗");
				}
			}else if(flg == 7){//タスクの削除
				int numRow = dao.deleteTask(id);
				if(numRow > 0) {
					System.out.println("削除しました");
				}else {
					System.out.println("削除失敗しました");
				}
			}
		}catch(SQLException e) {
			System.out.println("JDBCエラーです");
			e.getMessage();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("クラスの読み込みに失敗しました");
		}finally {
			if(dao != null) {
				dao.close();
			}
		}
	}
}
