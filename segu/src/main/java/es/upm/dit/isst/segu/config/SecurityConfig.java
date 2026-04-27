package es.upm.dit.isst.segu.config;

import org.springframework.security.config.Customizer;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http.authorizeHttpRequests(auth -> {
        //el orden es por prioridad, el primero que coincida se aplica. Por eso el "/todos" va al final, porque coincide con todo (Tanto alumnos como profesores)
        auth.requestMatchers("/").permitAll();
        auth.requestMatchers("/alumnos").hasRole("ALUM");
        auth.requestMatchers("/profesores").hasRole("PROF");
        auth.requestMatchers("/todos").authenticated();
        }
    ).formLogin(Customizer.withDefaults()) .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")))
    .httpBasic(Customizer.withDefaults());
    return http.build();
    }

    //Configuración de usuarios en base de datos
     @Bean
     public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
       String usersByUsernameQuery = "select username, password, enabled from users where username = ?";
       String authsByUserQuery = "select username, authority from authorities where username = ?";          
       JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);    
       users.setUsersByUsernameQuery(usersByUsernameQuery);
       users.setAuthoritiesByUsernameQuery(authsByUserQuery);
       return users;
     }
}