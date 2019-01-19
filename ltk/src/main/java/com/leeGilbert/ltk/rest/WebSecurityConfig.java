//package com.leeGilbert.ltk.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.*;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import javax.servlet.Filter;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//
//import javax.servlet.Filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
//import org.springframework.security.web.context.SecurityContextPersistenceFilter;
//import org.springframework.security.web.header.writers.HstsHeaderWriter;
//import org.springframework.security.web.util.matcher.AnyRequestMatcher;
//import org.springframework.web.reactive.function.server.ServerRequest;
//
//
//
////@EnableWebSecurity(debug=true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//   private String REST_API_URL_PREFIX ="ltk";
//
//    @Autowired
//    protected ApplicationContext context;
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.anonymous()
////                .antMatcher(REST_API_URL_PREFIX + "/**")
////                // As of Spring Security 4.0, CSRF protection is enabled by default.
////                .csrf().disable()
////                // Configure CORS
////                .addFilterBefore(corsFilter, SecurityContextPersistenceFilter.class)
////                .authorizeRequests()
////                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
////                .antMatchers("/**").hasAnyAuthority(ROLE_USER)
////                .and()
////                .httpBasic().authenticationEntryPoint(basicAuthenticationEntryPoint);
//
//        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
//            @Override
//            public void commence(HttpServletRequest request, HttpServletResponse response,
//                                 AuthenticationException authException) throws IOException, ServletException {
//                if (authException != null) {
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    response.getWriter().print("Unauthorizated....");
//                }
//            }
//        });
//    }
//
//
//
//
//}a