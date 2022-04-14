package org.ezenboard.controller;

import org.ezenboard.domain.BoardVO;
import org.ezenboard.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService service;

	@GetMapping("/list")
	public void list(Model model) {
		log.info("list 로그 ");
		model.addAttribute("list", service.getList());
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register" + board);
		service.register(board);
		log.info("글번호: " + board.getBno());
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/get")
	public void get(@RequestParam ("bno") Long bno, Model model) {
		log.info("get 글 번호 : " + bno);
		service.get(bno);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
	if(service.remove(bno)) {
		rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}

}
