package com.clolud.microblog.search.service;


import com.clolud.microblog.search.service.feign.UserDeleteFeignService;
import com.microblog.user.dao.model.UserDelete;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *功能描述 
 * @author lgj
 * @Description 1. 数据库删除用户数据
 *              2. 删除的用户id信息放入删除表
 *              3. 本类定时查看删除表数据
 *              4. 远程从 microblog_user获取删除表数据，根据user_id删除ela中的数据
 *              5. 将删除成功的id再回送，删除表删除记录
 *
 * @date 4/5/19
*/
@Slf4j
@Component
public class SynchDataScheduler {

    @Autowired
    UserDeleteFeignService  userDeleteFeignService;


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Scheduled(cron = "0/10 * * * * ? " )
    public void  synchData(){

        log.info("synchData ... ");
         List<UserDelete> userDeletes =  userDeleteFeignService.query();

         log.debug("userDeletes = " + userDeletes);

         if((userDeletes == null) || (userDeletes.size() == 0)){

             return;
         }



         List<Long>  uids = new ArrayList<Long>();

        for(UserDelete  userDelete:userDeletes){
            String uid  = elasticsearchTemplate.delete("index-user","user",userDelete.getuId().toString());
            if(uid != null){
                uids.add(Long.valueOf(uid));
            }
        }
        userDeleteFeignService.delete(uids);

    }
}


