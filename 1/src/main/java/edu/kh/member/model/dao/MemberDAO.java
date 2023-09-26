package edu.kh.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.member.model.dto.Member;

import static edu.kh.todo.common.JDBCTemplate.*;

public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;

	public MemberDAO(){

		try {

			prop = new Properties();
			String filePath = MemberDAO.class.getResource("/edu/kh/todo/sql/member-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/** 회원가입 DAO
	 * @param conn
	 * @param inputId
	 * @param inputPw
	 * @param inputNickname
	 * @return
	 * @throws Exception
	 */
	public int signup(Connection conn, String inputId, String inputPw, String inputNickname) throws Exception{

		int result = 0;

		try {

			String sql = prop.getProperty("signup");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);
			pstmt.setString(3, inputNickname);

			result= pstmt.executeUpdate();

		}finally {

			close(pstmt);

		}

		return result;
	}


	/** 로그인 DAO
	 * @param conn
	 * @param inputId
	 * @param inputPw
	 * @return
	 * @throws Exception
	 */
	public Member login(Connection conn, String inputId, String inputPw) throws Exception {

		Member member = new Member();

		try {

			String sql = prop.getProperty("login");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);

			rs = pstmt.executeQuery();

			if(rs.next()) {

				int memberNo = rs.getInt("MEMBER_NO");
				String memberId = rs.getString("MEMBER_ID");
				String memberPw = rs.getString("MEMBER_PW");
				String memberNickname = rs.getString("MEMBER_NICKNAME");
				String enrollDate = rs.getString("ENROLL_DATE");

				member.setMemberNo(memberNo);
				member.setMemberId(memberId);
				member.setMemberPw(memberPw);
				member.setMemberNickname(memberNickname);
				member.setEnrollDate(enrollDate);
			}


		}finally {
			close(rs);
			close(pstmt);

		}

		return member;
	}



	/** 회원 조회 DAO
	 * @param conn
	 * @param inputId
	 * @return
	 */
	public Member select(Connection conn, String inputId) throws Exception {

		Member member = new Member();

		try {

			String sql = prop.getProperty("select");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, inputId);

			rs= pstmt.executeQuery();
					
			if(rs.next()) {

				int memberNo = rs.getInt("MEMBER_NO");
				String memberId = rs.getString("MEMBER_ID");
				String memberPw = rs.getString("MEMBER_PW");
				String memberNickname = rs.getString("MEMBER_NICKNAME");
				String enrollDate = rs.getString("ENROLL_DATE");

				member.setMemberNo(memberNo);
				member.setMemberId(memberId);
				member.setMemberPw(memberPw);
				member.setMemberNickname(memberNickname);
				member.setEnrollDate(enrollDate);
			}

		}finally {
			
			close(rs);
			close(pstmt);
			
		}

		return member;

	}


}
