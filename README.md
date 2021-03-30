# Graalvm使用流程

+ 引入依赖：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>cn.gloduck</groupId>
    <artifactId>Native</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <graalvm.version>21.0.0</graalvm.version>
        <main.class>cn.gloduck.NativeStarter</main.class>
        <image.name>cn.gloduck</image.name>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.graalvm.sdk</groupId>
            <artifactId>graal-sdk</artifactId>
            <version>${graalvm.version}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.graalvm.nativeimage</groupId>
            <artifactId>native-image-maven-plugin</artifactId>
            <version>${graalvm.version}</version>
        </dependency>

    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.0</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>cn.gloduck.NativeStarter</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.graalvm.nativeimage</groupId>
                <artifactId>native-image-maven-plugin</artifactId>
                <version>${graalvm.version}</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>native-image</goal>
                        </goals>
                        <phase>package</phase>
                    </execution>
                </executions>
                <configuration>
                    <skip>false</skip>
                    <mainClass>${main.class}</mainClass>
                    <imageName>${image.name}</imageName>
                    <buildArgs>
                        --no-fallback
                    </buildArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

+ 打包Jar包
+ 使用分析工具运行一遍Jar包，注意跑完所有用例
  + `java -agentlib:native-image-agent=config-output-dir=配置文件输出路径 -jar 分析的jar包`
+ 这些输出的配置文件全部复制到项目的`resource/META-INF/native-image`路径下。