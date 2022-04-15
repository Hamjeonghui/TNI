package com.ham.app.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil { 

	static final String driverName="com.mysql.cj.jdbc.Driver";
	static final String url="jdbc:mysql://localhost:3306/hamdb"; //hamdb부분은 본인이 사용중인 db이름 입력
	static final String user="root";
	static final String passwd="12341234";

	// DB연결
	public static Connection connect() {
		Connection conn = null;

		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	//DB연결끊기
	public static void disconnect(PreparedStatement pstmt,Connection conn) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}