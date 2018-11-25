package xin.chung.feign_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableEurekaClient //开启Eureka发现
public class FeignProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignProviderApplication.class, args);
    }
}
