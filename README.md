# 基于SpringCloud的仿微博系统
[我的博客](https://www.cnblogs.com/lgjlife/)
## 设计思路
![](https://github.com/lgjlife/micro-blog/blob/master/micro-blog%20%E8%AE%BE%E8%AE%A1%E5%9B%BE.jpg)

# 模块说明
├─microblog 父工程

├─── config-repo 配置中心配置文件

├─── FrontEnd 前端项目文件,nginx 配置文件

├─── img  README.md文件所用图片

├─── microblog-common 公共实现类

├─── microblog-chat 私信应用

├─── microblog-config 配置中心应用

├─── microblog-gateway 网关应用

     ├─── microblog-gateway-controller  网关控制层
     
     ├─── microblog-gateway-service     网关服务层
     
     ├─── microblog-gateway-dao         网关数据层
     
├─── microblog-support  

     ├─── microblog-hystrix  hystrix 监控应用
     
     ├─── microblog-sleuth
     
     ├─── microblog-admin  监控
     
     ├─── microblog-center 注册中心应用 port: 8001
     
     ├─── zipkin-server  zipkin 监控

├─── microblog-scheduler 任务调度应用

├─── microblog-search 搜索应用

├─── mysql 数据库文件

├─── github 上传脚本

├─── micro-blog 设计图.jpg

## 使用工具和技术
### 工具
* 开发环境： IDEA
* 前端开发工具： Brackets
* 版本管理： git
* 思维导图软件： MindMaster
### 技术
* 核心框架： Spring Cloud , Spring ,Spring MVC
* 持久层： Mybatis
* 数据库： MySQL
* 数据库连接池： Druid
* 缓存： Redis
* 代理服务器： nginx
* 安全框架： shiro
* 网络通信： netty
* 消息中间件： RabbitMQ
* 搜索：elasticsearch
* 日志： log4j2
* 日志聚合： kafka
* 前端:Html,Javascript,css,jquery
### 数据库文件
每次git提交时都会执行数据库备份操作，备份类位于[数据库备份类]()。
备份的文件存放于[数据库备份文件 目录：mysql/all]()，最新日期为最新的操作，文件名称(xxx/mysql/all/microblog-sql-all-2019-03-30-16:22:33.sql)。
[工程目录：mysql 存放各个模块的sql文件]()

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

1.启动类注解扫描路径(com.cloud.microblog.common) 

```$xslt
@ComponentScan(basePackages = {"com.cloud.microblog.gateway.*","com.cloud.microblog.common"})
```
2.Controller方法上使用
```$xslt
@PrintUrlAnno
@PostMapping("/login")
public BaseResult login(@RequestBody Map<String, Object> requestMap){
}
```
3.输出
```
访问  com.cloud.microblog.chat.controller.UserController  method = login  路径 = /user/login  描述：
```
* 获取方法执行时间： @PrintUseTimeAspect   

## 前端页面
![首页](https://github.com/lgjlife/micro-blog/blob/master/img/index.png)
![登录页](https://github.com/lgjlife/micro-blog/blob/master/img/login.png)
![注册页](https://github.com/lgjlife/micro-blog/blob/master/img/register.png)