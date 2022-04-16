package com.ham.app.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ham.app.service.ArticleService;
import com.ham.app.service.FavService;
import com.ham.app.service.UsersService;
import com.ham.app.vo.ArticleSet;
import com.ham.app.vo.ArticleVO;
import com.ham.app.vo.CommentVO;
import com.ham.app.vo.FavVO;
import com.ham.app.vo.TestVO;
import com.ham.app.vo.UsersVO;

@Controller
public class AllController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private FavService favService;
	@Autowired
	private UsersService usersService;

	@ResponseBody
	@RequestMapping(value = "/idCheck.do")
	public String idCheck(UsersVO vo) {

		System.out.println(vo);

		int result;

		vo=usersService.get_users(vo);

		if(vo!=null) {
			System.out.println("사용 불가능 아이디");
			result=0;
		}else {
			System.out.println("사용가능 아이디");
			result=1;
		}
		return String.valueOf(result);
	}

	@RequestMapping(value = "/main.do")
	public String main(ArticleVO vo , Model model, FavVO fvo, HttpSession session) {

		List<ArticleVO> datas=articleService.getList_article(vo); // if 요청하는 정렬 방식이 있다면 값을 1로 전달

		String uid=(String) session.getAttribute("uid");

		if(uid != null) {//로그인 상태라면,
			fvo.setUid(uid);
			List<FavVO> fdatas=favService.getList_fav(fvo);
			// 로그인 된 유저의 좋아요된 게시글이면, faid를 1로 변경
			for(int i=0; i<datas.size(); i++) {
				for(int j=0; j<fdatas.size(); j++) {
					if(datas.get(i).getAid()==fdatas.get(j).getAid()) {
						datas.get(i).setFaid(1);
					}
				}
			}
		}
		boolean check=false; // 좋아요 목록 보기 요청인지 체크할 flag
		session.setAttribute("favcheck", check);
		model.addAttribute("adatas", datas);// 게시글 목록을 담은 datas를 adatas라는 이름으로 전송
		return "main.jsp";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login_get() {
		System.out.println("다국어 처리 적용하여 로그인 페이지로 이동");
		return "login.jsp";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login_users (UsersVO vo,Model model, HttpSession session) {

		int flag=usersService.login_users(vo);

		if(flag==1) { //로그인 성공시 정보 session에 저장
			vo=usersService.get_users(vo);			
			session.setAttribute("uid", vo.getUid());
			session.setAttribute("uname", vo.getUname());
			session.setAttribute("ubirth", vo.getUbirth());
			session.setAttribute("uauth", vo.getUauth());
			model.addAttribute("url", "/main.do");
		}else {
			model.addAttribute("msg", "아이디나 비밀번호를 다시 확인해주세요.");
			model.addAttribute("url", "/login.do");
		}
		return "redirect.jsp";
	}

	@RequestMapping(value = "/logout.do")
	public String logout_users (HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "/main.do");
		return "redirect.jsp";
	}

	@RequestMapping(value = "/signup.do")
	public String insert_users(UsersVO vo, Model model) {

		System.out.println(vo);
		int flag=usersService.insert_users(vo);

		if(flag==1) { // 가입 성공시
			model.addAttribute("msg", "회원가입을 축하합니다!");
			model.addAttribute("url", "/login.do");
		}else {
			model.addAttribute("msg", "가입 실패");
			model.addAttribute("url", "/singup.jsp");
		}
		return "redirect.jsp";
	}

	@RequestMapping(value = "/favList.do")
	public String getList_fav(FavVO fvo, Model model, ArticleVO avo, ArrayList<ArticleVO> adatas, HttpSession session) {

		List<FavVO> fdatas=favService.getList_fav(fvo); // 해당 id가 좋아하는 글

		if(fdatas==null) { //좋아하는 글이 없다면,
			model.addAttribute("msg", "회원님이 좋아하는 글이 존재하지 않습니다.");
			model.addAttribute("url", "/main.do");
			return "redirect.jsp";
		}
		//좋아하는 글이 있다면,
		//해당 게시글의 faid를 1로 설정해주기
		for(int i=0; i<fdatas.size(); i++) {
			avo.setAid(fdatas.get(i).getAid()); //해당 id가 좋아하는 게시글 pk를 하나씩 넘겨서
			adatas.add(articleService.get_article_fav(avo)); // 그 게시글 pk로 검색된 게시글 가져오기
		}
		for(int i=0; i<adatas.size(); i++) {
			adatas.get(i).setFaid(1);
		}

		boolean flag=true; // 좋아요 목록 보기 요청이면 true
		session.setAttribute("favcheck", flag);
		//세팅된 aid의 검색 결과를 반환
		model.addAttribute("adatas", adatas);
		return "main.jsp";
	}

	@RequestMapping(value = "/mypage.do")
	public String get_user (UsersVO vo, Model model) {

		vo=usersService.get_users(vo);
		System.out.println("회원정보"+vo);

		model.addAttribute("udata", vo); //해당 유저 정보 반환
		return "mypage.jsp";

	}

	@RequestMapping(value = "/updateUsers.do")
	public String update_users (UsersVO vo, HttpSession session, Model model) {

		if(usersService.update_users(vo)) { //성공
			model.addAttribute("msg", "회원정보가 수정되었습니다. 다시 로그인해주세요.");
			model.addAttribute("url", "/logout.do");
		}else { //실패
			model.addAttribute("msg", "수정에 실패하였습니다. 관리자에게 문의 바랍니다.");
			model.addAttribute("url", "/error.jsp");
		}
		model.addAttribute("udata", vo);
		return "redirect.jsp";

	}

	@RequestMapping(value = "/deleteUsers.do")
	public String delete_users(UsersVO vo, HttpSession session, Model model) {

		if(usersService.delete_users(vo)) { //성공
			session.invalidate();
			model.addAttribute("msg", "정상적으로 삭제처리 되었습니다.");
			model.addAttribute("url", "/main.do");
		}else { //실패
			model.addAttribute("msg", "삭제에 실패하였습니다. 관리자에게 문의 바랍니다.");
			model.addAttribute("url", "/error.jsp");
		}
		return "redirect.jsp";
	}

	@RequestMapping(value = "/fav.do")
	public String insert_fav (FavVO vo, HttpSession session) throws SQLException {

		System.out.println(vo);

		// 일단 insert를 실행하고,
		boolean flag=favService.insert_fav(vo);

		if(flag==false) { //실패시, pk중복으로 간주
			System.out.println("c:좋아요 실패");

			// delete 실행
			if(favService.delete_fav(vo)) {

				System.out.println("c:좋아요취소 성공");

			}else {
				System.out.println("c:좋아요취소 실패");
			}

		}
		//vo.setAid(0); // 이게 들어가면 "게시글을 좋아요하는 사람들"이 조회되므로!
		//List<FavVO> fdatas=dao.getList_fav(vo);

		//session.setAttribute("fdatas", fdatas);
		return "redirect: main.do";
	}

	@ResponseBody
	@RequestMapping(value = "/fav2.do")
	public String insert_fav_test (FavVO fvo, ArticleVO avo) throws SQLException {

		System.out.println(fvo);
		System.out.println(avo);

		int result=0;

		// 일단 insert를 실행하고,
		boolean flag=favService.insert_fav(fvo);

		if(flag==false) { //실패시, pk중복으로 간주
			System.out.println("c:좋아요 실패");
			// delete 실행
			if(favService.delete_fav(fvo)) {
				result=0;
				System.out.println("c:좋아요취소 성공");
			}else {
				result=1;
				System.out.println("c:좋아요취소 실패");
			}	
		}else {
			result=1;
		}
		return String.valueOf(result);
	}

	@RequestMapping(value = "/insert.do")
	public String insert_article (ArticleVO vo, Model model) throws IllegalStateException, IOException {

		//파일 업로드 로직
		MultipartFile uploadFile=vo.getUploadFile();
		// 파일명을 aid와 일치시키기 위해 가장 최근 insert된 aid 구하기
		List<ArticleVO> datas=articleService.getList_article(vo);
		ArticleVO data= articleService.get_article_fav(datas.get(0));

		// 비어있지 않다면, 로직 수행
		if(!uploadFile.isEmpty()) {// !=null로도 체크 가능하지만, 해당 방법을 더 선호하기도 함
			//파일명
			String originalFile=uploadFile.getOriginalFilename();
			//파일명 중 확장자만 추출 - > lastIndexOf(".") - 뒤에 있는 . 의 index번호
			String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));
			//변경할 파일명 -> 게시글 고유번호.확장자
			String newname = (data.getAid()+1) + originalFileExtension;
			vo.setFilename(newname);
			System.out.println("파일명: "+vo.getFilename()); // 파일명 확인
			uploadFile.transferTo(new File("/Users/hamjeonghui/Desktop/java/workspace_2/tni/src/main/webapp/img_tattoo/"+newname)); // 인자로 입력된 위치에 파일을 생성해주는 메서드이다.
		}

		boolean flag=articleService.insert_article(vo);

		if(flag==true) { //성공
			//작성된 글의 상세페이지로 이동
			model.addAttribute("url", "/detail.do?uid="+vo.getUid()+"&aid="+(data.getAid()+1));
		}else { //실패
			model.addAttribute("msg", "정상 등록에 실패하였습니다. 관리자에게 문의 바랍니다.");
			model.addAttribute("url", "/error.jsp");
		}
		return "redirect.jsp";
	}

	@RequestMapping(value = "/update.do")
	public String get_article (ArticleVO vo, Model model) {

		vo =articleService.get_article_fav(vo);

		System.out.println("반환된 게시글 상제정보 확인" + vo);

		if(vo!=null) {
			model.addAttribute("adata", vo);
			return"update.jsp";			
		}else {
			System.out.println("업데이트 페이지 진입 실패");
			return "redirect: error.jsp";
		}
	}

	@RequestMapping(value = "/updateArticle.do")
	public String update_article (ArticleVO vo, Model model) throws IllegalStateException, IOException {

		//파일 업로드 로직
		MultipartFile uploadFile=vo.getUploadFile();
		String path="/Users/hamjeonghui/Desktop/java/workspace_2/tni/src/main/webapp/img_tattoo/";
		//변경할 파일명 -> 게시글 고유번호.확장자

		// 새로운 파일이 등록되었는지 확인
		if(uploadFile.getOriginalFilename() != null && uploadFile.getOriginalFilename() != "") {
			// 기존 파일을 삭제
			String originalFile=uploadFile.getOriginalFilename(); //파일명
			String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));//파일명 중 확장자만 추출
			new File(path + vo.getAid()+originalFileExtension).delete(); //삭제진행
			String newname = vo.getAid() + originalFileExtension;
			vo.setFilename(newname);
			System.out.println("파일명: "+vo.getFilename()); // 파일명 확인
			uploadFile.transferTo(new File(path+newname)); // 인자로 입력된 위치에 파일을 생성해주는 메서드이다.
		}else {//새 파일이 등록되지 않았다면, 기존의 filename을 저장
			ArticleVO data=articleService.get_article_fav(vo);
			vo.setFilename(data.getFilename());
		}

		boolean flag=articleService.update_article(vo);

		System.out.println("반환된 게시글 상제정보 확인" + vo);

		if(flag==false) { //실패
			model.addAttribute("msg", "게시글 수정이 정상적으로 이루어지지 않았습니다. 관리자에게 문의 바랍니다.");
			model.addAttribute("url", "/error.jsp");
		}else { //성공
			//수정 완료된 게시글 상세 페이지로 이동
			model.addAttribute("url", "/detail.do?uid="+vo.getUid()+"&aid="+vo.getAid());
		}

		return "redirect.jsp";
	}

	@RequestMapping(value = "/deleteArticle.do")
	public String delete_article (ArticleVO vo, Model model) {

		boolean flag=articleService.delete_article(vo);

		System.out.println("반환된 게시글 상제정보 확인" + vo);

		if(flag==false) { //실패
			model.addAttribute("msg", "게시글 삭제가 정상적으로 이루어지지 않았습니다. 관리자에게 문의 바랍니다.");
			model.addAttribute("url", "/error.jsp");
		}else { //성공
			model.addAttribute("msg", "게시글 삭제 처리가 완료되었습니다.");
			model.addAttribute("url", "/main.do");
		}
		return "redirect.jsp";
	}

	@RequestMapping(value = "/detail.do")
	public String detail_article (ArticleVO vo, Model model, FavVO fvo, HttpSession session) {

		String uid=(String)session.getAttribute("uid");

		System.out.println(uid);

		// 혹여 로그인 된 아이디와, 해당 게시글의 작성 id가 일치하지 않을때만 조회수 누적
		if(vo.getUid().equals(uid)) {
			System.out.println("작성 본인 아이디! 조회수 누적 불가능");
		}else {
			articleService.update_article(vo); // 조회수 누적
		}
		List<ArticleSet> datas =articleService.getList_all(vo);

		fvo.setUid(uid);
		int flag=favService.get_favCheck(fvo); //1이나 0 반환 : 좋아요 유무

		// 가져온 목록에 faid만 바꿔서 누적시킴
		vo=datas.get(0).getArticleVO();
		vo.setFaid(flag);
		datas.get(0).setArticleVO(vo);
		//System.out.println("반환된 게시글 상제정보 확인" + datas.get(0));

		model.addAttribute("data", datas.get(0));

		return "detail.jsp";
	}

	@RequestMapping(value = "/insertComment.do")
	public String insert_comment(CommentVO vo, Model model) {

		System.out.println(vo);
		boolean flag=articleService.insert_comment(vo);

		if(flag==true) { // 등록성공
			model.addAttribute("url", "/detail.do?uid="+vo.getUid()+"&aid="+vo.getAid());
		}else {
			model.addAttribute("msg", "댓글 작성이 정상적으로 이루어지지 않았습니다. 관리자에게 문의 바랍니다.");
			model.addAttribute("url", "/error.jsp");
		}
		return "redirect.jsp";
	}

	@RequestMapping(value = "/insertComment2.do")
	public String insert_comment2(CommentVO vo, Model model) {

		vo.setCcheck(1);

		System.out.println(vo);
		boolean flag=articleService.insert_comment_2(vo);

		if(flag==true) { // 등록성공
			model.addAttribute("url", "/detail.do?uid="+vo.getUid()+"&aid="+vo.getAid());
		}else {
			model.addAttribute("msg", "댓글 작성이 정상적으로 이루어지지 않았습니다. 관리자에게 문의 바랍니다.");
			model.addAttribute("url", "/error.jsp");
		}
		return "redirect.jsp";
	}

	@RequestMapping(value = "/deleteComment.do")
	public String delete_comment(CommentVO vo, Model model) {

		// 부모댓글이 삭제되었다면, 해당 댓글의 대댓글 삭제하기
		if(vo.getCgroup() != 0) {
			List<CommentVO> datas = articleService.getList_comment(vo); // 삭제된 댓글의 대댓글 가져오기
			for(int i=0; i<datas.size(); i++) {
				articleService.delete_comment(datas.get(i));
			}
		}

		System.out.println(vo);
		boolean flag=articleService.delete_comment(vo);

		if(flag==true) { // 등록성공
			model.addAttribute("url", "/detail.do?uid="+vo.getUid()+"&aid="+vo.getAid());
		}else {
			model.addAttribute("msg", "댓글 삭제가 정상적으로 이루어지지 않았습니다. 관리자에게 문의 바랍니다.");
			model.addAttribute("url", "/error.jsp");
		}
		return "redirect.jsp";
	}

	@RequestMapping(value = "/search.do")
	public String getList_article_search(ArticleVO vo, Model model) {

		System.out.println(vo);

		// 검색 로직 임시처리
		if(vo.getCondition().equals("title")) {
			vo.setTitle(vo.getSearch());
		}else if(vo.getCondition().equals("uname")) {
			vo.setUname(vo.getSearch());
		}else if(vo.getCondition().equals("acontent")) {
			vo.setAcontent(vo.getSearch());
		}else if(vo.getCondition().equals("part")) {
			vo.setPart(vo.getSearch());
		}else if(vo.getCondition().equals("addr")) {
			vo.setAddr(vo.getSearch());
		}else {
			System.out.println("????");
		}

		System.out.println(vo);

		List<ArticleVO> adatas= articleService.getList_article_search(vo);
		System.out.println("search.do_c : "+adatas);

		model.addAttribute("adatas", adatas);

		return "main.jsp";
	}

}
