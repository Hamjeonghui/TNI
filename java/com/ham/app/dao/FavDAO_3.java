package com.ham.app.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ham.app.vo.FavVO;

@Repository("favDAO_3")
public class FavDAO_3 {

	@Autowired
	private SqlSessionTemplate mybatis;

	//좋아요 "트랜잭션처리"
	@Transactional
	public boolean insert_fav(FavVO vo) {
		boolean flag = false;
		vo.setFid(vo.getUid()+"_"+vo.getAid());

		try {
			if(mybatis.insert("FavDAO.insertFav", vo) > 0) { // 좋아요 성공했다면
				System.out.println("dao3: 좋아요 성공");
				if(mybatis.update("FavDAO.updateArticle_fav_p", vo) > 0) {// 게시글 좋아요개수 수정
					System.out.println("dao3: 해당 게시글 좋아요 개수 추가 성공");
					flag=true;
				}
			}
		} catch(Exception e) {
			System.out.println("dao3: 좋아요 실패");
			flag=false;
		}
		return flag;
	}

	// 좋아요 목록
	public List<FavVO> getList_fav (FavVO vo){
		return mybatis.selectList("FavDAO.getListFav", vo);
	}

	//좋아요 취소 "트랜잭션처리"
	@Transactional
	public boolean delete_fav(FavVO vo) {
		boolean flag = false;
		if(mybatis.delete("FavDAO.deleteFav", vo)>0) {
			System.out.println("dao3: 좋아요 취소 성공");
			if(mybatis.update("FavDAO.updateArticle_fav_m", vo)>0) {
				System.out.println("dao3: 게시글 좋아요 개수 감소 성공");
				flag=true;
			}
		}
		return flag;
	}

	// 좋아요의 유무 확인
	public int get_favCheck(FavVO vo) {
		int flag=0;//좋아요가 아닌 상태
		if (mybatis.selectOne("FavDAO.getFav", vo) != null) {
			System.out.println("dao3: 좋아요인 상태");
			flag=1;
		}
		return flag;//해당 id의 좋아요 상태를 반환
	}

}
