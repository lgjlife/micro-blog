package com.microblog.msg.message.producer;

import com.utils.serialization.AbstractSerialize;
import com.utils.serialization.JdkSerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 *功能描述 
 * @author lgj
 * @Description  rocketmq 消息生产者
 * @date 5/12/19
*/
@Slf4j
public class MqProducer {

    private DefaultMQProducer producer;

    private String group;
    private String namesrvAddr;


    AbstractSerialize serialize = new JdkSerializeUtil();

    public MqProducer(String group, String namesrvAddr) {
        this.group = group;
        this.namesrvAddr = namesrvAddr;
    }


    public  void createProducer() throws Exception{

        producer = new DefaultMQProducer(group);
        producer.setNamesrvAddr(namesrvAddr);

        //设置自动创建topic的key值
        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");

        producer.start();
    }

    public void asyncSend(String topic,String subExpression,Object object) throws Exception{

        byte[] body = serialize.serialize(object);
        Message message = new Message(topic,subExpression,body);
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }

            @Override
            public void onException(Throwable throwable) {
                log.error("message[{}]send fail !{}",object.toString(),throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
