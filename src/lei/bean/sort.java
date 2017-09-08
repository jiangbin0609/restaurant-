package lei.bean;

import java.io.Serializable;

public class sort  implements Serializable{
 
	private static final long serialVersionUID = 1L;
private int  sortId;
 private String sortName;
public sort(int sortId, String sortName) {
	super();
	this.sortId = sortId;
	this.sortName = sortName;
}
public sort() {
	super();
}
public int getSortId() {
	return sortId;
}
public void setSortId(int sortId) {
	this.sortId = sortId;
}
public String getSortName() {
	return sortName;
}
public void setSortName(String sortName) {
	this.sortName = sortName;
}
 
}
