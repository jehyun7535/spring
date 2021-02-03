package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDao {
	
	//����� ���̵�� ����� ��ȸ
	UserVo selectUser(String userid);
	
	List<UserVo> selectAllUser();
	
	//����� ��ü �� ��ȸ
	int selectAllUserCnt();

	List<UserVo> selectPagingUser(PageVo vo);
	
	//����� ���� ����
	int modifyUser(UserVo userVo);
	
	//����� ���� ���
	int registUser(UserVo userVo);
	
	//����� ���� ����
	int deleteUser(String userid);
}
