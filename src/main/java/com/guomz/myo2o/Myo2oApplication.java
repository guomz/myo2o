package com.guomz.myo2o;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//注解扫描
@ComponentScan("com.guomz.myo2o")
//mybatis接口扫描
@MapperScan("com.guomz.myo2o.dao")
//开启事务
@EnableTransactionManagement
//开启缓存redis
@EnableCaching
public class Myo2oApplication {

	public static void main(String[] args) {
		SpringApplication.run(Myo2oApplication.class, args);
	}

}
