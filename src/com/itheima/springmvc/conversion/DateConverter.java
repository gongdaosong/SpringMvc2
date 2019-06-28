package com.itheima.springmvc.conversion;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * 
 */
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		try {
			if(null != source)
			{
				DateFormat format = new SimpleDateFormat("yyyy:MM-dd HH_mm-ss");
				 Date parse = format.parse(source);
				 System.out.println(parse);
				 return parse;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
