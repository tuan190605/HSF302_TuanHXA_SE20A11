package org.example.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Aspect
public class LoggingAspect {
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Before("execution(* org.example.services.StudentServiceImpl.save(..))")
    public void logBefore() {
        logger.debug("LoggingAspect: Before method Save Student()");
        System.out.println("LoggingAspect: Before method Save Student()");
    }

    @After("execution(* org.example.services.StudentServiceImpl.save(..))")
    public void logAfter() {
        logger.debug("LoggingAspect: After method Save Student()");
        System.out.println("LoggingAspect: After method Save Student()");
    }
}
