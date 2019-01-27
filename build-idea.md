Tomcat 源码阅读
==========================

## Build

### 准备工作

- 创建pom.xml文件

- 设置Sources: java 和 webapps/examples/WEB-INF/classes

- 设置Test Sources: test

- 设置build out目录: tomcat源代码所在目录/target/classes

- 创建 build.properties， 从build.properties.default拷贝，并修改base.path

- 将工程导入Intellij IDEA中，IDEA会根据pom.xml文件的内容自动下载依赖

### 启动

- 进入Bootstrap.java文件，直接Run

- 访问 [http://localhost:8080](http://localhost:8080) 会报错

```
org.apache.jasper.JasperException: org.apache.jasper.JasperException: Unable to compile class for JSP
```

原因是我们直接启动org.apache.catalina.startup.Bootstrap的时候没有加载org.apache.jasper.servlet.JasperInitializer，从而无法编译JSP。

- 在tomcat的源码org.apache.catalina.startup.ContextConfig的configureStart()中手动将JSP解析器初始化

```
context.addServletContainerInitializer(new JasperInitializer(), null);

```

- 重新启动并访问[http://localhost:8080](http://localhost:8080)就会看到熟悉的tomcat启动页面




