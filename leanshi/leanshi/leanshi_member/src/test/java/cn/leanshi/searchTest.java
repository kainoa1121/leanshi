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
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
        String monthS ="";
		if (month < 10){
			monthS = 0+""+month;
		}else{
			monthS = month+"";
		}

		System.out.println(year+""+monthS);

	}

	@Test
	public void fun02(){
		DateConverter dateConverter = new DateConverter();
		Date beginDate = dateConverter.convert("2018-09-01");
		Date endDate = dateConverter.convert("2018-08-31");
		System.out.println(endDate.getTime()>beginDate.getTime());
	}

	@Test
	public void fun03(){

		String i = "201809";
		String j = i.substring(0,4);
		String s = i.substring(4);
		System.out.println(j+">>>>>>"+s);


	}


}
