package com.soldiersoft.traveler.exception;

import lombok.EqualsAndHashCode;

import java.util.Arrays;

@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {
    private String message;
    private Object[] args;

    public BizException(String message) {
        super(message);
        this.message = message;
    }

    public BizException(Object... args) {
        this.args = args;
    }

    public BizException(String message, Object... args) {
        super(message);
        this.message = message;
        this.args = args;
    }

    @Override
    public String toString() {
        if (this.args != null && this.args.length > 0)
            return "BizException{" +
                    "message='" + message + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        return "BizException{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
