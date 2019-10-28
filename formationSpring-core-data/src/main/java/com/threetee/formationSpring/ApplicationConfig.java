package com.threetee.formationSpring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages = "com.threetee.formationSpring")
public class ApplicationConfig {

}
