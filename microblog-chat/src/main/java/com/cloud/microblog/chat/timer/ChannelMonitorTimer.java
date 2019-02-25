package com.cloud.microblog.chat.timer;


import com.cloud.microblog.chat.utils.NettyClientConnectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ChannelMonitorTimer {

    @Scheduled(cron = "0/5 * * * * ?")  //每隔5秒执行一次定时任务
    public void monitor() {
        int connectCounter = NettyClientConnectUtil.getConnectCounter();
        log.info("当前连接的客户端个数为：" + connectCounter);

    }
}
