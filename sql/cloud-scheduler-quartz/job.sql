/*任务表*/
DROP TABLE IF  EXISTS `quartz_job`;
CREATE TABLE `quartz_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(80) DEFAULT "" COMMENT '任务名称',
  `description` varchar(200) DEFAULT "" COMMENT '任务描述',
  `cron` varchar(30) DEFAULT "" COMMENT '时间表达式',
  `job_class` varchar(80) DEFAULT "" COMMENT '任名称',
  `job_group` varchar(30) DEFAULT "" COMMENT '任务组别',
  `status` enum('停止','运行','暂停') DEFAULT '停止' COMMENT '状态',
  `create_time` DATETIME COMMENT '创建时间',
  `create_by` varchar(30) DEFAULT "" COMMENT '创建人',
  `finish_time` DATETIME COMMENT '结束时间',
  `finish_by` varchar(30) DEFAULT "" COMMENT '结束人',
  unique  index jod_index (`job_class`,`job_group`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

insert into `quartz_job` (name,description,cron,job_class,job_group,status,create_time,create_by,finish_time,finish_by)
 values ("定时任务1","我是定时任务1","0/10 * * * * ?","com.cloud.frame.scheduler.quartz.job.HelloJob","group1","停止"
        ,"2018-11-15 00:00","张三","2018-11-25 00:00","李四");
insert into `quartz_job` (name,description,cron,job_class,job_group,status,create_time,create_by,finish_time,finish_by)
 values ("定时任务2","我是定时任务2","0/10 * * * * ?","com.cloud.frame.scheduler.quartz.job.HelloJob","group2","停止"
        ,"2018-11-15 00:00","张三","2018-11-25 00:00","李四");
insert into `quartz_job` (name,description,cron,job_class,job_group,status,create_time,create_by,finish_time,finish_by)
 values ("定时任务3","我是定时任务3","0/10 * * * * ?","com.cloud.frame.scheduler.quartz.job.HelloJob","group3","停止"
        ,"2018-11-15 00:00","张三","2018-11-25 00:00","李四");
insert into `quartz_job` (name,description,cron,job_class,job_group,status,create_time,create_by,finish_time,finish_by)
 values ("定时任务4","我是定时任务4","0/10 * * * * ?","com.cloud.frame.scheduler.quartz.job.HelloJob","group4","停止"
        ,"2018-11-15 00:00","张三","2018-11-25 00:00","李四");

delete from quartz_job where id  < 1000;