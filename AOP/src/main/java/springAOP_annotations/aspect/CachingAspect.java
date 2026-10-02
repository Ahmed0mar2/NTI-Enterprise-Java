package springAOP_annotations.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CachingAspect {

    private final Map<Object, Object> cache = new HashMap<>();

    @Around("@annotation(springAOP_annotations.annotation.Cacheable)")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {

        Object key = joinPoint.getArgs()[0];

        if (cache.containsKey(key)) {
            System.out.println("Cache hit for: " + key);
            return cache.get(key);
        }

        System.out.println("Cache miss for: " + key);

        Object result = joinPoint.proceed();

        cache.put(key, result);

        return result;
    }
}