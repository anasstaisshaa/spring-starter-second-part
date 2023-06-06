package com.dmdev.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class FirstAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer(){
    }

    @Pointcut("within(com.dmdev.spring.service.*Service)")
    public void isServiceLayer(){
    }

    @Pointcut("this(org.springframework.data.repository.Repository))")
//    @Pointcut("target(org.springframework.data.repository.Repository))")
    public void isRepositoryLayer(){
    }

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping(){
    }

    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelParam(){
    }

    @Pointcut("bean(*Service)")
    public void isServiceLayerBean(){
    }

    @Pointcut("execution(public * com.dmdev.spring.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod(){
    }

    @Before("anyFindByIdServiceMethod()")
    public void addLogging(){
        log.info("invoked findById method");
    }

}
