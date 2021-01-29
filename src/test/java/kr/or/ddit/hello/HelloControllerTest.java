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
@WebAppConfiguration	//스프링 환경을 Web기반의 application Context로 생성
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest {

//	@Resource(name="helloController")
// 스프링빈중에 대입 가능한 타입의 스프링 빈을 주입한다	
// 특정 스프링 빈의 이름을 지칭할 수 있다
// ==> @Resource 어노테이션 하나를 사용 했을 때와 동일 하다

	
	@Autowired		
	
	private HelloController h;
	
	@Test
	public void helloControllerTest() {
		assertNotNull(h);
	}

}
