package com.ham.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ham.app.dao.ArticleDAO_2;
import com.ham.app.service.ArticleService;
import com.ham.app.vo.ArticleSet;
import com.ham.app.vo.ArticleVO;
import com.ham.app.vo.CommentVO;

// 어노테이션으로 의존성 주입
@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	// 실질적으로 메서드를 수행할 dao객체
	private ArticleDAO_2 articleDAO;
	
	@Override
	public boolean insert_article(ArticleVO vo) {
		return articleDAO.insert_article(vo);
	}

	@Override
	public boolean delete_article(ArticleVO vo) {
		return articleDAO.delete_article(vo);
	}

	@Override
	public boolean update_article(ArticleVO vo) {
		return articleDAO.update_article(vo);
	}

	@Override
	public List<ArticleVO> getList_article(ArticleVO vo) {
		return articleDAO.getList_article(vo);
	}

	@Override
	public List<ArticleSet> getList_all(ArticleVO vo) {
		return articleDAO.getList_all(vo);
	}

	@Override
	public boolean insert_comment(CommentVO vo) {
		return articleDAO.insert_comment(vo);
	}

	@Override
	public boolean delete_comment(CommentVO vo) {
		return articleDAO.delete_comment(vo);
	}

	@Override
	public boolean insert_comment_2(CommentVO vo) {
		return articleDAO.insert_comment_2(vo);
	}

	@Override
	public List<CommentVO> getList_comment(CommentVO vo) {
		return articleDAO.getList_comment(vo);
	}

	@Override
	public List<ArticleVO> getList_article_search(ArticleVO vo) {
		return articleDAO.getList_article_search(vo);
	}

	@Override
	public ArticleVO get_article_fav(ArticleVO vo) {
		return articleDAO.get_article_fav(vo);
	}

}
