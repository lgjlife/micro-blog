# 基于SpringCloud的仿微博系统
[![博客园](https://img.shields.io/badge/%E5%8D%9A%E5%AE%A2%E5%9B%AD-%E5%86%AC%E7%9C%A0%E7%9A%84%E5%B1%B1%E8%B0%B7-brightgreen.svg)](https://www.cnblogs.com/lgjlife/)
## 设计思路
![](https://github.com/lgjlife/micro-blog/blob/master/micro-blog%20%E8%AE%BE%E8%AE%A1%E5%9B%BE.jpg)

# 实现功能介绍

* 整体架构
    * nginx作为反向代理服务器
    * Spring Cloud 作为微服务实现方案 
    * 前后端分离
    * 前端文件部署在nginx中
    * 网关统一实现拦截认证

* 集成Spring Cloud 相关模块
    * Eureka服务注册与发现
    * Feign声明式调用
    * admin微服务监控
    * config分布式统一配置，支持本地和git，仅用于测试，各个微服务未使用
    * hystrix-dashboard & hystrix-turbine 用于微服务熔断器监控
    * sleuth服务请求链监控
    * gateway实现网关，不使用zuul.
* 前端
    * 前后端分离，单独部署
    * html,css,javascript,jquery实现
    * 前端部署在nginx中
 
* 网关
    * spring dloud gateway实现
    * 统一拦截请求并鉴权，确认当前请求是否需要登录
    * 路由前端的请求
    
* zookeeper统一配置中心
    * 路径分为需登录才可访问以及无需登录普两种类型，需登录才可访问的路径需要各个微服务自行向zk设置
    * 微服务向zk写入本应用的拦截路径配置
    * 网关监听zk的拦截路径配置节点，有更新则更新网关本地的拦截路径配置
    * 节点类型为无序临时节点，微服务下线时将会删除本节点的配置
    
* 登录认证方式
    * JWT实现
    * 登录成功之后后端会向前端返回生成的token
    * 前端缓存token  
    * 前端每个请求都会带上Authorization的Header字段，也就是token 
    * 网关统一拦截校验是否为需要登录路径，需要登录则校验token是否有效，无效则跳转到登录页面
 
* 用户模块
    * 用户注册
        * 
    * 用户登录
    

# 模块说明
├─microblog 父工程

├─── config-repo 配置中心配置文件

├─── FrontEnd 前端项目文件,nginx 配置文件

├─── img  README.md文件所用图片

├─── microblog-blog 网关应用

     ├─── blog-dao
     
     ├─── blog-service
     
     ├─── blog-web
     
├─── microblog-common 公共实现类,配置为SpringBoot-starter

├─── microblog-chat 私信应用

├─── microblog-config 配置中心应用

├─── microblog-gateway 网关应用

     ├─── microblog-gateway-controller  网关控制层
     
     ├─── microblog-gateway-service     网关服务层
     
     ├─── microblog-gateway-dao         网关数据层
 
├─── microblog-log 日志模块

├─── microblog-msg 邮件和短信处理应用

├─── microblog-points 积分应用

    ├─── microblog-points-dao

    ├─── microblog-points-service

    ├─── microblog-points-web
     

├─── microblog-scheduler 任务调度应用

     ├─── microblog-scheduler-dao 
     
     ├─── microblog-scheduler-service
     
     ├─── microblog-scheduler-web

├─── microblog-search 搜索应用

├─── microblog-support  spring cloud 配套服务

     ├─── microblog-hystrix  hystrix 监控应用
     
     ├─── microblog-sleuth
     
     ├─── microblog-admin  监控
     
     ├─── microblog-center 注册中心应用 port: 8001
     
     ├─── zipkin-server  zipkin 监控

├─── mysql 数据库文件
      
      ├─── all 整个工程的sql文件
      
           ├───  microblog-sql-all-xxxx-xx-xx-xx:xx:xx.sql
           
      ├───　blog.sql 微博相关的sql
      
      ├───　job.sql　 定时任务
      
      ├───　points.sql  积分应用　
      
      ├───　quartz.sql  quartz本身的sql
      
      ├───　user.sql  用户相关

├─── 项目的一些启动脚本          

├─── github 上传脚本

├─── micro-blog 设计图.jpg

## 端口使用

|服务|端口|
|---|---|
|microblog-center|8001|
|microblog-admin-server|8002|
|microblog-config-server|8003|
|microblog-hystrix-dashboard|8004|
|microblog-hystrix-turbine|8005|
|zipkin-server|9411|
|microblog-blog-web|8006|
|microblog-chat|8007|
|microblog-cache|8008|
|microblog-filesystem|8009|
|microblog-gateway|8010|
|microblog-log|8011|
|microblog-msg-service|8012|
|microblog-points-web|8013|
|microblog-scheduler-web|8014|
|microblog-search-web|8015|
|microblog-short-url|8016|
|microblog-user-web|8017|
|microblog-websocket|8018|


## 使用工具和技术
### 工具
* 开发环境： IDEA
* 项目构建：　Maven
* 前端开发工具： Brackets
* 版本管理： Git
* 思维导图软件： MindMaster
### 技术
* 核心框架： Spring ,Spring MVC, Spring Boot, Spring Cloud 
* 持久层： Mybatis
* 数据库： MySQL
* 数据库连接池： Druid
* 缓存： Redis
* 分布式锁：　Redisson
* 反向代理服务器： Nginx
* 认证方式:  JWT
* 网络通信： Netty
* 消息中间件： RocketMQ
* 搜索：elasticsearch
* 日志： Logback
* 日志聚合： Kafka + Logstash + elasticsearch + Kibana
* 前端:Html,Javascript,css,jquery

:apple:

## 功能列表
### 架构
* Nginx 作为反向代理服务器，前端代码部署在Nginx中
* 微服务框架使用Spring Cloud实现
* 客户端--->Nginx--->Zuul--->微服务--->数据库

### Scheduler任务调度模块
* 使用Quartz实现
* 支持分布式定时任务
* 实现任务增删改查
* 支持任务类注入Spring Bean

### Log日志集中输出模块
* 拦截日志
* 输送到Kafka服务器
* 整个系统为Logback+Kafka+LogStash+ElasticSearch+Kibana实现日志集中监控，避免单个微服务日志查看困难的问题

### Swagger Rest API模块
* 使用Swagger2.0实现
* 给整个系统的所有微服务提供统一的访问页面

### GateWay 网关模块
* 使用Spring Cloud Zuul实现
* 访问路径拦截，校验是否需要进行JWK校验
* 使用Zookeeper获取各个微服务的拦截配置

### Common 公共基础模块
* 为整个微服务提供通用的基础类
* AOP实现访问Url打印，注解配置
* AOP实现方法调用时间打印，注解配置
* 微服务向Zookeeper写入访问路径拦截配置的实现
* MySQL数据备份
* 通用Dto类
* Token校验具体逻辑业务实现
* RSA加解密业务实现
* 邮件，短信业务实现
* Zookeeper客户端封装

### Docker
* 使用DockerFile构建镜像
* 项目文件夹中的build.sh为镜像构建脚本
* 项目文件夹中的run.sh为创建容器并启动脚本

### Spring Cloud 集成组件

### 前端
* 相关语言和框架使用Html,CSS,JavaScript,Jquery,BootStrap.
* 前端代码部署在Nginx中
* Nginx同时也作为后端网关的代理服务器

### User 用户模块
* 用户注册登录，支持邮件、电话注册
* 密码传输加密
* 密码MD5之后再保存
* 图片验证码
* 登录之后使用JWK进行访问用户验证
* 用户信息修改

### fileSystem分布式文件系统
* 使用FastDFS实现
* 用于存放头像图片，微博图片

### search 搜索模块
* 使用ElasticSearch实现
* 用于搜索用于，微博
* 使用LogStash实现和MySQL数据同步更新。使用定时任务监控MySQL数据删除情况，实现数据删除同步更新

## 设计思路

### 登录拦截
　整个系统中，有些url是不需要用户登录就可以访问的，有些是需要登录之后才能访问，因此需要进行路径拦截校验是否需要登录。

### 如何启动项目
1. 安装Redis并启动
2. 安装Mysql并启动，导入sql文件
3. 安装RabbitMQ并启动
4. 安装elasticsearch并启动
5. 安装nginx，配置文件，并启动
6. 配置

    6.1  配置Mysql用户名和密码
    
    6.2  配置Redis用户名和密码
    
    6.3  配置RabbitMq 
    
    6.4  配置elasticsearch
    
    6.5  配置nginx
    
7. 启动项目
8. 访问路径

    8.1 [首页:http://localhost:7300/index.html](http://localhost:7300/index.html)
    
    8.2 [监控页面:http://localhost:7300/monitor/index.html](http://localhost:7300/monitor/index.html)


### 数据库文件
每次git提交时都会执行数据库备份操作，备份类位于[数据库备份类](https://github.com/lgjlife/micro-blog/blob/master/microblog-common%2Fsrc%2Fmain%2Fjava%2Fcom%2Fcloud%2Fmicroblog%2Fcommon%2Fbackup%2FMysqlBackupUtil.java)。
备份的文件存放于[数据库备份文件 目录：mysql/all](https://github.com/lgjlife/micro-blog/tree/master/mysql/all)，最新日期为最新的操作，文件名称(xxx/mysql/all/microblog-sql-all-2019-03-30-16:22:33.sql)。
[工程目录：mysql 存放各个模块的sql文件](https://github.com/lgjlife/micro-blog/tree/master/mysql)

请选择日期最新备份文件进行数据库导入操作。
```java
mysql -uroot -p
mysql> create database microblog
mysql> use microblog
//xxx/mysql/all/microblog-sql-all-2019-03-30-16:22:33.sql 为备份文件本身的目录
mysql> source xxx/mysql/all/microblog-sql-all-2019-03-30-16:22:33.sql
```
### Controller打印请求日志
* 使用AOP实现
* [切面类定义](https://github.com/lgjlife/micro-blog/blob/master/microblog-common/src/main/java/com/cloud/microblog/common/aop/syslog/aspect/PrintUrlAspect.java)
* 如何使用：

1.引入pom
```xml
<dependency>
    <groupId>com.microblog</groupId>
    <artifactId>microblog-common</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```
2.application.yml配置
```yaml
microblog:
  common:
    # 使能打印访问Url，在Controller方法上添加注解@PrintUrlAnno
    printUrlEnable: true
    # 使能打印方法执行耗时，在方法上添加注解@PrintUseTimeAnno
    printUseTimeEnable: true
```

3.Controller方法上使用
```$xslt
@PrintUrlAnno
@PostMapping("/login")
public BaseResult login(@RequestBody Map<String, Object> requestMap){
}
```
4.输出
```
访问  com.microblog.chat.controller.UserController  method = login  路径 = /user/login  描述：
```
* 获取方法执行时间： @PrintUseTimeAspect   

## 前端页面
![首页](https://github.com/lgjlife/micro-blog/blob/master/img/index.png)
![登录页](https://github.com/lgjlife/micro-blog/blob/master/img/login.png)
![注册页](https://github.com/lgjlife/micro-blog/blob/master/img/register.png)


## 其他信息

* [Spring Boot Admin Reference Guide](https://codecentric.github.io/spring-boot-admin/2.2.3/#getting-started)
* [seata](http://seata.io/zh-cn/docs/user/microservice.html)