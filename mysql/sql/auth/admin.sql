/******************************************************************************/
/*管理员表*/
DROP TABLE IF  EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(30) NOT NULL DEFAULT "" COMMENT '名字',
  `nick_name` varchar(30) NOT NULL DEFAULT "" COMMENT '昵称',
  `password` varchar(30) NOT NULL DEFAULT "" COMMENT '密码',
  `email` varchar(30) NOT NULL DEFAULT "" COMMENT '邮箱',
  `phone` varchar(30) NOT NULL DEFAULT "" COMMENT '电话',
  `status` enum('未激活','已激活','禁用') DEFAULT '未激活' COMMENT '状态',
  `create_time` DATETIME NOT NULL DEFAULT '2018-01-01 00:00'  COMMENT '创建时间',

  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';


insert into admin (aid,name,password) values(1,"admin1","123456"),(2,"admin2","123456"),(3,"admin3","123456");

select  * from admin;
/******************************************************************************/
/*角色表*/
DROP TABLE IF  EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(30) NOT NULL DEFAULT "" COMMENT '名字',
  `enable` TINYINT NOT NULL DEFAULT 0 COMMENT '有效标志',
  `create_time` DATETIME NOT NULL DEFAULT '2018-01-01 00:00'  COMMENT '创建时间',
  `create_by` varchar(30) DEFAULT "" COMMENT '创建人',

  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

insert into role (rid,name) values(1,"role_user1"),(2,"role_user2");

/******************************************************************************/
/*权限表*/
DROP TABLE IF  EXISTS `permission`;
CREATE TABLE `permission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(30) NOT NULL DEFAULT "" COMMENT '名字',
  `url` varchar(30) NOT NULL DEFAULT "" COMMENT '权限URL',
  `create_time` DATETIME NOT NULL DEFAULT '2018-01-01 00:00'  COMMENT '创建时间',
  `create_by` varchar(30) DEFAULT "" COMMENT '创建人',

  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

insert into permission (pid,name,url) values(1,"ROLE_USER","ROLE_USER"),(2,"permission2","/admin/per2");

/******************************************************************************/
/*管理员角色关联表*/
DROP TABLE IF  EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `aid` int(11)  COMMENT '管理员ID',
  `rid` int(11)  COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员角色关联表';

insert into admin_role (aid,rid) values(1,1),(2,2);
/******************************************************************************/
/*角色权限关联表*/
DROP TABLE IF  EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `rid` int(11)  COMMENT '角色ID',
  `pid` int(11)  COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';
insert into role_permission (rid,pid) values(1,1),(1,2),(2,2);

select a.aid,a.name ,p.pid,p.name
  from admin a left join admin_role ar on a.aid = ar.rid
  left join role r on r.rid = ar.rid
  left join role_permission rp on   r.rid = rp.rid
  left join permission p on rp.pid = p.pid
  where a.aid = 2;