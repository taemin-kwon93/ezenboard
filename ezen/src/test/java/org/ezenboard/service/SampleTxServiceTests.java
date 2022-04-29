package org.ezenboard.service;

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
public class SampleTxServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private SampleTxService service;
	
	@Test
	public void testLong() {

		String testCol1 = "50byte를 넘기는 용량이 입력되면 tbl_sample2 "
				+ "테이블 col2에 해당 데이터가 들어가지 못하게 되고 문제가 발생한다. "
				+ "@Transactional 어노테이션을 사용해 이를 해결하려 한다. ";
		
		String testCol2 = "짧은 글";
		

		service.insertTest(testCol1);
	}
}
