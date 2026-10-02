package springAOP.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class LogException implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        System.out.println("ERROR in " + method.getName() + ": " + ex.getMessage());
    }
}
