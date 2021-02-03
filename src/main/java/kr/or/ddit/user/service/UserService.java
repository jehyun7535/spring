package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserService {
	
	List<UserVo> selectAllUser();

	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	
	//페이징처리
	Map<String, Object> selectPagingUser(PageVo vo);
	
	int modifyUser(UserVo userVo);
	
	int registUser(UserVo userVo);
	
	int deleteUser(String userid);
}
