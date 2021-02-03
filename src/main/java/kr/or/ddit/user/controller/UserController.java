package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name="userService")
	private UserService userService;

	@RequestMapping("allUser")
	public String view(Model model) {
		
		model.addAttribute("userList", userService.selectAllUser());
		
		return "user/allUser";
	}

	@RequestMapping("allUserTiles")
	public String view2(Model model) {
		
		model.addAttribute("userList", userService.selectAllUser());
		
		return "tiles.user.allUser";
	}
	
	@RequestMapping(path="user", method=RequestMethod.GET)
	public String user(String userid, Model model) {
		
		model.addAttribute("user", userService.selectUser(userid));
		
		return "user/user";
	}
	
	@RequestMapping(path="userTiles", method=RequestMethod.GET)
	public String userTiles(String userid, Model model) {
		
		model.addAttribute("user", userService.selectUser(userid));
		
		return "tiles.user.user";
	}
	
	@RequestMapping(path = "/userModify", method = {RequestMethod.GET})
	public String modify(Model model, String userid) {
		
		model.addAttribute("user", userService.selectUser(userid));
		
		return "user/userModify";
	}

	@RequestMapping(path="userModify", method= {RequestMethod.POST})
	public String modify(UserVo userVo, MultipartFile profile, RedirectAttributes ra, Model model) {
		
		logger.debug("modify post");
		String originalFilename = "";
		String filename = "";
		int updateCnt = 0;
		if(profile.isEmpty() && profile.getSize() > 0) {
			 originalFilename = profile.getOriginalFilename();
			 filename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			
		
			
			try {
				profile.transferTo(new File("d:\\upload\\" + userVo.getRealfilename()));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		updateCnt = userService.modifyUser(userVo);
		userVo.setFilename(originalFilename);
		userVo.setRealfilename("d:\\upload\\" + filename);
		
		//����� ������ ���������� �� ���	==> �ش� ������� ����ȸ �������� �̵�
		if(updateCnt == 1) {
			ra.addAttribute("userid", userVo.getUserid());
			return "redirect:/user/user";
		}
		//����� ������ ������������ �� ��� ==> �ش� ������� ���� ���� �������� �̵�
		else {
			return "user/userModify";
		}
	}

	@RequestMapping(path="registUser", method=RequestMethod.GET)
	public String regist() {
		return "user/registUser";
	}
	
	@RequestMapping(path="registUser", method=RequestMethod.POST)
	public String regist(@Valid UserVo userVo, BindingResult result, MultipartFile profile, Model model) {
		//BindingResult �� Ŀ�ǵ� ��ü(UserVo) �ٷ� �ڿ� ���ڷ� ����ؾ� �Ѵ�.
		

//		new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors()) {
			logger.debug("result has error");
			return "user/registUser";
		}
	
		logger.debug("����");
		int insertCnt = 0;
		
		String originalFilename = "";
		String filename = "";
		logger.debug("����222 {}",profile.getSize());
			if(profile.getSize() > 0) {
			originalFilename = profile.getOriginalFilename();
			filename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			logger.debug("����2");
			
			try {
				profile.transferTo(new File("d:\\upload\\" + userVo.getRealfilename()));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
			userVo.setFilename(originalFilename);
			userVo.setRealfilename("d:\\upload\\" + filename);
			insertCnt = userService.registUser(userVo);

		//����� ����� ���������� �� ���	==> ����� ����¡ ����Ʈ�� �̵�(1������)
		if(insertCnt == 1) {
			return "redirect:/user/pagingUser";
		}
		//����� ������ ������������ �� ��� ==> ����� ��� �������� �̵�(����ڰ� �Է��� �� ����)
		else {
			return "user/registUser";
		}
	}
	
	
	@RequestMapping(path = "/deleteUser", method = {RequestMethod.POST})
	public String delete(Model model, String userid) {
		int deleteCnt = 0;
		try {
			model.addAttribute("user", userService.deleteUser(userid));
		}catch (Exception e) {
			deleteCnt = -1;
		}
		if(deleteCnt == 1) {
			
		}
		return "main";
	}
	
	
	
	//@RequestMapping("pagingUser")
	public String paigingUser(@RequestParam(defaultValue= "1" ) int page,
							@RequestParam(defaultValue= "5") int pageSize,
							@RequestParam(name="p") int price) {
		logger.debug("page : {}, pageSize : {}", page, pageSize);
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		
		
		return "";
	}
	
	
	
//	Map<String, Object> map = userService.selectPagingUser(vo);
//	
//	List<UserVo> userList =(List<UserVo>) map.get("userList");
//	int userCnt = (int)map.get("userCnt");
//	int pagination = (int)Math.ceil((double)userCnt/pagesize);
//	req.setAttribute("userList", userList);
//	req.setAttribute("pagination", pagination);
//	
//	req.setAttribute("pageVo", vo);
// �������� ����¡ �κ�
	
	@RequestMapping("pagingUser")
	public String paigingUser(@RequestParam(defaultValue = "1") int page,
							  @RequestParam(defaultValue = "5") int pageSize,
							  Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		return "user/pagingUser";
	}
	
	@RequestMapping("pagingUserTiles")
	public String pagingUserTiles(@RequestParam(defaultValue= "1" ) int page,
			@RequestParam(defaultValue= "5") int pageSize,
			Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
//		Map<String, Object> map = userService.selectPagingUser(pageVo);
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		//addAllAttributes ����Ͽ� �ڵ� ���̱�
		
//		List<UserVo> userList = (List<UserVo>)map.get("userList");
//		int userCnt = (int)map.get("userCnt");
//		int pagination = (int)Math.ceil((double)userCnt/pageSize);
//		
//		model.addAttribute("userList", userList);
//		model.addAttribute("pagination", pagination);
//		model.addAttribute("pageVo", pageVo);		
//UserServiceImpl ���� �۾��Ͽ� �ּ��صε�		
		
		//tiles-definition�� ������ name
		return "tiles.user.pagingUser";
	}
	
	
	@RequestMapping("excelDownload")
	public String excelDownload(Model model) {
		List<String> header = new ArrayList<String>();
		header.add("����ھ��̵�");
		header.add("������̸�");
		header.add("����ں���");

		model.addAttribute("header", header);
		model.addAttribute("data", userService.selectAllUser());
		
		return "userExcelDownloadView";
	}
	
	//localhost/user/profile
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		resp.setContentType("image");
		
		//userid �Ķ���͸� �̿��Ͽ�
		//userService ��ü�� ���� ������� ���� ���� �̸��� ȹ��
		//���� ������� ���� ������ �о�鿩 resp��ü�� outputStream���� ���� ����
		UserVo userVo = userService.selectUser(userid);
		
		String path="";
		if(userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/da.png");
		}
		else {
			path= userVo.getRealfilename();
		}
		try {
					
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			fis.close();
			sos.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
