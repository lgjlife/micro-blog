package com.microblog.user.service.rocketmq;

import com.utils.serialization.AbstractSerialize;
import com.utils.serialization.HessianSerializeUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;


/**
 *功能描述
 * @author lgj
 * @Description  Rocketmq 生产者
 * @date 3/22/19
*/

@Slf4j
@Data
public class RocketmqProducer {

    private DefaultMQProducer producer;
    private String group = "default_group";

    private RocketProperties rocketProperties;

    private AbstractSerialize  serialize  = new HessianSerializeUtil();


    public RocketmqProducer(RocketProperties properties ,String group){
        this.rocketProperties = properties;
        this.group = group;
        create();
        
    }


    /**
     *功能描述 
     * @author lgj
     * @Description  创建连接
     * @date 4/2/19
     * @param: 
     * @return: 
     *
    */
    public   void  create(){

        producer = new DefaultMQProducer(group);
        producer.setNamesrvAddr(rocketProperties.getAddress());

        try{
            producer.start();
        }
        catch(Exception ex){
            log.error("create rocketmq error! group={},address ={}, message ={}",group,rocketProperties.getAddress(),ex.getMessage());
            ex.printStackTrace();
        }
    }


    /**
     *功能描述 
     * @author lgj
     * @Description  推送消息
     * @date 4/2/19
     * @param: 
     * @return: 
     *
    */
    public void  publish(Object msg,RocketmqPublishConfig config) {


        byte[] sendData = serialize.serialize(msg);
        Message message = new Message(config.getTopic(), config.getTags(), sendData);

        try {

            log.debug("异步方式发送数据....");
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.debug("异步方式发送数据结束！");
                    log.info("SyncProducer result = {}", sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("rocketmq send data fail! topic = {} ,tags = {}", config.getTopic(), config.getTags(), throwable.getMessage());

                }
            });


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
