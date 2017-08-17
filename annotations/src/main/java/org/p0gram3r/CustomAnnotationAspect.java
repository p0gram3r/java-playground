package org.p0gram3r;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class CustomAnnotationAspect {

    @Around("@annotation(org.p0gram3r.CustomAnnotation) && execution(* *(..))")
    public Object timedAndMeteredException(ProceedingJoinPoint joinPoint) throws Throwable {
        final CustomAnnotation annotation = fetchAnnotation(joinPoint, CustomAnnotation.class);

        if (annotation == null) {
            return joinPoint.proceed();
        }

        String prefix = annotation.prefix();
        try {
            System.out.println(prefix + "start processing CustomAnnotation");
            return joinPoint.proceed();
        } finally {
            System.out.println(prefix + "finished processing CustomAnnotation");
        }
    }

    private <T extends Annotation> T fetchAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            return method.getAnnotation(annotationClass);
        } catch (Exception e) {
            return null;
        }
    }
}
