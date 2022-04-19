package com.ham.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ham.app.vo.ArticleSet;
import com.ham.app.vo.ArticleVO;
import com.ham.app.vo.CommentVO;

@Repository("articleDAO_2")
public class ArticleDAO_2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String ARTICLE_INSERT="insert into article (uid, uname, title, acontent, part, leadTime, addr, filename) values (?,?,?,?,?,?,?,?)";//댓글 등록
	private final String COMMENT_INSERT="insert into comment (uid, aid, ccontent) values (?,?,?)";//댓글 등록
	private final String COMMENT_INSERT_2="insert into comment (uid, aid, ccontent, ccheck, cgroup) values (?,?,?,?,?)";//대댓글 등록
	private final String ARTICLE_DELETE="delete from article where aid=?"; //게시글 삭제
	private final String COMMENT_DELETE="delete from comment where cid=?"; //댓글 삭제
	private final String ARTICLE_UPDATE="update article set title=?, acontent=?, part=?, leadTime=?, addr=?, filename=? where aid=?"; //게시글수정
	private final String ARTICLE_UPDATE_CNT="update article set cnt=cnt+? where aid=?";//조회수
	private final String ARTICLE_UPDATE_RCNT_P="update article set rcnt=rcnt+1 where aid=?";//댓글수 증가
	private final String ARTICLE_UPDATE_RCNT_M="update article set rcnt=rcnt-1 where aid=?";//댓글수 감소
	private final String ARTICLE_SELECTALL="select * from article order by aid desc";//게시글 전체목록
	private final String ARTICLE_FILTER_CNT="select * from article order by cnt desc";//조회수정렬
	private final String ARTICLE_FILTER_FAV="select * from article order by fav desc";//좋아요 기준정렬
	private final String ARTICLE_FILTER_RCNT="select * from article order by rcnt desc";//댓글수 기준정렬
	private final String ARTICLE_SELECTALL_FAV="select * from article where aid=? order by aid desc";//aid에 해당하는 게시글 목록
	private final String ARTICLE_SELECTALL_TITLE="select * from article where title like '%' ? '%' order by aid desc";//제목검색
	private final String ARTICLE_SELECTALL_UNAME="select * from article where uname like '%' ? '%' order by aid desc";//작성자(이름)검색
	private final String ARTICLE_SELECTALL_ACONTENT="select * from article where acontent like '%' ? '%' order by aid desc";//내용 검색
	private final String ARTICLE_SELECTALL_PART="select * from article where part like '%' ? '%' order by aid desc";//작업 부위 검색
	private final String ARTICLE_SELECTALL_ADDR="select * from article where addr like '%' ? '%' order by aid desc";//주소(위치)검색
	private final String ARTICLE_SELECTONE="select * from article where aid=?"; //게시글 상세보기
	private final String COMMENT_SELECTALL="select * from comment where aid=? and ccheck=0 order by cid desc";//게시글에 일반 댓글목록
	private final String COMMENT_SELECTALL_2="select * from comment where aid=? and cgroup=?";//해당게시글,cid에 해당하는 대댓글 목록

	// 게시글 등록
	public boolean insert_article(ArticleVO vo) {
		boolean flag = false;
		Object[] args= {vo.getUid(), vo.getUname(), vo.getTitle(), vo.getAcontent(), vo.getPart(), vo.getLeadTime(), vo.getAddr(), vo.getFilename()};
		if(jdbcTemplate.update(ARTICLE_INSERT, args)>0) {
			flag=true;
			System.out.println("dao2: 게시글 등록 성공");
		}
		return flag;
	}

	// 게시글 삭제
	public boolean delete_article(ArticleVO vo) {
		boolean flag = false;
		if(jdbcTemplate.update(ARTICLE_DELETE, vo.getAid())>0) {
			flag=true;
			System.out.println("dao2: 게시글 삭제 성공");
		}
		return flag;
	}

	// 게시글 수정
	public boolean update_article(ArticleVO vo) {
		boolean flag = false;

		if(vo.getCnt() != 0) { //원하는 요청이 조회수 누적이라면,
			System.out.println("dao2: 조회수 누적 요청");
			Object[] args= {1 ,vo.getAid()};
			if(jdbcTemplate.update(ARTICLE_UPDATE_CNT, args)>0) {
				System.out.println("dao2: 조회수 누적 성공");
				flag=true;
			}
		}else { // 그게 아니라면 그냥 게시글 수정 요청으로 간주
			System.out.println("dao2: 게시글 수정 요청");
			Object[] args= {vo.getTitle(), vo.getAcontent(), vo.getPart(), vo.getLeadTime(), vo.getAddr(),vo.getFilename() ,vo.getAid()};
			if(jdbcTemplate.update(ARTICLE_UPDATE, args)>0) {
				System.out.println("dao2: 게시글 수정 성공");
				flag=true;
			}
		}
		return flag;
	}

	// aid에 해당하는 게시글 목록 보기
	public ArticleVO get_article_fav(ArticleVO vo){
		ArticleVO data = null;
		Object[] args= {vo.getAid()};
		try {
			System.out.println("dao2: 해당하는 게시글 출력");
			data=jdbcTemplate.queryForObject(ARTICLE_SELECTALL_FAV, args, new ArticleRowMapper());
		} catch (Exception e) {
			System.out.println("dao2: 해당하는 게시글 없음");
		}
		return data;
	}

	// 검색에 해당하는 게시글 목록 보기
	public List<ArticleVO> getList_article_search(ArticleVO vo){
		List<ArticleVO> datas = null;
		if(vo.getTitle()!=null) {
			System.out.println("dao2: 제목 검색");
			Object[] args= {vo.getTitle()};
			datas=jdbcTemplate.query(ARTICLE_SELECTALL_TITLE, args, new ArticleRowMapper());
		}else if(vo.getUname()!=null) {
			System.out.println("dao2: 작성자 검색");
			Object[] args= {vo.getUname()};
			datas=jdbcTemplate.query(ARTICLE_SELECTALL_UNAME, args, new ArticleRowMapper());
		}else if(vo.getAcontent()!=null) {
			System.out.println("dao2: 내용 검색 검색");
			Object[] args= {vo.getAcontent()};
			datas=jdbcTemplate.query(ARTICLE_SELECTALL_ACONTENT, args, new ArticleRowMapper());
		}else if(vo.getPart()!=null) {
			System.out.println("dao2: 부위 검색");
			Object[] args= {vo.getPart()};
			datas=jdbcTemplate.query(ARTICLE_SELECTALL_PART, args, new ArticleRowMapper());
		}else if(vo.getAddr()!=null) {
			System.out.println("dao2: 주소 검색");
			Object[] args= {vo.getAddr()};
			datas=jdbcTemplate.query(ARTICLE_SELECTALL_ADDR, args, new ArticleRowMapper());
		}
		return datas;
	}

	// 게시글 목록만 전체보기
	public List<ArticleVO> getList_article(ArticleVO vo){
		List<ArticleVO> datas = null;
		
		// 각 int형 멤버변수에 1이 들어오면, 해당 기준 정렬하여 데이터 반환 예정
		if(vo.getCnt()!=0) {
			System.out.println("조회수 기준 정렬");
			datas=jdbcTemplate.query(ARTICLE_FILTER_CNT, new ArticleRowMapper());
		}else if(vo.getFav()!=0) {
			System.out.println("좋아요 기준 정렬");
			datas=jdbcTemplate.query(ARTICLE_FILTER_FAV, new ArticleRowMapper());
		}else if(vo.getRcnt()!=0) {
			System.out.println("댓글수 기준 정렬");
			datas=jdbcTemplate.query(ARTICLE_FILTER_RCNT, new ArticleRowMapper());
		}else if(vo.getAid() != 0){ // 기본 정렬 조건일때,
			System.out.println("최신순 정렬");
			datas=jdbcTemplate.query(ARTICLE_SELECTALL, new ArticleRowMapper());
		}
		return datas;
	}

	// ================================================================================

	// 게시글 1개 + 해당 게시글의 댓글
	public List<ArticleSet> getList_all(ArticleVO avo){
		// ArticleSet을 여러개 담은 배열리스트
		List<ArticleSet> datas=new ArrayList<ArticleSet>();
		// 해당 객체를 사용하기 위해 필요한 매개변수 articleVO와 ArrayList<commentVO>
		ArticleSet as=new ArticleSet();
		// 댓글 List를 사용하기위해 필요한 CommnetVO객체
		List<CommentVO> commentDatas1=null;
		// 대댓글 List를 사용하기위해 필요한 CommnetVO객체
		List<CommentVO> commentDatas2=null;
		
		try {
			
			Object[] args= {avo.getAid()};
			ArticleVO data=jdbcTemplate.queryForObject(ARTICLE_SELECTONE, args, new ArticleRowMapper());
			
			System.out.println("dao2_상세보기 요청 게시글: "+data);
			
			as.setArticleVO(data); // 요청한 게시글의 상세정보
			
			commentDatas1=jdbcTemplate.query(COMMENT_SELECTALL, args, new CommentRowMapper());
			
			System.out.println("dao2_해당 게시글의 일반댓글: "+commentDatas1);
			
			as.setCommentList1((ArrayList<CommentVO>)commentDatas1); // 해당 게시글의 일반댓글
			
			for(int i=0; i<commentDatas1.size(); i++) {
				Object[] args2= {avo.getAid(), commentDatas1.get(i).getCid()};
				commentDatas2=jdbcTemplate.query(COMMENT_SELECTALL_2, args2, new CommentRowMapper());
			}
			System.out.println("dao2_해당 게시글의 대댓글: "+commentDatas2);
			as.setCommentList2((ArrayList<CommentVO>)commentDatas2);// 해당 게시글의 대댓글
			datas.add(as);
		}catch (Exception e) {
			System.out.println("dao2: 게시글 상세보기 실패");
		}
		System.out.println("결과: "+datas);
		return datas;
	}

	// ================================================================================

	// 댓글 등록 "트랜잭션처리"
	public boolean insert_comment(CommentVO vo) {
		boolean flag=false;
		Object[] args= {vo.getUid(), vo.getAid(), vo.getCcontent()};
		if(jdbcTemplate.update(COMMENT_INSERT, args)>0) { // 등록이 성공했다면
			System.out.println("dao2: 댓글 등록 성공");
			if(jdbcTemplate.update(ARTICLE_UPDATE_RCNT_P, vo.getAid())>0) {
				flag=true;
				System.out.println("dao2: 게시글 댓글 수 + 성공");
			}
		}
		return flag;
	}
	
	// 대댓글 등록 "트랜잭션 처리"
	public boolean insert_comment_2(CommentVO vo) {
		boolean flag=false;
		Object[] args= {vo.getUid(), vo.getAid(), vo.getCcontent(), vo.getCcheck(), vo.getCgroup()};
		if(jdbcTemplate.update(COMMENT_INSERT_2, args)>0) { // 등록이 성공했다면
			System.out.println("dao2: 대댓글 등록 성공");
			if(jdbcTemplate.update(ARTICLE_UPDATE_RCNT_P, vo.getAid())>0) {
				flag=true;
				System.out.println("dao2: 게시글 댓글 수 + 성공");
			}
		}
		return flag;
	}

	// 대댓글 조회
	public List<CommentVO> getList_comment(CommentVO vo){
		Object[] args= {vo.getAid(), vo.getCgroup()};
		List<CommentVO> datas=jdbcTemplate.query(COMMENT_SELECTALL_2, args, new CommentRowMapper());
		return datas;
	}

	// 댓글 삭제 "트랜잭션"
	public boolean delete_comment(CommentVO vo) {
		boolean flag=false;
		if(jdbcTemplate.update(COMMENT_DELETE, vo.getCid())>0) {
			System.out.println("dao2: 댓글 삭제 성공");
			if(jdbcTemplate.update(ARTICLE_UPDATE_RCNT_M, vo.getAid())>0) {
				System.out.println("dao2: 게시글 댓글 수 - 성공");
				flag=true;
			}
		}
		return flag;
	}

}

// RowMapper
class CommentRowMapper implements RowMapper<CommentVO>{

	@Override
	public CommentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommentVO data=new CommentVO();

		data.setCid(rs.getInt("cid"));
		data.setAid(rs.getInt("aid"));
		data.setCcontent(rs.getString("ccontent"));
		data.setCdate(rs.getString("cdate"));
		data.setUid(rs.getString("uid"));
		data.setCcheck(rs.getInt("ccheck"));
		data.setCgroup(rs.getInt("cgroup"));

		return data;
	}

}

class ArticleRowMapper implements RowMapper<ArticleVO>{

	@Override
	public ArticleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ArticleVO data=new ArticleVO();

		data.setAid(rs.getInt("aid"));
		data.setUid(rs.getString("uid"));
		data.setUname(rs.getString("uname"));
		data.setTitle(rs.getString("title"));
		data.setAcontent(rs.getString("acontent"));
		data.setPart(rs.getString("part"));
		data.setLeadTime(rs.getString("leadTime"));
		data.setAddr(rs.getString("addr"));
		data.setCnt(rs.getInt("cnt"));
		data.setFav(rs.getInt("fav"));
		data.setRcnt(rs.getInt("rcnt"));
		data.setAdate(rs.getString("adate"));
		data.setFilename(rs.getString("filename"));

		return data;
	}

}
