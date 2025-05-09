package com.loveis.demo.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.loveis.demo.common.interceptor.CheckLoginSessionInterceptor;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CheckLoginSessionInterceptor())
//		.order(1)
//		.addPathPatterns("/*/*/*Xdm*", "/*/*/*Love*")
		.addPathPatterns("/*/*/*Xdm*")
		.excludePathPatterns(
//				"/resources/**",
				"/static/**",
				"/xdm/member/LoginXdmForm",
				"/xdm/member/LoginXdmProc",
				"/love/index/IndexLoveView"
		);
	}

	
}
