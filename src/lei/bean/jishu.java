package lei.bean;

public class jishu {
private static int aa=0;
 
public static int getAa() {
	return aa;
}

public static void setAa(int aa) {
	jishu.aa = aa;
}

public static  int  js() {
	aa+=1;
	return aa;
}
}
