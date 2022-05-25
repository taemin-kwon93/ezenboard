package org.ezenboard.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.ezenboard.domain.BoardAttachVO;
import org.ezenboard.domain.BoardVO;
import org.ezenboard.domain.Criteria;
import org.ezenboard.domain.PageDTO;
import org.ezenboard.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public void list(Criteria cri, Model model) {
		
		model.addAttribute("list", service.getListWithPaging(cri));
		
		int total = service.getTotalCount(cri);
		log.info("list with paging, total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		
	}
	
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register() {
		
	}
	
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register_ log: " + board);
		
		if (board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach));
		}
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, Model model,
				@ModelAttribute("cri") Criteria cri) {
		
		log.info("/get or /modify");
		model.addAttribute("board", service.get(bno));
		
	}
	
	@PreAuthorize("principal.username == #board.writer")
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr,
						@ModelAttribute("cri") Criteria cri) {
		log.info("modify:" + board);

		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/board/list" + cri.getListLink();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, 
			RedirectAttributes rttr, 
			@ModelAttribute("cri") Criteria cri) {
	
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		if(service.remove(bno)) {
			deleteFiles(attachList);
			rttr.addFlashAttribute("result", "success");
		}
	
		return "redirect:/board/list" + cri.getListLink();
	}

	private void deleteFiles(List<BoardAttachVO> attachList) {

		if (attachList == null || attachList.size() == 0) {
			return;
		}

		attachList.forEach(attach -> {
			try { Path file = Paths.get(
					"C:\\upload\\" + attach.getUploadPath() + 
					"\\" + attach.getUuid() + 
					"_" + attach.getFileName());

				Files.deleteIfExists(file);

				if (Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get(
						"C:\\upload\\" +
						attach.getUploadPath() + 
						"\\s_" + attach.getUuid() + 
						"_" + attach.getFileName());
	
						Files.delete(thumbNail);
				}

			} catch (Exception e) {
				log.error("delete file error" + e.getMessage());
			} 
		});
	}
	
	@GetMapping(value = "/getAttachList",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK); 
	}
	
}
