package com.kkb.annotation.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kkb.project.po.User;

//相当于编写了一个spring配置文件
@Configuration
@ComponentScan(basePackages="com.kkb.project")
public class SpringConfiguration {

	// spring容器初始化时，会调用配置类的无参构造函数
	public SpringConfiguration() {
		System.out.println("容器启动初始化。。");
	}
	
	@Bean("user")
	public User getUser() {
		return new User();
	}
	
	@Bean("dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/ssm?characterEncoding=utf-8");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	@Bean("jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(getDataSource());
		
		return template;
		
	}
}
