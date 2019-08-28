/**
 * Copyright(C) 2019 Hangzhou zhaoyunxing Technology Co., Ltd. All rights reserved.
 */
package io.github.sunny.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
/**
 * @author zhaoyunxing
 * @date: 2019-08-28 10:18
 * @des:
 */
@Data
@AllArgsConstructor
@ToString
public class User {

    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private String age;
}
