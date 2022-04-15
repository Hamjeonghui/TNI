package com.ham.app.service;

import java.util.List;

import com.ham.app.vo.FavVO;

//Service의 메서드를 통해 DAO의 메서드를 수행(중간다리:C의 역할)
public interface FavService {

	public boolean insert_fav(FavVO vo) ; //좋아요 + 트랜잭션(게시글 좋아요수 ++)
	public List<FavVO> getList_fav (FavVO vo); //좋아요 목록(aid의 좋아요/uid의 좋아요)
	public boolean delete_fav(FavVO vo); //좋아요 취소 + 트랜잭션(게시글 좋아요수 ++)
	public int get_favCheck(FavVO vo); //전달된 id와 게시글 pk를 가지고, 좋아요의 유무를 반환
	
}
