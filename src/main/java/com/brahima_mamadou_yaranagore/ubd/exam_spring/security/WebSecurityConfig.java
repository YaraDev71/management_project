package com.brahima_mamadou_yaranagore.ubd.exam_spring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {



    public static final String SUPER = "super";
    public static final String MANAGER = "manager";
    public static final String CHEF_PROJET = "chef_projet";
    public static final String DEVELOPER = "developper";
    public static final String DESIGNER = "designer";
    public static final String TESTER = "testeur";
    public static final String DBA = "dba";
    public static final String DEVOPS = "devops";
    public static final String RESPONSABLE = "responsable";

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        // Désactiver la protection CSRF (Cross-Site Request Forgery)
        http.csrf(AbstractHttpConfigurer::disable);

        // Configurer les autorisations pour différentes URL
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()  // Permettre l'accès à "/login" sans authentification

                .requestMatchers(new AntPathRequestMatcher("/**")).hasAuthority(SUPER)

                .requestMatchers(new AntPathRequestMatcher("/projects/**")).hasAnyAuthority("MANAGER", "CHEF_DE_PROJET", "SUPER")  // Restreindre l'accès à "/projet/**" aux rôles Manager, Chef_de_projet et Super

                .requestMatchers(new AntPathRequestMatcher("/tasks/**")).hasAnyAuthority( "CHEF_DE_PROJET", "RESPONSABLE", "SUPER")  // Restreindre l'accès à "/newclasse/**" au rôle ADMIN

                .requestMatchers(new AntPathRequestMatcher("/activities/**")).hasAnyAuthority("RESPONSABLE", "DEVEVELOPER", "DESIGNER", "TESTER", "DBA", "DEVOPS", "SUPER")  // Restreindre l'accès à "/newclasse/**" au rôle ADMIN

                .requestMatchers(new AntPathRequestMatcher("/resources/**")).hasAnyAuthority("DEV", "DESIGNER", "ROLE_TESTER", "DBA", "DEVOPS", "SUPER")  // Restreindre l'accès à "/newclasse/**" au rôle ADMIN

                .requestMatchers(new AntPathRequestMatcher("/keycloak/**")).hasAuthority(SUPER)  // Restreindre l'accès à "/keycloak/**" au rôle SUPER

                .anyRequest().authenticated());  // Exiger l'authentification pour toute autre demande

        // Configurer la gestion des sessions comme sans état (stateless)
        http.sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Configurer OAuth2 Resource Server avec JWT et définir l'URI du JWK Set (JSON Web Key Set)
        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)
                        .jwkSetUri("http://localhost:8180/realms/exam-spring-realm/protocol/openid-connect/certs")));

        return http.build();
    }
}