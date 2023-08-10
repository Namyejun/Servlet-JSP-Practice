package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssamz.biz.common.JDBCUtil;

public class UserDAO {
	
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// USERS 테이블 관련 SQL 명령어
	private static String USER_LIST = "select * from users";
	private static String USER_INSERT = "insert into users values(?, ?, ?, ?)";
	private static String USER_UPDATE = "update users set name = ?, role = ? where id = ?";
	private static String USER_DELETE = "delete from users where id = ?";
	private static String USER_GET = "select * from users where id = ?";
	
	// USERS 테이블 관련 CRUD 메소드
	// 회원 상세 조회
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPw(rs.getString("PW"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}
	
	// 회원 목록 조회
	public List<UserVO> getUserList() {
		List<UserVO> userList = new ArrayList<UserVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_LIST);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				UserVO vo = new UserVO();
				vo.setId(rs.getString("ID"));
				vo.setPw(rs.getString("PW"));;
				vo.setName(rs.getString("NAME"));
				vo.setRole(rs.getString("ROLE"));
				
				userList.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return userList;
	}
//	// 조회 2
//	public void getUserList2() {
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(USER_LIST);
//			rs = stmt.executeQuery();
//			
//			System.out.println("[ USER 목록 ]");
//			while (rs.next()) {
//				System.out.println(rs.getString("NAME") + "의 권한 : " + rs.getString("ROLE"));
//			}
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(rs, stmt, conn);
//		}
//	}
//	// 건 수 조회
//	public void getUserCount() {
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(USER_LIST);
//			rs = stmt.executeQuery();
//			
//			int cnt = 0;
//			while (rs.next()) {
//				cnt++;
//			}
//			System.out.println("회원 수 : " + cnt);
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(rs, stmt, conn);
//		}
//	}
	
	// 회원 가입
	public void insertUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_INSERT);
			
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPw());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			
			int count = stmt.executeUpdate();
			
			System.out.println(count + "건의 데이터 입력 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 회원 정보 수정
	public void updateUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_UPDATE);
			
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getRole());
			stmt.setString(3, vo.getId());
			
			int count = stmt.executeUpdate();
			
			System.out.println(count + "건의 데이터 업데이트 성공");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 회원 탈퇴
	public void deleteUser(String id) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_DELETE);
			
			stmt.setString(1, id);

			int count = stmt.executeUpdate();
			
			System.out.println(count + "건의 데이터 삭제 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
}
