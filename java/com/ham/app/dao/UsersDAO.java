package com.ham.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ham.app.common.JDBCUtil;
import com.ham.app.vo.UsersVO;

@Repository("usersDAO")
public class UsersDAO {

	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs= null;
	private List<UsersVO> datas;

	private final String USERS_INSERT="insert into users values(?,?,?,?,?)";
	// private final String USERS_SELECTALL="select * from users"; //전체목록조회
	private final String USERS_SELECTALL_SELLER="select * from users where uauth=1";//판매자 전체목록
	private final String USERS_SELECTONE="select * from users where uid=?"; //한명의 유저정보
	private final String USERS_DELETE="delete from users where uid=?"; //유저 삭제
	private final String USERS_UPDATE="update users set pw=?, uname=? where uid=?"; //유저정보수정
	private final String USERS_LOGIN="select pw from users where uid=?"; //pw확인
	private final String ARTICLE_UPDATE="update article set uname=? where uid=?;"; //게시글 테이블의 작성자 이름도 수정
	
	// 회원가입
	public int insert_users(UsersVO vo) {

		int flag=0; // 예외에 대한 처리를 controller에서 유동처리 가능
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(USERS_INSERT);
			pstmt.setString(1, vo.getUid());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getUname());
			pstmt.setString(4, vo.getUbirth());
			pstmt.setInt(5, vo.getUauth());
			pstmt.executeUpdate();
			System.out.println("dao: 회원가입 성공");
			flag = 1;// 성공

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("dao: 회원가입 실패_아이디 중복");
			flag = 2;// 아이디중복
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("dao: 회원가입 실패_기타 사유");
			flag = 3;// 예외
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;

	}

	//로그인
	public int login_users(UsersVO vo) {

		int flag=0;
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(USERS_LOGIN);
			pstmt.setString(1, vo.getUid());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				String pw = rs.getString("pw");
				
				System.out.println("DB: "+pw);
				System.out.println("입력된: "+vo.getPw()); //null...?
				
				//입력된 pw와 DB의 pw가 일치한다면,
				if (vo.getPw().equals(pw)) {
					System.out.println("dao : pw일치!");
					flag = 1;// 성공
				} else {
					System.out.println("dao : pw불일치!");
					flag = 2;// 비밀번호틀림
				}
			} else {
				System.out.println("dao : 존재하지 않는 id");
				flag = 3;// 아이디없음
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;
	}

	// 아이디 중복 체크
	public int idCheck_users(UsersVO vo) {
		int flag = 1;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(USERS_SELECTONE);
			pstmt.setString(1, vo.getUid());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("dao : 사용 불가능_id 존재");
				flag = 1; //id존재
			}else {
				System.out.println("dao : 사용 가능");
				flag = 0; //사용가능
			}
		}catch (Exception e) {
			flag = 1; //이외오류
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;
	}

	// 회원정보조회
	public UsersVO get_users(UsersVO vo) {
		UsersVO data = null;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(USERS_SELECTONE);
			pstmt.setString(1, vo.getUid());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("dao : 회원 정보 조회");
				data = new UsersVO();
				data.setUid(rs.getString("uid"));
				data.setPw(rs.getString("pw"));
				data.setUname(rs.getString("uname"));
				data.setUbirth(rs.getString("ubirth"));
				data.setUauth(rs.getInt("uauth"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}

	//회원정보변경 + 트랜잭션 처리
	public boolean update_users(UsersVO vo) {
		boolean flag = false;
		conn = JDBCUtil.connect();

		try {
			
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(USERS_UPDATE);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getUname());
			pstmt.setString(3, vo.getUid());
			// update가 성공했다면,
			if(pstmt.executeUpdate() > 0 ) {
				System.out.println("dao : 회원 정보 수정 성공");
				
				pstmt= conn.prepareStatement(ARTICLE_UPDATE);
				pstmt.setString(1, vo.getUname());
				pstmt.setString(2, vo.getUid());
				if(pstmt.executeUpdate() > 0 ) {
					System.out.println("dao: 게시글 작성자 명 변경 성공");
					flag = true;
					conn.commit();
					conn.setAutoCommit(true);
				}
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;
	}

	//회원탈퇴
	public boolean delete_users(UsersVO vo) {
		boolean flag = false;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(USERS_DELETE);
			pstmt.setString(1, vo.getUid());
			// delete가 성공했다면,
			if(pstmt.executeUpdate() > 0) {
				System.out.println("dao : 회원 정보 삭제 성공");
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;
	}

	//판매자목록
	public List<UsersVO> getList_user(UsersVO vo){
		datas = null;
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(USERS_SELECTALL_SELLER);
			rs =  pstmt.executeQuery();
			while(rs.next()) {
				UsersVO data = new UsersVO();
				data.setUid(rs.getString("uid"));
				data.setPw(rs.getString("pw"));
				data.setUname(rs.getString("uname"));
				data.setUbirth(rs.getString("ubirth"));
				data.setUauth(rs.getInt("uauth"));
				datas.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}

}
