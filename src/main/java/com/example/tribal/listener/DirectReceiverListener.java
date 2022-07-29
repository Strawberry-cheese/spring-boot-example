package com.example.tribal.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: ；lh
 * @Description: TODO
 * @DateTime: 2022/7/28$ 下午1:41$
 * @Params: $
 * @Return $
 */

@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiverListener {

    @RabbitHandler
    public void process(Map testMessages) {
        System.out.println("DirectReceiver 接收到消费者消息------------->" + testMessages.toString());
    }
}
