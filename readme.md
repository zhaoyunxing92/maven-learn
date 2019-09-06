# logback-spring

[![Build Status](https://travis-ci.com/zhaoyunxing92/maven-learn.svg?branch=logback)](https://travis-ci.com/zhaoyunxing92/maven-learn)

这个分支主要说明怎么使用logback-spring.xml、动态修改日志

### 可以参考的文档

 * [logback-extensions](https://docs.spring.io/spring-boot/docs/2.2.0.M5/reference/html/spring-boot-features.html#boot-features-logback-extensions) spring boot对logback的扩展

### spring boot下的logback

 在使用`logback-spring.xml`就可以读取yml文件的配置了
 
 * 根据环境不同采取不同的策略
 
  ```xml
   <springProfile name="local">
        <root level="DEBUG">
            <appender-ref ref="console"/>
            <appender-ref ref="file"/>
        </root>
    </springProfile>
    <springProfile name="dev">
        <root level="DEBUG">
            <appender-ref ref="file"/>
        </root>
    </springProfile>
  ```
 
 * 读取yml文件配置
 
 ```xml
  <springProperty scope="context" name="log_home" source="log.path" defaultValue="./logs"/>
 ``` 

### 动态日志级别

这个根据项目架构来，如果项目中集成了[spring-boot-starter-actuator](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready)则用`actuator`如果没有自己编写

#### [spring-boot-starter-actuator](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready)