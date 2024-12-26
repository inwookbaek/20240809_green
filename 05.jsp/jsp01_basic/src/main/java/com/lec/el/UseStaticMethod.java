package com.lec.el;

import java.text.DecimalFormat;

public class UseStaticMethod {

	public static String numberFormat(long number, String pattern) {
		DecimalFormat ptrn = new DecimalFormat(pattern);
		System.out.println(ptrn.format(number));
		return ptrn.format(number);
	}
}
