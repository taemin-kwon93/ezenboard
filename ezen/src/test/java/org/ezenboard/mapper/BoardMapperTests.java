package org.ezenboard.mapper;
import org.ezenboard.domain.BoardVO;
import org.ezenboard.domain.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	/*
	@Test
	public void getTest(){
		mapper.getList().forEach(board -> log.info(board));
		
	    
	} 
	getList() 함수의 결과 값을 하나씩 BoardVO 타입의 board 에 넘긴다! + log.info(board)를 실행한다.
	getList() 결과 값이 하나도 없을 때까지 
	*/
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("0412title");
		board.setContent("0412title");
		board.setWriter("0412title");
		
		mapper.insert(board);
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("0412title_2");
		board.setContent("0412title_2");
		board.setWriter("0412title_2");
		
		mapper.insertSelectKey(board);
		log.info(board);
	}
	
	@Test
	public void testRead() {
		Long bno = 1L;
		log.info(mapper.read(bno));
	}
	
	@Test
	public void testDelete() {
		log.info("Delete Success? : " + mapper.delete(88L));
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		
		board.setBno(87L);
		board.setTitle("Test Title");
		board.setContent("Test Content");
		board.setWriter("Test Writer");
		
		int i = mapper.update(board);
		log.info("log 남기기! " + i);
	}
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(3);
		cri.setAmount(10);
		log.info("paging : " + mapper.getListWithPaging(cri));
		
	}
	
	@Test
	public void testSearchPaging() {
		Criteria cri = new Criteria();
		cri.setAmount(10);
		cri.setPageNum(1);
		cri.setKeyword("test");
		cri.setType("CT");
		
		mapper.getListWithPaging(cri);//console에서 'INFO : jdbc.resultsettable - '확인.
	}
	
}
