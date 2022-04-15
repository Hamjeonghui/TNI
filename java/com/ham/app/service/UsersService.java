package com.ham.app.service;

import java.util.List;

import com.ham.app.vo.UsersVO;

//Service의 메서드를 통해 DAO의 메서드를 수행(중간다리:C의 역할)
public interface UsersService {

	public int insert_users(UsersVO vo); //회원가입
	public int login_users(UsersVO vo); //로그인
//	public int idCheck_users(UsersVO vo); //아이디 중복확인
	public UsersVO get_users(UsersVO vo); //특정 회원 조회
	public boolean update_users(UsersVO vo); //회원정보 수정 + 트랜잭션
	public boolean delete_users(UsersVO vo); //회원탈퇴
	public List<UsersVO> getList_user(UsersVO vo); //판매자 권한 유저 정보 조회

}