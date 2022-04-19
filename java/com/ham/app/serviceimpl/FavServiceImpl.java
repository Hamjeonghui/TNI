package com.ham.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ham.app.dao.FavDAO_3;
import com.ham.app.service.FavService;
import com.ham.app.vo.FavVO;

@Service("favService")
public class FavServiceImpl implements FavService{

	@Autowired
	// 실질적으로 메서드를 수행할 dao객체
	private FavDAO_3 favDAO;
	
	@Override
	public boolean insert_fav(FavVO vo) {
		return favDAO.insert_fav(vo);
	}

	@Override
	public List<FavVO> getList_fav(FavVO vo) {
		return favDAO.getList_fav(vo);
	}

	@Override
	public boolean delete_fav(FavVO vo) {
		return favDAO.delete_fav(vo);
	}

	@Override
	public int get_favCheck(FavVO vo) {
		return favDAO.get_favCheck(vo);
	}

}
