package com.ham.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ham.app.common.JDBCUtil;
import com.ham.app.vo.ArticleSet;
import com.ham.app.vo.ArticleVO;
import com.ham.app.vo.CommentVO;

@Repository("articleDAO")
// comment의 DAO도 함께 수행할 클래스
public class ArticleDAO {

	//트랜잭션: executeQuery,executeUpdate()등의 sql문 적용을 하나의 작업처리단위로  수행해야할때 사용
	// -> 하나의 dao비즈니스메서드() 안에서 여러번 sql문을 수행해야할때 사용
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs= null;
	private List<ArticleVO> datas=null;
	private List<CommentVO> cdatas=null;
	
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
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(ARTICLE_INSERT);
			pstmt.setString(1, vo.getUid());
			pstmt.setString(2, vo.getUname());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getAcontent());
			pstmt.setString(5, vo.getPart());
			pstmt.setString(6, vo.getLeadTime());
			pstmt.setString(7, vo.getAddr());
			pstmt.setString(8, vo.getFilename());
			// 등록이 성공하면,
			if(pstmt.executeUpdate()>0) {
				System.out.println("dao: 게시글 등록 성공"+vo.getFilename());
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;
	}

	// 게시글 삭제
	public boolean delete_article(ArticleVO vo) {
		boolean flag = false;
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(ARTICLE_DELETE);
			pstmt.setInt(1, vo.getAid());
			// 삭제 성공하면,
			if(pstmt.executeUpdate()>0) {
				System.out.println("dao:게시글 삭제 성공");
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;
	}

	// 게시글 수정
	public boolean update_article(ArticleVO vo) {
		boolean flag = false;
		conn = JDBCUtil.connect();
		try {

			if(vo.getCnt()!=0) {// 조회수가 누적다면,
				System.out.println("dao: 조회수 누적 요청");
				pstmt = conn.prepareStatement(ARTICLE_UPDATE_CNT);
				pstmt.setInt(1, 1);
				pstmt.setInt(2, vo.getAid());
			}else {// 그게 아니라면(그냥 게시글 수정이라면)
				System.out.println("dao: 게시글 수정 요청");
				pstmt = conn.prepareStatement(ARTICLE_UPDATE);
				//update article set title=?, acontent=?, part=?, leadTime=?, addr=? where aid=?
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getAcontent());
				pstmt.setString(3, vo.getPart());
				pstmt.setString(4, vo.getLeadTime());
				pstmt.setString(5, vo.getAddr());
				pstmt.setString(6, vo.getFilename());
				pstmt.setInt(7, vo.getAid());
			}
			
			// 수정 성공했다면,
			if(pstmt.executeUpdate()>0) {
				System.out.println("dao: 게시글 수정 성공");
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;
	}

	// aid에 해당하는 게시글 목록 보기
	public ArticleVO get_article_fav(ArticleVO vo){
		System.out.println("해당하는 게시글");
		ArticleVO data = new ArticleVO();
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(ARTICLE_SELECTALL_FAV);
			pstmt.setInt(1, vo.getAid());
			rs = pstmt.executeQuery();

			if(rs.next()) {
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
				System.out.println("목록불러오는 중");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		System.out.println("목록 불러오기 완료");
		return data;
	}
	
	// 검색에 해당하는 게시글 목록 보기
		public List<ArticleVO> getList_article_search(ArticleVO vo){
			System.out.println("검색에 해당하는 게시글 목록");

			conn = JDBCUtil.connect();
			try {
				
				System.out.println("dao: "+vo);
				
				if(vo.getTitle()!=null) {
					System.out.println("dao: 제목 검색");
					pstmt = conn.prepareStatement(ARTICLE_SELECTALL_TITLE);
					pstmt.setString(1, vo.getTitle());
				}else if(vo.getUname()!=null) {
					System.out.println("dao: 작성자 검색");
					pstmt = conn.prepareStatement(ARTICLE_SELECTALL_UNAME);
					pstmt.setString(1, vo.getUname());
				}else if(vo.getAcontent()!=null) {
					System.out.println("dao: 내용 검색 검색");
					pstmt = conn.prepareStatement(ARTICLE_SELECTALL_ACONTENT);
					pstmt.setString(1, vo.getAcontent());
				}else if(vo.getPart()!=null) {
					System.out.println("dao: 부위 검색");
					pstmt = conn.prepareStatement(ARTICLE_SELECTALL_PART);
					pstmt.setString(1, vo.getPart());
				}else if(vo.getAddr()!=null) {
					System.out.println("dao: 주소 검색");
					pstmt = conn.prepareStatement(ARTICLE_SELECTALL_ADDR);
					pstmt.setString(1, vo.getAddr());
				}

				rs = pstmt.executeQuery();

				while(rs.next()) {
					ArticleVO data = new ArticleVO();
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
					System.out.println("목록불러오는 중");
					datas.add(data);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			System.out.println("목록 불러오기 완료");
			return datas;
		}
	
	// 게시글 목록만 전체보기
	public List<ArticleVO> getList_article(ArticleVO vo){
		System.out.println("전체보기 메서드 수행");
		conn = JDBCUtil.connect();
		try {
			
			// 각 int형 멤버변수에 1이 들어오면, 해당 기준 정렬하여 데이터 반환 예정
			if(vo.getCnt()!=0) {
				System.out.println("조회수 기준 정렬");
				pstmt = conn.prepareStatement(ARTICLE_FILTER_CNT);
			}else if(vo.getFav()!=0) {
				System.out.println("좋아요 기준 정렬");
				pstmt = conn.prepareStatement(ARTICLE_FILTER_FAV);
			}else if(vo.getRcnt()!=0) {
				System.out.println("댓글수 기준 정렬");
				pstmt = conn.prepareStatement(ARTICLE_FILTER_RCNT);
			}else { // 기본 정렬 조건일때,
				System.out.println("최신순 정렬");
				pstmt = conn.prepareStatement(ARTICLE_SELECTALL);
			}
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				ArticleVO data = new ArticleVO();
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
				data.setFaid(0);//일단 다 좋아요가 아닌 상태로 가져오기
				data.setFilename(rs.getString("filename"));
				System.out.println("목록불러오는 중");
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		System.out.println("목록 불러오기 완료");
		return datas;
	}

	// ================================================================================

	// 게시글 1개 + 해당 게시글의 댓글
	public List<ArticleSet> getList_all(ArticleVO vo){
		// ArticleSet을 여러개 담은 배열리스트
		ArrayList<ArticleSet> datas=new ArrayList<ArticleSet>();
		conn = JDBCUtil.connect();

		try {
			System.out.println("글 상세보기");
			pstmt=conn.prepareStatement(ARTICLE_SELECTONE);
			pstmt.setInt(1, vo.getAid());
			rs=pstmt.executeQuery();
			// 입력된 게시글이 존재한다면,
			if(rs.next()) {
				// 해당 객체를 사용하기 위해 필요한 매개변수 articleVO와 ArrayList<commentVO>
				ArticleSet as=new ArticleSet();
				//ArticleVO사용을 위한 초기화 + 값 설정
				ArticleVO articleVO=new ArticleVO();
				articleVO.setAid(rs.getInt("aid"));
				articleVO.setUid(rs.getString("uid"));
				articleVO.setUname(rs.getString("uname"));
				articleVO.setTitle(rs.getString("title"));
				articleVO.setAcontent(rs.getString("acontent"));
				articleVO.setPart(rs.getString("part"));
				articleVO.setLeadTime(rs.getString("leadTime"));
				articleVO.setAddr(rs.getString("addr"));
				articleVO.setCnt(rs.getInt("cnt"));
				articleVO.setFav(rs.getInt("fav"));
				articleVO.setRcnt(rs.getInt("rcnt"));
				articleVO.setAdate(rs.getString("adate"));
				articleVO.setFilename(rs.getString("filename"));
				// 해당 설정된 값들을 ArticleSet에 설정
				as.setArticleVO(articleVO);

				// 해당 List를 사용하기위해 필요한 CommnetVO객체
				ArrayList<CommentVO> commentDatas1=new ArrayList<CommentVO>();
				// 대댓글 List를 사용하기위해 필요한 CommnetVO객체
				ArrayList<CommentVO> commentDatas2=new ArrayList<CommentVO>();
				pstmt=conn.prepareStatement(COMMENT_SELECTALL);//DB값 조회를 위한 sql문 준비
				pstmt.setInt(1, rs.getInt("aid")); //이 게시글에 해당하는 댓글 목록을 출력
				ResultSet rs2=pstmt.executeQuery();
				// 해당 게시글에 댓글이 존재한다면, 
				while(rs2.next()) {
					//CommentVO사용을 위한 초기화 + 값 설정
					CommentVO commentVO=new CommentVO();
					commentVO.setCid(rs2.getInt("cid"));
					commentVO.setUid(rs2.getString("uid"));
					commentVO.setAid(rs2.getInt("aid"));
					commentVO.setCcontent(rs2.getString("ccontent"));
					commentVO.setCdate(rs2.getString("cdate"));
					commentVO.setCcheck(rs2.getInt("ccheck"));
					commentVO.setCgroup(rs2.getInt("cgroup"));
					// 값들을 ArrayList<CommnetVO>에 넣고,
					commentDatas1.add(commentVO);
					
					pstmt=conn.prepareStatement(COMMENT_SELECTALL_2);//DB값 조회를 위한 sql문 준비
					pstmt.setInt(1, rs.getInt("aid"));
					pstmt.setInt(2, rs2.getInt("cid"));
					
					System.out.println("=============");
					System.out.println("aid: "+rs.getInt("aid"));
					System.out.println("cgroup: "+rs2.getInt("cid"));
					System.out.println("=============");
					
					ResultSet rs3=pstmt.executeQuery();
					//CommentVO사용을 위한 초기화 + 값 설정
					// 해당 게시글에 댓글이 존재한다면, 
					while(rs3.next()) {
						CommentVO commentVO2=new CommentVO();
						commentVO2.setCid(rs3.getInt("cid"));
						commentVO2.setUid(rs3.getString("uid"));
						commentVO2.setAid(rs3.getInt("aid"));
						commentVO2.setCcontent(rs3.getString("ccontent"));
						commentVO2.setCdate(rs3.getString("cdate"));
						commentVO2.setCcheck(rs3.getInt("ccheck"));
						commentVO2.setCgroup(rs3.getInt("cgroup"));
						System.out.println("확인"+commentVO2);
						// 값들을 ArrayList<CommnetVO>에 넣고,
						commentDatas2.add(commentVO2);//누적 필요...!
						System.out.println("확인2"+commentDatas2);

					}
					rs3.close(); // 닫고,
				}
				rs2.close(); // 닫고,
				// 값이 들어간 List를 ArticleSet에 넣고,
				as.setCommentList2(commentDatas2); 
				as.setCommentList1(commentDatas1);
				
				// 각기 설정된 ArticleVO와 ArrayList<CommentVO>를 datas에 담기
				datas.add(as);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		System.out.println("결과"+datas);
		System.out.println("");
		return datas;
	}

	// ================================================================================

	// 댓글 등록 "트랜잭션처리"
	public boolean insert_comment(CommentVO vo) {
		boolean flag=false;
		conn = JDBCUtil.connect();

		try {
			
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(COMMENT_INSERT);
			pstmt.setString(1, vo.getUid());
			pstmt.setInt(2, vo.getAid());
			pstmt.setString(3, vo.getCcontent());
			// 등록이 성공했다면,
			if(pstmt.executeUpdate()>0) {
				System.out.println("dao: 댓글 등록 성공");
				//등록 성공했으면,게시판테이블 댓글수 증가
				pstmt=conn.prepareStatement(ARTICLE_UPDATE_RCNT_P);
				pstmt.setInt(1, vo.getAid());
				//수정 성공했으면,
				if(pstmt.executeUpdate()>0) {
					System.out.println("dao: 게시글 댓글 수 수정 성공");
					flag=true;
					conn.commit();
					conn.setAutoCommit(true);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;
	}
	// 대댓글 등록 "트랜잭션 처리"
	public boolean insert_comment_2(CommentVO vo) {
		boolean flag=false;
		conn = JDBCUtil.connect();

		try {
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(COMMENT_INSERT_2);
			pstmt.setString(1, vo.getUid());
			pstmt.setInt(2, vo.getAid());
			pstmt.setString(3, vo.getCcontent());
			pstmt.setInt(4,vo.getCcheck());
			pstmt.setInt(5, vo.getCgroup());
			// 등록이 성공했다면,
			if(pstmt.executeUpdate()>0) {
				System.out.println("dao: 대댓글 등록 성공");
				//등록 성공했으면,게시판테이블 댓글수 증가
				pstmt=conn.prepareStatement(ARTICLE_UPDATE_RCNT_P);
				pstmt.setInt(1, vo.getAid());
				//수정 성공했으면,
				if(pstmt.executeUpdate()>0) {
					System.out.println("dao: 게시글 댓글 수 수정 성공");
					flag=true;
					conn.commit();
					conn.setAutoCommit(true);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;
	}

	// 대댓글 조회
	public List<CommentVO> getList_comment(CommentVO vo){
		conn = JDBCUtil.connect();

		try {
			pstmt=conn.prepareStatement(COMMENT_SELECTALL_2);
			pstmt.setInt(1, vo.getAid());
			pstmt.setInt(2, vo.getCgroup());
			rs=pstmt.executeQuery();
			// 해당 대댓글이 존재한다면,
			while(rs.next()) {
				System.out.println("댓글의 대댓글 조회");
				CommentVO data=new CommentVO();
				data.setCid(rs.getInt("cid"));
				data.setAid(rs.getInt("aid"));
				data.setCcontent(rs.getString("ccontent"));
				data.setCdate(rs.getString("cdate"));
				data.setUid(rs.getString("uid"));
				data.setCcheck(rs.getInt("ccheck"));
				data.setCgroup(rs.getInt("cgroup"));
				cdatas.add(data);
				System.out.println("dao 대댓글 조회: "+cdatas);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}

		return cdatas;
	}

	// 댓글 삭제
	public boolean delete_comment(CommentVO vo) {
		boolean flag=false;
		conn = JDBCUtil.connect();

		try {
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(COMMENT_DELETE);
			pstmt.setInt(1, vo.getCid());
			// 삭제 성공했다면,
			if(pstmt.executeUpdate()>0) {
				System.out.println("dao: 댓글 삭제 성공");
				pstmt=conn.prepareStatement(ARTICLE_UPDATE_RCNT_M);
				pstmt.setInt(1, vo.getAid());
				//수정 성공했으면,
				if(pstmt.executeUpdate()>0) {
					System.out.println("dao: 게시글 댓글 수 수정 성공");
					flag=true;
					conn.commit();
					conn.setAutoCommit(true);
				}
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;
	}

}
