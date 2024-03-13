package com.soldiersoft.traveler.exception;

import com.soldiersoft.traveler.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
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
    public ResultVO<Exception> handleBizException(BizException e) {
        return ResultVO.fail(FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResultVO<Exception> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResultVO.fail(FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVO<Exception> handleMissingServletRequestParameterException() {
        return ResultVO.fail(BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVO<Exception> handleMethodNotSupportedException() {
        return ResultVO.fail(METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultVO<Exception> handleNotFoundException() {
        return ResultVO.fail(NOT_FOUND);
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResultVO<Exception> handleRedisConnectionFailureException() {
        return ResultVO.fail(INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultVO<Exception> handleRuntimeException(Exception e, Throwable cause) {
        log.error("RuntimeException: {}", e.getMessage(), cause);
        return ResultVO.fail(INTERNAL_SERVER_ERROR);
    }
}
