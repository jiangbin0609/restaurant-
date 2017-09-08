package com.newer.bean;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	// 定义用户的id号，用户名，密码，真实姓名，地址，联系电话，邮箱
	private int usersId;
	private String usersName;
	private String usersPass;
	private String usersTrueName;
	private String usersAddress;
	private String usersPhone;
	private String usersEmail;
	// 用户的状态，是否被禁用 1代表正常，0代表被禁用；
	private int userCon;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int usersId, String usersName, String usersPass, String usersTrueName, String usersAddress,
			String usersPhone, String usersEmail, int userCon) {
		super();
		this.usersId = usersId;
		this.usersName = usersName;
		this.usersPass = usersPass;
		this.usersTrueName = usersTrueName;
		this.usersAddress = usersAddress;
		this.usersPhone = usersPhone;
		this.usersEmail = usersEmail;
		this.userCon = userCon;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public String getUsersName() {
		return usersName;
	}

	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}

	public String getUsersPass() {
		return usersPass;
	}

	public void setUsersPass(String usersPass) {
		this.usersPass = usersPass;
	}

	public String getUsersTrueName() {
		return usersTrueName;
	}

	public void setUsersTrueName(String usersTrueName) {
		this.usersTrueName = usersTrueName;
	}

	public String getUsersAddress() {
		return usersAddress;
	}

	public void setUsersAddress(String usersAddress) {
		this.usersAddress = usersAddress;
	}

	public String getUsersPhone() {
		return usersPhone;
	}

	public void setUsersPhone(String usersPhone) {
		this.usersPhone = usersPhone;
	}

	public String getUsersEmail() {
		return usersEmail;
	}

	public void setUsersEmail(String usersEmail) {
		this.usersEmail = usersEmail;
	}

	public int getUserCon() {
		return userCon;
	}

	public void setUserCon(int userCon) {
		this.userCon = userCon;
	}

	@Override
	public String toString() {
		return "User [usersId=" + usersId + ", usersName=" + usersName + ", usersPass=" + usersPass + ", usersTrueName="
				+ usersTrueName + ", usersAddress=" + usersAddress + ", usersPhone=" + usersPhone + ", usersEmail="
				+ usersEmail + ", userCon=" + userCon + "]";
	}

}
