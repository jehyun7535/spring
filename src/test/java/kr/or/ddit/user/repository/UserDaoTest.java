package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

//������ ȯ�濡�� junit �ڵ带 ���� ==> junit �ڵ嵵 ������ ������ ���

public class UserDaoTest extends ModelTestConfig{

	@Resource(name="userDao")
	private UserDao userDao;
	
	
	@Test
	public void getUsertest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.selectUser(userid);
		
		/***Then***/
		assertEquals("����", userVo.getUsernm());
		
	}
	
	@Test
	public void getAllUserTest() {
		List<UserVo> userList = userDao.selectAllUser();
		
		assertEquals(27, userList.size());
	}
	
	@Test
	public void getPagingUserTest() {

		/*** Given ***/
		PageVo page = new PageVo();
		page.setPage(1);
		page.setPageSize(5);

		/*** When ***/
		List<UserVo> userList = userDao.selectPagingUser(page);

		/*** Then ***/
		assertEquals(5, userList.size());
	}

	
	@Test
	public void getAllUserCntTest() {
		/*** Given ***/

		/*** When ***/
		int userCnt = userDao.selectAllUserCnt();

		/*** Then ***/
		assertEquals(27, userCnt);
	}
	
	@Test
	public void modifyUserTest() {
		/*** Given ***/
		// userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit", "�������", "dditPass", new Date(), "���߿�", "������ �߱� �߾ӷ�79", "4��", "34940", "brown.png", "uuid-generated-filename.png");

		/*** When ***/
		int updateCnt = userDao.modifyUser(userVo);

		/*** Then ***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void registUserTest() {
		UserVo userVo = new UserVo("ddit_n", "�׽�Ʈ��", "1234",new Date(), "�׽�Ʈ", "������ �߱� �߾ӷ�79", "4��", "34940", "brown.png", "uuid-generated-filename.png");
	
		
		int registCnt = userDao.registUser(userVo);
		
		assertEquals(1, registCnt);
	}
	
	@Test
	public void deleteUserTest() {
		/*** Given ***/
		// �ش� �׽�Ʈ�� ����� ���� testUser��� ����ڰ� before�޼ҵ忡 ���� ����� �� ����
		String userid = "ddit_n";
		/*** When ***/
		int deleteCnt = userDao.deleteUser(userid);
		/*** Then ***/
		assertEquals(1, deleteCnt);

	}
	
}
