package org.ezenboard.controller;

import org.ezenboard.domain.MemberVO;
import org.ezenboard.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/*")
@AllArgsConstructor
public class MemberController {

	private MemberService service;
	
	@GetMapping("/joinForm")
	public void joinForm() {
		
	}
	
	@PostMapping("/join")
	public String save(MemberVO user) {

		log.info("MemberController _ join");
		log.info("user.toString() : " + user.toString());
		
		return "redirect:/customLogin";
	}
}
	
