package uts.sender;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "uts.sender.mapper")
@ComponentScan(basePackages = {"uts.sender.*", "uts.sender.config.*"})
public class MainConfig {
}
