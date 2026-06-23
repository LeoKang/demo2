package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrintExecutionTimeAspect {

    @Before("@annotation(PrintExecutionTime)")
    public void beforePrintExecutionTime(JoinPoint joinPoint) {
        System.out.println("do something before " + joinPoint.toShortString() + " with " + joinPoint.getArgs().length + " args. ");
    }

    @Around("@annotation(PrintExecutionTime)")
    public Object printExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        var object = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println("executed " + joinPoint.toShortString() + " with " + joinPoint.getArgs().length + " args in " + executionTime + "ms.");
        return object;
    }
}
