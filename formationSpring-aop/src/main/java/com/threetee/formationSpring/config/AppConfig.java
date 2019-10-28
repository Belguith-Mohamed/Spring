package com.threetee.formationSpring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by iuliana.cosmina on 4/17/16.
 */
@Configuration
@ComponentScan(basePackages = {"com.threetee.formationSpring"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
