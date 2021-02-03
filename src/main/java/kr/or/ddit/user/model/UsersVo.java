package kr.or.ddit.user.model;

import java.util.List;

public class UsersVo {
	
	private List<UsersVo> userVoList;

	public List<UsersVo> getUserVoList() {
		return userVoList;
	}

	public void setUserVoList(List<UsersVo> userVoList) {
		this.userVoList = userVoList;
	}

	@Override
	public String toString() {
		return "UsersVo [userVoList=" + userVoList + "]";
	}
}
