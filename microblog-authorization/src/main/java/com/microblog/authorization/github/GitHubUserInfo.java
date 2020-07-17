package com.microblog.authorization.github;

import lombok.Data;



/**
 *功能描述 github用户信息
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Data
public class GitHubUserInfo {

    private String login;
    private int id;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private boolean site_admin;
    private String name;
    private Object company;
    private String blog;
    private Object location;
    private String email;
    private Object hireable;
    private Object bio;
    private Object twitter_username;
    private int public_repos;
    private int public_gists;
    private int followers;
    private int following;
    private String created_at;
    private String updated_at;

}

/*
UserInfo(
    login=lgjlife,
    id=35231177,
     node_id=MDQ6VXNlcjM1MjMxMTc3,
      avatar_url=https://avatars0.githubusercontent.com/u/35231177?v=4,
      gravatar_id=,
      url=https://api.github.com/users/lgjlife,
      html_url=https://github.com/lgjlife,
      followers_url=https://api.github.com/users/lgjlife/followers,
      following_url=https://api.github.com/users/lgjlife/following{/other_user},
      gists_url=https://api.github.com/users/lgjlife/gists{/gist_id},
      starred_url=https://api.github.com/users/lgjlife/starred{/owner}{/repo},
      subscriptions_url=https://api.github.com/users/lgjlife/subscriptions,
      organizations_url=https://api.github.com/users/lgjlife/orgs,
      repos_url=https://api.github.com/users/lgjlife/repos,
      events_url=https://api.github.com/users/lgjlife/events{/privacy},
      received_events_url=https://api.github.com/users/lgjlife/received_events,
       type=User,
       site_admin=false,
       name=lgjlife,
       company=null,
       blog=https://www.cnblogs.com/lgjlife/,
       location= ShenZhen ,China,
       email=null,
       hireable=null,
        bio=null,
        twitter_username=null,
         public_repos=23,
          public_gists=0,
           followers=14,
           following=0,
           created_at=2018-01-08T16:21:19Z,
           updated_at=2020-07-17T07:09:07Z
       )

 */
