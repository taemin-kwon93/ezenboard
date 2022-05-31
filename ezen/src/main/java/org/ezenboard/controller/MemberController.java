package org.ezenboard.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.ezenboard.domain.MemberVO;
import org.ezenboard.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@PostMapping("/member/join")
	public String save(@ModelAttribute("user") MemberVO user, Model m) {

		log.info("MemberController _ join");
		
		return "redirect:/customLogin";
	}
}
	
