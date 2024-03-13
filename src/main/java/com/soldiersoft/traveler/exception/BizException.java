package com.soldiersoft.traveler.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.MessageFormat;

@Data
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {
    private String msg;
    private Object[] args;

    public BizException() {
        super();
    }

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BizException(String msg, Object... args) {
        this.msg = msg;
        this.args = args;
    }

    @Override
    public String getMessage() {
        if (StrUtil.isNotBlank(getMsg())) {
            if (args != null && args.length > 0) {
                return MessageFormat.format(msg, args);
            }
        }
        return super.getMessage();
    }
}
