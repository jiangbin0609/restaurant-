package com.newer.bean;

import java.io.Serializable;

/**
 * 菜系类
 */
public class Sort implements Serializable {
	private static final long serialVersionUID = 1L;
	private int sortID;
	private String sortName;

	public Sort() {
		super();
	}

	public Sort(int sortID, String sortName) {
		super();
		this.sortID = sortID;
		this.sortName = sortName;
	}

	public int getSortID() {
		return sortID;
	}

	public void setSortID(int sortID) {
		this.sortID = sortID;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	@Override
	public String toString() {
		return "Sort [sortID=" + sortID + ", sortName=" + sortName + "]";
	}
	
}
