package com.threetee.formationSpring.aspects;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.threetee.formationSpring.exception.UnexpectedException;
import com.threetee.formationSpring.services.UserService;




@Aspect
@Component
public class UserRepoMonitor {

    private static final Logger logger = Logger.getLogger(UserRepoMonitor.class);

    @Before("com.threetee.formationSpring.aspects.PointcutContainer.serviceUpdate(service, id, pass)")
    public void beforeServiceUpdate(UserService service, Long id, String pass) throws Throwable {
        logger.info(" ---> Target object " + service.getClass());

        if (StringUtils.indexOfAny(pass, new String[]{"$", "#", "$", "%"}) != -1) {
            throw new IllegalArgumentException("Text for " + id + " contains weird characters!");
        }
    }

    @AfterReturning(value="execution (* com.threetee.formationSpring.services.*Service+.update*(..))", returning = "result")
    public void afterServiceUpdate(JoinPoint joinPoint, int result) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        if(result == 0) {
            logger.info(" ---> Update method " + className + "." + methodName + " performed as expected.");
        }
    }


    @AfterThrowing(value="execution ( * com.threetee.formationSpring.services.*Service+.updateUsername(..))", throwing = "e")
    public void afterBadUpdate(JoinPoint joinPoint, Exception e) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        if(e instanceof DuplicateKeyException) {
            logger.info(" ---> Update method " + className + "." + methodName + " failed. Existing username found.");
        } else {
            throw new UnexpectedException(" Ooops!", e);
        }
    }

    @Before("com.threetee.formationSpring.aspects.PointcutContainer.repoUpdate()")
    public void beforeRepoUpdate(JoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + className + "." + methodName + " is about to be called");
    }

    @Around("execution(public * com.threetee.formationSpring.dao.*.*Repo+.find*(..))")
    public Object monitorFind(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Intercepting call of: " + methodName);
        long t1 = System.currentTimeMillis();
        try {
            //put a pause here so we can register an execution time
            Thread.sleep(1000L);
            return joinPoint.proceed();
        } finally {
            long t2 = System.currentTimeMillis();
            logger.info(" ---> Execution of " + methodName + " took: " + (t2 - t1) / 1000 + " ms.");
        }
    }

    private static long findByIdCount = 0;

    @After("execution(public * com.threetee.formationSpring.sao.*.JdbcUserRepo+.updateUsername(..))")
    public void afterUpdateUsername(JoinPoint joinPoint) throws Throwable {
        ++findByIdCount;
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + methodName + " was called " + findByIdCount + " times.");
    }

    @Before("com.threetee.formationSpring.aspects.PointcutContainer.proxyBubu()")
    public void bubuHappens(JoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info(" ---> BUBU when calling: " + className + "." + methodName);
    }
}
