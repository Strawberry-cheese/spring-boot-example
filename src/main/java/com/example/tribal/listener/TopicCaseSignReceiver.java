package com.example.tribal.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: ；lh
 * @Description: TODO
 * @DateTime: 2022/7/28$ 下午2:04$
 * @Params: $
 * @Return $
 */

@Component
@RabbitListener(queues = "topic_case_sign")
public class TopicCaseSignReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicManReceiver消费者收到消息  : " + testMessage.toString());
    }
}
