package com.amore.cash.config;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AOP Logging Config
 */
@Slf4j
@Component
@Aspect
public class AopLogging {

    @Around("within(com.amore.cash.controller..*)")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {

        String params = getRequestParams();

        long startAt = System.currentTimeMillis();
        log.info("===================== REQUEST : {}({}) = {}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), params);

        Object result = pjp.proceed();
        long endAt = System.currentTimeMillis();

        log.info("===================== RESPONSE : {}({}) = {} ({}ms)", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), result, endAt-startAt);

        return result;
    }

    private String getRequestParams() {

        String params=null;
        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();

        if(requestAttribute != null){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            Map<String, String[]> paramMap = request.getParameterMap();

            if (!paramMap.isEmpty()) {
                params = " [" + paramMapToString(paramMap) + "]";
            }
        }
        return params;
    }

    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)", entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }
}
