package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.model.NoticeBoard;
import com.app.service.BoardService;

@Controller("boardController")
@RequestMapping("/notice")
public class BoardController {
	private final BoardService boardService;
	private NoticeBoard noticeBoard;
	
	private List<NoticeBoard> articleList;
	
	Logger logger = LoggerFactory.getLogger("com.app.controller.BoardController");	

	public BoardController(BoardService boardService, NoticeBoard noticeBoard) {
		this.boardService = boardService;
		this.noticeBoard = noticeBoard;
	}
	
	
	// 최초 접속 시 리스트로 가져온 후 뿌리기 SELECT 및 화면에 뿌리기
	@GetMapping({"/list", "/"})
	public String getArticleList(Model model) {
		articleList = boardService.listArticles();
		model.addAttribute("dataList", articleList);
		return "list";
	}
	
	// 글쓰기 누르면 그 화면 불러오기
	@GetMapping("/add")
	public String writeArticle() {
		return "write";
	}
	
	//INSERT POST로 주기
	@PostMapping("/addarticle")
	public String addArticle(
			@RequestParam String title,
			@RequestParam String content){
		noticeBoard.setTitle(title);
		noticeBoard.setContent(content);
		noticeBoard.setWriteId("bit");
		
		boardService.addArticle(noticeBoard);
		
		return "redirect:list";
	}
	
	
	// articleNO로 글번호 가져온 후 글상세 들어가기(수정,삭제,저장이 있는)
	@GetMapping("/view")
	public ModelAndView viewArticle(@RequestParam(value="no") String articleNo) {
		logger.info("viewArticle => articleNo: " + articleNo);
		noticeBoard = boardService.viewArticle(Integer.parseInt(articleNo));
		ModelAndView mv = new ModelAndView();
		mv.setViewName("view");
		mv.addObject("article", noticeBoard);
		return mv;		
	}
	
	@PostMapping("/edit")
	public String editArticle(@RequestParam String articleNo,
			@RequestParam String title,
			@RequestParam String content, RedirectAttributes redirectAttr) { 
		noticeBoard.setArticleNo(Integer.parseInt(articleNo));
		noticeBoard.setTitle(title);
		noticeBoard.setContent(content);
		boardService.editArticle(noticeBoard);
		logger.info("editArticle => title: " + title);
		logger.info("editArticle => content: " + content);
		redirectAttr.addAttribute("no", articleNo);
		
		return "redirect:view";
	}
	
	@PostMapping("/remove")
	public String removeArticle(@RequestParam String articleNo) {
		boardService.removeArticle(Integer.parseInt(articleNo));
		return "redirect:list";
	}
}
