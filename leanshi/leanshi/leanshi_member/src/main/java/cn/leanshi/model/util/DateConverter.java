package cn.leanshi.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


/**
 * 时间格式化器
 * @author :ldq
 * @date:2018/8/17
 * @description:leanshi_member cn.leanshi.model.util
 */
public class DateConverter implements Converter<String, Date> {


	@Override
	public Date convert(String event) {

		String[] patterns = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyyMMdd", "yyyyMM", "yyyy年MM月dd日 HH:mm"};

		Date date = null;
		for (String pattern : patterns) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				date = sdf.parse(event);
				if (date != null) {
					break;
				}
			} catch (ParseException ignored) {

			}
		}
		return date;
	}

	//获取当前周期yyyyMM
	public static String getToday(){
        /*
        年份        yyyy
        月份        MM
        日期        dd
        星期        EE
        小时24      HH
        小时12      hh
        分钟        mm
        秒          ss
        */
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(date);
	}

}