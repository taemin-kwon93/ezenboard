package org.ezenboard.mapper;
import java.util.Date;
import java.util.List;

import org.ezenboard.domain.Criteria;
import org.ezenboard.domain.ReplyVO;
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
public class ReplyMapperTests {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void getReplyListTest() {
		Criteria cri = new Criteria();
		
		log.info("로그 test1 : " + mapper.getReplyList(cri, 113L));
	}
	
	@Test
	public void insertTest() {
		ReplyVO vo = new ReplyVO();
		
		vo.setBno(113L);
		vo.setReply("220425 Reply2");
		vo.setReplyer("Tom2");
		
		log.info(mapper.insert(vo));
	}
	
	@Test
	public void readTest() {
		log.info("로그 readTest() : " + mapper.read(113L));
	}
	
	@Test
	public void deleteTest() {
		Long target = 43L;
		mapper.delete(target);
	}

	@Test
	public void testUpdate() {
		ReplyVO vo = mapper.read(42L);
		log.info(vo);
		
		vo.setReply("update Reply"); 
		int count = mapper.update(vo);
		log.info("수정 완료 : : " + count);
 
	}
	
}
