# 怎么使用maven架构微服务

[![Build Status](https://travis-ci.com/zhaoyunxing92/maven-learn.svg?branch=master)](https://travis-ci.com/zhaoyunxing92/maven-learn)

### 参考文档
* [maven-ci-friendly](https://maven.apache.org/maven-ci-friendly.html) 动态ci使用说明

* [maven-3.5.0-beta-notes](https://maven.apache.org/docs/3.5.0-beta-1/release-notes.html) 主要添加动态修改版本号

* [encryption](http://maven.apache.org/guides/mini/guide-encryption.html) settings.xml密码脱密

### 使用前准备

```shell
# 安装插件到本地
mvn install -pl maven-plug-case -am
# 检验是否编译通过
mvn compile -up
```