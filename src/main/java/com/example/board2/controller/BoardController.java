package com.example.board2.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.board2.dto.Board;
import com.example.board2.service.BoardService;
import com.example.board2.util.MyUtil;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	
	@Autowired	// 의존성주입 (따로 객체를 생성하지않고 스프링에서 bin객체로 생성됨)
	private BoardService boardService;
	
	@Autowired	//boardcontroller와 myutil간에 의존성이 생김
	MyUtil myUtil;
	
	
	@RequestMapping(value="/") // localhost로 접속
	public String index(){
		return "/index";
	}
	
	// get방식으로 request가 들어올때
	// 페이지 보여줌
	@RequestMapping(value="/created", method=RequestMethod.GET) // localhost로 접속
	public String created(){
		return "bbs/created";	//foward로 이동됨: 데이터를 가지고 페이지를 이동시킴
	}
	
	// 게시글 등록
	@RequestMapping(value="/created", method=RequestMethod.POST) // localhost로 접속
	public String createdOK(Board board, HttpServletRequest request, Model model){
		
		try {
			int maxNum = boardService.maxNum();
			
			board.setNum(maxNum + 1);	//num 컬럼에 들어가 값을 1만큼 증가시켜준다.
			board.setIpAddr(request.getRemoteAddr());	//클라이언트의 ip주소를 구해준다.
			
			boardService.insertData(board);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/list";	//redirect:데이터는 가져갈 수 없이 페이지를 이동시킴
	}
	
	// 리스트 페이지 보여줌 (Get, Post 전부 여기로 들어온다.)
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST}) // localhost로 접속
	public String list(Board board, HttpServletRequest request, Model model){
		
		try {
			String pageNum = request.getParameter("pageNum");	// 바뀌는 페이지번호
			int currentPage = 1;	// 현재페이지 번호(디폴트값은 1)
			
			if(pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			}
			
			String searchKey = request.getParameter("searchKey");	// 검색키워드(subject, content, name)
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue == null) {
				searchKey = "subject";	// 검색키워드의 디폴트는 subject
				searchValue = "";	// 검색어의 디폴트는 빈문자열
			} else {
				if(request.getMethod().equalsIgnoreCase("GET")); {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");	
				}
				// get방식으로 request가 왔다면 
			}
		
		
			//1. 전체 게시물의 갯수를 가져온다.(페이징처리에 필요)
			int dataCount = boardService.getDataCount(searchKey, searchValue);
			
			//2. 페이징 처리를 한다.(준비단계)
			int numPerPage = 5;		// 페이지 당 보여줄 데이터리스트의 갯수
			int totalPage = myUtil.getPageCount(numPerPage, dataCount); 	// 페이지의 전체갯수를 구한다.
			
			if(currentPage > totalPage) currentPage = totalPage;
			
			int start = (currentPage -1) * numPerPage + 1;	// 1 6 11 16...
			int end = currentPage * numPerPage;	// 5 10 15 20...
		
			//3. 전체 게시물 리스트를 가져온다.
			List<Board> lists = boardService.getLists(searchKey, searchValue, start, end);
			
			
			//4. 페이징 처리를 한다.
			String param ="";
			
			// 검색어가 있다면
			if(searchValue != null && searchValue.equals("")) {
				param = "searchValue" + searchKey;
				param += "&searchValue" + URLEncoder.encode(searchValue, "UTF-8");	// 컴퓨터의 언어로 인코딩
			}
			
			String listUrl = "/list";
			
			// list?searchKey=name&searchValue=춘식
			if(!param.equals("")) listUrl += "?" + param;
			
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
			
		} catch (Exception e) {
			
		}
	
		
		return "bbs/list";
	}
	
	// 수정페이지 보여주기
	@RequestMapping(value="/updated", method=RequestMethod.GET) // localhost로 접속
	public String updated(HttpServletRequest request, Model model){
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue != null) {
			
		}
		
		return "bbs/updated";
	}
	
	// 게시글 수정하는 기능
	@RequestMapping(value="/updated_ok", method=RequestMethod.POST) // localhost로 접속
	public String updated_ok(){
		return "bbs/updated";
	}
	
	@RequestMapping(value="/article", method=RequestMethod.GET) // localhost로 접속
	public String article(){
		return "bbs/article";
	}
 }
