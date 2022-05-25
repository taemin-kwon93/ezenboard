package org.ezenboard.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	//22.05.25
	//제작 완료되면 해당 컨트롤러 지울것.
	
	@GetMapping("/all")
	public void doAll() {

		log.info("do all can access everybody");
	}

	@GetMapping("/member")
	public void doMember() {

		log.info("logined member");
	}

	@GetMapping("/admin")
	public void doAdmin() {

		log.info("admin only");
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@GetMapping("/annoMeber")
	public void doMember2() {
		log.info("doMember2 _ logined annotation member");
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/annoAdmin")
	public void doAdmin2() {
		log.info("admin annotation only");
	}

}
