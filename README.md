# 基于SpringCloud的仿微博系统

## 设计思路
![](https://github.com/lgjlife/micro-blog/blob/master/micro-blog%20%E8%AE%BE%E8%AE%A1%E5%9B%BE.jpg)

# 模块说明
├─microblog 父工程

├─── config-repo 配置中心配置文件

├─── FrontEnd 前端项目文件,nginx 配置文件

├─── img README.md 文件所用图片

├─── microblog-center 注册中心应用 port: 8001

├─── microblog-common 公共类

├─── microblog-config 配置中心应用

├─── microblog-gateway 网关应用

     ├─── microblog-gateway-controller  网关控制层
     
     ├─── microblog-gateway-service     网关服务层
     
     ├─── microblog-gateway-dao         网关数据层

├─── microblog-hystrix  hystrix 监控应用

├─── microblog-scheduler 任务调度应用

├─── microblog-search 搜索应用

├─── mysql 数据库文件

├─── zipkin-server  zipkin 监控

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

## 前端页面
![首页](https://github.com/lgjlife/micro-blog/blob/master/img/index.png)
![登录页](https://github.com/lgjlife/micro-blog/blob/master/img/login.png)
![注册页](https://github.com/lgjlife/micro-blog/blob/master/img/register.png)