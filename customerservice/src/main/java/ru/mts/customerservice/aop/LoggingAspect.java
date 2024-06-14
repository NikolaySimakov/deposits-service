package ru.mts.customerservice.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.mts.starter.annotation.Logging;
import ru.mts.starter.enums.LoggingLevel;

@Component
@Aspect
public class LoggingAspect {

    private static final String ENTER = ">>";
    private static final String EXIT = "<<";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("@annotation(loggingAnnotation) && execution(* ru.mts..*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint, Logging loggingAnnotation) throws Throwable {
        Logger logger = LogManager.getLogger(joinPoint.getTarget());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        if (loggingAnnotation == null) {
            logger.error("logging annotation is null");
            return joinPoint.proceed();
        }

        String message = loggingAnnotation.value().isEmpty() ? joinPoint.getSignature().getName()
                : loggingAnnotation.value();

        if (loggingAnnotation.enter()) {
            log(String.format("%s %s: %s", ENTER, methodName, message), loggingAnnotation.level(), logger);
        }

        if (loggingAnnotation.includeParams()) {
            log(String.format("%s %s params: %s", ENTER, methodName,
                            objectMapper.writeValueAsString(joinPoint.getArgs())),
                    loggingAnnotation.level(), logger);
        }

        Object result = joinPoint.proceed();

        if (loggingAnnotation.includeResult()) {
            log(String.format("%s %s result: %s", EXIT, methodName, objectMapper.writeValueAsString(result)), loggingAnnotation.level(), logger);
        }

        if (loggingAnnotation.exit()) {
            log(String.format("%s %s: %s", EXIT, methodName, message), loggingAnnotation.level(), logger);
        }

        return result;
    }

    private void log(String message, LoggingLevel loggingLevel, Logger logger) {
        switch (loggingLevel) {
            case INFO -> logger.info(message);
            case WARN -> logger.warn(message);
            case DEBUG -> logger.debug(message);
            case ERROR -> logger.error(message);
            case FATAL -> logger.fatal(message);
            case TRACE -> logger.trace(message);
        }
    }
}
