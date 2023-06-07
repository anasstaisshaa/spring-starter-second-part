package com.dmdev.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Slf4j
@Order(1)
public class FirstAspect {


    @Pointcut("this(org.springframework.data.repository.Repository))")
    public void isRepositoryLayer(){
    }

    @Pointcut("com.dmdev.spring.aop.CommonPointcuts.isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping(){
    }

    @Pointcut("com.dmdev.spring.aop.CommonPointcuts.isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelParam(){
    }

    @Pointcut("bean(*Service)")
    public void isServiceLayerBean(){
    }

    @Pointcut("execution(public * com.dmdev.spring.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod(){
    }

    @Before(value = "anyFindByIdServiceMethod() " +
            "&& args(id) " +
            "&& target(service) " +
            "&& this(serviceProxy) " +
            "&& @within(transactional)", argNames = "joinPoint,id,service,serviceProxy,transactional")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional){
        log.info("invoked findById method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "anyFindByIdServiceMethod() " +
            "&& target(service)",
            returning = "result")
    public void addLoggingAfterReturning(Object result, Object service){
        log.info("after returning - invoked findById method in class {}, result {}", service, result);
    }

    @AfterThrowing(value = "anyFindByIdServiceMethod() " +
            "&& target(service)",
            throwing = "ex")
    public void addLoggingAfterThrowing(Throwable ex, Object service){
        log.info("after throwing - invoked findById method in class {}, exception {}: {}", service, ex.getClass(), ex.getMessage());
    }

    @After("anyFindByIdServiceMethod() " +
            "&& target(service)")
    public void addLoggingAfterFinally(Object service){
        log.info("after (finally) - invoked findById method in class {}", service);
    }



}
