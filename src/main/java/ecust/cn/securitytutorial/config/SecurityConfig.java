package ecust.cn.securitytutorial.config;


import com.sun.org.apache.xpath.internal.operations.And;
import ecust.cn.securitytutorial.service.MyLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MyLoginService myLoginService;

    //    主要是配置Spring Security中的过滤器链
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.logout().logoutUrl("/user/logout").logoutSuccessUrl("/index").permitAll();

        httpSecurity.exceptionHandling().accessDeniedPage("/NoPerimission.html");
        httpSecurity.formLogin().loginPage("/login.html").loginProcessingUrl("/user/login").defaultSuccessUrl("/test/index").permitAll()
                .and().authorizeRequests().antMatchers("/test/hello1").authenticated()
                .and().authorizeRequests().antMatchers("/login").permitAll()
                .and().rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60).userDetailsService(myLoginService)
                .and().csrf().disable();
        return httpSecurity.build();
    }

    //主要是配置一些路径放行规则。
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return x -> {
            x.ignoring().antMatchers("/test1/hello1");
        };
    }

    //    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    /**
     * 密码处理
     */


}
