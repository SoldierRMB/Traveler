package com.soldiersoft.traveler.exception;

import com.soldiersoft.traveler.model.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.soldiersoft.traveler.enums.StatusCodeEnum.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public ResultVO<Exception> handleBizException() {
        return ResultVO.fail(FAIL);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO<Exception> handleIllegalArgumentException() {
        return ResultVO.fail(BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultVO<Exception> handleNotFoundException() {
        return ResultVO.fail(NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultVO<Exception> handleRuntimeException() {
        return ResultVO.fail(INTERNAL_SERVER_ERROR);
    }
}
