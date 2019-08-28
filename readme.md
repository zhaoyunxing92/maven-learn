# maven learn

[![Build Status](https://travis-ci.com/zhaoyunxing92/maven-learn.svg?branch=master)](https://travis-ci.com/zhaoyunxing92/maven-learn)

主要解决在使用maven中遇到的盲点以及怎么使用maven架构一个微服务

## 参考文档

* [maven-ci-friendly](https://maven.apache.org/maven-ci-friendly.html)

* [maven-3.5.0-beta-notes](https://maven.apache.org/docs/3.5.0-beta-1/release-notes.html)

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
├── dependencies -- 管理全部依赖以及版本
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
