
/**
* 博客表
*
 */
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`(
   `blog_id` BIGINT   AUTO_INCREMENT COMMENT "博客ID",
   `user_id` BIGINT   COMMENT "用户ID",
   `content`  VARCHAR(200)  COMMENT "内容",
   `video_url` VARCHAR(100)  default "" COMMENT "视频URL",
  /*`type` ENUM("PUBLIC","FREINDS","PRIVATE","GROUP")  default "PUBLIC" COMMENT "类型:公开,好友圈,仅自己可见,群可见",

   `create_time` DATETIME DEFAULT '2019-02-20 21:56:06' COMMENT "创建时间",
   `publish_time` DATETIME DEFAULT '2019-02-20 21:56:06' COMMENT "发布时间",*/
   `is_original` VARCHAR(5) DEFAULT "true" COMMENT "是否原创",
    PRIMARY KEY (`blog_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客表";

INSERT INTO blog VALUES (1,11,"啊啊所打动萨大赛","/a/a/c","true");
INSERT INTO blog VALUES (2,12,"啊啊所打动萨大赛","/a/a/c","true");
INSERT INTO blog VALUES (3,13,"啊啊所打动萨大赛","/a/a/c","true");
INSERT INTO blog VALUES (4,14,"啊啊所打动萨大赛","/a/a/c","true");
INSERT INTO blog VALUES (5,15,"啊啊所打动萨大赛","/a/a/c","true");
INSERT INTO blog VALUES (6,16,"啊啊所打动萨大赛","/a/a/c","true");


DROP TABLE IF EXISTS `blog1`;
CREATE TABLE `blog1`(
   `blog_id1` BIGINT   AUTO_INCREMENT COMMENT "博客ID",
   `user_id1` int   COMMENT "用户ID",
   `content1`  VARCHAR(200)  COMMENT "内容",
   `text`  VARCHAR(200)  COMMENT "内容",
   `idd` int   COMMENT "用户ID",
 /*  `video_url1` VARCHAR(100)  default "" COMMENT "视频URL",
   `type` ENUM("PUBLIC","FREINDS","PRIVATE","GROUP")  default "PUBLIC" COMMENT "类型:公开,好友圈,仅自己可见,群可见",

   `create_time` DATETIME DEFAULT '2019-02-20 21:56:06' COMMENT "创建时间",
   `publish_time` DATETIME DEFAULT '2019-02-20 21:56:06' COMMENT "发布时间",
   `is_original1` VARCHAR(5) DEFAULT "true" COMMENT "是否原创",*/
    PRIMARY KEY (`blog_id1`)
  /*  index(`user_id1`)*/
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客表";

INSERT INTO blog1 VALUES (11,111,"啊啊所打动萨大赛","打算给发得到放松",31);
INSERT INTO blog1 VALUES (21,121,"啊啊所打动萨大赛","打算给发得到放松",32);
INSERT INTO blog1 VALUES (31,131,"啊啊所打动萨大赛","打算给发得到放松",33);
INSERT INTO blog1 VALUES (41,141,"啊啊所打动萨大赛","打算给发得到放松",34);
INSERT INTO blog1 VALUES (51,151,"啊啊所打动萨大赛","打算给发得到放松",35);
INSERT INTO blog1 VALUES (61,161,"啊啊所打动萨大赛","打算给发得到放松",36);

INSERT INTO blog1 VALUES (11,111,"啊啊所打动萨大赛","/a/a/c","true");
INSERT INTO blog1 VALUES (21,121,"啊啊所打动萨大赛","/a/a/c","true");
INSERT INTO blog1 VALUES (31,131,"啊啊所打动萨大赛","/a/a/c","true");
INSERT INTO blog1 VALUES (41,141,"啊啊所打动萨大赛","/a/a/c","true");
INSERT INTO blog1 VALUES (51,151,"啊啊所打动萨大赛","/a/a/c","true");
INSERT INTO blog1 VALUES (61,161,"啊啊所打动萨大赛","/a/a/c","true");




DROP TABLE IF EXISTS `entity_demo`;
CREATE TABLE `entity_demo`(
   `demo_id` BIGINT   AUTO_INCREMENT COMMENT "博客ID",
   `user_id` BIGINT   COMMENT "用户ID",
   `content`  TEXT  COMMENT "内容",
   `video_url` VARCHAR(100)  default "" COMMENT "视频URL",
   `type` ENUM("PUBLIC","FREINDS","PRIVATE","GROUP")  default "PUBLIC" COMMENT "类型:公开,好友圈,仅自己可见,群可见",
   `create_time` DATETIME DEFAULT NULL COMMENT "创建时间",
   `publish_time` DATETIME DEFAULT NULL COMMENT "发布时间",
   `is_original` VARCHAR(5) DEFAULT "true" COMMENT "是否原创",
    PRIMARY KEY (`demo_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客表";

insert into `entity_demo` values(1,1001,"由腾讯推出的QQ浏览器微信版（微信浏览器）进行登录时保留了网页版微信通过二维码登录的方式，但是微信界面将不再占用单独的浏览器标签页，而是变成左侧的边栏。这样的方便用户浏览网页的同时，使用微信。","/vide0/1","PUBLIC","2019-03-01 01:55:35","2019-03-02 01:55:35","TRUE");
insert into `entity_demo` values(2,1002,"微信网页版虽然已经在内测中，只有使用同样在内测中的微信iPhone4.2版扫
描官方页面上的二维码，才能登录微信网页版，这种登录方式据传会是登录微信网页版的方式，那就是说，每一次都要使用手机扫描一下网页上的二维码，才能登录微信网页版。手机微信客户端变成了微信网页版的登录钥匙","/vide0/1","PUBLIC","2019-03-02 01:55:35","2019-03-03 01:55:35","TRUE");
insert into `entity_demo` values(3,1003,"首先打开微信网页版，微信网页版的地址，打开页面，就能看见页面正中央一个巨大的二维码。","/vide0/1","PUBLIC","2019-03-04 01:55:35","2019-03-01 01:55:35","TRUE");
insert into `entity_demo` values(4,1004,"然后扫描微信二维码：以安卓手机为例，在微信页面的“微信”两个字的右边有一个像一支笔四颗星的按钮，点一下这个按钮，就有个扫描二维码的选项，用手机对准屏幕进行扫描，（也可以存下2维码图片以备随时扫描）就可以了。这时，会在微信页面上出现网页版的对话框，发送和收取信息，跟手机都是同步的，很好用的。然后用浏览器生成桌面图标。","/vide0/1","PUBLIC","2019-03-01 01:55:35","2019-03-01 01:55:35","TRUE");
insert into `entity_demo` values(5,1005,"首先下载“网页微信客户端”，大家可以直接到微信官方网站下载。","/vide0/1","PUBLIC","2019-03-01 01:55:35","2019-03-01 01:55:35","TRUE");
insert into `entity_demo` values(6,1006,"打开“网页微信客户端”，会出现跟我们登陆微信页面版一样的二维码登陆界面。","/vide0/1","PUBLIC","2019-03-01 01:55:35","2019-03-01 01:55:35","TRUE");
insert into `entity_demo` values(7,1007,"当我们用打开手机微信，点击“发现→扫一扫”功能，扫描电脑上的“微信客户端”二维码，然后点击确认登陆即可。","/vide0/1","PUBLIC","2019-03-01 01:55:35","2019-03-01 01:55:35","TRUE");
insert into `entity_demo` values(8,1008,"微信主界面以侧边栏形式展示聊天联系人，与网页左右并存","/vide0/1","PUBLIC","2019-03-01 01:55:35","2019-03-01 01:55:35","TRUE");
insert into `entity_demo` values(9,1009,"当和某个联系人聊天时，聊天面板会并列在联系人面板的右边，浮在网页之上。点击网页区域，聊天面板会自动收起。","/vide0/1","PUBLIC","2019-03-01 01:55:35","2019-03-01 01:55:35","TRUE");
insert into `entity_demo` values(10,1010,"如果电脑屏幕较宽，建议点击右上角的图钉按钮，固定住聊天面板。就可以方便的分屏操作，左屏聊微信，右屏看网页啦。","/vide0/1","PUBLIC","2019-03-01 01:55:35","2019-03-01 01:55:35","TRUE");
insert into `entity_demo` values(11,1011,"点击联系人面板右上角的新建聊天按钮，就可以选择与单个人聊天，或多个人群聊","/vide0/1","PUBLIC","2019-03-01 01:55:35","2019-03-01 01:55:35","TRUE");

/**
* 博客图片表
 */
DROP TABLE IF EXISTS `blog_img`;
CREATE TABLE `blog_img`(
   `img_id` BIGINT   AUTO_INCREMENT COMMENT "图片ID",
   `blog_id` BIGINT   COMMENT "博客ID",
   `img_url` VARCHAR(100)  default "" COMMENT "图片URL",
    PRIMARY KEY (`img_id`),
    index(`blog_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客图片表";

/**
* 博客评论表
 */
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`(
   `comment_id` BIGINT   AUTO_INCREMENT COMMENT "评论ID",
   `blog_id` BIGINT   COMMENT "博客ID",
   `user_id` BIGINT   COMMENT "评论用户ID",
   `content`  TEXT   COMMENT "内容",
   `publish_time` DATETIME DEFAULT NULL COMMENT "发布时间",
    PRIMARY KEY (`comment_id`),
    index(`blog_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客评论表";

/**
* 博客收藏表
 */
DROP TABLE IF EXISTS `blog_collect`;
CREATE TABLE `blog_collect`(
   `collect_id` BIGINT   AUTO_INCREMENT COMMENT "转发ID",
   `blog_id` BIGINT   COMMENT "博客ID",
   `user_id` BIGINT   COMMENT "收藏用户ID",
   `create_time` DATETIME DEFAULT NULL COMMENT "创建时间",
    PRIMARY KEY (`collect_id`),
    index(`blog_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客收藏表";

/**
* 博客转发表
 */
DROP TABLE IF EXISTS `blog_repost`;
CREATE TABLE `blog_repost`(
   `repost_id` BIGINT   AUTO_INCREMENT COMMENT "转发ID",
   `blog_id` BIGINT   COMMENT "博客ID",
   `user_id` BIGINT   COMMENT "转发用户ID",
   `content`  TEXT   COMMENT "内容",
   `create_time` DATETIME DEFAULT NULL COMMENT "创建时间",
    PRIMARY KEY (`repost_id`),
    index(`blog_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客转发表";

/**
* 博客点赞表
 */
DROP TABLE IF EXISTS `blog_like`;
CREATE TABLE `blog_like`(
   `like_id` BIGINT   AUTO_INCREMENT COMMENT "转发ID",
   `blog_id` BIGINT   COMMENT "博客ID",
   `user_id` BIGINT   COMMENT "点赞用户ID",
   `create_time` DATETIME DEFAULT NULL COMMENT "创建时间",
    PRIMARY KEY (`like_id`),
    index(`blog_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客点赞表";



