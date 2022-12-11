package com.example.demo.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import jakarta.websocket.Session;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    // final AuthService authService;
    // final RSAKeyProperties rsaKeys;

    // @Autowired
    // public SecurityConfiguration(AuthService authService) {

        // this.authService = authService;
        // this.rsaKeys = rsaKeys;

    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("filterchain");
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/admin/**", "/api/teacher/**", "/api/student/**").permitAll()
                        .anyRequest().authenticated()
                        // .and()
                        // .authenticationProvider(authenticationProvider())
                        )
                .headers(headers -> headers.frameOptions().sameOrigin())
                // .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // @Bean
    // public JwtDecoder jwtDecoder() {
    //     return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    // }

    // @Bean
    // JwtEncoder jwtEncoder() {
    //     JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
    //     JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    //     return new NimbusJwtEncoder(jwks);
    // }

    // @Bean
    // public AuthenticationProvider authenticationProvider() {
    //     final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //     authenticationProvider.setUserDetailsService(userDetailsService());
    //     authenticationProvider.setPasswordEncoder(passwordEncoder());
    //     System.out.println("authprovider");
    //     return authenticationProvider;
    // }

    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
    //         throws Exception {
    //     System.out.println("authman");
    //     return authenticationConfiguration.getAuthenticationManager();
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Bean
    // UserDetailsService userDetailsService() {
    //     return email -> {
    //         return authService.loadUserByEmail(email);
    //     };

    // }
    // // @Bean
    // // public DataSource dataSource() {
    // // return new EmbeddedDatabaseBuilder()
    // // .setType(EmbeddedDatabaseType.H2)
    // // .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
    // // .build();
    // // }

    // @Bean
    // public UserDetailsManager users(DataSource dataSource) {
    // // UserDetails user = User.withDefaultPasswordEncoder()
    // // .username("user")
    // // .password("password")
    // // .roles("USER")
    // // .build();
    // JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
    // System.out.println("datasource"+dataSource);
    // // users.createUser(user);
    // // users.
    // return users;
    // }
    // // @Bean
    // // public InMemoryUserDetailsManager userDetailsService() {
    // // UserDetails user = User.withDefaultPasswordEncoder()
    // // .username("user")
    // // .password("password")
    // // .roles("USER")
    // // .build();
    // // return new InMemoryUserDetailsManager(user);
    // // }
}
