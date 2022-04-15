package com.ham.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ham.app.common.JDBCUtil;
import com.ham.app.vo.FavVO;

@Repository("favDAO")
public class FavDAO {

	//트랜잭션: executeQuery,executeUpdate()등의 sql문 적용을 하나의 작업처리단위로  수행해야할때 사용
	// -> 하나의 dao비즈니스메서드() 안에서 여러번 sql문을 수행해야할때 사용

	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs= null;
	private List<FavVO> datas=null;

	private final String FAV_INSERT="insert into fav (fid, aid, uid) values(?,?,?)";
	private final String FAV_SELECTALL_UID="select * from fav where uid=?"; //사용자가 좋아요한 글을 조회
	private final String FAV_SELECTONE="select * from fav where uid=? and aid=?";//해당 id가 이 글을 좋아요하는지 조회
	private final String FAV_SELECTALL_AID="select * from fav where aid=?"; //해당 글의 좋아요들을 조회
	private final String FAV_DELETE="delete from fav where aid=? and uid=?"; //누가 어떤 게시글에 한 좋아요를 삭제할 것인지
	private final String ARTICLE_UPDATE_FAV_P="update article set fav=fav+1 where aid=?";//좋아요에 대한 게시글 테이블 수정
	private final String ARTICLE_UPDATE_FAV_M="update article set fav=fav-1 where aid=?";//좋아요에 대한 게시글 테이블 수정

	
	//좋아요 "트랜잭션처리"
	public boolean insert_fav(FavVO vo) {

		// 결과에 따라 controller에서 분기처리를 유동적으로 하기 위할 목적
		boolean flag = false;
		conn = JDBCUtil.connect();

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(FAV_INSERT);
			pstmt.setString(1, vo.getUid()+"_"+vo.getAid());
			pstmt.setInt(2, vo.getAid());
			pstmt.setString(3, vo.getUid());

			// 성공했다면 flag=true
			if(pstmt.executeUpdate()>0) {
				System.out.println("dao: 좋아요 성공");
				//등록 성공했으면,게시판테이블 댓글수 증가
				pstmt=conn.prepareStatement(ARTICLE_UPDATE_FAV_P);
				pstmt.setInt(1, vo.getAid());
				//수정 성공했으면,
				if(pstmt.executeUpdate()>0) {
					System.out.println("dao: 게시글 좋아요 개수 수정 성공");
					flag=true;
					conn.commit();
					conn.setAutoCommit(true);
				}
			}
		} catch (SQLException e) {
			System.out.println("fav dao: 이미 좋아요를 누름");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag; //실패했다면 flag
	}

	// 좋아요 목록
	public List<FavVO> getList_fav (FavVO vo){

		conn = JDBCUtil.connect();

		try {
			if(vo.getAid()!=0) { //aid로 조회를 요청했다면,
				System.out.println("fav dao : 게시글을 좋아요하는사람들");
				pstmt = conn.prepareStatement(FAV_SELECTALL_AID);
				pstmt.setInt(1, vo.getAid());
			}else { //그게 아니라면(uid로 조회를 요청했다면),
				System.out.println("fav dao : 해당 Id가 좋아하는 글 목록");
				pstmt = conn.prepareStatement(FAV_SELECTALL_UID);
				pstmt.setString(1, vo.getUid());
			}

			rs = pstmt.executeQuery();

			while(rs.next()) {
				FavVO data = new FavVO();
				data.setFid(rs.getString("fid"));
				data.setAid(rs.getInt("aid"));
				data.setUid(rs.getString("uid"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}

	//좋아요 취소 "트랜잭션처리"
	public boolean delete_fav(FavVO vo) {
		boolean flag = false;
		conn = JDBCUtil.connect();

		try {
			
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(FAV_DELETE);
			pstmt.setInt(1, vo.getAid());
			pstmt.setString(2, vo.getUid());
			
			// 삭제가 성공했다면,
			if(pstmt.executeUpdate()>0) {
				System.out.println("dao: 좋아요 취소 성공");
				pstmt=conn.prepareStatement(ARTICLE_UPDATE_FAV_M);
				pstmt.setInt(1, vo.getAid());
				//수정 성공했으면,
				if(pstmt.executeUpdate()>0) {
					System.out.println("dao: 게시글 좋아요 개수 수정 성공");
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
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return flag;
	}
	
	public int get_favCheck(FavVO vo) {
		
		int flag=0;//좋아요가 아닌 상태
		
		conn = JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(FAV_SELECTONE);
			pstmt.setString(1, vo.getUid());
			pstmt.setInt(2, vo.getAid());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				flag=1; // 좋아요인 상태
				vo.setFid(rs.getString("fid"));
				vo.setUid(rs.getString("uid"));
				vo.setAid(rs.getInt("aid"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;//해당 id의 좋아요 상태를 반환
	}
	
}
