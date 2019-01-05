package com.project.utils;

public class Utils {

	public static boolean isDriverManagerEnabled = false;
	
	public static String getRelazioneTable(String tn, String[] attr){
		String str = tn + "(";
		int n = attr.length - 1;
		for(int i = 0; i< n; i++){
			str += attr[i] + ", ";
		}
		str += attr[n] + ")";
		return str;
	}
	
	
	public static void enableDriverManager(){
		isDriverManagerEnabled = true;
	}
	
	public static void disattivaDriverManager(){
		isDriverManagerEnabled = false;
	}
}
