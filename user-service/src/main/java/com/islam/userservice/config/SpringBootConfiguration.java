package com.islam.userservice.config;

import com.islam.userservice.interceptors.AuthInterceptor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.islam.userservice")
public class SpringBootConfiguration implements WebMvcConfigurer {

	private final AuthInterceptor authInterceptor;

	public SpringBootConfiguration(AuthInterceptor authInterceptor) {
		this.authInterceptor = authInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setAmbiguityIgnored(true)
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
				.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}