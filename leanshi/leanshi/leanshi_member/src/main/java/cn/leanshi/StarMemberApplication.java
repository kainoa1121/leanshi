package cn.leanshi;

import cn.leanshi.model.util.DateConverter;

import java.util.Date;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

/**
 * @author :ldq
 * @date:2018/7/27
 * @description:leanshi cn.leanshi
 */
@SpringBootApplication
@MapperScan("cn.leanshi.mapper")
public class StarMemberApplication {
	public static void main(String[] args) {
		SpringApplication.run(StarMemberApplication.class,args);
	}

	@Bean
	public Converter<String, Date> addNewConvert() {
		return new DateConverter();
	}

	
}
