package edu.miu.cs.cs489appsd.security.config;

import edu.miu.cs.cs489appsd.security.user.Permission;
import edu.miu.cs.cs489appsd.security.user.Role;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable) //we are working on this method ourselves so dont worry about csrf
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                                .requestMatchers("/api/v1/auth/*").permitAll() // * means auth/anything will be whitelisted
                                .requestMatchers("/api/v1/members/**").hasAnyRole(Role.MEMBER.name(), Role.ADMIN.name())
                                .requestMatchers("/api/v1/admin").hasAnyRole(Role.ADMIN.name())
                                .requestMatchers("/api/v1/members/admin-write").hasAuthority(
                                Permission.ADMIN_WRITE.getPermission()
                                )
                                .anyRequest().authenticated()
                        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS
                                )
                );
        return http. build();
    }
}
