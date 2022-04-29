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
public class SampleServiceTests {

	@Setter(onMethod_ = @Autowired)
	private SampleService service;

	@Test
	public void doAddTest() throws Exception {
		Integer i = service.doAdd("30", "28");
		log.info(i);
	}
  
	//AfterThrowing Test
	@Test
	public void throwingTest() throws Exception {
		
		Integer i = service.doAdd("30", "abc");
		log.info(i);

		/* console _ exception발생 : java.lang.NumberFormatException: For input string:
		 * "abc"
		 */
	}
  
	//AfterThrowing Test
	@Test
	public void throwingTest2() throws Exception {

		Integer i = service.doAdd("30", null);
		log.info(i);
		/*  console _ exception발생 : 
		 * java.lang.NumberFormatException: null
		 */

	}
	 
}
