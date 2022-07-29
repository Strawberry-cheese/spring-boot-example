package com.example.tribal.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: ；lh
 * @Description: TODO
 * @DateTime: 2022/7/28$ 下午2:07$
 * @Params: $
 * @Return $
 */
@RabbitListener(queues = "topic_case_no")
@Component
public class TopicCaseNoReceiver {


    @RabbitHandler
    private void process(Map message) {
        System.out.println("获取到caseNO 消费者获取消息---------》" + message.toString());
    }
}
