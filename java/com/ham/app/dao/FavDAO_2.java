package com.ham.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ham.app.vo.FavVO;

@Repository("favDAO_2")
public class FavDAO_2 {

	@Autowired // xml파일에 초기화 되어있음
	private JdbcTemplate jdbcTemplate;
	
	private final String FAV_INSERT="insert into fav (fid, aid, uid) values(?,?,?)";
	private final String FAV_SELECTALL_UID="select * from fav where uid=?"; //사용자가 좋아요한 글을 조회
	private final String FAV_SELECTONE="select * from fav where uid=? and aid=?";//해당 id가 이 글을 좋아요하는지 조회
	private final String FAV_SELECTALL_AID="select * from fav where aid=?"; //해당 글의 좋아요들을 조회
	private final String FAV_DELETE="delete from fav where aid=? and uid=?"; //누가 어떤 게시글에 한 좋아요를 삭제할 것인지
	private final String ARTICLE_UPDATE_FAV_P="update article set fav=fav+1 where aid=?";//좋아요에 대한 게시글 테이블 수정
	private final String ARTICLE_UPDATE_FAV_M="update article set fav=fav-1 where aid=?";//좋아요에 대한 게시글 테이블 수정

	//좋아요 "트랜잭션처리"
	public boolean insert_fav(FavVO vo) {
		boolean flag = false;
		vo.setFid(vo.getUid()+"_"+vo.getAid());
		Object[] args= {vo.getFid(), vo.getAid(), vo.getUid()};
		
		
		try {
			if(jdbcTemplate.update(FAV_INSERT, args)>0) { // 좋아요 성공했다면
				System.out.println("dao2: 좋아요 성공");
				if(jdbcTemplate.update(ARTICLE_UPDATE_FAV_P, vo.getAid())>0) {// 게시글 좋아요개수 수정
					System.out.println("dao2: 해당 게시글 좋아요 개수 추가 성공");
					flag=true;
				}
			}	
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("dao2: 좋아요 실패");
		}
		
		return flag;
	}

	// 좋아요 목록
	public List<FavVO> getList_fav (FavVO vo){

		List<FavVO> datas = null;
		if(vo.getAid()!=0) {
			System.out.println("dao2: 게시글을 좋아요 하는 사람들 목록 조회");
			Object [] args= {vo.getAid()};
			datas=jdbcTemplate.query(FAV_SELECTALL_AID, args, new FavRowMapper());
		}else {
			System.out.println("dao2: 해당 id가 좋아하는 글 목록 조회");
			Object [] args= {vo.getUid()};
			datas=jdbcTemplate.query(FAV_SELECTALL_UID, args, new FavRowMapper());
		}
		return datas;
	}

	//좋아요 취소 "트랜잭션처리"
	public boolean delete_fav(FavVO vo) {
		boolean flag = false;
		Object [] args= {vo.getAid(), vo.getUid()};
		if(jdbcTemplate.update(FAV_DELETE, args)>0) {
			System.out.println("dao2: 좋아요 취소 성공");
			if(jdbcTemplate.update(ARTICLE_UPDATE_FAV_M, vo.getAid())>0) {
				System.out.println("dao2: 게시글 좋아요 개수 감소 성공");
				flag=true;
			}
		}
		return flag;
	}
	
	// 좋아요의 유무 확인
	public int get_favCheck(FavVO vo) {
		
		int flag=0;//좋아요가 아닌 상태
		Object [] args= {vo.getUid(),vo.getAid()};
		try {
			jdbcTemplate.queryForObject(FAV_SELECTONE, args, new FavRowMapper());
			System.out.println("dao2: 좋아요인 상태");
			flag=1;
		}catch (Exception e) {
			System.out.println("dao2: 좋아요가 아닌 상태");
		}
		return flag;//해당 id의 좋아요 상태를 반환
	}
	
}

// RowMapper
class FavRowMapper implements RowMapper<FavVO>{

	@Override
	public FavVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		FavVO data=new FavVO();
		
		data.setFid(rs.getString("fid"));
		data.setAid(rs.getInt("aid"));
		data.setUid(rs.getString("uid"));
		
		return data;
	}
	
}