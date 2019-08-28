/**
 * Copyright(C) 2019 Hangzhou zhaoyunxing Technology Co., Ltd. All rights reserved.
 */
package io.github.sunny.core.adapter;

import io.github.sunny.bean.WebBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhaoyunxing
 * @date: 2019-08-27 17:49
 * @des:
 */
@ConditionalOnClass(WebBean.class)
public class WebMvcConfigurerAdapter implements WebMvcConfigurer {
}
