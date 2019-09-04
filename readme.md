# maven-dependency-mechanical

[![Build Status](https://travis-ci.com/zhaoyunxing92/maven-learn.svg?branch=maven-dep-mechanism)](https://travis-ci.com/zhaoyunxing92/maven-learn)


### 参考文档

* [maven-dependency-mechanism](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html) maven的依赖机制

* [maven-optional-and-excludes-dependencies](https://maven.apache.org/guides/introduction/introduction-to-optional-and-excludes-dependencies.html) 主要maven依赖的可选和排除

* [maven-dependency-tree](https://maven.apache.org/plugins/maven-dependency-plugin/examples/filtering-the-dependency-tree.html) maven依赖树插件

### 使用前准备

* 全部安装到本地(这里主要是为`mvn dependency：tree`铺垫)
```shell
mvn install
```

* 制作三个版本的`user-api` `1.0`、`2.0`、`3.0`

```shell
 mvn install -Drevision=1.0 -pl server/user-server/user-api -am && mvn install -Drevision=2.0 -pl server/user-server/user-api -am && mvn install -Drevision=3.0 -pl server/user-server/user-api -am
```

### 依赖关系


### 传递依赖
     
   父pom依赖的jar会传递到全部子pom，A项目依赖的包也会传递到B项目如果想阻断则需要使用<optional>标签或者使用<exclusions>标签排除依赖。关于包冲突选择机制：链路最短原则和谁先声明用谁原则，同pom文件谁最后面就用谁（具体看分支代码）
      
### 导入依赖
   
   这里就拿spring boot举例，我们想批量导入它的版本依赖
   
   ```xml
     <dependencyManagement>
        <dependencies>
            <!--spring boot-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.1.0-RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
      </dependencyManagement>
   ```

### 依赖冲突和版本选择机制

实验在`server-api/web-api`的pom文件

#### 谁在后面就用谁

```xml
<dependencies>
    <dependency>
        <groupId>io.github.sunny</groupId>
        <artifactId>user-api</artifactId>
        <version>2.0</version>
    </dependency>
    
    <dependency>
        <groupId>io.github.sunny</groupId>
        <artifactId>user-api</artifactId>
        <version>3.0</version>
    </dependency>
</dependencies>
```

执行`mvn dependency:tree -Dincludes=io.github.sunny:user-api`命令得到结果选择版本`3.0`

```log
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ web-api ---
[INFO] io.github.sunny:web-api:jar:1.0-SNAPSHOT
[INFO] \- io.github.sunny:user-api:jar:3.0:compile

```

#### 链路相同谁在上就用谁

* `user-service` > `user-api`:1.0

* `order-service` > `user-api`:2.0

```xml
<dependencies>
    <dependency>
        <groupId>io.github.sunny</groupId>
        <artifactId>user-service</artifactId>
    </dependency>
    
    <dependency>
        <groupId>io.github.sunny</groupId>
        <artifactId>order-service</artifactId>
    </dependency>
</dependencies>
```

执行`mvn dependency:tree -Dverbose -Dincludes=io.github.sunny:user-api`命令得到结果选择版本`1.0`,可以调整位置然后执行命令看结果

```log
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ web-api ---
[INFO] io.github.sunny:web-api:jar:1.0-SNAPSHOT
[INFO] +- io.github.sunny:user-service:jar:1.0-SNAPSHOT:compile
[INFO] |  \- io.github.sunny:user-api:jar:1.0:compile
[INFO] \- io.github.sunny:order-service:jar:1.0-SNAPSHOT:compile
[INFO]    \- (io.github.sunny:user-api:jar:2.0:compile - omitted for conflict with 1.0)
```

#### 链路最短原则

* `user-service` > `user-api`:1.0

* `web-api-dependencies` > `app-api-dependencies` > `user-api`:3.0

```xml
<dependencies>
    <dependency>
        <groupId>io.github.sunny</groupId>
        <artifactId>web-api-dependencies</artifactId>
    </dependency>
    <dependency>
        <groupId>io.github.sunny</groupId>
        <artifactId>user-service</artifactId>
    </dependency>
</dependencies>
```

执行`mvn dependency:tree -Dverbose -Dincludes=io.github.sunny:user-api`命令得到结果选择版本`1.0`

```log
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ web-api ---
[INFO] io.github.sunny:web-api:jar:1.0-SNAPSHOT
[INFO] +- io.github.sunny:web-api-dependencies:jar:1.0-SNAPSHOT:compile
[INFO] |  \- io.github.sunny:app-api-dependencies:jar:1.0-SNAPSHOT:compile
[INFO] |     \- (io.github.sunny:user-api:jar:3.0:compile - omitted for conflict with 1.0)
[INFO] \- io.github.sunny:user-service:jar:1.0-SNAPSHOT:compile
[INFO]    \- io.github.sunny:user-api:jar:1.0:compile
```