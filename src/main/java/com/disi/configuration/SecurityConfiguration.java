package com.disi.configuration;

import com.disi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder((encoder()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors().and()
                .authorizeRequests()
                .and().authorizeRequests().antMatchers("/user/currentUser").authenticated()
                .and().authorizeRequests().antMatchers("/user/inregistrare").permitAll()
                .and().authorizeRequests().antMatchers("/user/login").permitAll()
                .and().authorizeRequests().antMatchers("/user/bucatar/get").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers("/user/bucatar/all").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers("/user/bucatar/add").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers("/user/bucatar/edit").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers("/user/bucatar/delete").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers("/menu/all").hasAnyAuthority("ADMIN","USER","BUCATAR")
                .and().authorizeRequests().antMatchers("/menu/add").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers("/menu").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers("/order/finalize").hasAuthority("BUCATAR")
                .and().authorizeRequests().antMatchers("/order/confirm").hasAuthority("USER")
                .and().authorizeRequests().antMatchers("/order/add").hasAuthority("USER")
                //.and().authorizeRequests().antMatchers("/order/currentOrder").hasAuthority("BUCATAR")
                .and().authorizeRequests().antMatchers("/order/sendOrders").hasAuthority("BUCATAR")
                //.and().authorizeRequests().antMatchers("/order/doneOrders").hasAuthority("BUCATAR")

                .and()
                .formLogin().loginPage("/login").permitAll().usernameParameter("email").defaultSuccessUrl("/user/currentUser").permitAll()
                .and()
                .logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID");
        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        //TODO
        //without security/ login is not working
//        http.csrf().disable();
//        http.cors()
//                .and().authorizeRequests()
//                .anyRequest().permitAll()
//                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type","Access-Control-Allow-Origin"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
