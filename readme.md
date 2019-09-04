# maven-ci

[![Build Status](https://travis-ci.com/zhaoyunxing92/maven-learn.svg?branch=master)](https://travis-ci.com/zhaoyunxing92/maven-learn)

这个分支主要解释怎么动态修改版本

场景: 假如我们开发完成一个周期开发,或者完成一个版本开发需要编译一个版本

### 参考文档

* [maven-ci-friendly](https://maven.apache.org/maven-ci-friendly.html) 动态ci使用说明

* [maven-3.5.0-beta-notes](https://maven.apache.org/docs/3.5.0-beta-1/release-notes.html) 主要添加动态修改版本号

### 统一版本号

* 在`3.5.0-beta-1`版本以前

  ```shell
  # 先修改版本
  mvn versions:set -DnewVersion=2.0-SNAPSHOT
  
  # 这个时候会生成一个备份文件`pom.xml.versionsBackup`,如果你测试发现编译的`1.0.2-SNAPSHOT`需要归回滚或者放弃
  mvn versions:revert
  
  # 确定后提交本次修改
  mvn versions:commit
  ```

* 在`3.5.0-beta-1`版本之后

  添加了${revision}, ${sha1} , ${changelist} 三个关键字,方便动态修改版本
 
  ```shell
  mvn -Drevision=2.0-SNAPSHOT
  ```
 
  这样pom文件必须添加`flatten-maven-plugin`
  
   ```xml
     <project>
           <modelVersion>4.0.0</modelVersion>
           <groupId>io.github.sunny</groupId>
           <artifactId>ci-maven</artifactId>
           <version>${revision}</version>
           ...
           <properties>
             <revision>1.0.0-SNAPSHOT</revision>
           </properties>
         
          <build>
           <plugins>
             <plugin>
               <groupId>org.codehaus.mojo</groupId>
               <artifactId>flatten-maven-plugin</artifactId>
               <version>1.1.0</version>
               <configuration>
                 <updatePomFile>true</updatePomFile>
                 <flattenMode>resolveCiFriendliesOnly</flattenMode>
               </configuration>
               <executions>
                 <execution>
                   <id>flatten</id>
                   <phase>process-resources</phase>
                   <goals>
                     <goal>flatten</goal>
                   </goals>
                 </execution>
                 <execution>
                   <id>flatten.clean</id>
                   <phase>clean</phase>
                   <goals>
                     <goal>clean</goal>
                   </goals>
                 </execution>
               </executions>
             </plugin>
           </plugins>
           </build>
           <modules>
             <module>child1</module>
             ...
           </modules>
     </project>
   ```
