package org.ezenboard.mapper;
import java.util.Date;

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
	public void test1() {
		log.info(mapper.getReplyList());
	}
	
	@Test
	public void insertTest() {
		ReplyVO vo = new ReplyVO();
		
		vo.setBno(113L);
		vo.setReply("220425 Reply");
		vo.setReplyer("Tom");
		
		log.info(mapper.insert(vo));
	}
	
}
