package kr.or.ddit.hello;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

/*
 * 	java - gui swing, awt, java fx
 */

public class HelloControllerTest extends WebTestConfig{

//	@Resource(name="helloController")
// 스프링빈중에 대입 가능한 타입의 스프링 빈을 주입한다	
// 특정 스프링 빈의 이름을 지칭할 수 있다
// ==> @Resource 어노테이션 하나를 사용 했을 때와 동일 하다

	
	//localhost/hello/view
	@Test
	public void viewTest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("/hello/view"))
				.andExpect(status().isOk())
				.andExpect(view().name("hello"))
				.andExpect(model().attributeExists("userVo"))
				.andDo(print())
				.andReturn();
		
		//ModelAndView : 데이터를 전송시킬 수 있는 리턴 타입

		ModelAndView mav = mvcResult.getModelAndView();
		assertEquals("hello", mav.getViewName());
		UserVo userVo = (UserVo)mav.getModel().get("userVo");
		
		assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	public void pathVariableTest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("/hello/path/cony"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("subpath"))
				.andDo(print())
				.andReturn();
	}
}
