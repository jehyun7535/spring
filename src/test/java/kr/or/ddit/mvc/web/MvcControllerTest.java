package kr.or.ddit.mvc.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.test.config.WebTestConfig;

public class MvcControllerTest extends WebTestConfig{

	@Test
	public void filruploadViewTest() throws Exception{
		mockMvc.perform(get("/mvc/fileupload/view"))
		.andExpect(view().name("file/view"))
		.andDo(print());
	}

	
	@Test
	public void fileuploadTest() throws Exception{
		ClassPathResource resource = new ClassPathResource("kr/or/ddit/upload/da.png");
		MockMultipartFile file = 
				new MockMultipartFile("picture", "sally.png", "image/png", resource.getInputStream()); // 해당경로에 sally.png 라는 이름으로 가짐
		mockMvc.perform(fileUpload("/mvc/fileupload/upload")
				.file(file).param("userid", "brown"))
		.andExpect(view().name("file/view"))
		.andDo(print());
	}
}
