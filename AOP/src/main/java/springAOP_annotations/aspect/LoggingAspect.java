package springAOP_annotations.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class LoggingAspect {


//    @Before("execution(* springAOP_annotations.service.InventoryService.*(..))")
//    public void before() {
//        System.out.println("Before method");
//    }
//
//    @AfterReturning(
//            pointcut = "execution(* springAOP_annotations.service.InventoryService.checkStock(..))",
//            returning = "result"
//    )
//    public void afterReturning(Object result) {
//        System.out.println("Returned: " + result);
//    }
//
//    @AfterThrowing(
//            pointcut = "execution(* springAOP_annotations.service.InventoryService.reserveStock(..))",
//            throwing = "ex"
//    )
//    public void afterThrowing(IllegalStateException ex) {
//        System.out.println("Exception: " + ex.getMessage());
//    }
//
//    @After("execution(* springAOP_annotations.service.InventoryService.*(..))")
//    public void after() {
//        System.out.println("After method");
//    }

    @Around("execution(* springAOP_annotations.service.InventoryService.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Before");

        try {
            Object result = joinPoint.proceed();
            System.out.println("Returned: " + result);
            return result;

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            throw ex;

        } finally {
            System.out.println("After");
        }
    }

}
