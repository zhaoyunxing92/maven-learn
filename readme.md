# 怎么使用maven架构微服务

[![Build Status](https://travis-ci.com/zhaoyunxing92/maven-learn.svg?branch=master)](https://travis-ci.com/zhaoyunxing92/maven-learn)

### 参考文档

* [mojo-develop](http://maven.apache.org/guides/plugin/guide-java-plugin-development.html) 官方插件开发文档

### 使用前准备

```shell
# 安装到本地仓库
mvn install -Drevision=1.0
```

### 开始第一个mojo(插件)开发

使用idea的同学按照一下面流程就可以创建一个插件

file > new > project > maven项目 >打钩 create from archetype 选择 `maven-archetype-mojo` 一直下一步就可以了

#### pom.xml 代码

```xml
<project >
    <parent>
        <artifactId>maven-learn</artifactId>
        <groupId>io.github.sunny</groupId>
        <version>${revision}</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <!-- packaging设置为 maven-plugin -->
    <artifactId>maven-plug-case</artifactId>
    <packaging>maven-plugin</packaging>

    <properties>
        <maven-plugin-api>2.0</maven-plugin-api>
        <maven-plugin-annotations>3.4</maven-plugin-annotations>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.apache.maven</groupId>
            <artifactId>maven-plugin-api</artifactId>
            <version>${maven-plugin-api}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugin-tools</groupId>
            <artifactId>maven-plugin-annotations</artifactId>
            <version>3.4</version>
        </dependency>
    </dependencies>
</project>
```

#### java代码

```java
@Mojo(name = "start")
public class MavenPlugMojo extends AbstractMojo {
    /**
     * Location of the file.
     *
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private File outputDirectory;

    @Parameter(property = "name", defaultValue = "zhaoyunxing")
    private String name;

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("=================== MavenPlugMojo begin ===================");
        getLog().info("===============读取配置数据==============");
        getLog().info("name=" + name);
        getLog().info("=============================");
    }
}
```

#### 运行插件

> mvn groupId:artifactId:version:goal 下面运行`maven-plug-case`插件

```shell

mvn io.github.sunny:maven-plug-case:1.0:start
# 简化
mvn plug-case:start
```

#### debug插件

idea的同学使用`remote`功能,mvn默认的debug端口是8000

```xml
mvnDebug plug-case:start
```
