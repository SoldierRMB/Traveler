package com.soldiersoft.traveler.exception;

import com.soldiersoft.traveler.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.soldiersoft.traveler.enums.StatusCodeEnum.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public ResponseEntity<ResultVO<Exception>> handleBizException(BizException e) {
        return ResponseEntity.status(FAIL.getCode()).body(ResultVO.fail(FAIL.getCode(), e.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResultVO<Exception>> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.status(FAIL.getCode()).body(ResultVO.fail(FAIL.getCode(), e.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO<Exception> handleMissingServletRequestParameterException() {
        return ResultVO.fail(BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultVO<Exception> handleAuthenticationException() {
        return ResultVO.fail(UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO<Exception> handleAccessDeniedException() {
        return ResultVO.fail(FORBIDDEN);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO<Exception> handleHttpMessageNotReadableException() {
        return ResultVO.fail(BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultVO<Exception> handleMethodNotSupportedException() {
        return ResultVO.fail(METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultVO<Exception> handleNotFoundException() {
        return ResultVO.fail(NOT_FOUND);
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<Exception> handleRedisConnectionFailureException() {
        return ResultVO.fail(INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<Exception> handleRuntimeException(Exception e, Throwable cause) {
        log.error("RuntimeException: {}", e.getMessage(), cause);
        return ResultVO.fail(INTERNAL_SERVER_ERROR);
    }
}
