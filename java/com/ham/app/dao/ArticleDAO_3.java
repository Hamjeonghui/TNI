package com.ham.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ham.app.vo.ArticleSet;
import com.ham.app.vo.ArticleVO;
import com.ham.app.vo.CommentVO;

@Repository("articleDAO_3")
public class ArticleDAO_3 {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	//input파라미터 타입으로 vo를 주고 있는 것이다.
	public List<ArticleSet> getList_all(ArticleVO vo){
		CommentVO cvo = new CommentVO();
		// ArticleSet을 여러개 담은 배열리스트
		List<ArticleSet> datas=new ArrayList<ArticleSet>();
		// 해당 객체를 사용하기 위해 필요한 매개변수 articleVO와 ArrayList<commentVO>
		ArticleSet as=new ArticleSet();
		
		List<CommentVO> commentDatas1=mybatis.selectList("ArticleDAO.getListComment", vo);
		
		cvo.setAid(vo.getAid()); // 대댓글의 조회 기준
		for(int i=0; i<commentDatas1.size(); i++) {
			cvo.setCgroup(commentDatas1.get(i).getCid()); // 대댓글의 부모댓글
			List<CommentVO> commentDatas2=mybatis.selectList("ArticleDAO.getListComment2", cvo);			
			as.setCommentList2(commentDatas2);
		}
		as.setArticleVO((ArticleVO) mybatis.selectOne("ArticleDAO.getArticle", vo));
		as.setCommentList1(commentDatas1);
		datas.add(as);
		
		return datas;
	}
	
	// 검색
	public List<ArticleVO> getList_article_search(ArticleVO vo){
		return mybatis.selectList("ArticleDAO.getArticleList_search", vo);
	}

	// 정렬
	public List<ArticleVO> getList_article(ArticleVO vo){
		return mybatis.selectList("ArticleDAO.getArticleList", vo);
	}	
	
	// 대댓글 조회
	public List<CommentVO> getList_comment(CommentVO vo) {
		return mybatis.selectList("ArticleDAO.getListComment2", vo);
	}
	
	// aid에 해당하는 게시글 조회
	public ArticleVO get_article_fav(ArticleVO vo) {
		return mybatis.selectOne("ArticleDAO.getArticle", vo);
	}
	
	// 게시글등록
	public boolean insert_article(ArticleVO vo) {
		boolean flag=false;
		
		if(mybatis.insert("ArticleDAO.insertArticle", vo) > 0) {
			flag=true;
		}
		
		return flag;
	}
	
	// 게시글 삭제
	public boolean delete_article(ArticleVO vo) {
		boolean flag=false;
		
		if(mybatis.delete("ArticleDAO.deleteArticle", vo) > 0) {
			flag=true;
		}
		
		return flag;
	}
	
	// 게시글 수정
	public boolean update_article(ArticleVO vo) {
		boolean flag=false;
		
		if(vo.getCnt() == 1) { //원하는 요청이 조회수 누적이라면,
			if(mybatis.update("ArticleDAO.updateArticle_cnt", vo) > 0) {
				flag=true;
			}
		}else if(vo.getCnt() == 2) { // 아무런 수정도 원하지 않는다면,
			System.out.println("dao3: 조회수 누적을 원하지 않습니다.");
		} else { // 그게 아니라면 그냥 게시글 수정 요청으로 간주
			if(mybatis.update("ArticleDAO.updateArticle", vo) > 0) {
				flag=true;
			}
		}
		
		return flag;
	}
	
	// 댓글 등록 "트랜잭션 처리"
	public boolean insert_comment(CommentVO vo) {
		boolean flag=false;
		
		if(mybatis.insert("ArticleDAO.insertComment", vo)>0) {
			if(mybatis.update("ArticleDAO.updateArticle_rcnt_p", vo)>0) {
				flag=true;
			}
		}
		
		return flag;
	}
	
	// 대댓글 등록 "트랜잭션 처리"
	public boolean insert_comment_2(CommentVO vo) {
		boolean flag=false;
		
		if(mybatis.insert("ArticleDAO.insertComment2", vo)>0) {
			if(mybatis.update("ArticleDAO.updateArticle_rcnt_p", vo)>0) {
				flag=true;
			}
		}
		
		return flag;
	}

	// 댓글 삭제 "트랜잭션"
	public boolean delete_comment(CommentVO vo) {
		boolean flag=false;
		
		if(mybatis.delete("ArticleDAO.deleteComment", vo)>0) {
			System.out.println("dao3: 댓글 삭제 완료");
			if(mybatis.update("ArticleDAO.updateArticle_rcnt_m", vo)>0) {
				System.out.println("dao3: 게시글 댓글수 수정 완료");
				flag=true;
			}
		}
		
		return flag;
	}
}