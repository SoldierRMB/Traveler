package com.soldiersoft.traveler.model.vo;

import com.soldiersoft.traveler.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import static com.soldiersoft.traveler.enums.StatusCodeEnum.FAIL;
import static com.soldiersoft.traveler.enums.StatusCodeEnum.SUCCESS;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class ResultVO<T> {
    private Boolean flag;

    private Integer code;

    private String message;

    private T data;

    public static <T> ResultVO<T> ok() {
        return resultVO(true, SUCCESS.getCode(), SUCCESS.getDesc(), null);
    }

    public static <T> ResultVO<T> ok(T data) {
        return resultVO(true, SUCCESS.getCode(), SUCCESS.getDesc(), data);
    }

    public static <T> ResultVO<T> ok(T data, String message) {
        return resultVO(true, SUCCESS.getCode(), message, data);
    }

    public static <T> ResponseEntity<ResultVO<T>> fail() {
        return ResponseEntity
                .status(FAIL.getCode())
                .body(resultVO(false, FAIL.getCode(), FAIL.getDesc(), null));
    }

    public static <T> ResponseEntity<ResultVO<T>> fail(StatusCodeEnum statusCodeEnum) {
        return ResponseEntity
                .status(statusCodeEnum.getCode())
                .body(resultVO(false, statusCodeEnum.getCode(), statusCodeEnum.getDesc(), null));
    }

    public static <T> ResponseEntity<ResultVO<T>> fail(String message) {
        return ResponseEntity
                .status(FAIL.getCode())
                .body(resultVO(false, FAIL.getCode(), message, null));
    }

    public static <T> ResponseEntity<ResultVO<T>> fail(T data) {
        return ResponseEntity
                .status(FAIL.getCode())
                .body(resultVO(false, FAIL.getCode(), FAIL.getDesc(), data));
    }

    public static <T> ResponseEntity<ResultVO<T>> fail(T data, String message) {
        return ResponseEntity
                .status(FAIL.getCode()).body(resultVO(false, FAIL.getCode(), message, data));
    }

    public static <T> ResponseEntity<ResultVO<T>> fail(Integer code, String message) {
        return ResponseEntity
                .status(code)
                .body(resultVO(false, code, message, null));
    }

    private static <T> ResultVO<T> resultVO(Boolean flag, String message) {
        return ResultVO.<T>builder()
                .flag(flag)
                .code(flag ? SUCCESS.getCode() : FAIL.getCode())
                .message(message).build();
    }

    private static <T> ResultVO<T> resultVO(Boolean flag, Integer code, String message, T data) {
        return ResultVO.<T>builder()
                .flag(flag)
                .code(code)
                .message(message)
                .data(data).build();
    }
}