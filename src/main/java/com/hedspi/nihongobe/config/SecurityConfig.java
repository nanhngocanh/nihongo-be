package com.hedspi.nihongobe.config;

import com.hedspi.nihongobe.jwt.CustomJwtConverter;
import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final ErrorHandler errorHandler;
//
//    public SecurityConfig(final ErrorHandler errorHandler) {
//        this.errorHandler = errorHandler;
//    }
//
//    @Bean
//    protected SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
//        http.cors(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(r -> r.anyRequest().authenticated())
//                .exceptionHandling(e -> e.authenticationEntryPoint(errorHandler))
//                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2ResourceServer(
//                        httpSecurityOAuth2ResourceServerConfigurer ->
//                                httpSecurityOAuth2ResourceServerConfigurer.jwt(
//                                        jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtConverter())));
//
//        return http.build();
//    }
//
//    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtConverter() {
//        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new CustomJwtConverter());
//        return jwtAuthenticationConverter;
//    }
//}

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/**").fullyAuthenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                )
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
		        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak() {
        Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt -> {
            Object access = jwt.getClaim("realm_access");

            LinkedTreeMap<String, List<String>> clientRoleMap = (LinkedTreeMap<String, List<String>>) access;

            List<String> clientRoles = new ArrayList<>(clientRoleMap.get("roles"));

            return clientRoles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        };

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }
}