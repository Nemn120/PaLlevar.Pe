package com.paLlevar.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
	
	public static final String DEFAULT_TIMEZONE ="America/Lima";
	
	
	public static Date getTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		
		//Create formatter
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
		 
		//Get formatted String
		String ldtString = FOMATTER.format(localDateTime);
		 
		System.out.println(ldtString);      // 07/15/2018 at 02:49 PM
		
		 
		
		return convertToDateLocalDateTime(localDateTime);
	}

	
	public static Date convertToDateLocalDateTime(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date stringToDate(String dateAssString, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateAssString);
	}
	
}
