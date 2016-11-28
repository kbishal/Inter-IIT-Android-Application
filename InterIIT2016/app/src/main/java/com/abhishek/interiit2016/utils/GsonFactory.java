package com.abhishek.interiit2016.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
	
	public static final String ISO_DATE_FROMAT = "yyyy-MM-dd'T'HH:mm:ss.sssZ";
	
	private static Gson basicInstance;
	
	private static Gson ISOFormatInstance;

	public static Gson geBasictInstance() {
		if (basicInstance == null) {
			basicInstance = new Gson();
		}
		return basicInstance;
	}
	
	public static Gson getISOFormatInstance() {
		if (ISOFormatInstance == null) {
			ISOFormatInstance = new GsonBuilder().setDateFormat(ISO_DATE_FROMAT).create();
		}
		return ISOFormatInstance;
	}
	
	public static Gson getInstance(String datePattern) {
		return new GsonBuilder().setDateFormat(datePattern).create();
	}
	
	public static Gson getSerializeNullsISOFormatInstance() {
		return new GsonBuilder().setDateFormat(ISO_DATE_FROMAT).serializeNulls().create();
	}
}

