package springAOP.advice;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LoggingBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("LOGGING BEFORE " + method.getName() + "arguments: " + Arrays.toString(args) + "target: " + target);
    }
}
