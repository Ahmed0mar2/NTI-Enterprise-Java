package springAOP;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springAOP.advice.LoggingBeforeAdvice;
import springAOP.config.AppConfig;
import springAOP.service.InventoryService;
import springAOP.service.InventoryServiceImpl;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        InventoryService service = context.getBean(InventoryService.class);
        ProxyFactory factory = new ProxyFactory();

        factory.setTarget(new InventoryServiceImpl());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("reserveStock");

        Advisor advisor =
                new DefaultPointcutAdvisor(
                        pointcut,
                        new LoggingBeforeAdvice()
                );

        factory.addAdvisor(advisor);

        InventoryService proxy =
                (InventoryService) factory.getProxy();

        proxy.checkStock("ABC");
        proxy.reserveStock("ABC", 50);

        proxy.reserveStock("ABC", 150);
    }
}
