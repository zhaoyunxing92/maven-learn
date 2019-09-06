# 利用spring的@Conditional完成个性化输出

[![Build Status](https://travis-ci.com/zhaoyunxing92/maven-learn.svg?branch=master)](https://travis-ci.com/zhaoyunxing92/maven-learn)

需求: h5端要的时间格式是`yyyy-MM-dd HH:mm:ss`,但是手机端需要的时间格式是lang类型的时间差

思路: 利用`@ConditionalOnClass`注解,如果是`app`服务就使用lang类型的时间处理

### 参考的文档

* [beans-java-conditional](https://docs.spring.io/spring/docs/5.0.15.RELEASE/spring-framework-reference/core.html#beans-java-conditional)

步骤

 * git 