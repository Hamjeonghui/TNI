package com.ham.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ham.app.dao.UsersDAO_3;
import com.ham.app.service.UsersService;
import com.ham.app.vo.UsersVO;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Autowired
	// 실질적으로 메서드를 수행할 dao객체
	private UsersDAO_3 usersDAO;
	
	@Override
	public int insert_users(UsersVO vo) {
		return usersDAO.insert_users(vo);
	}

	@Override
	public int login_users(UsersVO vo) {
		return usersDAO.login_users(vo);
	}

/*	
	@Override
	public int idCheck_users(UsersVO vo) {
		return usersDAO.idCheck_users(vo);
	}
*/
	@Override
	public UsersVO get_users(UsersVO vo) {
		return usersDAO.get_users(vo);
	}

	@Override
	public boolean update_users(UsersVO vo) {
		return usersDAO.update_users(vo);
	}

	@Override
	public boolean delete_users(UsersVO vo) {
		return usersDAO.delete_users(vo);
	}

	@Override
	public List<UsersVO> getList_user(UsersVO vo) {
		return usersDAO.getList_user(vo);
	}

}
