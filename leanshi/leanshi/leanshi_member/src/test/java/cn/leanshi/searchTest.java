package cn.leanshi;

import cn.leanshi.model.util.DateConverter;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * @author :ldq
 * @date:2018/8/2
 * @description:leanshi_member cn.leanshi
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class searchTest {

	@Test
	public void fun01(){
		DateConverter dateConverter = new DateConverter();
		Date date = dateConverter.convert("201812");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,1);
		System.out.println(calendar.get(Calendar.MONTH+1));
	}
}
