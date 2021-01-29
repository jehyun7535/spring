package kr.or.ddit.hello;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/*
 * 	java - gui swing, awt, java fx
 */

@ContextConfiguration(locations = {"classpath:/kr/or/ddit/config/spring/application-context.xml",
								   "classpath:/kr/or/ddit/config/spring/root-context.xml"})
@WebAppConfiguration	//������ ȯ���� Web����� application Context�� ����
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest {

//	@Resource(name="helloController")
// ���������߿� ���� ������ Ÿ���� ������ ���� �����Ѵ�	
// Ư�� ������ ���� �̸��� ��Ī�� �� �ִ�
// ==> @Resource ������̼� �ϳ��� ��� ���� ���� ���� �ϴ�

	
	@Autowired		
	
	private HelloController h;
	
	@Test
	public void helloControllerTest() {
		assertNotNull(h);
	}

}
