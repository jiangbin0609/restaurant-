package com.newer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =sdf.parse("2017-07-04 10:07:39");
		System.out.println(sdf.format(date));
	}
}
