package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

//스프링 환경에서 junit 코드를 실행 ==> junit 코드도 스프링 빈으로 등록

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
		assertEquals("브라운", userVo.getUsernm());
		
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
		UserVo userVo = new UserVo("ddit", "대덕인재", "dditPass", new Date(), "개발원", "대전시 중구 중앙로79", "4층", "34940", "brown.png", "uuid-generated-filename.png");

		/*** When ***/
		int updateCnt = userDao.modifyUser(userVo);

		/*** Then ***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void registUserTest() {
		UserVo userVo = new UserVo("ddit_n", "테스트용", "1234",new Date(), "테스트", "대전시 중구 중앙로79", "4층", "34940", "brown.png", "uuid-generated-filename.png");
	
		
		int registCnt = userDao.registUser(userVo);
		
		assertEquals(1, registCnt);
	}
	
	@Test
	public void deleteUserTest() {
		/*** Given ***/
		// 해당 테스트가 실행될 때는 testUser라는 사용자가 before메소드에 의해 등록이 된 상태
		String userid = "ddit_n";
		/*** When ***/
		int deleteCnt = userDao.deleteUser(userid);
		/*** Then ***/
		assertEquals(1, deleteCnt);

	}
	
}
