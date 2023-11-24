package com.sunxx.handler;

import com.sunxx.exception.BizException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 方法参数解析失败
     * 400 - Bad Request
     *
     * @param request request
     * @param e       e
     * @return 返回值
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String httpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException e) {
        log.error("httpMessageNotReadableException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
        return "系统错误，资源不存在";
    }

    /**
     * 参数缺失
     *
     * @param request request
     * @param e       e
     * @return 返回值
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String missingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException e) {
        log.error("missingServletRequestParameterException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
        return "系统错误，缺少请求参数";
    }

    /**
     * 参数类型不匹配
     *
     * @param request request
     * @param e       e
     * @return 返回值
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String methodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        log.error("methodArgumentTypeMismatchException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
        return "系统错误，方法参数类型不匹配";
    }

    /**
     * spring 方法参数校验失败
     *
     * @param request request
     * @param e       e
     * @return 返回值
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
        String result = e.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + error.getDefaultMessage())
                .collect(Collectors.joining("，"));
        return "系统错误，请求数据验证失败";
    }

    /**
     * 资源不存在
     * 404 NO Found
     *
     * @param request request
     * @param e       e
     * @return 返回值
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        log.error("noHandlerFoundException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
        return "系统错误，资源不存在";
    }

    /**
     * 请求方式不支持
     * 405 - Method Not Allowed
     *
     * @param request request
     * @param e       e
     * @return 返回值
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String httpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        log.error("httpRequestMethodNotSupportedException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
        return "系统错误，请求方式不支持";
    }

    /**
     * 媒体类型异常
     * 415 - Unsupported Media Type
     *
     * @param request request
     * @param e       e
     * @return 返回值
     */
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public String httpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException e) {
        log.error("httpMediaTypeNotSupportedException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
        return "系统错误，媒体类型异常";
    }

//    /**
//     * Hibernate validate 参数校验错误
//     * TODO 后续查询
//     *
//     * @param request request
//     * @param e       e
//     * @return 返回值
//     */
//    @ExceptionHandler(ValidationException.class)
//    public Result<?, ?> validationException(HttpServletRequest request, ValidationExc
//    eption e) {
//        log.error("ValidationException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
//        return Result.error(419, e.getMessage(), e.getMessage());
//    }
//
//    /**
//     * 参数转换异常
//     */
//    @ExceptionHandler(value = HttpMessageConversionException.class)
//    private Result<?, ?> httpMessageConversionException(HttpServletRequest request, HttpMessageConversionException e) {
//        log.error("handleHttpMessageConversionException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
//        return Result.error(420, e.getMessage(), e.getMessage());
//    }
//
//
//    /**
//     * 限流异常
//     */
//    @ExceptionHandler(value = RequestNotPermitted.class)
//    private <T> Result<?, ?> requestNotPermitted(HttpServletRequest request, RequestNotPermitted e) {
//        log.error("RequestNotPermitted url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
//        return Result.error(421, "系统繁忙，请稍后再试", e.getMessage());
//    }

    // /**
    //  * 认证失败
    //  *
    //  * @param request request
    //  * @param e       e
    //  * @return 返回值
    //  */
    // @ExceptionHandler(AccessDeniedException.class)
    // public Result<?, ?> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
    //     log.error("handleAccessDeniedException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
    //     String message = messageSourceUtils.getMessage(ACCESS_DENIED.name());
    //     return Result.error(ACCESS_DENIED.value(), message, e.getMessage());
    // }


    // /**
    //  * 限流异常
    //  */
    // @ExceptionHandler(value = AccessDeniedException.class)
    // @ResponseStatus(HttpStatus.OK)
    // private <T> BaseResEntity<T> accessDeniedExceptionHandler(Exception e) {
    //     log.error("---------> AccessDenied  Exception!", e);
    //     return BaseResEntity.<T>builder()
    //             .code(ResEnum.FORBIDDEN.getCode())
    //             .message(ResEnum.FORBIDDEN.getMsg()).build();
    // }


    /**
     * 用户帐号已被锁定
     * 用于 spring security 认证
     */
    @ExceptionHandler(LockedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String lockedException(HttpServletRequest request, LockedException e) {
        log.error("lockedException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
        return e.getLocalizedMessage();
    }

    /**
     * 属性不存在或无法访问
     */
    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String propertyReferenceException(HttpServletRequest request, PropertyReferenceException e) {
        log.error("propertyReferenceException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
        return "系统错误，属性不存在或无法访问";
    }

    /**
     * 业务逻辑异常
     *
     * @param request request
     * @param e       e
     * @return 返回值
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String serviceException(HttpServletRequest request, BizException e) {
        log.error("serviceException url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
        return "系统错误，" + e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(HttpServletRequest request, Exception e) {
        log.error("exception url:{},message:{},e:", request.getRequestURI(), e.getMessage(), e);
        return "系统错误，服务器内部错误";
    }
}
