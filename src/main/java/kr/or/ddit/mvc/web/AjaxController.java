package kr.or.ddit.mvc.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.or.ddit.user.model.UserVo;

@RequestMapping("ajax")
@Controller
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	//우선순위 때문에 애가 나옴
	@ModelAttribute(name = "rangers")
	public List<String> rangers(){
		List<String> rangers = new ArrayList<String>();
		
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("james");
		rangers.add("sally");
		rangers.add("moon");
		
		return rangers;
	}
	
	// localhost/ajax/jsomView
	@RequestMapping("View")
	public String View(Model model) {
		return "ajax/ajaxView";
	}
	
	@RequestMapping("form")
	public String form(UserVo userVo) {
		logger.debug("userVo", userVo);
		
		//return "jsonView";
		return "jsonView";
	}
	
	// localhost/ajax/jsomView
	@RequestMapping("jsonView")
	public String jsonView(Model model) {
		return "jsonView";
	}
	
	@RequestMapping("jsonViewViewObj")
	public View jsonViewViewOjb() {
		return new MappingJackson2JsonView();
	}

	@RequestMapping("jsonViewMav")
	public ModelAndView jsonViewMav() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("jsonView");
		
		return mav;
	}
}
