package lei.bean;

import java.io.Serializable;

public class admin implements Serializable {

	private static final long serialVersionUID = 1L;
	private int adminId;
	private String adminName;
	private String  adminPass;
	public admin(int adminId, String adminName, String adminPass) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPass = adminPass;
	}
	public admin() {
		super();
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	@Override
	public String toString() {
		return "admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPass=" + adminPass + "]";
	}

}
