package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;


public class UserServiceTest extends ModelTestConfig{
	
	@Resource(name="userService")
	private UserService userService;
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userService.selectUser(userid);
		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	public void gteAllUserTest() {
		/*** Given ***/
		/*** When ***/
		List<UserVo> userList = userService.selectAllUser();

		/*** Then ***/
		assertEquals(27, userList.size());
	}
	
	@Test
	public void selectUserTest() {
		/*** Given ***/
		String userid = "brown";
		/*** When ***/
		UserVo user = userService.selectUser(userid);

		/*** Then ***/
		assertNotNull(user);
		assertEquals("브라운", user.getUsernm());

	}
	
	@Test
	public void selectUserNotExsistTest() {
		/*** Given ***/
		String userid = "brownNotexists";

		/*** When ***/
		UserVo user = userService.selectUser(userid);

		/*** Then ***/
		assertNull(user);

	}
	
	@Test
	public void selectPagingUserTest() {
		/*** Given ***/
		PageVo vo = new PageVo(2, 5);

		/*** When ***/
		Map<String, Object> userList = userService.selectPagingUser(vo);

		/*** Then ***/
		assertEquals(3, userList.size());
	}
	

	@Test
	public void modifyUserTest() {
		/*** Given ***/
		// userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit", "대덕인재", "dditPass", new Date(), "개발원", "대전시 중구 중앙로79", "4층", "34940", "brown.png", "uuid-generated-filename.png");

		/*** When ***/
		int updateCnt = userService.modifyUser(userVo);

		/*** Then ***/
		assertEquals(1, updateCnt);
	}
	
	
	@Test
	public void deleteUserTest() {
		/*** Given ***/
		// 해당 테스트가 실행될 때는 testUser라는 사용자가 before메소드에 의해 등록이 된 상태
		String userid = "ddit_n";
		/*** When ***/
		int deleteCnt = userService.deleteUser(userid);
		/*** Then ***/
		assertEquals(1, deleteCnt);
	}
	
	@Test 
	public void registUserTest() {
		UserVo userVo = new UserVo("ddit_n", "테스트용", "1234",new Date(), "테스트", "대전시 중구 중앙로79", "4층", "34940", "brown.png", "uuid-generated-filename.png");
		
	int registCnt = userService.registUser(userVo);
		
	assertEquals(1, registCnt);

	}
}

