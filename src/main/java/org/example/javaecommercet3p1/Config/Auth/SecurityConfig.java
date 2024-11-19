package org.example.javaecommercet3p1.Config.Auth;


import org.example.javaecommercet3p1.Entities.Auth.Role;
import org.example.javaecommercet3p1.security.CustomerUserDetailService;
import org.example.javaecommercet3p1.security.jwt.InternalApiAuthenticationFilter;
import org.example.javaecommercet3p1.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${authentication.internal-api-key}")
    private String internalApiKey;

    @Autowired @Qualifier("authDataSource")
    private DataSource dataSource;


    @Autowired
    private CustomerUserDetailService userDetailService;

    @Autowired @Qualifier("passwordEncoder")
    PasswordEncoder passwordEncoder;



    @Bean
    public UserDetailsManager users(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
//                .passwordEncoder(passwordEncoder()) // i remove it, because it is in the add Dao Provider
                .and()
                .authenticationProvider(authenticationProvider())
                .build();

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
        return jdbcUserDetailsManager;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf()
                .disable();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .requestMatchers("/api/authentication/**").permitAll()
//                .requestMatchers(HttpMethod.GET,"/api/transaction").permitAll()
//                .requestMatchers(HttpMethod.GET,"/categories/**").permitAll() // will not require auth
                .requestMatchers(HttpMethod.GET,"/api/shop/**").permitAll() // will not require auth
//                .requestMatchers(HttpMethod.POST,"/api/shop/**").permitAll() // will not require auth
                .requestMatchers("/api/transaction/**").hasRole(Role.ADMIN.name())
                .requestMatchers("/api/internal/**").hasRole(Role.SYSTEM_MANAGER.name())
                .anyRequest()
                .authenticated();
//         jwt filter
//        internal > jwt > authentication >
        http.addFilterBefore(jwtAuthorizationFilter(),
                UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(internalApiAuthenticationFilter(),
                        JwtAuthorizationFilter.class);
        return http.build();
    }

    @Bean
    public InternalApiAuthenticationFilter internalApiAuthenticationFilter() {
        return new InternalApiAuthenticationFilter(internalApiKey);
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
//        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }














}












