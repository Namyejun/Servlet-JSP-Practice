package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssamz.biz.common.JDBCUtil;

public class SelectUserTest {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from users";
			
			stmt = conn.prepareStatement(sql);
			System.out.println(stmt);
			
			rs = stmt.executeQuery();
			
			System.out.println("[ USER 목록 ]");
			while (rs.next()) {
				System.out.print(rs.getString("ID") + " : ");
				System.out.print(rs.getString("PW") + " : ");
				System.out.print(rs.getString("NAME") + " : ");
				System.out.println(rs.getString("ROLE"));
			}
			
			JDBCUtil.close(rs, stmt, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
