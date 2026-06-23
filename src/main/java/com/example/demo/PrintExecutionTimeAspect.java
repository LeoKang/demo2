package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrintExecutionTimeAspect {
    @Around("@annotation(PrintExecutionTime)")
    public Object printExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        var object = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println("executed " + joinPoint.toShortString() + " with " + joinPoint.getArgs().length + " args in " + executionTime + "ms.");
        return object;
    }
}
