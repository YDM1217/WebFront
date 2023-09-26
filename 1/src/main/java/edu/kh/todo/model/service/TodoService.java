package edu.kh.todo.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import static edu.kh.todo.common.JDBCTemplate.*;

import edu.kh.todo.model.dao.TodoDAO;
import edu.kh.todo.model.dto.Todo;


public class TodoService {
	
	TodoDAO dao = new TodoDAO();

	/** 회원별 투두 리스트 조회 서비스
	 * @param memberNo
	 * @return
	 */
	public List<Todo> select(int memberNo) throws Exception{
		
		List<Todo> list = new ArrayList<Todo>();
		
		Connection conn = getConnection();
		
		list = dao.select(conn, memberNo);
		
		close(conn);		
		
		return list;
	}

	/** 투두 등록 서비스
	 * @param todoTitle
	 * @param todoMemo
	 * @param memberNo 
	 * @return
	 */
	public int insert(String todoTitle, String todoMemo, int memberNo) throws Exception{
		
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.insert(conn, todoTitle, todoMemo, memberNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	/** 투두 삭제 서비스
	 * @param todoNo 
	 * @return
	 */
	public int delete(int todoNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.delete(conn, todoNo); 
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}

		close(conn);
		
		return result;
	}

	/** 투두 수정 서비스
	 * @param todoTitle
	 * @param todoMemo
	 * @param memberNo
	 * @param todoNo 
	 * @param todoNo 
	 * @return
	 */
	public int update(String todoTitle, String todoMemo, int memberNo, int todoNo) throws Exception{
	
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.update(conn, todoTitle, todoMemo, memberNo, todoNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	
	}

	/** 투두 전체 삭제 서비스
	 * @param memberNo
	 * @return
	 */
	public int deleteAll(int memberNo) throws Exception{
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.deleteAll(conn, memberNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	
	}

}

