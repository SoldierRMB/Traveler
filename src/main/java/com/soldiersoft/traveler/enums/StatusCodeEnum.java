package com.soldiersoft.traveler.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    SUCCESS(200, "操作成功"),

    FAIL(210, "操作失败"),

    BAD_REQUEST(400, "请求参数错误"),

    UNAUTHORIZED(401, "授权失败"),

    FORBIDDEN(403, "禁止访问"),

    NOT_FOUND(404, "资源未找到"),

    METHOD_NOT_ALLOWED(405, "请求方法不支持"),

    INTERNAL_SERVER_ERROR(500, "系统异常");

    private final Integer code;

    private final String desc;
}
