package com.lhf.exam.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;
@EnableAspectJAutoProxy
@Aspect
public class AspectUtil {

    // 抽取公共的切入点表达式
    // 1、本类引用
    // 2、其他的切面引用
    @Pointcut("execution(public * com.lhf.exam.service.*Service.*(..))")
    public void pointCut() {
    };

    // @Before在目标方法之前切入；切入点表达式(指定在哪个方法切入)
    @Before("execution(public * com.lhf.exam.service.*Service.*(..))")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String kind = joinPoint.getKind();
        Signature signature = joinPoint.getSignature();
        SourceLocation location = joinPoint.getSourceLocation();
        JoinPoint.StaticPart part = joinPoint.getStaticPart();
        Object thisObj = joinPoint.getThis();
        Object targetObj = joinPoint.getTarget();
        System.out
                .println("" + joinPoint.getSignature().getName() + "运行。。。@Before:参数列表是：{" + Arrays.asList(args) + "}");
        System.out.println("logStart point cut method execute ......");
    }

    @After("com.lhf.exam.util.AspectUtil.pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("" + joinPoint.getSignature().getName() + "结束。。。@After");
    }


}
