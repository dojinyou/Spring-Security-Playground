package com.dojinyou.infleansecurity;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터 체인에 등록됨.
public class SecurityConfig {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return ((request, response, exception) -> {
      Authentication authentication = SecurityContextHolder
          .getContext()
          .getAuthentication();
      Object principal = authentication != null ? authentication.getPrincipal() : null;
      logger.warn("{} is denied", principal, exception);
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.setContentType("text/plain;charset=UTF-8");
      response
          .getWriter()
          .write("ACCESS DENIED");
      response
          .getWriter()
          .flush();
      response
          .getWriter()
          .close();

    });
  }

  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOriginPattern("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/security/auth")
        .authenticated()
        .anyRequest()
        .permitAll()
        .and()
        .cors()
        .configurationSource(corsConfigurationSource())
        .and()
        /**
         * formLogin, csrf, headers, http-basic, rememberMe, logout filter 비활성화
         */
        .formLogin()
        .loginPage("/login")
        .usernameParameter("id")
        .loginProcessingUrl("/request-login")
        .defaultSuccessUrl("/security/auth")
        .and()
        .csrf()
        .disable()
        .headers()
        .disable()
        .httpBasic()
        .disable()
        .rememberMe()
        .disable()
        .logout()
        .disable()
        /**
         * Session 사용하지 않음
         */
        // .sessionManagement()
        // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        // .and()
        /**
         * 예외처리 핸들러
         */
        .exceptionHandling()
        .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
        .accessDeniedHandler(accessDeniedHandler());

    return http.build();
  }
}
