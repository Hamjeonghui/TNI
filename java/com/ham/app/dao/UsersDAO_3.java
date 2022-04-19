package com.ham.app.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ham.app.vo.UsersVO;

@Repository("usersDAO_3")
public class UsersDAO_3 {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 회원가입
	public int insert_users(UsersVO vo) {
		int flag=0;
		if(mybatis.insert("UsersDAO.insertUsers", vo)>0) { //가입이 성공했다면,
			flag=1; //1 반환
		}
		return flag; //실패시 0 반환됨
	}

	//로그인
	public int login_users(UsersVO vo) {
		int flag=0;
		
		if(mybatis.selectOne("UsersDAO.loginUsers", vo) != null) { // 로그인 성공
			System.out.println("dao3: 로그인 성공");
			flag=1;
		}else {
			System.out.println("dao3: 로그인 실패");
			flag=0;
		}
		
		return flag;
	}

	// 회원정보조회
	public UsersVO get_users(UsersVO vo) {	
		return mybatis.selectOne("UsersDAO.getUsers", vo);
	}

	//회원정보변경
	public boolean update_users(UsersVO vo) {
		boolean flag = false;
		
		if(mybatis.update("UsersDAO.updateUsers", vo)>0) {
			System.out.println("dao3: 회원정보변경 성공");
			flag=true;
		}
		// 타투이스트라면 본인 작성 게시글의 "작성자명"도 변경
		if(vo.getUauth()==1) {
			if(mybatis.update("UsersDAO.updateArticle_uname", vo)>0) {
				System.out.println("dao3: 게시글 작성자명 변경 성공");
				flag=true;// flag=true
			}
		}
		return flag;
	}

	//회원탈퇴
	public boolean delete_users(UsersVO vo) {
		boolean flag = false;
		if(mybatis.update("UsersDAO.deleteUsers", vo)>0) {
			System.out.println("dao3: 회원 삭제 성공");
			flag=true;
		}
		return flag;
	}

	//판매자목록
	public List<UsersVO> getList_user(UsersVO vo){
		return mybatis.selectList("UsersDAO.getListUsers", vo);
	}
	
}
