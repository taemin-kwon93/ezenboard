package org.ezenboard.service;

import static org.junit.Assert.assertNotNull;

import org.ezenboard.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@Test
	public void testT1() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testT2() {
		log.info(service.getList());
	}
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("0413");
		board.setContent("0413");
		board.setWriter("0413");
		
		service.register(board);
		
		log.info("등록된 글의 번호 : " + board.getBno());
	}
	
	@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info("testGetList() 로그 : " + board));;
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = service.get(87L);
		
		if(board == null) {
			return;
		}

		board.setTitle("0413");
		board.setContent("0413");
		board.setWriter("0413");
		log.info("게시글 수정 : " + service.modify(board));
	}
	
	@Test
	public void testDelete() {
		log.info("글 삭제 : " + service.remove(90L));
	}
}
