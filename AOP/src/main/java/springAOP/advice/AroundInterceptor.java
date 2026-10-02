package springAOP.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        long start = System.currentTimeMillis();
        System.out.println("START: " + invocation.getMethod().getName());
        try {
            result = invocation.proceed();
        }
        finally {
            System.out.println("END: took " + (System.currentTimeMillis() - start) + "ms");
        }
        return result;
    }
}
