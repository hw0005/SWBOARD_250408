package com.app.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.entity.Board;
import com.app.service.BoardService;
import com.app.service.UserService;

@Controller("boardController")
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	private final UserService userService;
	
	
	private List<Board> articleList;
	
	Logger logger = LoggerFactory.getLogger("com.app.controller.BoardController");	

	public BoardController(BoardService boardService, UserService userService) {
		this.boardService = boardService;
		this.userService = userService;
	}
	
	
	// 최초 접속 시 리스트로 가져온 후 뿌리기 SELECT 및 화면에 뿌리기
	@GetMapping({"/list", "/"})
	public String getArticleList(Model model) {
		articleList = boardService.listArticles();
		model.addAttribute("dataList", articleList);
		return "list";
	}
	
	// 글쓰기 누르면 그 화면 불러오기
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/add")
	public String writeArticle() {
		return "write";
	}
	
	//INSERT POST로 주기
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/addarticle")
	public String addArticle(
			@RequestParam (value="i_title") String title,
			@RequestParam (value="i_content") String content,
			Principal principal){
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(userService.getUser(principal.getName()));
		
		boardService.addArticle(board);
		
		return "redirect:list";
	}
	
	
	// articleNO로 글번호 가져온 후 글상세 들어가기(수정,삭제,저장이 있는)
	@GetMapping("/view")
	public ModelAndView viewArticle(@RequestParam(value="no") String articleNo) {
		logger.info("viewArticle => articleNo: " + articleNo);
		Board board = boardService.viewArticle(Integer.parseInt(articleNo));
		ModelAndView mv = new ModelAndView();
		mv.setViewName("view");
		mv.addObject("article", board);
		return mv;		
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/edit")
	public String editArticle(@RequestParam String articleNo,
			@RequestParam String title,
			@RequestParam String content, RedirectAttributes redirectAttr) { 
		Board board = new Board();
		board.setId(Integer.parseInt(articleNo));
		board.setTitle(title);
		board.setContent(content);
		boardService.editArticle(board);
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
