package springAOP.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springAOP.advice.AroundInterceptor;
import springAOP.advice.LogException;
import springAOP.advice.LoggingAfterReturningAdvice;
import springAOP.advice.LoggingBeforeAdvice;
import springAOP.service.InventoryService;
import springAOP.service.InventoryServiceImpl;

@Configuration
public class AppConfig {
//    @Bean
//    public InventoryService inventoryService() {
//        ProxyFactory factory = new ProxyFactory();
//
//        factory.setTarget(new InventoryServiceImpl());
//
//        factory.addAdvice(new LoggingBeforeAdvice());
//        factory.addAdvice(new LoggingAfterReturningAdvice());
//        factory.addAdvice(new LogException());
//        factory.addAdvice(new AroundInterceptor());
//
//        return (InventoryService) factory.getProxy();
//    }

        @Bean
    public ProxyFactoryBean inventoryService() {
        ProxyFactoryBean factory = new ProxyFactoryBean();

        factory.setTarget(new InventoryServiceImpl());

        factory.setInterceptorNames(
                "loggingAfterReturningAdvice",
                "loggingBeforeAdvice",
                "logException",
                "aroundInterceptor"
        );

        return factory;
    }
    @Bean
    public MethodBeforeAdvice loggingBeforeAdvice() {
        return new LoggingBeforeAdvice();
    }
    @Bean
    public AfterReturningAdvice loggingAfterReturningAdvice(){
        return new LoggingAfterReturningAdvice();
    }

    @Bean
    public MethodInterceptor aroundInterceptor(){
        return new AroundInterceptor();
    }

    @Bean
    public ThrowsAdvice logException(){
        return new LogException();
    }

}

