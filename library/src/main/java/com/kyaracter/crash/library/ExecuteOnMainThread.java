package com.kyaracter.crash.library;

import android.os.Looper;
import android.util.Log;

import com.kyaracter.crash.library.exception.ExecuteOnWorkerThreadException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;

@Aspect
public class ExecuteOnMainThread {
    private void execute(JoinPoint joinPoint) {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        Class<?> cls = codeSignature.getDeclaringType();
        String methodName = codeSignature.getName();

        Log.v(getTag(cls), "\u21E2 " + methodName);
    }

    private String getTag(Class<?> cls) {
        if (cls.isAnonymousClass()) {
            return getTag(cls.getEnclosingClass());
        }

        return cls.getSimpleName();
    }

    @Pointcut("within(@android.support.annotation.MainThread *)")
    public void withinAnnotatedClass() {
    }

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {
    }

    @Pointcut("execution(@android.support.annotation.MainThread * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    @Around("method()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        execute(joinPoint);

        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new ExecuteOnWorkerThreadException();
        }

        return joinPoint.proceed();
    }
}
