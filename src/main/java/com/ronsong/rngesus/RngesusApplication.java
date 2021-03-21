package com.ronsong.rngesus;

import com.ronsong.rngesus.common.jwt.JwtAuthenticationFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@MapperScan("com.ronsong.rngesus.mapper")
@SpringBootApplication
public class RngesusApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RngesusApplication.class);
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final  FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        filterRegistrationBean.setFilter(filter);
        return filterRegistrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(RngesusApplication.class, args);
    }

}
