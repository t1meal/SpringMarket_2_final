package ru.gb.market.core.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.gb.market.core.utils.StatisticUtil;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ServiceRuntimeAspect {



    @Around("execution(* ru.gb.market.core.services.*.* (..))")
    public Object timeAround(ProceedingJoinPoint joinPoint) {

        try {
            Long start = System.currentTimeMillis();
            Object out =  joinPoint.proceed();
            Long end = System.currentTimeMillis();
            StatisticUtil.add(joinPoint.getTarget().getClass(), end - start);
            return out;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
