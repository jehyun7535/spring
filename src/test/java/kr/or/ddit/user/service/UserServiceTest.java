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
		assertEquals("����", userVo.getUsernm());
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
		assertEquals("����", user.getUsernm());

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
		UserVo userVo = new UserVo("ddit", "�������", "dditPass", new Date(), "���߿�", "������ �߱� �߾ӷ�79", "4��", "34940", "brown.png", "uuid-generated-filename.png");

		/*** When ***/
		int updateCnt = userService.modifyUser(userVo);

		/*** Then ***/
		assertEquals(1, updateCnt);
	}
	
	
	@Test
	public void deleteUserTest() {
		/*** Given ***/
		// �ش� �׽�Ʈ�� ����� ���� testUser��� ����ڰ� before�޼ҵ忡 ���� ����� �� ����
		String userid = "ddit_n";
		/*** When ***/
		int deleteCnt = userService.deleteUser(userid);
		/*** Then ***/
		assertEquals(1, deleteCnt);
	}
	
	@Test 
	public void registUserTest() {
		UserVo userVo = new UserVo("ddit_n", "�׽�Ʈ��", "1234",new Date(), "�׽�Ʈ", "������ �߱� �߾ӷ�79", "4��", "34940", "brown.png", "uuid-generated-filename.png");
		
	int registCnt = userService.registUser(userVo);
		
	assertEquals(1, registCnt);

	}
}

