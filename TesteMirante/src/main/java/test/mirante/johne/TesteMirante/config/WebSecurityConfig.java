package test.mirante.johne.TesteMirante.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import test.mirante.johne.TesteMirante.enums.RolesEnum;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors()
        .and()
        .csrf().disable()
        .authorizeRequests()
		.antMatchers("/", "/login").permitAll()
		.antMatchers("/", "/logout").permitAll()
        .antMatchers(HttpMethod.GET, "/clientes/**").hasAnyRole(RolesEnum.ADMIN.name(), RolesEnum.USER.name())
        .antMatchers(HttpMethod.POST, "/clientes/**").hasAnyRole(RolesEnum.ADMIN.name())
        .antMatchers(HttpMethod.PUT, "/clientes/**").hasAnyRole(RolesEnum.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/clientes/**").hasAnyRole(RolesEnum.ADMIN.name())
        .anyRequest().authenticated()
        .and()
        .headers().frameOptions().sameOrigin()
        .and()
        .httpBasic();
    }



    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        UserDetails theUser = User.withUsername("admin")
                        .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                        .password("123456").roles(RolesEnum.ADMIN.name()).build();

        UserDetails theManager = User.withUsername("comum")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("123456").roles(RolesEnum.USER.name()).build();


        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(theUser);
        userDetailsManager.createUser(theManager);

        return userDetailsManager;
    }

}
