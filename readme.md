# 怎么使用maven架构微服务

[![Build Status](https://travis-ci.com/zhaoyunxing92/maven-learn.svg?branch=master)](https://travis-ci.com/zhaoyunxing92/maven-learn)

主要解决在使用maven中遇到的盲点以及怎么使用maven架构一个微服务

## 参考文档

* [maven-ci-friendly](https://maven.apache.org/maven-ci-friendly.html) 动态ci使用说明

* [maven-3.5.0-beta-notes](https://maven.apache.org/docs/3.5.0-beta-1/release-notes.html) 主要添加动态修改版本号

* [encryption](http://maven.apache.org/guides/mini/guide-encryption.html) settings.xml密码脱密

## 目录结构说明

```log
├── base-dependencies  -- 管理依赖
│   ├── app-api-dependencies -- app web项目依赖
│   ├── common-dependencies -- 公共包
│   ├── service-api-dependencies -- 内部服务api依赖
│   ├── service-dependencies -- 内部服务依赖
│   └── web-api-dependencies -- web项目依赖
│ 
│  ---- 以上部分不在项目内,为了方便演示添加
│
├── server -- 内部服务
│   ├── order-server  -- 服务A
│   │   ├── order-api
│   │   └── order-service
│   └── user-server  -- 服务B
│       ├── user-api
│       └── user-service
└── server-api  --- 对外web服务
    ├── app-api  -- 手机端api
    └── web-api  -- h5端api
```

### maven中的水平拆分和垂直拆分

* 水平拆分:
 
  来个服务单独创建一个项目
 
  * 优点: 这样源头上物理隔离保证项目安全,独立性高
  
  * 缺点: 开发之间交互复杂,需要依赖其他服务`deploy`才能开发,一个成员如果开发几个微服务需要频繁切换,开发效率低

* 垂直拆分
  
  来个服务添加一个`module`
  
  * 优点: 代码都在一起,方便成员相互review,开发之间不需要`mvn deploy`交互
  
  * 缺点: 代码全部公开

## maven基本概念

### maven生命周期

* **validate** 验证项目是否正确,pom依赖关系是否正确,但是不检查代码本身语法

* **compile** 编译项目代码(检查代码语法、pom依赖关系)

* **test** 执行单元测试代码(validate+compile)

* **package** 打包源码,jar/war

* **verify** 检验打的包是否有效

* **install** 安装源码到本地,一般不建议使用,除非你很了解maven的拉包机制

* **deploy** 打包上传服务器

### 依赖关系

### pom配置

#### 统一版本号

#### [version有几种写法](https://maven.apache.org/pom.html)

 * **1.0**: 项目依赖版本是1.0
 
 * **\[1.0\]** : 同上
 
 * **(,1.0]** : x<= 1.0 项目中如果存在`0.8`、`1.2`两个版本,则根据这个规则选择`0.8`.如果是一个`0.5`和`0.8`,最终选择的版本是`0.8`
 
 * **\[1.0,3.0\]** : 1.0<=x<=3.0
 
 * **\[1.0,3.0\)** : 1.0<=x<3.0
 
 * **\[1.0,)** : x>=1.0
 
 * **\(,1.0],[3.0,)** : x<=1.0 or x>=3.0 如果现在有一个`0.8`、`2.0`、`3.2`三个版本,根据这条规则选择`3.2`

 * **\(,1.0),(3.0,)** : x<1.0 or x>3.0

> 技巧: `[`在那边最终版本选择就靠近那边,且版本号最大的

#### 

### settings文件配置

#### 密码加密

#### 

### [插件开发](http://maven.apache.org/guides/plugin/guide-java-report-plugin-development.html)

#### 配置读取

####

