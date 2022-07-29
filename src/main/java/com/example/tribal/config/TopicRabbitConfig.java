package com.example.tribal.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;


/**
 * @Author: ；lh
 * @Description: TODO
 * @DateTime: 2022/7/28$ 下午1:46$
 * @Params: 生产者队列$
 * @Return $
 */
@Configuration
public class TopicRabbitConfig {

    public final static String caseSign = "topic_case_sign";

    public final static String caseNo = "topic_case_no";


    @Bean
    public Queue firstQueue() {
        return new Queue(TopicRabbitConfig.caseSign);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(TopicRabbitConfig.caseNo);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }


    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(caseSign);
    }


    @Bean
    Binding bindingExchangeMessage3(){
        return BindingBuilder.bind(secondQueue()).to(exchange()).with(caseNo);

    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }
}
