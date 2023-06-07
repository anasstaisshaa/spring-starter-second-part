package com.dmdev.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(2)
public class SecondAspect {
    @Around(value = "com.dmdev.spring.aop.FirstAspect.anyFindByIdServiceMethod() && target(service) && args(id)", argNames = "proceedingJoinPoint,service,id")
    public Object addLoggingAround(ProceedingJoinPoint proceedingJoinPoint, Object service, Object id) throws Throwable {
        log.info("AROUND invoked findById method in class {}, with id {}", service, id);

        try{
            Object result = proceedingJoinPoint.proceed();
            log.info("AROUND after returning - invoked findById method in class {}, result {}", service, result);
            return result;
        } catch (Throwable ex){
            log.info("AROUND after throwing - invoked findById method in class {}, exception {}: {}", service, ex.getClass(), ex.getMessage());
            throw ex;
        } finally {
            log.info("AROUND after (finally) - invoked findById method in class {}", service);
        }
    }
}
