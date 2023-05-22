package ecust.cn.securitytutorial;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@MapperScan("ecust.cn.securitytutorial.mapper")
public class SecurityTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityTutorialApplication.class, args);
    }

}
