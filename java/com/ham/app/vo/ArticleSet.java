package com.ham.app.vo;

import java.util.ArrayList;
import java.util.List;

//게시글 하나랑 댓글 목록 통째로 들고다닐 자료형가방
public class ArticleSet {

	//멤버변수
	private ArticleVO articleVO;
	private List<CommentVO> commentList1=new ArrayList<CommentVO>();
	private List<CommentVO> commentList2=new ArrayList<CommentVO>();
	
	//gettet & setter
	public ArticleVO getArticleVO() {
		return articleVO;
	}
	public void setArticleVO(ArticleVO articleVO) {
		this.articleVO = articleVO;
	}
	public List<CommentVO> getCommentList1() {
		return commentList1;
	}
	public void setCommentList1(List<CommentVO> commentList1) {
		this.commentList1 = commentList1;
	}
	public List<CommentVO> getCommentList2() {
		return commentList2;
	}
	public void setCommentList2(List<CommentVO> commentList2) {
		this.commentList2 = commentList2;
	}
	
	//toString
	@Override
	public String toString() {
		return "ArticleSet [articleVO=" + articleVO + ", commentList1=" + commentList1 + ", commentList2="
				+ commentList2 + "]";
	}
		
}