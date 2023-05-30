package com.example.asm7_phamvanthanh.config;

import com.example.asm7_phamvanthanh.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService uds;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(uds);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize

                        .requestMatchers("/home/**", "/login/**", "/logout/**").permitAll()
                        .requestMatchers("/products/**").hasAnyAuthority("ROLE_SALES", "ROLE_USER")
                        .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/register/**").permitAll()
                        .anyRequest().authenticated()
                )
                // .formLogin(withDefaults())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home")
                        .failureUrl("/login?error")
                        .permitAll())

                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll())

                //.httpBasic(withDefaults())
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(
                exception -> exception
                        // Xử lý trang truy cập bị từ chối
                        .accessDeniedPage("/home/403"));

        return http.build();
    }
}



//    /*
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("USER", "ADMIN")
//                .build();
//        UserDetails sales = User.builder()
//                .username("sales")
//                .password(passwordEncoder().encode("sales"))
//                .roles("SALES")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, sales, admin);
//    }