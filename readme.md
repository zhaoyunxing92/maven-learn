# maven-plugin

[![Build Status](https://travis-ci.com/zhaoyunxing92/maven-learn.svg?branch=maven-plug)](https://travis-ci.com/zhaoyunxing92/maven-learn)

这个分支主要安利插件的

#### 使用前准备工作

* mvn install

#### 插件安利

 * [docker-maven-plugin](https://github.com/spotify/docker-maven-plugin) 编译docker images 代码参考地址: https://github.com/zhaoyunxing92/springboot-docker
 
  * 跳转到app-api目录执行:`mvn clean package docker:build`生成docker image文件
  
  * docker run -d --name=app-api -p 8081:8081 app-api:1.0-SNAPSHOT
 
  * curl localhost:8081/git　(或者浏览器访问) # 可以看到git commit 信息
 
 * `git-commit-id-plugin` git 提交记录抽取
 
  跳转到app-api目录执行：`mvn clean package` 生成git.properties文件

 * `appassembler-maven-plugin` 生成多环境运行脚本
   
  跳转到web-api目录执行：`mvn clean package appassembler:assemble` target/dist目录下
