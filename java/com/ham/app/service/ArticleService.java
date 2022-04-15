package com.ham.app.service;

import java.util.List;

import com.ham.app.vo.ArticleSet;
import com.ham.app.vo.ArticleVO;
import com.ham.app.vo.CommentVO;

// Service의 메서드를 통해 DAO의 메서드를 수행(중간다리:C의 역할)
public interface ArticleService {
	
	public boolean insert_article(ArticleVO vo); //게시글 등록
	public boolean delete_article(ArticleVO vo); //게시글 삭제
	public boolean update_article(ArticleVO vo); //게시글 수정
	public List<ArticleVO> getList_article(ArticleVO vo); //전체 게시글 목록
	public List<ArticleSet> getList_all(ArticleVO vo); //게시글 1개+댓글 목록 전체 가져오기
	public boolean insert_comment(CommentVO vo); //댓글 작성 + 트랜잭션(게시글 댓글수++)
	public boolean delete_comment(CommentVO vo); //댓글 삭제 + 트랜잭션(게시글 댓글수--)
	public boolean insert_comment_2(CommentVO vo); //대댓글 작성 + 트랜잭션(게시글 댓글수++)
	public List<CommentVO> getList_comment(CommentVO vo); //대댓글 목록
	public List<ArticleVO> getList_article_search(ArticleVO vo); //검색결과목록
	public ArticleVO get_article_fav(ArticleVO vo);//aid에 해당하는 게시글
	
	
}
