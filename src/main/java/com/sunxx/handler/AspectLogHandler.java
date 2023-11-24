//package com.sunxx.handler;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.annotation.Resource;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.*;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.core.NamedThreadLocal;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Optional;
//
///**
// * 切面统一处理
// *
// * @author sunxx
// * @since 2019-8-23 08:49:10
// */
//@Slf4j
//@Aspect
//@Component
//public class AspectLogHandler {
//
//    @Resource
//    private ObjectMapper objectMapper;
//
//    private static final ThreadLocal<WebLog.WebLogBuilder> REST_LOG = new NamedThreadLocal<>("rest-log");
//    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//
//    @Pointcut(value = "execution(* com.sunxx.*.controller..*.*(..))")
//    public void log() {
//    }
//
//    @Before(value = "log()")
//    public void doBefore(JoinPoint joinPoint) {
//        WebLog.WebLogBuilder builder = WebLog.builder();
//
//        LocalDateTime localDateTime = LocalDateTime.now();
//        builder.start(localDateTime);
//
//        Optional.ofNullable(RequestContextHolder.getRequestAttributes())
//                .map(requestAttributes -> (ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                .ifPresent(attributes -> {
//                            HttpServletRequest request = attributes.getRequest();
//                            builder.ip(request.getRemoteAddr());
//                            builder.url(request.getRequestURL().toString());
//                            builder.method(request.getMethod());
//                        }
//                );
//        builder.function(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        builder.args(joinPoint.getArgs());
//        REST_LOG.set(builder);
//    }
//
//    @AfterReturning(value = "log()", returning = "response")
//    public void doAfterReturning(Object response) throws JsonProcessingException {
//        WebLog.WebLogBuilder webLogBuilder = REST_LOG.get();
//        WebLog webLog = webLogBuilder.response(objectMapper.writeValueAsString(response))
//                .end(LocalDateTime.now())
//                .build();
//        Long millis = Duration.between(webLog.getStart(), webLog.getEnd()).toMillis();
//        webLog.setTotalTime(millis);
//
//        printLogInfo(webLog);
//        REST_LOG.remove();
//    }
//
//    @AfterThrowing(value = "log()", throwing = "throwable")
//    public void doAfterThrowing(Throwable throwable) {
//        WebLog.WebLogBuilder webLogBuilder = REST_LOG.get();
//        String message = throwable.getMessage();
//        WebLog webLog = webLogBuilder.message(message).error(true).build();
//        printLogInfo(webLog);
//        REST_LOG.remove();
//    }
//
//    private void printLogInfo(WebLog webLog) {
//        log.info("------------------START---------------------");
//        log.info("START       TIME：{}", webLog.getStart().format(FORMATTER));
//        log.info("REMOTE        IP：{}", webLog.getIp());
//        log.info("REQUEST      URL：{}", webLog.getUrl());
//        log.info("REQUEST   METHOD：{}", webLog.getMethod());
//        log.info("REQUEST FUNCTION：{}", webLog.getFunction());
//        log.info("REQUEST     ARGS：{}", webLog.getArgs());
//        if (webLog.isError()) {
//            log.error("ERROR    MESSAGE：{}", webLog.getMessage());
//        } else {
//            log.info("RESP        BODY：{}", webLog.getResponse());
//            log.info("END         TIME：{}", webLog.getEnd().format(FORMATTER));
//            log.info("TOTAL       TIME：{}", webLog.getTotalTime() + "ms");
//        }
//        log.info("-------------------END----------------------");
//    }
//
//    @Setter
//    @Getter
//    @Builder
//    @ToString
//    @NoArgsConstructor
//    @AllArgsConstructor
//    static class WebLog {
//        private String ip;
//        private String url;
//        private String method;
//        private String function;
//        private Object[] args;
//        private LocalDateTime start;
//        private LocalDateTime end;
//        private String response;
//        private Long totalTime;
//        private boolean error;
//        private String message;
//    }
//}
