/**
 * Copyright(C) 2019 Hangzhou zhaoyunxing Technology Co., Ltd. All rights reserved.
 */
package io.github.sunny.core.msg;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoyunxing
 * @date: 2019-08-27 16:44
 * @des:
 */
@Data
@AllArgsConstructor
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 6280855035125657876L;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;
    /**
     * 是否成功
     */
    private Boolean success;

    private Response() {
    }

    public static Response createSuccess(String msg) {
        return createSuccess(msg, (Object) null);
    }

    public static Response createSuccess(String msg, Object... args) {
        return createFormatMsg(msg, null, true, args);
    }

    public static Response createError(String msg, Integer code) {
        return createFormatMsg(msg, code, null, false);
    }

    private static Response createFormatMsg(String msg, Object data, Boolean success, Object... args) {
        return new Response<>(String.format(msg, args), data, success);
    }
}
