package org.ezenboard.controller;

import org.ezenboard.domain.Criteria;
import org.ezenboard.domain.ReplyPageDTO;
import org.ezenboard.domain.ReplyVO;
import org.ezenboard.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/replies/")	
@AllArgsConstructor
public class ReplyController {
	
	@Setter(onMethod_ = @Autowired)	
	private ReplyService service;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/new", 
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		
		log.info("create(@RequestBody ReplyVO vo)" + vo);
		int insertCount = service.register(vo);
		
		return insertCount == 1? 
				new ResponseEntity<String>("success", HttpStatus.OK) 
			:	new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/pages/{bno}/{page}",
			produces = {MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(
			@PathVariable("bno") Long bno,
			@PathVariable("page") int page) {
		
		Criteria cri = new Criteria(page, 10);
		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{rno}",
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	@PreAuthorize("principal.username == #vo.replyer")
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(
			@RequestBody ReplyVO vo,
			@PathVariable("rno") Long rno) {
		log.info("remove _ rno: " + rno);
		log.info("remove _ replyer: " + vo.getReplyer());

		return service.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@PreAuthorize("principal.username == #vo.replyer")
	@RequestMapping(value = "/{rno}",
			method = {RequestMethod.PUT, RequestMethod.PATCH},
			consumes = "application/json")
	public ResponseEntity<String> modify(
			@PathVariable("rno") Long rno,
			@RequestBody ReplyVO vo) {
		
		vo.setRno(rno);
		
		return service.modify(vo) == 1 ?
				new ResponseEntity<>("Modify Success", HttpStatus.OK)
			:	new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
