package com.coviam.kafkaTrial.Model;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "sankalp.properties" })
public class DataConfig {
	
	@Value("${spring.datasource.url}")
	String datasourceUrl;
	@Value("${spring.datasource.username}")
	String datasourceUsername;
	@Value("${spring.datasource.password}")
	String datasourcePassword;
	@Value("${spring.datasource.driverClassName}")
	String driverClassName;
	@Value("${spring.datasource.max-active}")
	int maxActive;
	@Value("${spring.datasource.max-wait}")
	int maxWait;
	
	@Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(datasourceUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(datasourceUsername);
        dataSource.setPassword(datasourcePassword);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        System.out.println("Property Created");
        return dataSource;
    }
	
	@Bean
	public ServerProperties tomcatServer(){
		ServerProperties server = new ServerProperties();
		server.setPort(8082);
		return server;
	}
}