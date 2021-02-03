package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDao {
	
	//사용자 아이디로 사용자 조회
	UserVo selectUser(String userid);
	
	List<UserVo> selectAllUser();
	
	//사용자 전체 수 조회
	int selectAllUserCnt();

	List<UserVo> selectPagingUser(PageVo vo);
	
	//사용자 정보 수정
	int modifyUser(UserVo userVo);
	
	//사용자 정보 등록
	int registUser(UserVo userVo);
	
	//사용자 정보 삭제
	int deleteUser(String userid);
}
