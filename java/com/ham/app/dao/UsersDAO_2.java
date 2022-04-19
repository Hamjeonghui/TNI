package com.ham.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ham.app.vo.UsersVO;

@Repository("usersDAO_2") // 해당 클래스 초기화
public class UsersDAO_2 {

	@Autowired // xml파일에 초기화 되어있음
	private JdbcTemplate jdbcTemplate;

	private final String USERS_INSERT="insert into users values(?,?,?,?,?)";
	// private final String USERS_SELECTALL="select * from users"; //전체목록조회
	private final String USERS_SELECTALL_SELLER="select * from users where uauth=1";//판매자 전체목록
	private final String USERS_SELECTONE="select * from users where uid=?"; //한명의 유저정보
	private final String USERS_DELETE="delete from users where uid=?"; //유저 삭제
	private final String USERS_UPDATE="update users set pw=?, uname=? where uid=?"; //유저정보수정
	private final String USERS_LOGIN="select * from users where uid=? and pw=?"; //로그인
	private final String ARTICLE_UPDATE="update article set uname=? where uid=?"; //게시글 테이블의 작성자 이름도 수정

	// 회원가입
	public int insert_users(UsersVO vo) {
		int flag=0;
		Object[] args= {vo.getUid(), vo.getPw(), vo.getUname(), vo.getUbirth(), vo.getUauth()};
		if(jdbcTemplate.update(USERS_INSERT, args)>0) { //가입이 성공했다면,
			flag=1; //1 반환
		}
		return flag; //실패시 0 반환됨
	}

	//로그인
	public int login_users(UsersVO vo) {
		// null이 반환되어 발생할 예외를 방지하여 try catch
		int flag=0;
		Object[] args= {vo.getUid(), vo.getPw()};
		try {			
			jdbcTemplate.queryForObject(USERS_LOGIN, args, new UsersRowMapper());
			System.out.println("dao2: 로그인 성공");
			flag=1;
		} catch (Exception e) {
			System.out.println("dao2: 로그인 실패");
			flag=0;
		}
		return flag;
	}

	// 회원정보조회
	public UsersVO get_users(UsersVO vo) {
		// null이 반환되어 발생할 예외를 방지하여 try catch
		UsersVO data = null;
		Object[] args= {vo.getUid()};
		try {			
			data=jdbcTemplate.queryForObject(USERS_SELECTONE, args, new UsersRowMapper());
			System.out.println("dao2:선택 유저 정보 조회");
		} catch (Exception e) {
			System.out.println("dao2: 유저 정보 조회 실패");
		}
		return data;
	}

	//회원정보변경 + 트랜잭션 처리
	public boolean update_users(UsersVO vo) {
		boolean flag = false;

		Object[] args= {vo.getPw(), vo.getUname(), vo.getUid()};
		if(jdbcTemplate.update(USERS_UPDATE, args)>0) { //타투이스트가 아니라면, 회원정보만 변경
			System.out.println("dao2: 회원정보변경 성공");
			flag=true;
		}
		if(vo.getUauth()==1) { // 타투이스트라면 게시글 작성자명도 변경
			Object[] args2= {vo.getUname(), vo.getUid()};
			if(jdbcTemplate.update(ARTICLE_UPDATE, args2)>0) {//유저정보+게시글의 작성자명 수정 성공하면,
				System.out.println("dao2: 게시글 작성자명 변경 성공");
				flag=true;// flag=true
			}
		}

		return flag;
	}

	//회원탈퇴
	public boolean delete_users(UsersVO vo) {
		boolean flag = false;
		if(jdbcTemplate.update(USERS_DELETE, vo.getUid())>0) {
			System.out.println("dao2: 회원 삭제 성공");
			flag=true;
		}
		return flag;
	}

	//판매자목록
	public List<UsersVO> getList_user(UsersVO vo){
		List<UsersVO> datas = null;
		datas=jdbcTemplate.query(USERS_SELECTALL_SELLER, new UsersRowMapper());
		return datas;
	}

}

//RowMapper
class UsersRowMapper implements RowMapper<UsersVO>{

	@Override
	public UsersVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UsersVO data = new UsersVO();

		data.setUid(rs.getString("uid"));
		data.setPw(rs.getString("pw"));
		data.setUname(rs.getString("uname"));
		data.setUbirth(rs.getString("ubirth"));
		data.setUauth(rs.getInt("uauth"));

		return data;
	}

}
