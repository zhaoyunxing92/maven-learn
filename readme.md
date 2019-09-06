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

这个根据项目架构来，如果项目中集成了[spring-boot-starter-actuator](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready)则用`actuator`，如果没有自己编写

#### [spring-boot-starter-actuator](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready)

```shell
# 通过 http://localhost:8081/log 观察日志输出
curl http://localhost:8081/actuator/loggers/io.github.sunny.controller -X POST -H "Content-Type:application/json" -d '{"configuredLevel": "debug"}'
```

#### 编码方式修改

```java
    /**
     * 动态修改日志级别
     *
     * @param pgk   包路径 eg:com.io.github.sunny
     * @param level 日志级别 ALL、TRACE、DEBUG、INFO、WARN、ERROR
     * @return {@link Response}
     */
    @PostMapping("/log/{pgk}/{level}")
    public Response setLogLevel(@PathVariable("pgk") String pgk, @PathVariable("level") String level) {

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger(pgk).setLevel(Level.valueOf(level));
        
        return Response.createSuccess("成功修改包[%s]日志级别为：[%s]", null, pgk, level);
    }
```

通过curl修改或者postman修改

```shell
curl http://localhost:8081/log/io.github.sunny.controller/info -X POST
```