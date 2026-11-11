package com.craft.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ComponentScan          finds the service and the aspects
 * @EnableAspectJAutoProxy turns on AOP: Spring wraps matching beans in a proxy
 */
@Configuration
@ComponentScan("com.craft.aop")
@EnableAspectJAutoProxy
public class AppConfig {
}
