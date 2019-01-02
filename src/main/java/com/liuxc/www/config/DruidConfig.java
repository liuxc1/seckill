package com.liuxc.www.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {
	/**
	 * 首先配置一个DruidDataSOurce
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource") // 使用配置文件的配置
	public DataSource druidDataSource() {

		return new DruidDataSource();
	}

	/**
	 * 注册Servlet
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");// 配置web访问路径
		// 添加初始化参数：initParams
		// 白名单：
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
		// permitted to view this page.
		servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
		// 登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername", "lxc");
		servletRegistrationBean.addInitParameter("loginPassword", "lxc");
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}
	/**
	 * 注册Filet
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		// 添加不需要忽略的格式信息.
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
