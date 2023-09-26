package edu.kh.todo.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.todo.common.JDBCTemplate.*;


import edu.kh.member.model.dao.MemberDAO;
import edu.kh.todo.model.dto.Todo;


public class TodoDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;

	public TodoDAO(){

		try {

			prop = new Properties();
			String filePath = MemberDAO.class.getResource("/edu/kh/todo/sql/todo-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/** 투두리스트 조회 DAO
	 * @param conn
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Todo> select(Connection conn, int memberNo) throws Exception{
		List<Todo> list = new ArrayList<Todo>();

		try {

			String sql = prop.getProperty("select");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				int todoNo = rs.getInt("TODO_NO");
				String todoTitle = rs.getString("TODO_TITLE");
				String todoMemo = rs.getString("TODO_MEMO");
				String todoDate = rs.getString("TODO_DATE");
				String todoDelFlag = rs.getString("TODO_DEL_FL");

				list.add( new Todo(todoNo, todoTitle, todoMemo, todoDate, todoDelFlag, memberNo));
			}

		}finally {

			close(rs);
			close(pstmt);
		}

		return list;
	}

	/** 투두 등록 DAO
	 * @param conn
	 * @param todoTitle
	 * @param todoMemo
	 * @param memberNo 
	 * @return
	 */
	public int insert(Connection conn, String todoTitle, String todoMemo, int memberNo) throws Exception{

		int result = 0;

		try {

			String sql = prop.getProperty("insert");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, todoTitle);
			pstmt.setString(2, todoMemo);
			pstmt.setInt(3, memberNo);

			result = pstmt.executeUpdate();


		}finally {
			close(rs);
			close(pstmt);

		}
		return result;
	}

	/** 투두 삭제 DAO
	 * @param conn
	 * @return
	 */
	public int delete(Connection conn, int todoNo) throws Exception{

		int result = 0;

		try {

			String sql = prop.getProperty("delete");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, todoNo);

			result = pstmt.executeUpdate();

		}finally {

			close(pstmt);

		}

		return result;
	}

	/** 투두 수정 DAO
	 * @param conn
	 * @param todoTitle
	 * @param todoMemo
	 * @param memberNo
	 * @param todoNo 
	 * @param todoNo 
	 * @return
	 */
	public int update(Connection conn, String todoTitle, String todoMemo, int memberNo, int todoNo) throws Exception {
		int result = 0;

		try {

			String sql = prop.getProperty("update");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, todoTitle);
			pstmt.setString(2, todoMemo);
			pstmt.setInt(3, memberNo);
			pstmt.setInt(4, todoNo);
			
			result = pstmt.executeUpdate();


		}finally {
			close(rs);
			close(pstmt);

		}
		return result;
	}

	/** 투두 전체삭제 DAO
	 * @param conn
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int deleteAll(Connection conn, int memberNo) throws Exception{
		int result = 0;

		try {

			String sql = prop.getProperty("deleteAll");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			result = pstmt.executeUpdate();

		}finally {

			close(pstmt);

		}

		return result;
	}

}

