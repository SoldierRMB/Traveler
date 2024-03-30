package com.soldiersoft.traveler.exception;

import com.soldiersoft.traveler.enums.StatusCodeEnum;
import com.soldiersoft.traveler.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.soldiersoft.traveler.enums.StatusCodeEnum.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public ResponseEntity<ResultVO<StatusCodeEnum>> handleBizException(BizException e) {
        return ResultVO.fail(FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResultVO<StatusCodeEnum>> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResultVO.fail(FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResultVO<StatusCodeEnum>> handleMissingServletRequestParameterException() {
        return ResultVO.fail(BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResultVO<StatusCodeEnum>> handleAccessDeniedException() {
        return ResultVO.fail(FORBIDDEN);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultVO<StatusCodeEnum>> handleHttpMessageNotReadableException() {
        return ResultVO.fail(BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResultVO<StatusCodeEnum>> handleMethodNotSupportedException() {
        return ResultVO.fail(METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResultVO<StatusCodeEnum>> handleNotFoundException() {
        return ResultVO.fail(NOT_FOUND);
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseEntity<ResultVO<StatusCodeEnum>> handleRedisConnectionFailureException() {
        return ResultVO.fail(INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResultVO<StatusCodeEnum>> handleRuntimeException(Exception e, Throwable cause) {
        log.error("RuntimeException: {}", e.getMessage(), cause);
        return ResultVO.fail(INTERNAL_SERVER_ERROR);
    }
}
