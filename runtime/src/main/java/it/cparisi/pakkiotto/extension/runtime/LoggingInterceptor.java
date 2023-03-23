package it.cparisi.pakkiotto.extension.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

@Log
@Interceptor
public class LoggingInterceptor {
    @AroundInvoke
    Object around(InvocationContext context) throws Throwable {
        Method method = context.getMethod();
        Logger logger = LoggerFactory.getLogger(method.getDeclaringClass());
        logger.info("Entering {}.{}",
                method.getDeclaringClass().getSimpleName(),
                method.getName());
        return context.proceed();
    }
}
